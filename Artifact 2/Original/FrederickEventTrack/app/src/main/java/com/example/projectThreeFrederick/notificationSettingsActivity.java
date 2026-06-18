package com.example.projectThreeFrederick;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class notificationSettingsActivity extends AppCompatActivity {

    //int for SMS Permission
    private int SEND_SMS_PERMISSION_REQUEST_CODE = 1;
    SmsManager smsManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_notification_settings);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //initialize button for UI
        ImageButton buttonUser = findViewById(R.id.imageButtonUser); //User button
        Button buttonAllow = findViewById(R.id.buttonAllow); //Allow button
        Button buttonDeny = findViewById(R.id.buttonDeny);  //Dent button

        //Listener for user button
        buttonUser.setOnClickListener(v -> {
        });

        //Listener for allow button
        buttonAllow.setOnClickListener(v -> {
            //Check and request permission to send SMS
            if(!checkPermission(Manifest.permission.SEND_SMS)) {
                ActivityCompat.requestPermissions(notificationSettingsActivity.this,
                        new String[]{Manifest.permission.SEND_SMS},
                        SEND_SMS_PERMISSION_REQUEST_CODE);
            }
            else{
                //Send message when enabled
                enableSMS();
                finish();
            }
        });

        //Listener for Deny button
        buttonDeny.setOnClickListener(v -> finish());
    }
    //Boolean to check permissions
    public boolean checkPermission(String permission){
        int check = ContextCompat.checkSelfPermission(this, permission);
        return (check == PackageManager.PERMISSION_GRANTED);
    }

    //Method to send test SMS
    public void enableSMS(){
        //Create smsManager Message to test SMS
        Context context = this;
        String phoneNumber = "(650) 555-1212";
        String message = "You have enabled notifications!";
        SmsManager smsManager = context.getSystemService(SmsManager.class);
        smsManager.sendTextMessage(phoneNumber, null, message, null, null);
        finish();
    }

}
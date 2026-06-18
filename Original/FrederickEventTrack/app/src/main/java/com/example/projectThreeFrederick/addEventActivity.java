package com.example.projectThreeFrederick;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class addEventActivity extends AppCompatActivity {

    private UserDatabaseHelper helper; //database helper

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_event);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Edit text for event data
        EditText editTextName = findViewById(R.id.editTextEventName);
        EditText editTextDate = findViewById(R.id.editTextEventDate);
        EditText editTextNote = findViewById(R.id.editTextNotes);

        //image buttons for settings, user, notifications, and calendar
        ImageButton buttonSettings = findViewById(R.id.imageButtonSettings);
        ImageButton buttonUser = findViewById(R.id.imageButtonUser);
        ImageButton buttonNotifications = findViewById(R.id.imageButtonNotifications);
        ImageButton buttonCalendar = findViewById(R.id.imageButtonCalendar);

        //Save and cancel button
        Button buttonCancel = findViewById(R.id.buttonCancel);
        Button buttonSave = findViewById(R.id.buttonSave);

        //Boolean for edit event or add event
        boolean eventEdit;

        //long for evenId
        long eventId;

        //Edit event information if values were passed to activity
        if(getIntent().hasExtra("name")){
            eventId = getIntent().getLongExtra("id", -1);
            editTextName.setText(getIntent().getStringExtra("name"));
            editTextDate.setText(getIntent().getStringExtra("date"));
            editTextNote.setText(getIntent().getStringExtra("notes"));
            eventEdit = true;
        } else {
            //else new event is created
            eventEdit = false;
            eventId = -1;
        }

        //initialize database helper
        helper = new UserDatabaseHelper(this);

        //Listener for settings button
        buttonSettings.setOnClickListener(v -> {
            Intent intent = new Intent(addEventActivity.this, notificationSettingsActivity.class);
            startActivity(intent);
        });

        //Listener for user button
        buttonUser.setOnClickListener(v -> {

        });

        //Listener for notifications button
        buttonNotifications.setOnClickListener(v -> {

        });

        //Listener for cancel button
        buttonCancel.setOnClickListener(v -> finish());

        //Listener for save button
        buttonSave.setOnClickListener(v -> {
            String eventName = editTextName.getText().toString();
            String eventDate = editTextDate.getText().toString();
            String eventNote = editTextNote.getText().toString();
            //Save event to table
            if(!eventEdit) {
                helper.addEvent(eventName, eventDate, eventNote);
            }
            //else, update existing event
            else{
                helper.updateEvent(eventId, eventName, eventDate, eventNote);
            }
            finish();
        });

        //Listener for calendar
        buttonCalendar.setOnClickListener(v -> {

        });
    }


}
package com.example.projectThreeFrederick;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import android.view.View;
import android.content.Intent;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class homescreenActivity extends AppCompatActivity {

    private UserDatabaseHelper helper; //database helper
    private ArrayList<event> eventList; //event list
    private RecyclerView.Adapter adapter; //recycler adapter

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_homescreen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //initialize UI buttons
        //Floating action button to add events
        FloatingActionButton FAB = findViewById(R.id.floatingActionButton);
        //Settings button
        ImageButton buttonSettings = findViewById(R.id.imageButtonSettings);
        //User Button
        ImageButton buttonUser = findViewById(R.id.imageButtonUser);
        //Notification button
        ImageButton buttonNotifications = findViewById(R.id.imageButtonNotifications);

        //TextView for date
        TextView textDate = findViewById(R.id.textViewDate);

        //initialize database helper
        helper = new UserDatabaseHelper(this);

        //Display current date header
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatting = DateTimeFormatter.ofPattern("EEEE, MMM dd");
        textDate.setText("Today: " + date.format(formatting));

        //Spinner for log out functionality.
        String[] options = {"User", "Log out"};
        Spinner spinner = findViewById(R.id.spinnerUserLogout);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                options
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        //floating action button listener
        FAB.setOnClickListener(v -> {
            //Go to addEvent activity
            Intent intent = new Intent(homescreenActivity.this, addEventActivity.class);
            startActivity(intent);
        });

        buttonSettings.setOnClickListener(v -> {
            //Go to settings activity
            Intent intent = new Intent(homescreenActivity.this, notificationSettingsActivity.class);
            startActivity(intent);
        });

        //make spinner visible when user icon is clicked
        buttonUser.setOnClickListener(v ->
                spinner.setVisibility(View.VISIBLE));

        //Listener for notification button
        buttonNotifications.setOnClickListener(v -> {
            if(checkPermission(Manifest.permission.SEND_SMS)){
               sendSMS();
            }
        });

        //log out when log out option is selected.
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String selected = parent.getItemAtPosition(position).toString();

                if (selected.equals("Log out")) {
                    Intent intent = new Intent(homescreenActivity.this, mainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //Do nothing
            }
        });

        //Set up adapter to display event table data in recycler view
        RecyclerView recyclerView = findViewById(R.id.recyclerViewContainer);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        eventList = helper.getAllEvents(eventList);
        this.adapter = new eventAdapter(eventList, new eventAdapter.OnEventClickListener() {
            //Delete event when button is selected
            @Override
            public void onDeleteClick(int position) {
                if (position < 0 || position > eventList.size()) {
                    return;
                }

                event event = eventList.get(position);
                //Alert to confirm even deletion.
                new AlertDialog.Builder(homescreenActivity.this)
                        .setTitle("Delete event")
                        .setMessage("Are you sure you want to delete this event?")
                        .setPositiveButton("Delete", (dialog, which) -> {
                            helper.deleteEvent(event.getId());
                            eventList.remove(event);
                            homescreenActivity.this.adapter.notifyItemRemoved(position);
                            homescreenActivity.this.adapter.notifyItemRangeRemoved(position, eventList.size());
                        })
                        .setNegativeButton("Cancel", null)
                        .show();
            }

            //Pass event data to addEventActivity when edit button is selected
            @Override
            public void onEditClick(int position) {
                Intent intent = new Intent(homescreenActivity.this, addEventActivity.class);
                event event = eventList.get(position);
                intent.putExtra("id", event.getId());
                intent.putExtra("name", event.getName());
                intent.putExtra("date", event.getDate());
                intent.putExtra("notes", event.getNote());
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(this.adapter);
    }

    //update recycler view on resume
    @Override
    protected void onResume(){
        super.onResume();
        eventList.clear();
        eventList.addAll(helper.getAllEvents(eventList));
        adapter.notifyDataSetChanged();
    }

    //sendSMS method to send SMS notifications for events
    public void sendSMS(){
        //Get delay for notification.
        long currentTime = System.currentTimeMillis(); //Test time for notification
        long targetTime = System.currentTimeMillis(); //Current time

        long delay = targetTime - currentTime + 10;

        //Create workManager for notification
        OneTimeWorkRequest smsWork =
                new OneTimeWorkRequest.Builder(smsWorker.class)
                        .setInitialDelay(delay, TimeUnit.MILLISECONDS)
                        .build();

        WorkManager.getInstance(homescreenActivity.this).enqueue(smsWork);
    }

    //Check permission to send SMS message
    public boolean checkPermission(String permission){
        int check = ContextCompat.checkSelfPermission(this, permission);
        return (check == PackageManager.PERMISSION_GRANTED);
    }
}
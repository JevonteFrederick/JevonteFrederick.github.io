package com.example.projectThreeFrederick;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.ImageButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.concurrent.TimeUnit;
import java.util.PriorityQueue;

public class homescreenActivity extends AppCompatActivity {

    private UserDatabaseHelper databaseHelper; //database helper
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
        //User button
        ImageButton buttonUser = findViewById(R.id.imageButtonUser);
        //Notification button
        ImageButton buttonNotifications = findViewById(R.id.imageButtonNotifications);

        //TextView for date
        TextView textDate = findViewById(R.id.textViewDate);

        //initialize database helper
        databaseHelper = new UserDatabaseHelper(this);

        //Display current date header
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatting = DateTimeFormatter.ofPattern("EEEE, MMM dd");
        textDate.setText("Today: " + date.format(formatting));

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

        //Clicking user icon prompts to log out
        buttonUser.setOnClickListener(v -> {
            logout();
        });

        //Listener for notification button
        buttonNotifications.setOnClickListener(v -> {
            //Queue notifications if permission is enabled.
            if(checkPermission(Manifest.permission.SEND_SMS)) {
                scheduleSMS();
            }
            else{
                //Else, display message to enable SMS.
                Toast.makeText(homescreenActivity.this,
                        "Please enable SMS to set notifications",
                        Toast.LENGTH_SHORT).show();
            }
        });

        //Set up adapter to display event table data in recycler view
        RecyclerView recyclerView = findViewById(R.id.recyclerViewContainer);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        eventList = databaseHelper.getAllEvents(eventList);
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
                            databaseHelper.deleteEvent(event.getId());
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
        eventList.addAll(databaseHelper.getAllEvents(eventList));

        //Sort events by date in recyclerview.
        eventList.sort(Comparator.comparing(event::getTimeMillis));
        adapter.notifyDataSetChanged();
    }

    //method to schedule SMS notifications
    public void scheduleSMS(){
        //PriorityQueue for event notifications.
        PriorityQueue<event> eventQueue = new PriorityQueue<>(
                Comparator.comparing(event::getTimeMillis));

        //add eventList to queue and schedule notifications.
        if(!eventList.isEmpty()) {
            eventQueue.addAll(eventList);
        }
        sendSMS(eventQueue);
    }

    //Method to queue SMS notifications for events
    public void sendSMS(PriorityQueue<event> queue){
        //Get delay for notification.
        long currentTime = System.currentTimeMillis(); //Current time for notification
        long delay = 0;

        //While queue is not empty, smsWork requests are enqueued for notifications
        while (!queue.isEmpty()) {
            //Calculate delay from event time in queue - the current system time
            try {
                delay = queue.peek().getTimeMillis() - currentTime;
            }
            catch (Exception e){
                Toast.makeText(homescreenActivity.this,
                        "Error has occurred with sending SMS",
                        Toast.LENGTH_SHORT).show();
            }

            //Create SMS message with queue event name
            String message = "Event Alert: " + queue.poll().getName();

            //Build data for notification message
            Data input = new Data.Builder()
                    .putString("SMS_MESSAGE", message)
                    .build();

            //Create workManager for notification
            OneTimeWorkRequest smsWork =
                    new OneTimeWorkRequest.Builder(smsWorker.class)
                            .setInputData(input)
                            .setInitialDelay(delay, TimeUnit.MILLISECONDS)
                            .build();

            WorkManager.getInstance(homescreenActivity.this).enqueue(smsWork);
        }
    }

    //Check permission to send SMS message
    public boolean checkPermission(String permission){
        int check = ContextCompat.checkSelfPermission(this, permission);
        return (check == PackageManager.PERMISSION_GRANTED);
    }

    public void logout(){
        //Alert to confirm logout
        new AlertDialog.Builder(homescreenActivity.this)
                .setTitle("Logout")
                .setMessage("Would you like to log out??")
                .setPositiveButton("Yes", (dialog, which) -> {
                    Intent intent = new Intent(homescreenActivity.this, mainActivity.class);
                    startActivity(intent);
                    finishAffinity();
                })
                .setNegativeButton("No", null)
                .show();
    }
}
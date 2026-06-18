package com.example.projectThreeFrederick;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class addEventActivity extends AppCompatActivity {

    private UserDatabaseHelper databaseHelper; //database helper

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

        //image buttons for settings, user, and calendar
        ImageButton buttonSettings = findViewById(R.id.imageButtonSettings);
        ImageButton buttonUser = findViewById(R.id.imageButtonUser);
        ImageButton buttonCalendar = findViewById(R.id.imageButtonCalendar);

        //Save and cancel button
        Button buttonCancel = findViewById(R.id.buttonCancel);
        Button buttonSave = findViewById(R.id.buttonSave);

        //Boolean for edit event or add event
        boolean eventEditState;

        //long for evenId
        long eventId;

        //Edit event information if values were passed to activity
        if (getIntent().hasExtra("name")) {
            eventId = getIntent().getLongExtra("id", -1);
            editTextName.setText(getIntent().getStringExtra("name"));
            editTextDate.setText(getIntent().getStringExtra("date"));
            editTextNote.setText(getIntent().getStringExtra("notes"));
            eventEditState = true;
        } else {
            //else new event is created
            eventEditState = false;
            eventId = -1;
        }

        //initialize database helper
        databaseHelper = new UserDatabaseHelper(this);

        //Listener for settings button
        buttonSettings.setOnClickListener(v -> {
            Intent intent = new Intent(addEventActivity.this, notificationSettingsActivity.class);
            startActivity(intent);
        });

        //Listener for user button
        buttonUser.setOnClickListener(v -> {
            logout();
        });

        //Listener for cancel button
        buttonCancel.setOnClickListener(v -> {
            cancelAddEvent(editTextName, editTextDate, editTextNote);
        });


        //Listener for save button
        buttonSave.setOnClickListener(v -> {
            SaveEvent(editTextName, editTextDate, editTextNote, eventEditState, eventId);
        });

        //Listener for calendar
        buttonCalendar.setOnClickListener(v -> {
            //set current date to eventDate edit text
            LocalDate date = LocalDate.now();
            DateTimeFormatter formatting = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            editTextDate.setText(date.format(formatting));
        });
    }

    public void SaveEvent(EditText editTextName, EditText editTextDate, EditText editTextNote,
                          boolean eventEditState, long eventId){
        String eventName;
        //Set default event name if name text is empty.
        if(editTextName.getText().toString().isEmpty()) {
            eventName = "Unnamed Event";
        }
        else{
            eventName = editTextName.getText().toString();
        }
        String eventNote = editTextNote.getText().toString();
        String eventDate = editTextDate.getText().toString();

        long timeMillisLong = 0;
        boolean validDate = false;

        //Date format to parse date
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);

        try {
            //Parse date from editTextDate to calculate timeMillis
            Date date = dateFormat.parse(eventDate);
            if (date != null) {
                timeMillisLong = date.getTime();
            }
            validDate = true;
        } catch (ParseException e) {
            //Catch date parse error and prompt date format
            Toast.makeText(addEventActivity.this,
                    "Please enter a valid date: MM/DD/YYYY",
                    Toast.LENGTH_SHORT).show();
        }

        //Save event if date is valid
        if (validDate) {
            //Save event to table
            if (!eventEditState) {
                databaseHelper.addEvent(eventName, eventDate, eventNote, timeMillisLong);
            }
            //else, update existing event
            else {
                databaseHelper.updateEvent(eventId, eventName, eventDate, eventNote, timeMillisLong);
            }
            finish();
        }
    }

    public void cancelAddEvent(EditText editTextName, EditText editTextDate, EditText editTextNote){
        if (!editTextName.getText().toString().isEmpty() ||
                !editTextDate.getText().toString().isEmpty() ||
                !editTextNote.getText().toString().isEmpty()) {
            //Alert to cancel event
            new AlertDialog.Builder(addEventActivity.this)
                    .setTitle("Cancel Event")
                    .setMessage("Are you sure you want to cancel adding an event?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        finish();
                    })
                    .setNegativeButton("No", null)
                    .show();
        }
        else{
            finish();
        }
    }

    public void logout(){
        //Alert to confirm logout
        new AlertDialog.Builder(addEventActivity.this)
                .setTitle("Logout")
                .setMessage("Would you like to log out?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    Intent intent = new Intent(addEventActivity.this, mainActivity.class);
                    startActivity(intent);
                    finishAffinity();
                })
                .setNegativeButton("No", null)
                .show();
    }
}
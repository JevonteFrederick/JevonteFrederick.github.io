package com.example.projectThreeFrederick;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class mainActivity extends AppCompatActivity {
    //EditText for usernameText and passwordText
    private EditText usernameText;
    private EditText passwordText;

    //Database helper
    UserDatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Retrieve login and register button
        Button buttonLogin = findViewById(R.id.buttonLogin);
        Button buttonRegister = findViewById(R.id.buttonRegister);

        //Retrieve usernameText and passwordText
        usernameText = findViewById(R.id.editTextUsername);
        passwordText = findViewById(R.id.editTextPassword);

        //Database helper
        helper = new UserDatabaseHelper(this);

        //Listener for login button
        buttonLogin.setOnClickListener(v -> {
            //call login method when clicked.
            login();
        });

        //Register button listener
        buttonRegister.setOnClickListener(v -> {
            //call register method when clicked.
            register();
        });
    }

    //Login method
    private void login(){
        //String for username and password
        String username = usernameText.getText().toString();
        String password = passwordText.getText().toString();

        //Check user credentials
        if (!helper.checkUserLogin(username, password)) {
            //display login error message for login unverified.
            Toast.makeText(mainActivity.this, "Incorrect login. Try again.",
                    Toast.LENGTH_SHORT).show();
        } else {
            //Else, log in to app.
            Intent intent = new Intent(mainActivity.this, homescreenActivity.class);
            startActivity(intent);
            finish();
        }
    }

    //register method
    private void register(){
        String username = usernameText.getText().toString();
        String password = passwordText.getText().toString();

        //Check if username doesn't already exist in user table
        if (!helper.userExists(username)) {
            //Add new login and continue to app
            helper.addUser(username, password);
            Intent intent = new Intent(mainActivity.this, homescreenActivity.class);
            startActivity(intent);
            finish();
        } else {
            //Else, display error message.
            Toast.makeText(mainActivity.this, "Username already exists",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
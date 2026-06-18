package com.example.projectThreeFrederick;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;

import androidx.annotation.NonNull;
import java.util.ArrayList;

public class UserDatabaseHelper extends SQLiteOpenHelper {
    private static UserDatabaseHelper helper; //database helper
    public static UserDatabaseHelper getInstance(Context context) {
        //initial helper
        if (helper == null)
            helper = new UserDatabaseHelper(context);

        return helper;
    }
    private static final String DATABASE_NAME = "events.db"; //create database
    private static final int DATABASE_VERSION = 2;

    public UserDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //Table for user credentials
    private static final class UserTable {
        private static final String TABLE = "users";
        private static final String COL_ID = "_id";
        private static final String COL_NAME = "name";
        private static final String COL_PASSWORD = "password";
    }

    //Table for events
    private static final class EventTable {
        private static final String TABLE = "events";
        private static final String COL_ID = "_id";
        private static final String COL_NAME = "name";
        private static final String COL_DATE = "date";
        private static final String COL_NOTES = "notes";
        private static final String COL_TIME = "time";
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create user table
        db.execSQL("create table " + UserTable.TABLE + " (" +
                UserTable.COL_ID + " integer primary key autoincrement, " +
                UserTable.COL_NAME + " text, " +
                UserTable.COL_PASSWORD + " text) ");

        //create event table
        db.execSQL("create table " + EventTable.TABLE + " (" +
                EventTable.COL_ID + " integer primary key autoincrement, " +
                EventTable.COL_NAME + " text, " +
                EventTable.COL_DATE + " text, " +
                EventTable.COL_NOTES + " text, " +
                EventTable.COL_TIME + " integer) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + UserTable.TABLE);
        onCreate(db);
    }

    //Add new user to user table
    public long addUser(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(UserTable.COL_NAME, username);
        values.put(UserTable.COL_PASSWORD, password);

        long id = db.insert(UserTable.TABLE, null, values);
        db.close();

        return id;
    }

    //Check if user credentials are in table
    public boolean checkUserLogin(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();


        String query = "SELECT * FROM " + UserTable.TABLE +
                " WHERE name =? AND password=?";

        Cursor cursor = db.rawQuery(query, new String[]{username, password});

        boolean exists = (cursor.getCount() > 0);
        cursor.close();

        return exists;
    }

    //check if username exists before adding to table
    public boolean userExists(String username) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + UserTable.TABLE + " WHERE name=?",
                new String[]{username}
        );

        boolean exists = (cursor.getCount() > 0);
        cursor.close();

        return exists;
    }

    //Get all events from event table
    public ArrayList<event> getAllEvents(@NonNull ArrayList<event> events){
        events = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + EventTable.TABLE;
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                String id = cursor.getString(0);
                String name = cursor.getString(1);
                String date = cursor.getString(2);
                String notes = cursor.getString(3);
                String timeMillis = cursor.getString(4);
                long ide = Long.parseLong(id);
                long timeMillisLong = Long.parseLong(timeMillis);
                events.add(new event(ide, name, date, notes, timeMillisLong));

            } while (cursor.moveToNext());
        }
        cursor.close();

        return events;
    }

    //Add event to event table
    public long addEvent(String name, String date, String notes, long timeMillis){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(EventTable.COL_NAME, name);
        values.put(EventTable.COL_DATE, date);
        values.put(EventTable.COL_NOTES, notes);
        values.put(EventTable.COL_TIME, timeMillis);

        long id = db.insert(EventTable.TABLE, null, values);
        db.close();

        return id;
    }

    //delete event from event table
    public int deleteEvent(long id){
        int num;
        SQLiteDatabase db = this.getReadableDatabase();
        num = db.delete(EventTable.TABLE, EventTable.COL_ID + " = ?",
                new String[] {Long.toString(id)}
        );
        db.close();

        return num;
    }

    //Update event in event table
    public long updateEvent(Long eventID, String name, String date, String notes, Long time){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();

        values.put(EventTable.COL_NAME, name);
        values.put(EventTable.COL_DATE, date);
        values.put(EventTable.COL_NOTES, notes);
        values.put(EventTable.COL_TIME, time);

        long id = db.update(EventTable.TABLE, values, EventTable.COL_ID + " = ?",
                new String[] {Long.toString(eventID)}
        );
        db.close();

        return id;
    }
}

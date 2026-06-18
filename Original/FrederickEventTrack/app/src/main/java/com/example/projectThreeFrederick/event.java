package com.example.projectThreeFrederick;

//Class for event objects
public class event {
    //declare variables for event data
    private final long id;
    private final String name;
    private final String date;
    private final String note;

    public event(long id, String name, String date, String note){
        this.id = id;
        this.name = name;
        this.date = date;
        this.note = note;
    }

    public long getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getDate(){
        return date;
    }

    public String getNote(){
        return note;
    }
}

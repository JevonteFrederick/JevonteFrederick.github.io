package com.example.projectThreeFrederick;

//Class for event objects
public class event{
    //declare variables for event data
    private final long id;
    private final String name;
    private final String date;
    private final String note;
    private final long timeMillis; //Time in milliseconds of event date

    //Constructor
    public event(long id, String name, String date, String note, long time){
        this.id = id;
        this.name = name;
        this.date = date;
        this.note = note;
        this.timeMillis = time;
    }


    //Getter methods
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

    public long getTimeMillis(){ return timeMillis; }
}

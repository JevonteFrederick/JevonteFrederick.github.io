package com.example.projectThreeFrederick;

import android.content.Context;
import android.telephony.SmsManager;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

//Custom smsWorker class
public class smsWorker extends Worker {
    public smsWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        //notification data
        String phoneNumber = "(650) 555-1212";
        String message = getInputData().getString("SMS_MESSAGE"); //Retrieve event name for message

        //Send SMS Message
        Context context = getApplicationContext();
        SmsManager smsManager = context.getSystemService(SmsManager.class);
        smsManager.sendTextMessage(phoneNumber, null, message,null, null);

        //Log work
        Log.d("smsWorker", "Background task done...");
        return Result.success();
    }
}

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
        //Test notification
        String phoneNumber = "(650) 555-1212";
        String message = "Notification for Event!";
        Context context = getApplicationContext();
        SmsManager smsManager = context.getSystemService(SmsManager.class);
        smsManager.sendTextMessage(phoneNumber, null, message,null, null);

        Log.d("smsWorker", "Background task done...");
        return Result.success();
    }
}

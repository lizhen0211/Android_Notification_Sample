package com.lz.example.android_notification_sample;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class ProcessService extends Service {

    private ProcessBinder binder = new ProcessBinder();

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    public ProcessService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    class ProcessBinder extends Binder {

        int i = 0;

        public int getProcess() {
            if (i < 100) {
                i += 10;
            } else {
                i = 0;
            }
            return i;
        }
    }
}

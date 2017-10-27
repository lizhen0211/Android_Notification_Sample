package com.lz.example.android_notification_sample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by lz on 2017/10/27.
 */

public class PendingBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "接收到广播", Toast.LENGTH_SHORT).show();
    }
}

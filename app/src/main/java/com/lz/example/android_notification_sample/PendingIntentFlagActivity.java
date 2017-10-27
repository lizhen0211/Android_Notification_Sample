package com.lz.example.android_notification_sample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import static com.lz.example.android_notification_sample.NotificationWithPendingIntent.FLAG_CANCEL_CURRENT_ACTION;
import static com.lz.example.android_notification_sample.NotificationWithPendingIntent.FLAG_NO_CREATE_ACTION;
import static com.lz.example.android_notification_sample.NotificationWithPendingIntent.FLAG_ONE_SHOT_ACTION;
import static com.lz.example.android_notification_sample.NotificationWithPendingIntent.FLAG_UPDATE_CURRENT_ACTION;

public class PendingIntentFlagActivity extends Activity {

    public static final String Tag = PendingIntentFlagActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_intent_flag);
        Intent intent = getIntent();
        String action = intent.getAction();
        if (FLAG_CANCEL_CURRENT_ACTION.equals(action)) {
            String extra = intent.getStringExtra("extra");
            Log.e(Tag, extra);
        } else if (FLAG_UPDATE_CURRENT_ACTION.equals(action)) {
            String extra = intent.getStringExtra("extra");
            Log.e(Tag, extra);
        } else if (FLAG_NO_CREATE_ACTION.equals(action)) {
            String extra = intent.getStringExtra("extra");
            Log.e(Tag, extra);
        } else if (FLAG_ONE_SHOT_ACTION.equals(action)) {
            String extra = intent.getStringExtra("extra");
            Log.e(Tag, extra);
        }
    }
}

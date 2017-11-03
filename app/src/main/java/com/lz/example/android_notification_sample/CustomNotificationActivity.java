package com.lz.example.android_notification_sample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class CustomNotificationActivity extends Activity {

    private String Tag = CustomNotificationActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_notification);
        Intent intent = getIntent();
        String action = intent.getAction();
        Log.e(Tag, action);
    }
}

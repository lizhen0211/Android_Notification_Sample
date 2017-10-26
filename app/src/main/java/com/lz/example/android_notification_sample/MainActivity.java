package com.lz.example.android_notification_sample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void simpleNotificationClick(View view) {
        Intent intent = new Intent(this, BasicNotificationActivity.class);
        startActivity(intent);
    }
}

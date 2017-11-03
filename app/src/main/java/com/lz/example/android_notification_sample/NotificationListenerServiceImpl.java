package com.lz.example.android_notification_sample;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;

/**
 * Created by lz on 2017/11/3.
 * 如果使用NotificationListenerService,则最低版本为18
 * 调用getActiveNotifications() 也一样
 */

@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
public class NotificationListenerServiceImpl extends NotificationListenerService {

    private static final String TAG = NotificationListenerServiceImpl.class.getSimpleName();

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        super.onNotificationPosted(sbn);
        Log.e(TAG, "onNotificationPosted=" + sbn.toString());
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        super.onNotificationRemoved(sbn);
        Log.e(TAG, "onNotificationPosted=" + sbn.toString());
    }
}

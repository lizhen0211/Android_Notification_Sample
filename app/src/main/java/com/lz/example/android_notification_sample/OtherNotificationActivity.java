package com.lz.example.android_notification_sample;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.View;

public class OtherNotificationActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_notification);
    }

    /**
     * 此种效果只在5.0以上系统中有效
     * mainfest中需要添加<uses-permission android:name="android.permission.SYSTEM_ALERT_WINDOW" />
     * 需要在设置开启横幅通知权限（在设置通知管理中）
     * 在部分改版rom上可能会直接弹出应用而不是显示横幅
     *
     * @param view
     */
    public void onHangupStyleNotification(View view) {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setContentTitle("横幅通知");
        builder.setContentText("请在设置通知管理中开启消息横幅提醒权限");
        builder.setDefaults(NotificationCompat.DEFAULT_ALL);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
        Intent intent = new Intent(this, NotificationActionActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pIntent);
        //设置横幅通知模式
        builder.setFullScreenIntent(pIntent, true);
        builder.setAutoCancel(true);
        Notification notification = builder.build();
        notificationManager.notify(4, notification);
    }

    /**
     * Android 5.0(API level 21)开始，通知可以显示在锁屏上。用户可以通过设置选择是否允许敏感的通知内容显示在安全的锁屏上。
     * 你的应用可以通过setVisibility()控制通知的显示等级:
     * VISIBILITY_PRIVATE : 显示基本信息，如通知的图标，但隐藏通知的全部内容
     * VISIBILITY_PUBLIC : 显示通知的全部内容
     * VISIBILITY_SECRET : 不显示任何内容，包括图标
     */

    public void onScreenLockedNotification(View view) {
        NotificationManager notifyManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("screenLockNotification")
                .setContentText("我是一个锁屏通知")
                .setVisibility(Notification.VISIBILITY_PUBLIC);
        notifyManager.notify(1, builder.build());
    }
}

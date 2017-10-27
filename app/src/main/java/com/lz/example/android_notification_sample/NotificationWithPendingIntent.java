package com.lz.example.android_notification_sample;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.View;

/**
 * PendingIntent 主要可以通过以下三种方式获取：
 * <p>
 * 获取一个用于启动 Activity 的 PendingIntent 对象
 * public static PendingIntent getActivity(Context context, int requestCode, Intent intent, int flags);
 * <p>
 * 获取一个用于启动 Service 的 PendingIntent 对象
 * public static PendingIntent getService(Context context, int requestCode, Intent intent, int flags);
 * <p>
 * 获取一个用于向 BroadcastReceiver 广播的 PendingIntent 对象
 * public static PendingIntent getBroadcast(Context context, int requestCode, Intent intent, int flags)
 */
public class NotificationWithPendingIntent extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_with_pending_intent);
    }


    public static final String SEND_BROADCAST_ACTION = "com.lz.example.android_notification_sample.send_broadcast_action";

    /**
     * 使用PendingIntent 启动一个服务
     */
    public void onStartService(View view) {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Intent intent = new Intent(this, PendingService.class);
        PendingIntent pendingIntent = PendingIntent.getService(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        //创建 Notification.Builder 对象
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(true)
                .setContentTitle("我是启动服务的Notification")
                .setContentText("点我会启动一个服务")
                .setContentIntent(pendingIntent);
        //发送通知
        notificationManager.notify(1, builder.build());
    }

    /**
     * 使用PendingIntent 发送一个广播
     */
    public void onSendBroadcast(View view) {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Intent intent = new Intent(this, PendingBroadcastReceiver.class);
        intent.setAction(SEND_BROADCAST_ACTION);
        //获取PendingIntent
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        //创建 Notification.Builder 对象
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(true)
                .setContentTitle("我是发送广播的Notification")
                .setContentText("点我会发送一个广播")
                .setContentIntent(pendingIntent);
        //发送通知
        notificationManager.notify(0, builder.build());
    }
}

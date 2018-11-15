package com.lz.example.android_notification_sample;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.View;

public class BasicNotificationActivity extends Activity {

    private Bitmap largeIcon;

    private static final String CHANNEL_ID = "channel_id";

    public static final String CHANNEL_NAME = "channel_name";

    public static final String CHANNEL_DESCRIPTION = "channel_description";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_notification);
        largeIcon = BitmapFactory.decodeResource(getResources(), R.mipmap.large_icon);
    }


    /**
     * 给通知设置Action
     */
    public void onNotificationWithActionClick(View view) {
        NotificationManager notifyManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        createNotificationChannel(notifyManager);
        //获取PendingIntent
        Intent intent = new Intent(this, NotificationActionActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        //创建 Notification.Builder 对象
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                //点击通知后自动清除
                .setAutoCancel(true)
                .setContentTitle("我是带Action的Notification")
                .setContentText("点我会打开NotificationActionActivity")
                //设置通知的action
                .setContentIntent(pendingIntent);
        //发送通知
        notifyManager.notify(3, builder.build());
    }

    /**
     * 发送一个具有大图标的简单通知
     * 当setSmallIcon与setLargeIcon同时存在时,smallIcon显示在通知的右下角,largeIcon显示在左侧
     * 当只设置setSmallIcon时,smallIcon显示在左侧
     * 对于部分 ROM ，可能修改过源码，如 MIUI 上通知的大图标和小图标是没有区别的。
     */
    public void onNotificationWithLargeIcon(View view) {
        NotificationManager notifyManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        createNotificationChannel(notifyManager);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("带大图标的Notification")
                .setContentText("有小图标、大图标、标题、内容")
                .setLargeIcon(largeIcon);
        notifyManager.notify(2, builder.build());
    }

    /**
     * 发送一个最简单的通知
     *
     * @param view
     */
    public void onSimpleNotificationClick(View view) {
        //获取NotificationManager实例
        NotificationManager notifyManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        //创建通道
        createNotificationChannel(notifyManager);
        //Notification 的必要属性有三项，如果不设置则在运行时会抛出异常：
        //实例化NotificationCompat.Builde并设置相关属性
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                //设置小图标
                .setSmallIcon(R.mipmap.ic_launcher)
                //设置通知标题
                .setContentTitle("最简单的Notification")
                //设置通知内容
                .setContentText("只有小图标、标题、内容");
        //设置通知时间，默认为系统发出通知的时间，通常不用设置
        //.setWhen(System.currentTimeMillis());

        //通过builder.build()方法生成Notification对象,并发送通知,id=1
        notifyManager.notify(1, builder.build());
    }

    private void createNotificationChannel(NotificationManager notificationManager) {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, importance);
            channel.setDescription(CHANNEL_DESCRIPTION);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            notificationManager.createNotificationChannel(channel);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (largeIcon != null) {
            if (!largeIcon.isRecycled()) {
                largeIcon.recycle();
            }
            largeIcon = null;
        }
    }
}

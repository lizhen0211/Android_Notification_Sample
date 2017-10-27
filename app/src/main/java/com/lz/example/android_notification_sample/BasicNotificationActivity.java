package com.lz.example.android_notification_sample;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.View;

public class BasicNotificationActivity extends Activity {

    private NotificationManager mNotifyManager;

    private Bitmap largeIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_notification);
        mNotifyManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        largeIcon = BitmapFactory.decodeResource(getResources(), R.mipmap.large_icon);
    }


    /**
     * 给通知设置Action
     */
    public void onNotificationWithActionClick(View view) {
        //获取PendingIntent
        Intent intent = new Intent(this, NotificationActionActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        //创建 Notification.Builder 对象
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                //点击通知后自动清除
                .setAutoCancel(true)
                .setContentTitle("我是带Action的Notification")
                .setContentText("点我会打开NotificationActionActivity")
                //设置通知的action
                .setContentIntent(pendingIntent);
        //发送通知
        mNotifyManager.notify(3, builder.build());
    }

    /**
     * 发送一个具有大图标的简单通知
     * 当setSmallIcon与setLargeIcon同时存在时,smallIcon显示在通知的右下角,largeIcon显示在左侧
     * 当只设置setSmallIcon时,smallIcon显示在左侧
     * 对于部分 ROM ，可能修改过源码，如 MIUI 上通知的大图标和小图标是没有区别的。
     */
    public void onNotificationWithLargeIcon(View view) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("带大图标的Notification")
                .setContentText("有小图标、大图标、标题、内容")
                .setLargeIcon(largeIcon);
        mNotifyManager.notify(2, builder.build());
    }

    /**
     * 发送一个最简单的通知
     *
     * @param view
     */
    public void onSimpleNotificationClick(View view) {
        //Notification 的必要属性有三项，如果不设置则在运行时会抛出异常：
        //获取NotificationManager实例
        NotificationManager notifyManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        //实例化NotificationCompat.Builde并设置相关属性
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
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

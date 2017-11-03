package com.lz.example.android_notification_sample;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

public class NotificationStyleActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_style);
    }

    public void onBigTextStyleNotification(View view) {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle();
        //相当于 setContentTitle()
        bigTextStyle.setBigContentTitle("系统支持BigTextStyle时显示的标题");
        //bigText() 方法相当于 setContentText()
        bigTextStyle.bigText("系统支持BigTextStyle\n" +
                "系统支持BigTextStyle1\n" +
                "系统支持BigTextStyle2\n" +
                "系统支持BigTextStyle3\n" +
                "系统支持BigTextStyle4\n" +
                "系统支持BigTextStyle5\n" +
                "系统支持BigTextStyle6\n" +
                "系统支持BigTextStyle7\n" +
                "系统支持BigTextStyle8\n");
        bigTextStyle.setSummaryText("BigTextStyle SummaryText");
        //当系统不支持BitTextStyle时，显示正常内容
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setContentTitle("BigTextStyle示例")
                .setContentText("BigTextStyle示例演示")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setDefaults(Notification.DEFAULT_SOUND)
                .setStyle(bigTextStyle);
        notificationManager.notify(1, builder.build());
    }

    public void onInboxStyleNotification(View view) {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
        inboxStyle.setBigContentTitle("系统支持InboxStyle时显示的标题")
                .addLine("Line 1")
                .addLine("Line 2")
                .addLine("Line 3")
                .addLine("Line 4")
                .addLine("Line 5")
                .addLine("Line 6")
                .addLine("Line 7")
                .addLine("Line 8")
                .addLine("Line 9")
                .setSummaryText("+3 more");
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setContentTitle("InboxStyle示例")
                .setContentText("InboxStyle演示示例")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setStyle(inboxStyle);
        notificationManager.notify(2, builder.build());
    }

    public void onBigPictureStyleNotification(View view) {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.BigPictureStyle bigPictureStyle = new NotificationCompat.BigPictureStyle();
        bigPictureStyle.setBigContentTitle("BigPictureTitle")
                .bigPicture(BitmapFactory.decodeResource(getResources(), R.mipmap.timg))
                .bigLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.large_icon))
                .setSummaryText("BigPicture SummaryText");
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setContentTitle("BigPicture 示例")
                .setContentText("BigPicture 演示示例")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setStyle(bigPictureStyle);
        notificationManager.notify(3, builder.build());
    }

    public void onProcessStyleNotification(View view) {
        final NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Intent serviceIntent = new Intent(NotificationStyleActivity.this, ProcessService.class);
        bindService(serviceIntent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                final ProcessService.ProcessBinder binder = (ProcessService.ProcessBinder) service;
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        int process = binder.getProcess();
                        if (process != 0) {
                            NotificationCompat.Builder builder = new NotificationCompat.Builder(NotificationStyleActivity.this);
                            builder.setSmallIcon(R.mipmap.ic_launcher);
                            builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
                            //禁止用户点击删除按钮删除
                            builder.setAutoCancel(false);
                            //禁止滑动删除
                            builder.setOngoing(true);
                            //取消右上角的时间显示
                            builder.setShowWhen(false);
                            builder.setContentTitle("下载中..." + process + "%");
                            int max = 100;
                            builder.setProgress(max, process, false);
                            //builder.setContentInfo(progress+"%");
                            builder.setOngoing(true);
                            builder.setShowWhen(false);
                            Notification notification = builder.build();
                            notificationManager.notify(5, notification);
                        } else {
                            this.cancel();
                            notificationManager.cancel(5);
                        }

                    }
                }, 0, 1000);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        }, BIND_AUTO_CREATE);
    }

    public void onMediaStyleNotification(View view) {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    }
}

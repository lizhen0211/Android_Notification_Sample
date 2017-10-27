package com.lz.example.android_notification_sample;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class NotificationWithPendingIntent extends Activity {

    public static final String SEND_BROADCAST_ACTION = "com.lz.example.android_notification_sample.send_broadcast_action";

    public static final String Tag = NotificationWithPendingIntent.class.getSimpleName();

    public static final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_with_pending_intent);
    }

    //--------------------PendingIntent Flag start--------------------
    public static final int FLAG_ID = 2;

    public static final String FLAG_CANCEL_CURRENT_ACTION = "FLAG_CANCEL_CURRENT";

    public static final String FLAG_NO_CREATE_ACTION = "FLAG_NO_CREATE";

    public static final String FLAG_ONE_SHOT_ACTION = "FLAG_ONE_SHOT";

    public static final String FLAG_UPDATE_CURRENT_ACTION = "FLAG_UPDATE_CURRENT";

    /**
     * PendingIntent Flag 只对相同的PendingIntent生效
     */

    /**
     * 判断两个PendingIntent 是否相等：
     * action, data, categories, components和flags都一样。
     * 但是它们的Intent的Extra 值可以不一样。
     */

    /**
     * FLAG_CANCEL_CURRENT、FLAG_UPDATE_CURRENT
     *
     * 区别在于CANCEL 取消之前的PendingIntent 重新生成一个新的PendingIntent对象
     * UPDATE 直接更新之前的PendingIntent对象，没有重新生成一个新的PendingIntent对象
     */

    /**
     * FLAG_CANCEL_CURRENT:如果当前系统中已经存在一个相同的 PendingIntent 对象，
     * 那么先将已有的 PendingIntent 取消，然后重新生成一个 PendingIntent 对象。
     */

    public void onFlagCanceCurrent(View view) {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Intent intent = new Intent(this, PendingIntentFlagActivity.class);
        intent.setAction(FLAG_CANCEL_CURRENT_ACTION);
        String date = sdf.format(Calendar.getInstance().getTime());
        intent.putExtra("extra", date);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        //创建 Notification.Builder 对象
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(true)
                .setContentTitle("我是FLAG_CANCEL_CURRENT")
                .setContentText(date + " " + String.valueOf(pendingIntent.hashCode()))
                .setContentIntent(pendingIntent);
        // 此处从hasCode可以看出 重新生成了一个PendingIntent 他们的地址不同
        Log.e(Tag, String.valueOf(pendingIntent.hashCode()));
        //发送通知
        notificationManager.notify(FLAG_ID, builder.build());
    }

    /**
     * FLAG_NO_CREATE:如果当前系统中不存在相同的 PendingIntent 对象，
     * 系统将不会创建该 PendingIntent 对象而是直接返回 null 。
     */
    public void onFlagNoCreate(View view) {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Intent intent = new Intent(this, NotificationActionActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        //创建 Notification.Builder 对象
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(true)
                .setContentTitle("我是FLAG_NO_CREATE")
                .setContentText("系统不存在相同的PendingIntent，将不会创建，直接返回null")
                .setContentIntent(pendingIntent);
        //发送通知
        notificationManager.notify(FLAG_ID, builder.build());
    }

    /**
     * FLAG_ONE_SHOT:该 PendingIntent 只作用一次。
     */
    public void onFlagOneShot(View view) {

    }

    /**
     * FLAG_UPDATE_CURRENT:如果系统中已存在该 PendingIntent 对象，那么系统将保留该 PendingIntent 对象，
     * 但是会使用新的 Intent 来更新之前 PendingIntent 中的 Intent 对象数据，例如更新 Intent 中的 Extras 。
     */
    public void onFlagUpdateCurrent(View view) {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Intent intent = new Intent(this, PendingIntentFlagActivity.class);
        intent.setAction(FLAG_UPDATE_CURRENT_ACTION);
        String date = sdf.format(Calendar.getInstance().getTime());
        intent.putExtra("extra", date);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        //创建 Notification.Builder 对象
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(true)
                .setContentTitle("我是FLAG_UPDATE_CURRENT")
                .setContentText(date + " " + String.valueOf(pendingIntent.hashCode()))
                .setContentIntent(pendingIntent);
        // 此处从hasCode可以看出 没有重新生成了一个PendingIntent 他们的地址相同
        Log.e(Tag, String.valueOf(pendingIntent.hashCode()));
        //发送通知
        notificationManager.notify(FLAG_ID, builder.build());
    }

    //--------------------PendingIntent Flag end--------------------


    //--------------------获取PendingIntent start--------------------
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

    //--------------------获取PendingIntent end--------------------
}

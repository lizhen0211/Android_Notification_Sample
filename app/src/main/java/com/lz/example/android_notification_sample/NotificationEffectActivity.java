package com.lz.example.android_notification_sample;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v4.app.NotificationCompat;
import android.view.View;

/**
 * 设置系统默认提醒效果，一旦设置默认提醒效果，则自定义的提醒效果会全部失效。具体可看源码
 * 添加默认震动效果,需要申请震动权限
 * <uses-permission android:name="android.permission.VIBRATE" />
 * Notification.DEFAULT_VIBRATE
 * <p>
 * 添加系统默认声音效果，设置此值后，调用setSound()设置自定义声音无效
 * Notification.DEFAULT_SOUND
 * <p>
 * 添加默认呼吸灯效果，使用时须与 Notification.FLAG_SHOW_LIGHTS 结合使用，否则无效
 * Notification.DEFAULT_LIGHTS
 * <p>
 * 添加上述三种默认提醒效果
 * Notification.DEFAULT_ALL
 */
public class NotificationEffectActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_effect);
    }

    /**
     * 添加系统默认声音效果，设置此值后，调用setSound()设置自定义声音无效
     * <p>
     * Notification.DEFAULT_SOUND
     *
     * @param view
     */
    public void onNotifyWithRing(View view) {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("带有铃声的通知")
                .setContentText("我是伴有铃声效果的通知")//调用系统默认响铃,设置此属性后setSound()会无效
                .setDefaults(Notification.DEFAULT_SOUND);
        //调用系统多媒体库的铃声
        //.setSound(Uri.withAppendedPath(MediaStore.Audio.Media.INTERNAL_CONTENT_URI,"2"));
        //调用res下的铃声
        //.setSound(Uri.parse("android.resource://com.lz.example.android_notification_sample/" + R.raw.sound));
        notificationManager.notify(2, builder.build());

    }

    public void onNotifyWithVibrate(View view) {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        //延迟0ms，然后振动300ms，在延迟500ms，接着在振动500ms，延迟100ms，震动1500ms。
        long[] vibrate = new long[]{0, 300, 500, 500, 1000, 1500};
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("带有震动效果的通知")
                .setContentText("我是伴有震动效果的通知")
                //使用系统默认的震动参数,会与自定义的冲突
                //.setDefaults(Notification.DEFAULT_VIBRATE);
                //自定义震动效果
                .setVibrate(vibrate);
        //另一种设置震动的方法
        //Notification notify = builder.build();
        //调用系统默认震动
        //notify.defaults = Notification.DEFAULT_VIBRATE;
        //调用自己设置的震动
        //notify.vibrate = vibrate;
        //mManager.notify(3,notify);
        notificationManager.notify(3, builder.build());
    }

    public void onNotifyWithLights(View view) {
        final NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        final NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("带有呼吸灯效果的通知")
                .setContentText("我是伴呼吸灯动效果的通知~")
                //ledARGB 表示灯光颜色、 ledOnMS 亮持续时间、ledOffMS 暗的时间
                .setLights(0xFF0000, 3000, 3000);
        Notification notify = builder.build();
        //只有在设置了标志符Flags为Notification.FLAG_SHOW_LIGHTS的时候，才支持呼吸灯提醒。
        notify.flags = Notification.FLAG_SHOW_LIGHTS;
        //设置lights参数的另一种方式
        //notify.ledARGB = 0xFF0000;
        //notify.ledOnMS = 500;
        //notify.ledOffMS = 5000;
        //使用handler延迟发送通知,因为连接usb时,呼吸灯一直会亮着
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                notificationManager.notify(4, builder.build());
            }
        }, 10000);
    }

    /**
     * 默认铃声、震动、呼吸灯效果
     *
     * @param view
     */
    public void showNotifyWithDefault(View view) {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("带有默认铃声+震动+呼吸灯效果")
                .setContentText("我是有铃声+震动+呼吸灯效果的通知")
                //等价于setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE);
                .setDefaults(Notification.DEFAULT_ALL);
        notificationManager.notify(5, builder.build());
    }

    public void showInsistentNotify(View view) {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("重复通知")
                .setContentText("重复通知直到通知被取消或通知窗口被打开")
                .setDefaults(Notification.DEFAULT_ALL);
        Notification notify = builder.build();
        //参见源码　doc
        notify.flags |= Notification.FLAG_INSISTENT;
        notificationManager.notify(6, notify);
    }
}

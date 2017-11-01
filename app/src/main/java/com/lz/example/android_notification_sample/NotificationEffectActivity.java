package com.lz.example.android_notification_sample;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.NotificationCompat;
import android.view.View;

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

    }
}

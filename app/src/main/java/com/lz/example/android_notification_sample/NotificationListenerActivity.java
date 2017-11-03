package com.lz.example.android_notification_sample;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;

public class NotificationListenerActivity extends Activity {

    //此为 Settings 中的常量,不过是属于隐藏字段
    private static final String ENABLED_NOTIFICATION_LISTENERS = "enabled_notification_listeners";

    private static final String ACTION_NOTIFICATION_LISTENER_SETTINGS = "android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_listener);
    }

    public void onOpenSettingClick(View view) {
        Intent intent = new Intent(ACTION_NOTIFICATION_LISTENER_SETTINGS);
        startActivity(intent);
    }

    public void onStartNotificationListenerService(View view) {
        Intent intent = new Intent(NotificationListenerActivity.this, NotificationListenerServiceImpl.class);
        startService(intent);
    }

    /**
     * 判断 Notification access 是否开启
     *
     * @return
     */
    private boolean isEnabled() {
        String pkgName = getPackageName();
        final String flat = Settings.Secure.getString(getContentResolver(),
                ENABLED_NOTIFICATION_LISTENERS);
        if (!TextUtils.isEmpty(flat)) {
            final String[] names = flat.split(":");
            for (int i = 0; i < names.length; i++) {
                final ComponentName cn = ComponentName.unflattenFromString(names[i]);
                if (cn != null) {
                    if (TextUtils.equals(pkgName, cn.getPackageName())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}

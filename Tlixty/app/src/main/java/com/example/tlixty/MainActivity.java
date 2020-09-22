package com.example.tlixty;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements MyListener {

    private boolean checkNotificationListenerPermission() {
        ComponentName cn = new ComponentName(this, NotificationService.class);
        String flat = Settings.Secure.getString(this.getContentResolver(), "enabled_notification_listeners");
        final boolean enabled = flat != null && flat.contains(cn.flattenToString());
        return enabled;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if(!checkNotificationListenerPermission()) {
            Intent intent = new Intent();
            intent.setAction(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS);
            startActivity(intent);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new NotificationService().setListener(this);
    }

    @Override
    public void setValue(String packageName) {
    }
}
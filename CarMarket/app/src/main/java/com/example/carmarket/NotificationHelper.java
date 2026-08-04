package com.example.carmarket;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

public class NotificationHelper {
    private static final String TEST_CHANNEL_ID = "test";
    private static final String CHANNEL_NAME = "Test Info";

    private static NotificationHelper mNotificationHelper = null;

    private NotificationHelper() {
    }

    public static NotificationHelper getInstance() {
        if(mNotificationHelper == null) {
            mNotificationHelper = new NotificationHelper();
        }
        return mNotificationHelper;
    }
    public void createChannels(@NonNull Context context) {
        NotificationChannel channel = new NotificationChannel(TEST_CHANNEL_ID,
                CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);

        channel.setDescription("just use for test");

        NotificationManager manager = context.getSystemService(NotificationManager.class);
        if(manager != null) {
            manager.createNotificationChannel(channel);
        }
    }

    public void sendNotification(@NonNull Context context, String title, String content) {
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if(manager == null) return;

        Intent intent = new Intent(context, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

        int flags = PendingIntent.FLAG_UPDATE_CURRENT;
        flags = flags | PendingIntent.FLAG_IMMUTABLE;
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, flags);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, TEST_CHANNEL_ID)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle(title)
                .setContentText(content)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);
        int notificationId = (int)System.currentTimeMillis();
        manager.notify(notificationId,builder.build());
    }
}

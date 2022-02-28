package com.smartworks.smartwork.alarm;

import static com.smartworks.smartwork.base.SmartworkApp.CHANNEL_ID;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.os.Vibrator;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.smartworks.smartwork.MainActivity;
import com.smartworks.smartwork.R;

public class AlarmService extends Service {
    private MediaPlayer mediaPlayer;
    int count = 0;

    @Override
    public void onCreate() {
        super.onCreate();

        mediaPlayer = MediaPlayer.create(this, R.raw.alarm);
        mediaPlayer.setLooping(false);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Intent notificationIntent = new Intent(this, MainActivity.class);
        notificationIntent.putExtra("playNotifSound", true);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);

        String alarmTitle = intent.getStringExtra(AlarmBroadcastReceiver.TITLE);

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("SmartWork")
                .setContentText(alarmTitle)
                .setSmallIcon(R.drawable.ic_launcherfiks_foreground)
                .setContentIntent(pendingIntent)
                .setOngoing(false)
                .build();

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            int maxCount = 3;

            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                if(count < maxCount){
                    mediaPlayer.start();
                    count++;
                }

            }
        });

        mediaPlayer.start();

        startForeground(15, notification);

        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        mediaPlayer.stop();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}

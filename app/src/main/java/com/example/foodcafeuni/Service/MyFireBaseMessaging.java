package com.example.foodcafeuni.Service;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;

import androidx.core.app.NotificationCompat;

import com.example.foodcafeuni.MainActivity;
import com.example.foodcafeuni.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFireBaseMessaging extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        SendNotification(remoteMessage);
    }

    private void SendNotification(RemoteMessage remoteMessage) {
        RemoteMessage.Notification notification  = remoteMessage.getNotification();
        Intent obj = new Intent(this, MainActivity.class);
        obj.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,obj,PendingIntent.FLAG_ONE_SHOT);
        Uri DefaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle(notification.getTitle())
                .setContentText(notification.getBody())
                .setAutoCancel(true)
                .setSound(DefaultSoundUri)
                .setContentIntent(pendingIntent);
        NotificationManager Notification_Manager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        Notification_Manager.notify(0,builder.build());

    }
}

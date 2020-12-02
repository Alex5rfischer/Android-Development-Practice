package alex.la.n01313354;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class AlexBroadCast extends BroadcastReceiver {
//Author: Alex La
//Student Id: n01313354
//Section: RNA
    String detectedLifeform = "facehugger"; //"Alf";
    public static final String NEW_LIFEFORM_ACTION
            = "com.professionalandroid.alien.action.NEW_LIFEFORM_ACTION";
    public static final String EXTRA_LIFEFORM_NAME
            = "EXTRA_LIFEFORM_NAME";

    public static final String EXTRA_LATITUDE = "EXTRA_LATITUDE";
    public static final String EXTRA_LONGITUDE = "EXTRA_LONGITUDE";
    public static final String FACE_HUGGER = "facehugger";

    private static final int NOTIFICATION_ID = 0;
    private static final String MESSAGES_CHANNEL = "messages";

    double mLatitude = 43.7289;
    double mLongitude = 79.6074;


    @Override
    public void onReceive(Context context, Intent intent) {
        // Get the lifeform details from the intent.
        String type = intent.getStringExtra(EXTRA_LIFEFORM_NAME);
        double lat = intent.getDoubleExtra(EXTRA_LATITUDE, Double.NaN);
        double lng = intent.getDoubleExtra(EXTRA_LONGITUDE, Double.NaN);
        //create a notification channel
        createMessagesNotificationChannel(context);
        if (type.equals(FACE_HUGGER)) {
            //NotificationManager notificationManager = createNotificationChannel(context);
            NotificationManagerCompat notificationManagerCompat =
                    NotificationManagerCompat.from(context);
            //
            NotificationCompat.Builder builder = new NotificationCompat.Builder(
                    context, MESSAGES_CHANNEL);
            //
            CharSequence notificationTitle = context.getString(R.string.notification_title);
            //
            builder.setSmallIcon(R.drawable.ic_alien);
            builder.setContentTitle(notificationTitle);
            builder.setColor(Color.GREEN);
            builder.setContentText(Double.isNaN(lat) || Double.isNaN(lng) ?
                    "Location Unknown" :
                    "Located at " + lat + "," + lng);
            //
            Toast.makeText(context, "Located at " + lat + "," + lng, Toast.LENGTH_LONG).show();
            Notification notification = builder.build();
            notificationManagerCompat.notify(NOTIFICATION_ID, notification);
        }
    }

    //
    public void createMessagesNotificationChannel(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = context.getString(R.string.messages_channel_name);
            //Notification channel should only be created for devices running Android 26
            NotificationChannel channel = new NotificationChannel(
                    MESSAGES_CHANNEL,
                    name,
                    NotificationManager.IMPORTANCE_HIGH);
            //Sets the color of Notification Light
            channel.setLightColor(Color.GREEN);
            NotificationManager notificationManager =
                    context.getSystemService(NotificationManager.class);

            notificationManager.createNotificationChannel(channel);
        }
    }
    //

}
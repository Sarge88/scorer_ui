package gkalapis.scorerui.interactor.main;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import gkalapis.scorerui.R;
import gkalapis.scorerui.ui.bets.BetsActivity;
import gkalapis.scorerui.ui.livematches.LiveMatchesActivity;

public class NotificationReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        /*
        Intent service1 = new Intent(context, NotificationService.class);
        context.startService(service1);
        Log.i("App", "called receiver method");
        try{
            //Utils.generateNotification(context);
        }catch(Exception e){
            e.printStackTrace();
        }
        */

        createNotificationChannel(context);

        NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "channelID");
        builder.setContentTitle("Your match is starting soon");
        builder.setContentText("Open the application to make your bet!");
        builder.setSmallIcon(R.drawable.ic_launcher_background);
        Intent notifyIntent = new Intent(context, LiveMatchesActivity.class); //melyik nyíljon meg
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 2, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        //to be able to launch your activity from the notification
        builder.setContentIntent(pendingIntent);
        Notification notificationCompat = builder.build();
        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(context);
        managerCompat.notify(100, notificationCompat);

    }

    private void createNotificationChannel(Context context) {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Upcoming matches";
            String description = "";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("channelID", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}

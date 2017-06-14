package com.fenniao.a3kezhi.Model;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.NotificationCompat;

import com.fenniao.a3kezhi.R;
import com.fenniao.a3kezhi.View.Chart.ChartActivity;

/**
 * Created by Administrator on 2017/6/14 0014.
 */

public class NotificationModel {
    public static void notify(Context context,String title, String message) {
        //android 5.0以下4.1以上
        if (Build.VERSION.SDK_INT < 21) {
            Intent intent = new Intent(context, ChartActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
            builder.setSmallIcon(R.mipmap.ic_launcher);
            builder.setContentTitle(title);
            builder.setContentText(message);
            builder.setDefaults(Notification.DEFAULT_ALL);
            builder.setAutoCancel(true);
            builder.setContentIntent(pendingIntent);
//            builder.setDeleteIntent(pendingIntent);//超级贱的一个方法，用户移除通知后依然打开activity页面
            notificationManager.notify(0, builder.build());
        }
        //Android 5.0以上
        else {
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            Intent intent = new Intent(context, ChartActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
            Notification.Builder builder = new Notification.Builder(context);
            builder.setSmallIcon(R.mipmap.ic_launcher);
            builder.setContentTitle(title);
            builder.setContentText(message);
            builder.setContentIntent(pendingIntent);
            builder.setPriority(Notification.PRIORITY_MAX);
            builder.setVisibility(Notification.VISIBILITY_PUBLIC);
            Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            builder.setSound(alarmSound);
            Notification notification=builder.build();
            notificationManager.notify(0, builder.build());
        }
    }
}

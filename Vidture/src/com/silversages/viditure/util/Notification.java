package com.silversages.viditure.util;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

import com.silversages.viditure.R;
import com.silversages.viditure.ViditureApp;
import com.silversages.viditure.controller.Dashboard;

public class Notification {

	public  void NewMessageNotification(String message, String title) {
		// Creates an explicit intent for an Activity in your app
		Intent resultIntent = new Intent(ViditureApp.context, Dashboard.class);

		// The stack builder object will contain an artificial back
		// stack for the
		// started Activity.
		// This ensures that navigating backward from the Activity leads
		// out of
		// your application to the Home screen.
		TaskStackBuilder stackBuilder = TaskStackBuilder
				.create(ViditureApp.context);
		// Adds the back stack for the Intent (but not the Intent
		// itself)
		stackBuilder.addParentStack(Dashboard.class);
		// Adds the Intent that starts the Activity to the top of the
		// stack
		stackBuilder.addNextIntent(resultIntent);
		PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0,
				PendingIntent.FLAG_UPDATE_CURRENT);

		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
				ViditureApp.context).setSmallIcon(R.drawable.ic_launcher)
				.setContentTitle(title).setContentIntent(resultPendingIntent)
				.setVibrate(new long[] { 100, 250, 100, 500 })
				.setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
				.setContentText(message);

		int notifyID = 1;
		// mBuilder.setContentIntent(resultPendingIntent);
		NotificationManager mNotificationManager = (NotificationManager) ViditureApp.context
				.getSystemService(Context.NOTIFICATION_SERVICE);

		// mId allows you to update the notification later on.
		
		mNotificationManager.notify(notifyID, mBuilder.build());

	}
}

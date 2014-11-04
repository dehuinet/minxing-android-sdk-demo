package com.minxing.client.receiver;

import java.util.Timer;
import java.util.TimerTask;

import com.minxing.SystemTabActivity;
import com.minxing.client.MainActivity;
import com.minxing.client.R;
import com.minxing.kit.MXManager;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Vibrator;

public class NotifycationReceiver extends BroadcastReceiver{
	private static Timer notificationTimer = null;
	@Override
	public void onReceive(final Context context, Intent intent) {
		String content = intent.getStringExtra(MXManager.notifycationActionKeyContent);
		
		final Notification notification = new Notification(R.drawable.mx_ic_launcher, content, System.currentTimeMillis());

		notification.defaults = Notification.DEFAULT_LIGHTS;
		notification.ledARGB = Color.BLUE;
		notification.ledOnMS = 5000;

//		boolean isNotifySound = isMessageNotificationSound(context, accountID);
//
//		if (isNotifySound && isNeedSoundOrShakeNotify) {
			notification.defaults |= Notification.DEFAULT_SOUND;
//		}
		final boolean isNotifyShake = true;
		final boolean isNeedSoundOrShakeNotify = true;
		Intent notificationViewIntent = new Intent(context, MainActivity.class);
		PendingIntent contentItent = PendingIntent.getActivity(context, 0, notificationViewIntent, 0);
		notification.setLatestEventInfo(context, context.getString(R.string.mx_app_name), content, contentItent);

		if (notificationTimer != null) {
			notificationTimer.cancel();
			notificationTimer = null;
		}
		notificationTimer = new Timer(true);
		notificationTimer.schedule(new TimerTask() {

			@Override
			public void run() {
				NotificationManager notificationManager = (NotificationManager) context
						.getSystemService(android.content.Context.NOTIFICATION_SERVICE);
				notificationManager.notify(0, notification);
				if (isNotifyShake && isNeedSoundOrShakeNotify) {
					Vibrator vib = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
					vib.vibrate(new long[] { 300, 300, 300, 300 }, -1);
				}
			}
		}, 500);
		
	}

}

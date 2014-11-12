package com.minxing.kit.demo;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import com.minxing.kit.MXConstants;
import com.minxing.kit.MXKit;
import com.minxing.kit.MXKit.MXKitUserConflictListener;
import com.minxing.kit.MXKit.MXUIListener;
import com.minxing.kit.MXKitConfiguration;
import com.minxing.kit.MXUIEngine.ViewSwitchListener;
import com.minxing.kit.api.bean.MXError;

public class AppApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		
		MXKitConfiguration config = new MXKitConfiguration.Builder(AppApplication.this).hostOptions("http://dev3.dehuinet.com:8013",
				"ssl://dev3.dehuinet.com:1813").build();
		
		MXKit.getInstance().init(getApplicationContext(), config);
		
		MXKit.getInstance().setConflictListener(new MXKitUserConflictListener() {
			
			@Override
			public void onUserConflict(Context context, MXError error) {
				/*Utils.toast(getApplicationContext(), String.valueOf(error.getMessage()), Toast.LENGTH_SHORT);
				NotificationUtil.clearNotification(context);

				Intent finishIntent = new Intent(context, ClientTabActivity.class);
				finishIntent.setAction("finish");
				finishIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				context.startActivity(finishIntent);*/
			}
		});

		MXKit.getInstance().setMinxingUIListener(new MXUIListener() {

			@Override
			public void switchToMainScreen(Context context) {
				Intent intent = new Intent(context, HomeActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				context.startActivity(intent);
			}
		});

		MXKit.getInstance().setViewSwitchListener(new ViewSwitchListener() {

			@Override
			public void switchToCircle(Context context, int groupID) {
				Intent intent = new Intent(context, HomeActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				intent.putExtra(MXConstants.IntentKey.SHOW_CURRENT_GROUP_WORK_CIRCLE, groupID);
				context.startActivity(intent);
			}
		});
	
	}
}

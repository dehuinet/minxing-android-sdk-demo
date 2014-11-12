package com.minxing.demo;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.minxing.client.ClientTabActivity;
import com.minxing.client.activity.GesturePasswordActivity;
import com.minxing.client.util.BackgroundDetector;
import com.minxing.client.util.ImageUtil;
import com.minxing.client.util.NotificationUtil;
import com.minxing.client.util.Utils;
import com.minxing.kit.MXConstants;
import com.minxing.kit.MXKit;
import com.minxing.kit.MXKit.MXKitLifecycleCallbacks;
import com.minxing.kit.MXKit.MXKitUserConflictListener;
import com.minxing.kit.MXKit.MXUIListener;
import com.minxing.kit.MXKitConfiguration;
import com.minxing.kit.MXUIEngine.ViewSwitchListener;
import com.minxing.kit.api.bean.MXError;
import com.umeng.analytics.MobclickAgent;
import com.umeng.analytics.ReportPolicy;

public class AppApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		NotificationUtil.clearAllNotification(getApplicationContext());
		MXKitConfiguration config = new MXKitConfiguration.Builder(AppApplication.this).hostOptions("http://dev3.dehuinet.com:8013",
				"ssl://dev3.dehuinet.com:1813").build();
		
		MXKit.getInstance().init(getApplicationContext(), config);
		ImageUtil.initImageEngine(getApplicationContext());
		
		MXKit.getInstance().setConflictListener(new MXKitUserConflictListener() {
			
			@Override
			public void onUserConflict(Context context, MXError error) {
				Utils.toast(getApplicationContext(), String.valueOf(error.getMessage()), Toast.LENGTH_SHORT);
				Intent finishIntent = new Intent(context, ClientTabActivity.class);
				finishIntent.setAction("finish");
				finishIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				context.startActivity(finishIntent);
			}
		});

		MXKit.getInstance().setMinxingUIListener(new MXUIListener() {

			@Override
			public void switchToMainScreen(Context context) {
				Intent intent = new Intent(context, ClientTabActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				context.startActivity(intent);
			}
		});

		MXKit.getInstance().setViewSwitchListener(new ViewSwitchListener() {

			@Override
			public void switchToCircle(Context context, int groupID) {
				Intent intent = new Intent(context, ClientTabActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				intent.putExtra(MXConstants.IntentKey.SHOW_CURRENT_GROUP_WORK_CIRCLE, groupID);
				context.startActivity(intent);
			}
		});
		MXKit.getInstance().setLifecycleCallbacks(new MXKitLifecycleCallbacks() {

			@Override
			public void onActivityStop(Activity activity) {
			}

			@Override
			public void onActivityStart(Activity activity) {
				if (BackgroundDetector.getInstance().isGesturePwdViewEnabled()) {
					Intent intent = new Intent(activity, GesturePasswordActivity.class);
					intent.putExtra(GesturePasswordActivity.PWD_SCREEN_MODE_KEY, GesturePasswordActivity.PWD_SCREEN_MODE_BACKGROUND);
					intent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
					activity.startActivity(intent);
					BackgroundDetector.getInstance().setPasswordCheckActive(true);
				}
				BackgroundDetector.getInstance().setDetectorStop(false);
				BackgroundDetector.getInstance().onAppStart(activity);
			}

			@Override
			public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
			}

			@Override
			public void onActivityResume(Activity activity) {
			}

			@Override
			public void onActivityPause(Activity activity) {
				BackgroundDetector.getInstance().onAppPause(activity);
			}

			@Override
			public void onActivityDestroy(Activity activity) {
			}

			@Override
			public void onActivityCreate(Activity activity, Bundle savedInstanceState) {
			}

			@Override
			public void onStartActivityForResult(Activity activity) {
				BackgroundDetector.getInstance().setDetectorStop(true);
			}
		});

		MobclickAgent.setDefaultReportPolicy(getApplicationContext(), ReportPolicy.BATCH_AT_LAUNCH);
	}
}

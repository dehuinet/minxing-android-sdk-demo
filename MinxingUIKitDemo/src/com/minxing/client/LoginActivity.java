package com.minxing.client;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.minxing.kit.MXManager.ViewSwitchListener;
import com.minxing.uikit.Constants;
import com.minxing.uikit.MXUIKit;
import com.minxing.uikit.MXUIKit.MXUIListener;
import com.minxing.uikit.MXUIKit.UIKitInitListener;

public class LoginActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		Button lginBtn = (Button) findViewById(R.id.btn_login);
		lginBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				initMXKits();
				Intent intent = new Intent(LoginActivity.this, MainActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
			}
		});
		
		
	}
	
	public void initMXKits(){
		MXUIKit.getInstance().setMinxingUIListener(new MXUIListener() {
			
			@Override
			public void switchToMainScreen(Context context) {
				Intent intent = new Intent(context, MainActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
			}
		});
		MXUIKit.getInstance().setUiKitInitListener( new UIKitInitListener() {

			@Override
			public void onSucess() {
				Intent intent = new Intent(LoginActivity.this, MainActivity.class);
				startActivity(intent);
				finish();
			}
		});
		
		MXUIKit.getInstance().setViewSwitchListener(new ViewSwitchListener() {
			
			@Override
			public void switchToWorkCircle(Context context, int groupID) {
				Intent intent = new Intent(context, MainActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				intent.putExtra(Constants.IntentKey.SHOW_CURRENT_GROUP_WORK_CIRCLE, groupID);
				context.startActivity(intent);
			}
		});
		MXUIKit.getInstance().init(LoginActivity.this);
	}
	

}

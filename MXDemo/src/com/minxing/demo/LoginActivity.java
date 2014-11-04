package com.minxing.demo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.minxing.kit.MXKit;
import com.minxing.kit.MXKit.MXKitLoginListener;
import com.minxing.kit.MXKit.MXUIListener;
import com.minxing.kit.MXKitConfiguration;
import com.minxing.kit.MXManager.ViewSwitchListener;
import com.minxing.kit.ui.MXTabActivity;


/**
 * 演示登陆界面
 * 用户可以在此添加自己的登录逻辑
 * 
 */
public class LoginActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		
		Button lginBtn = (Button) findViewById(R.id.btn_login);
		lginBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				
				Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
				startActivity(intent);
				finish();
			}
		});
		
		
	}
	
	

}

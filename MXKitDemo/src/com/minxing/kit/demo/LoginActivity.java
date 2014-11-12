package com.minxing.kit.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.minxing.kit.MXKit;
import com.minxing.kit.MXKit.MXKitLoginListener;
import com.minxing.kit.api.bean.MXError;


/**
 * 演示登陆界面
 * 用户可以在此添加自己的登录逻辑
 * 
 */
public class LoginActivity extends Activity {
	private EditText textUsername;
	private EditText textPassword;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		textUsername = (EditText) findViewById(R.id.username);
		textPassword = (EditText) findViewById(R.id.password);
		
		Button lginBtn = (Button) findViewById(R.id.btn_login);
		lginBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				String username = textUsername.getText().toString();
				String password = textPassword.getText().toString();
				
				MXKit.getInstance().loginMXKit(LoginActivity.this, username, password, new MXKitLoginListener() {

					@Override
					public void onSuccess() {
//						launchApp(usernameString);
//						progressDialog.dismiss();
						
						Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
						startActivity(intent);
						finish();
					}

					@Override
					public void onFail(MXError error) {
						System.out.println(""+error);
				/*		if (error != null) {
							Utils.toast(LoginActivity.this, error.getMessage(), Toast.LENGTH_SHORT);
						}
						progressDialog.dismiss();*/
					}
				});
				/*Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
				startActivity(intent);
				finish();*/
			}
		});
		
		
	}
	
	

}

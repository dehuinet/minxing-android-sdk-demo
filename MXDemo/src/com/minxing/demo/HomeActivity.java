package com.minxing.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.minxing.kit.MXKit;
import com.minxing.kit.MXKit.MXKitLoginListener;
import com.minxing.kit.MXKitConfiguration;
import com.minxing.kit.ui.MXTabActivity;

public class HomeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		// 初始化敏行
		initMXKits();
		
		// 敏行启动按钮
		TextView minxingLauncher = (TextView) findViewById(R.id.minxing_launcher);

		minxingLauncher.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				//使用用户名密码登录敏行
				MXKit.getInstance().loginMXKit(HomeActivity.this, "18901010101", "111111", new MXKitLoginListener() {

					@Override
					public void onSuccess() {
						Intent intent = new Intent(HomeActivity.this, MXTabActivity.class);
						startActivity(intent);
					}

					@Override
					public void onFail() {
						// TODO Auto-generated method stub

					}
				});

			}
		});
	}

	public void initMXKits() {

		//初始化敏行相关配置
		MXKitConfiguration configuration = new MXKitConfiguration.Builder(HomeActivity.this).hostOptions("http://183.129.203.74:8088",
				"ssl://183.129.203.78:1883").build();
		MXKit.getInstance().init(HomeActivity.this, configuration);
	}

}

package com.minxing.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.minxing.client.ClientTabActivity;
import com.minxing.kit.MXKit;
import com.minxing.kit.MXKit.MXKitLoginListener;
import com.minxing.kit.MXKitConfiguration;
import com.minxing.kit.api.bean.MXError;

public class HomeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		// 敏行启动按钮
		TextView minxingLauncher = (TextView) findViewById(R.id.minxing_launcher);

		minxingLauncher.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				//使用用户名密码登录敏行
				MXKit.getInstance().loginMXKit(HomeActivity.this, "3999", "111111", new MXKitLoginListener() {

					@Override
					public void onSuccess() {
						Intent intent = new Intent(HomeActivity.this, ClientTabActivity.class);
						startActivity(intent);
					}

				
					@Override
					public void onFail(MXError error) {
						System.out.println(""+error);
						
					}
				});

			}
		});
	}

	

}

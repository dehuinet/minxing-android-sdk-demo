package com.minxing.client;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.minxing.uikit.MinxingUIKit;
import com.minxing.uikit.MinxingUIKit.InitUIKitListener;

public class LoginActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		Button lginBtn = (Button) findViewById(R.id.btn_login);
		lginBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				MinxingUIKit.getInstance().init(LoginActivity.this, new InitUIKitListener() {

					@Override
					public void onSucess() {
						Intent intent = new Intent(LoginActivity.this, MainActivity.class);
						startActivity(intent);
						finish();
					}
				});

			}
		});
	}

}

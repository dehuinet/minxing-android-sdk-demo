package com.minxing.client;

import com.minxing.SystemTabActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class LaunchActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_launch);

		Button btnUIkit = (Button) findViewById(R.id.btn_uikit);
		Button btnAPP = (Button) findViewById(R.id.btn_app);
		btnUIkit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(LaunchActivity.this, MainActivity.class);
				startActivity(intent);

			}
		});

		btnAPP.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(LaunchActivity.this, SystemTabActivity.class);
				startActivity(intent);

			}
		});
	}

}

package com.minxing.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.minxing.api.MXEngine;
import com.minxing.api.test.R;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		MXEngine.getInstance().init(this);
	}

	private void initView() {
		Button btnCrm = (Button) findViewById(R.id.btn_crm);
		btnCrm.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//进入CRM界面
				Intent intent = new Intent(MainActivity.this, CRMActivity.class);
				startActivity(intent);
			}
		});
		
		Button btnReinit = (Button) findViewById(R.id.btn_reinit);
		btnReinit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				MXEngine.getInstance().init(MainActivity.this);
			}
		});
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == android.R.id.home) {
			finish();
		} 
		return super.onOptionsItemSelected(item);
	}
}

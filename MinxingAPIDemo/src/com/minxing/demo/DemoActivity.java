package com.minxing.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class DemoActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_demo);
		// 初始化Demo界面
		initView();
	}

	private void initView() {

		setupActionBar();

		

	}

	private void setupActionBar() {
	/*	ActionBar actionBar = getActionBar();
		actionBar.setTitle("商机");
		actionBar.setIcon(R.drawable.back);
		actionBar.setHomeButtonEnabled(true);*/
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.activity_crm, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == android.R.id.home) {
			finish();
		} else if (item.getItemId() == R.id.menu_forward) {
//			forwardToOthers();
		}
		return super.onOptionsItemSelected(item);
	}

/*	private void forwardToOthers() {
		//转发到工作圈,参数为标题和默认内容
		AgileManager.getInstance().sendToCircle(DemoActivity.this, "分享", "测试转发到工作圈");

	}*/

}

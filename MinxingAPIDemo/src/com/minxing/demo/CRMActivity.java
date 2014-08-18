package com.minxing.demo;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.minxing.api.AgileManager;

public class CRMActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_crm);
		// 初始化Demo界面
		initView();
	}

	private void initView() {
		LinearLayout mutiConversationBtn = (LinearLayout) findViewById(R.id.muti_conversation);
		LinearLayout singleConversationBtn = (LinearLayout) findViewById(R.id.single_conversation);
		TextView crmContact1 = (TextView) findViewById(R.id.crm_contact_1);
		TextView crmContact2 = (TextView) findViewById(R.id.crm_contact_2);
		TextView crmContact3 = (TextView) findViewById(R.id.crm_contact_3);
		setupActionBar();

		singleConversationBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				//聊天人员ID数组不包括本人
				String[] persons = new String[] { "45" };
				//调用接口发起聊天
				AgileManager.getInstance().startConversation(CRMActivity.this, persons);
			}
		});

		mutiConversationBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String[] persons = new String[] { "55", "4" };
				AgileManager.getInstance().startConversation(CRMActivity.this, persons);
			}
		});

		crmContact1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// 用户ID
				String memberId = "45";
				// 调用API进入查看人员详细信息界面
				AgileManager.getInstance().viewMemberDetail(CRMActivity.this, memberId);
			}
		});
		crmContact2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String memberId = "55";
				AgileManager.getInstance().viewMemberDetail(CRMActivity.this, memberId);
			}
		});
		crmContact3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String memberId = "4";
				AgileManager.getInstance().viewMemberDetail(CRMActivity.this, memberId);
			}
		});

	}

	private void setupActionBar() {
		ActionBar actionBar = getActionBar();
		actionBar.setTitle("商机");
		actionBar.setIcon(R.drawable.back);
		actionBar.setHomeButtonEnabled(true);
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
			forwardToOthers();
		}
		return super.onOptionsItemSelected(item);
	}

	private void forwardToOthers() {
		//转发到工作圈,参数为标题和默认内容
		AgileManager.getInstance().sendToCircle(CRMActivity.this, "分享", "测试转发到工作圈");

	}

}

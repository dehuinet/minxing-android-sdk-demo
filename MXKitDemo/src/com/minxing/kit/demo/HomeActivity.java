package com.minxing.kit.demo;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TabHost;

import com.minxing.kit.MXUIEngine;
import com.minxing.kit.ui.appcenter.AppCenterManager;
import com.minxing.kit.ui.appcenter.MXAppCenterActivity;
import com.minxing.kit.ui.chat.ChatManager;
import com.minxing.kit.ui.chat.MXChatActivity;
import com.minxing.kit.ui.circle.CircleManager;
import com.minxing.kit.ui.circle.MXCircleActivity;
import com.minxing.kit.ui.contacts.ContactManager;
import com.minxing.kit.ui.contacts.MXContactsActivity;

public class HomeActivity extends TabActivity {
	private TabHost mTabHost = null;
	private Intent mChatIntent = null; // 敏信
	private Intent mContactsIntent = null; // 通讯录
	private Intent mAppCenterIntent = null; // 应用中心
	private Intent mCircleIntent = null; // 工作圈
	
	
	public final static String TAB_TAG_CHAT = "chat";
	public final static String TAB_TAG_CONTACTS = "contacts";
	public final static String TAB_TAG_APP_CENTER = "appcenter";
	public final static String TAB_TAG_CIRCLES = "circle";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		setupIntent();
		// 初始化敏行
		// 敏行启动按钮
		
	}
	
	private void setupIntent() {
		mChatIntent = new Intent(this, MXChatActivity.class);
		mContactsIntent = new Intent(this, MXContactsActivity.class);
		mAppCenterIntent = new Intent(this, MXAppCenterActivity.class);
		mCircleIntent = new Intent(this, MXCircleActivity.class);
		initTabhost();
	}

	
	/**
	 * 初始化底部菜单用户可以在此方法中添加自定义的底部菜单
	 * 
	 * 
	 */
	private void initTabhost() {
		mTabHost = getTabHost();
		TabHost.TabSpec tSpec1 = mTabHost.newTabSpec(TAB_TAG_CHAT);
		tSpec1.setContent(mChatIntent);
		tSpec1.setIndicator("敏信", getResources().getDrawable(R.drawable.ic_launcher));
		initChatHeaderView();
		mTabHost.addTab(tSpec1);
		
		TabHost.TabSpec tSpec2 = mTabHost.newTabSpec(TAB_TAG_CONTACTS);
		tSpec2.setContent(mContactsIntent);
		tSpec2.setIndicator("通讯录", getResources().getDrawable(R.drawable.ic_launcher));
		initContactsHeaderView();
		mTabHost.addTab(tSpec2);
		
		TabHost.TabSpec tSpec3 = mTabHost.newTabSpec(TAB_TAG_APP_CENTER);
		tSpec3.setContent(mAppCenterIntent);
		tSpec3.setIndicator("应用中心", getResources().getDrawable(R.drawable.ic_launcher));
		initAppcenterHeaderView();
		mTabHost.addTab(tSpec3);
		
		TabHost.TabSpec tSpec4 = mTabHost.newTabSpec(TAB_TAG_CIRCLES);
		tSpec4.setContent(mCircleIntent);
		tSpec4.setIndicator("工作圈", getResources().getDrawable(R.drawable.ic_launcher));
		initCircleHeaderView();
		mTabHost.addTab(tSpec4);
	}
	
	
	

	/**
	 * 初始化聊天header view
	 * 
	 */
	private void initChatHeaderView() {
		ChatManager chatManager = MXUIEngine.getInstance().getChatManager();
		RelativeLayout chatHeader = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.chat_header_view, null);

		ImageButton backBtn = (ImageButton) chatHeader.findViewById(R.id.title_left_back_button);
		final ImageButton addButton = (ImageButton) chatHeader.findViewById(R.id.title_right_new_function);
		final SystemMainTopRightPopMenu functionPopMenu = new SystemMainTopRightPopMenu(HomeActivity.this);
		addButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				new Handler().post(new Runnable() {
					public void run() {
						if (!functionPopMenu.isShowing()) {
							functionPopMenu.showAsDropDown(addButton);
						}
					}
				});
			}
		});

		backBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
		chatManager.setHeaderView(chatHeader);

	}
	
	private void initContactsHeaderView() {
		ContactManager contactManager = MXUIEngine.getInstance().getContactManager();
		RelativeLayout contactsHeader = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.contacts_header_view, null);
		ImageButton backBtn = (ImageButton) contactsHeader.findViewById(R.id.title_left_back_button);
		backBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
		contactManager.setHeaderView(contactsHeader);
	}
	
	private void initAppcenterHeaderView() {
		AppCenterManager appCenterManager = MXUIEngine.getInstance().getAppCenterManager();
		RelativeLayout appcenterHeader = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.app_center_header_view, null);
		ImageButton backBtn = (ImageButton) appcenterHeader.findViewById(R.id.title_left_back_button);
		backBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
		appCenterManager.setHeaderView(appcenterHeader);
	}
	
	
	private void initCircleHeaderView() {
		CircleManager circleManager = MXUIEngine.getInstance().getCircleManager();
		RelativeLayout circleHeader = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.circle_header_view, null);
		ImageButton backBtn = (ImageButton) circleHeader.findViewById(R.id.title_left_back_button);
		backBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
		circleManager.setHeaderView(circleHeader);
	}

}

package com.minxing.client;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;
import android.widget.TabHost;

import com.minxing.api.MXEngine;


public class MainActivity extends FragmentActivity{
	
	private TabHost mTabHost = null;
	private String[] mMenuNames;
	private FragmentManager fragmentManager;
	private ChatFragment chatFragment;
	private CircleFragment circleFragment;
	private BaseFragment baseFragment;
	private ConsumerFragment consumerFragment;
	private BusinessFragment businessFragment;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MXEngine.getInstance().init(MainActivity.this);
		fragmentManager = getSupportFragmentManager();
		chatFragment = new ChatFragment();
		circleFragment = new CircleFragment();
		consumerFragment = new ConsumerFragment();
		businessFragment = new BusinessFragment();
	    initView();
	}

	private void initView() {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		
		mTabHost = (TabHost)findViewById(R.id.tabhost);
		mTabHost.setup();
		initTabs();
	}
	
	private void initTabs() {
		mMenuNames = getResources().getStringArray(R.array.home_menu_names);

		TabHost.OnTabChangeListener tabChangeListener = new TabHost.OnTabChangeListener(){

			@Override
			public void onTabChanged(String tabId) {
				FragmentTransaction transaction = fragmentManager.beginTransaction();
				fragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
				if(tabId.equalsIgnoreCase(mMenuNames[0])){
			        transaction.replace(R.id.realtabcontent, chatFragment);
			        baseFragment = chatFragment;
			        transaction.commit();
				}else if(tabId.equalsIgnoreCase(mMenuNames[1])){
			        transaction.replace(R.id.realtabcontent, circleFragment);
			        baseFragment = circleFragment;
			        transaction.commit();
				}else if(tabId.equalsIgnoreCase(mMenuNames[2])){
			        transaction.replace(R.id.realtabcontent, consumerFragment);
			        baseFragment = consumerFragment;
			        transaction.commit();
				}else if(tabId.equalsIgnoreCase(mMenuNames[3])){
			        transaction.replace(R.id.realtabcontent, businessFragment);
			        baseFragment = businessFragment;
			        transaction.commit();
				}
			}
			
		};
		mTabHost.setOnTabChangedListener(tabChangeListener);  
		
		
		TabHost.TabSpec tSpec1 = mTabHost.newTabSpec(mMenuNames[0]);
		tSpec1.setIndicator(mMenuNames[0],getResources().getDrawable(R.drawable.black));
		tSpec1.setContent(new DummyTabContent(getBaseContext()));
		mTabHost.addTab(tSpec1);

		TabHost.TabSpec tSpec2 = mTabHost.newTabSpec(mMenuNames[1]);
		tSpec2.setIndicator(mMenuNames[1], getResources().getDrawable(R.drawable.black));
		tSpec2.setContent(new DummyTabContent(getBaseContext()));
		mTabHost.addTab(tSpec2);
		
		TabHost.TabSpec tSpec3 = mTabHost.newTabSpec(mMenuNames[2]);
		tSpec3.setIndicator(mMenuNames[2], getResources().getDrawable(R.drawable.black));
		tSpec3.setContent(new DummyTabContent(getBaseContext()));
		mTabHost.addTab(tSpec3);
		
		TabHost.TabSpec tSpec4 = mTabHost.newTabSpec(mMenuNames[3]);
		tSpec4.setIndicator(mMenuNames[3], getResources().getDrawable(R.drawable.black));
		tSpec4.setContent(new DummyTabContent(getBaseContext()));
		mTabHost.addTab(tSpec4);
		
	}
	
	
	
	
	public int getCurrentTab(){
		return mTabHost.getCurrentTab();
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		baseFragment.onActivityResult(requestCode, resultCode, data);
	}
	
	
}

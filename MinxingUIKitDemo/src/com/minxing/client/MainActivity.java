package com.minxing.client;


import java.util.ArrayList;
import java.util.List;

import android.app.TabActivity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.DrawerListener;
import android.view.View;
import android.view.Window;
import android.widget.TabHost;

import com.minxing.api.internal.NativeOperation;
import com.minxing.api.internal.NativeOperationInvoker;
import com.minxing.cache.WBCacheManager;
import com.minxing.client.receiver.NotifycationReceiver;
import com.minxing.client.tab.MenuTabHost;
import com.minxing.client.tab.MenuTabItem;
import com.minxing.db.conversation.ConversationOcuInfo;
import com.minxing.kit.MXManager;
import com.minxing.kit.activity.MXAppCenterActivity;
import com.minxing.kit.activity.MXConversationListActivity;
import com.minxing.kit.activity.MXHomeActivity;
import com.minxing.kit.activity.MXMainContactActivity;
import com.minxing.pojo.MenuInfo;
import com.minxing.uikit.Constants;
import com.minxing.widget.SlidingMenu;


public class MainActivity extends TabActivity{
	
	private String[] mMenuNames;
	private FragmentManager fragmentManager;
	private ChatFragment chatFragment;
	private CircleFragment circleFragment;
	private BaseFragment baseFragment;
	private ConsumerFragment consumerFragment;
	private BusinessFragment businessFragment;
	private ContactFragment contactFragment;
	private NotifycationReceiver notifycationReceiver;
	
	private SlidingMenu slidingMenu;
	private DrawerLayout drawerLayout;
	
	private MenuTabHost mTabHost = null;
	private Intent mConversationIntent = null; // 敏信
	private Intent mContactIntent = null; // 通讯录
	private Intent mAppCenterIntent = null; // 应用中心
	private Intent mWorkCirclesIntent = null; // 工作圈
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

	    initView();
	    notifycationReceiver = new  NotifycationReceiver();
	    IntentFilter intentFilter = new IntentFilter(MXManager.notifycationBroadcastAction);
	    registerReceiver(notifycationReceiver, intentFilter);
	}

	private void initView() {
		setContentView(R.layout.activity_main);
		drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		slidingMenu = (SlidingMenu) findViewById(R.id.sliding_menu);
		initSlidingMenu();
//		mTabHost = (MenuTabHost)findViewById(R.id.tabhost);
		mTabHost = (MenuTabHost) getTabHost();
//		mTabHost.setup();
		setupIntent();
		initTabs();
	}
	
	private void initTabs() {
		mMenuNames = getResources().getStringArray(R.array.home_menu_names);
		
		TabHost.OnTabChangeListener tabChangeListener = new TabHost.OnTabChangeListener(){

			@Override
			public void onTabChanged(String tabId) {
				MenuTabItem menuTabItem = mTabHost.getMenuTabItemByTag(tabId);
				if (menuTabItem.getContentType() == MenuTabItem.ContentType.ACTIVITY) {
					ConversationOcuInfo ocuInfo = WBCacheManager.getInstance().cachedOcus().get(menuTabItem.getOcuId());
					NativeOperation operation = new NativeOperation();
					operation.construct(ocuInfo.getUrl());
					NativeOperationInvoker.getInstance().handleNativeInvoke(MainActivity.this, operation, ocuInfo, null);
				}
			}
			
		};
		
		mTabHost.setOnTabChangedListener(tabChangeListener);
		List<MenuInfo> menuList = WBCacheManager.getInstance().getCurrentUser().getMenuList();
		if (menuList == null || menuList.isEmpty()) {
			menuList = mTabHost.generateFakeMenuInfos(mMenuNames);
		}
		List<MenuTabItem> menuTabItemList = generateMenuItemList(menuList);
		
		mTabHost.initMenuItem(menuTabItemList);
		
		
		/*TabHost.TabSpec tSpec1 = mTabHost.newTabSpec(mMenuNames[0]);
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
		mTabHost.addTab(tSpec4);*/
		
	}
	private List<MenuTabItem> generateMenuItemList(List<MenuInfo> menuList) {
		List<MenuTabItem> menuTabItemList = new ArrayList<MenuTabItem>();
		for (MenuInfo menuInfo : menuList) {
			MenuTabItem menuTabItem = new MenuTabItem(MainActivity.this);
			menuTabItem.setMenuInfo(menuInfo);
			String menuIdentifier = menuInfo.getIdentifier();

			if (MenuTabItem.TAB_TAG_CONVERSATION.equals(menuIdentifier)) {
				menuTabItem.setIntent(mConversationIntent);
			} else if (MenuTabItem.TAB_TAG_CONTACT.equals(menuIdentifier)) {
				menuTabItem.setIntent(mContactIntent);
			} else if (MenuTabItem.TAB_TAG_APP_CENTER.equals(menuIdentifier)) {
				menuTabItem.setIntent(mAppCenterIntent);
			} else if (MenuTabItem.TAB_TAG_WORK_CIRCLES.equals(menuIdentifier)) {
				menuTabItem.setIntent(mWorkCirclesIntent);
			} else {
//				Intent pluginIntent = new Intent(this, TabPluginActivity.class);
//				if (menuTabItem.getContentType() == MenuTabItem.ContentType.ACTIVITY) {
//					menuTabItem.setContent(new DummyTabContent(getBaseContext()));
//				} else if (menuTabItem.getContentType() == MenuTabItem.ContentType.VIEW) {
//					menuTabItem.setIntent(pluginIntent);
//					pluginIntent.putExtra(Constants.IntentKey.INTENT_TAB_MENU_INFO, menuInfo);
//				}
			}
			menuTabItemList.add(menuTabItem);
		}
		return menuTabItemList;
	}
	
	private void setupIntent() {
		mConversationIntent = new Intent(this, MXConversationListActivity.class);
//		mConversationIntent.putExtra(TabConversationListActivity.IS_NEED_LOAD_USERINFO,
//				getIntent().getIntExtra(APP_LAUNCH_TYPE, APP_LAUNCH_TYPE_OAUTH) == APP_LAUNCH_TYPE_TOKEN ? true : false);
		mContactIntent = new Intent(this, MXMainContactActivity.class);
		mAppCenterIntent = new Intent(this, MXAppCenterActivity.class);
		mWorkCirclesIntent = new Intent(this, MXHomeActivity.class);
		this.mTabHost = (MenuTabHost) getTabHost();
	}
	
	private void initSlidingMenu() {
		slidingMenu.init(drawerLayout, WBCacheManager.getInstance().getCurrentUser());
		drawerLayout.setDrawerListener(new DrawerListener() {

			@Override
			public void onDrawerStateChanged(int state) {
				if (state == DrawerLayout.STATE_DRAGGING) {
					slidingMenu.refreshNetwork(WBCacheManager.getInstance().getCurrentUser());
				}
			}

			@Override
			public void onDrawerSlide(View arg0, float arg1) {
			}

			@Override
			public void onDrawerOpened(View arg0) {
			}

			@Override
			public void onDrawerClosed(View arg0) {
			}
		});
	}
	
	@Override
	protected void onResume() {
		super.onResume();

		int groupID = getIntent().getIntExtra(Constants.IntentKey.SHOW_CURRENT_GROUP_WORK_CIRCLE, -1);
		if (groupID != -1) {
			getIntent().removeExtra(Constants.IntentKey.SHOW_CURRENT_GROUP_WORK_CIRCLE);
			circleFragment.updateGroup(groupID);
			mTabHost.setCurrentTabByTag(mMenuNames[1]);

		}

	}
	
	public int getCurrentTab(){
		return mTabHost.getCurrentTab();
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		baseFragment.onActivityResult(requestCode, resultCode, data);
	}
	
	
}

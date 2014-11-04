package com.minxing.client.tab;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.widget.TabHost.TabContentFactory;

import com.minxing.cache.WBCacheManager;
import com.minxing.client.BaseFragment;
import com.minxing.db.conversation.ConversationOcuInfo;
import com.minxing.pojo.MenuInfo;

public class MenuTabItem {
	public final static String TAB_TAG_CONVERSATION = "im";
	public final static String TAB_TAG_CONTACT = "contacts";
	public final static String TAB_TAG_APP_CENTER = "apps";
	public final static String TAB_TAG_WORK_CIRCLES = "circles";
	
	public static enum ContentType {VIEW, ACTIVITY, UNKNOWN};
	
	private Intent intent;
	private TabContentFactory content;
	private Drawable imageRes;
	
	private MenuInfo menuInfo;
	private Context context;

	private BaseFragment fragment;
	
	public MenuTabItem(Context context) {
		this.context = context;
	}

	
	public MenuInfo getMenuInfo() {
		return menuInfo;
	}

	public void setMenuInfo(MenuInfo menuInfo) {
		this.menuInfo = menuInfo;
		int drawableResourceId = context.getResources().getIdentifier("mx_tab_"+menuInfo.getIdentifier(), "drawable", context.getPackageName());
	    if(drawableResourceId != 0){
	    	imageRes = context.getResources().getDrawable(drawableResourceId);
	    }
	}

	public Intent getIntent() {
		return intent;
	}

	public void setIntent(Intent intent) {
		this.intent = intent;
	}

	public Drawable getImageRes() {
		return imageRes;
	}

	public void setImageRes(Drawable imageRes) {
		this.imageRes = imageRes;
	}



	public String getName() {
		if(menuInfo == null){
			return null;
		}
		return menuInfo.getName();
	}



	public String getIdentifier() {
		if(menuInfo == null){
			return null;
		}
		return menuInfo.getIdentifier();
	}
	
	
	public TabContentFactory getContent() {
		return content;
	}


	public void setContent(TabContentFactory content) {
		this.content = content;
	}


	public ContentType getContentType(){
		ConversationOcuInfo ocuInfo = WBCacheManager.getInstance().cachedOcus().get(menuInfo.getOcuId());
		if(ocuInfo == null || ocuInfo.getAndroid_launcher() == null || "".equals(ocuInfo.getAndroid_launcher())){
			return ContentType.UNKNOWN;
		}
		String android_launcher = ocuInfo.getAndroid_launcher();
		if(android_launcher.indexOf("://") != -1){
			return ContentType.ACTIVITY;
		}
		return ContentType.VIEW;
	}


	public String getOcuId() {
		return menuInfo.getOcuId();
	}


	public void setFragment(BaseFragment fragment) {
		this.fragment = fragment;
		
	}

	public BaseFragment getFragment() {
		return fragment;
	}
	
}

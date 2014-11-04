package com.minxing.client.tab;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.minxing.R;
import com.minxing.client.BaseFragment;
import com.minxing.pojo.MenuInfo;

public class MenuTabHost extends TabHost {
	private OnReClickListener onReClickListener;
	private OnUnSwitchClickListener OnUnSwitchClickListener;

	private HashMap<String, Integer> menuMap = new HashMap<String, Integer>();
	private List<MenuTabItem> menuTabItemList = new ArrayList<MenuTabItem>();
	private Context context;

	public MenuTabHost(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
	}

	public MenuTabHost(Context context) {
		super(context);
		this.context = context;
	}

	public interface OnReClickListener {
		public void onReClick(int index, String tabTag);
	}

	public interface OnUnSwitchClickListener {
		public void OnUnSwitchClick(MenuTabItem menuTabItem);
	}

	@Override
	public void setCurrentTab(int index) {
		MenuTabItem menuTabItem = getMenuTabItemByIndex(index);
		String tabTag = menuTabItem.getIdentifier();
		if (index == getCurrentTab()) {
			if (null != onReClickListener) {
				onReClickListener.onReClick(index, tabTag);
			}
		} else {
			if (tabTag == null || getMenuTabItemByTag(tabTag).getContentType() != MenuTabItem.ContentType.ACTIVITY) {
				super.setCurrentTab(index);
			} else {
				if (OnUnSwitchClickListener != null) {
					OnUnSwitchClickListener.OnUnSwitchClick(menuTabItem);
				}
			}
		}
	}

	public OnReClickListener getOnReClickListener() {
		return onReClickListener;
	}

	public void setOnReClickListener(OnReClickListener onReClickListener) {
		this.onReClickListener = onReClickListener;
	}

	public OnUnSwitchClickListener getOnUnSwitchClickListener() {
		return OnUnSwitchClickListener;
	}

	public void setOnUnSwitchClickListener(OnUnSwitchClickListener onUnSwitchClickListener) {
		OnUnSwitchClickListener = onUnSwitchClickListener;
	}

	public void showNumberMarker(String tabTag, String number) {
		View v = getTabViewByTag(tabTag);
		TextView numberMark = (TextView) v.findViewById(R.id.main_tab_unread_tv);
		numberMark.setVisibility(View.VISIBLE);
		numberMark.setText(number);
	}

	public void hideNumberMarker(String tabTag) {
		View v = getTabViewByTag(tabTag);
		TextView numberMark = (TextView) v.findViewById(R.id.main_tab_unread_tv);
		numberMark.setText("");
		numberMark.setVisibility(View.INVISIBLE);
	}

	public void showMarker(String tabTag) {
		View v = getTabViewByTag(tabTag);
		ImageView mark = (ImageView) v.findViewById(R.id.main_tab_work_circles_unseen);
		mark.setVisibility(View.VISIBLE);
	}

	public void hideMarker(String tabTag) {
		View v = getTabViewByTag(tabTag);
		ImageView numberMark = (ImageView) v.findViewById(R.id.main_tab_work_circles_unseen);
		numberMark.setVisibility(View.INVISIBLE);
	}

	private View getTabViewByTag(String tag) {
		int index = menuMap.get(tag);
		return getTabWidget().getChildTabViewAt(index);

	}

	public void clearMenuMap() {
		menuMap.clear();

	}

	@Override
	public void addTab(TabSpec tabSpec) {
		int currentTabSize = getTabWidget().getTabCount();
		menuMap.put(tabSpec.getTag(), currentTabSize);
		super.addTab(tabSpec);

	}

	public void initMenuItem(List<MenuTabItem> menuTabItemList) {
		this.menuTabItemList = menuTabItemList;
		clearMenuMap();
		for (MenuTabItem menuTabItem : menuTabItemList) {
			String menuIdentifier = menuTabItem.getIdentifier();
			if (menuIdentifier == null) {
				continue;
			}
			TabHost.TabSpec tSpec = newTabSpec(menuIdentifier);
			View tabview = null;
			if (menuTabItem.getContent() != null) {
				tSpec.setContent(menuTabItem.getContent());
			} else if (menuTabItem.getIntent() != null) {
				tSpec.setContent(menuTabItem.getIntent());
			} else {
				tSpec.setContent(new DummyTabContent(context));
			}
			tabview = createTabView(menuTabItem);
			tSpec.setIndicator(tabview);
			addTab(tSpec);
		}
	}

	private View createTabView(MenuTabItem menuTabItem) {
		View view = LayoutInflater.from(getContext()).inflate(R.layout.mx_menu_tab_view, null);
		TextView tv = (TextView) view.findViewById(R.id.menu_tab_name);
		Drawable drawable = menuTabItem.getImageRes();
		if (drawable == null) {
			drawable = getContext().getResources().getDrawable(R.drawable.mx_tab_apps);
		}
		tv.setCompoundDrawablesWithIntrinsicBounds(null, drawable, null, null);
		tv.setText(menuTabItem.getName());
		return view;
	}

	public MenuTabItem getMenuTabItemByTag(String tabId) {
		if (menuTabItemList == null || menuTabItemList.isEmpty()) {
			return null;
		}
		for (MenuTabItem menuTabItem : menuTabItemList) {
			if (tabId.equals(menuTabItem.getIdentifier())) {
				return menuTabItem;
			}
		}
		return null;
	}

	private MenuTabItem getMenuTabItemByIndex(int index) {
		return menuTabItemList.get(index);
	}
	
	public List<MenuInfo> generateFakeMenuInfos(String[] mMenuNames) {
		List<MenuInfo> menuList = new ArrayList<MenuInfo>();

		MenuInfo im = new MenuInfo();
		im.setId(1);
		im.setIdentifier("im");
		im.setName(mMenuNames[0]);
		im.setOrder(1);
		menuList.add(im);

		MenuInfo contact = new MenuInfo();
		contact.setId(2);
		contact.setIdentifier("contacts");
		contact.setName(mMenuNames[1]);
		contact.setOrder(2);
		menuList.add(contact);

		MenuInfo appCenter = new MenuInfo();
		appCenter.setId(3);
		appCenter.setIdentifier("apps");
		appCenter.setName(mMenuNames[2]);
		appCenter.setOrder(3);
		menuList.add(appCenter);

		MenuInfo circle = new MenuInfo();
		circle.setId(4);
		circle.setIdentifier("circles");
		circle.setName(mMenuNames[3]);
		circle.setOrder(4);
		menuList.add(circle);

		return menuList;
	}

	public BaseFragment getFragment(String tabId) {
		for(MenuTabItem menuTabItem : menuTabItemList){
			String id = menuTabItem.getIdentifier();
			if(tabId.equals(id)){
				return menuTabItem.getFragment();
			}
		}
		
		return null;
	}
}

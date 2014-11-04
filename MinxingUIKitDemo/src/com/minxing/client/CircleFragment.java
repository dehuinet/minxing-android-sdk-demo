package com.minxing.client;

import android.app.ActionBar.OnNavigationListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.minxing.uikit.circle.CircleView;
import com.minxing.uikit.circle.CircleView.CircleEventListener;

public class CircleFragment extends BaseFragment{
	private String[] mMenuNames;
	private CircleView circleView;
	private OnNavigationListener onNavigationListener;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mMenuNames = getResources().getStringArray(R.array.home_menu_names);
		setHasOptionsMenu(true);
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		circleView = new CircleView(getActivity());
		circleView.setCircleEventListener(new CircleEventListener() {
			
			@Override
			public void startActivityForResult(Intent intent, int newMessageRequest) {
				getActivity().startActivityForResult(intent, newMessageRequest);
			}
			
			@Override
			public void onBack() {
				getActivity().finish();
				
			}
		});
		return circleView;
		
	}
	
	@Override
	public void onResume() {
		super.onResume();
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		circleView.onActivityResult(requestCode, resultCode, data);
	}

	public void updateGroup(int groupID) {
		circleView.updateGroup(groupID);
		
	}


}

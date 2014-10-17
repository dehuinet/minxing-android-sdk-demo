package com.minxing.client;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class ConsumerFragment extends BaseFragment{
	private String[] mMenuNames;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mMenuNames = getResources().getStringArray(R.array.home_menu_names);
		RelativeLayout layout = (RelativeLayout) LayoutInflater.from(getActivity()).inflate(R.layout.fragment_consumer, null);
		ImageButton backButton =  (ImageButton) layout.findViewById(R.id.title_left_back_button);
		backButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				getActivity().finish();
				
			}
		});
		TextView tittle = (TextView) layout.findViewById(R.id.title);
		tittle.setText(mMenuNames[2]);
		return   layout;
		
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		
	}
}

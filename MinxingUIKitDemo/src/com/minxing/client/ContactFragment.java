package com.minxing.client;

import com.minxing.uikit.contact.ContactView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;




public class ContactFragment extends BaseFragment{
	private ContactView contactView;
	private String[] mMenuNames;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mMenuNames = getResources().getStringArray(R.array.home_menu_names);
		setHasOptionsMenu(true);
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		contactView = new ContactView(getActivity());
		contactView.setIntent(getActivity().getIntent());
		/*contactView.setConversationEventListener(new ConversationEventListener() {
			
			@Override
			public void startActivityForResult(Intent intent, int newMessageRequest) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onBack() {
				getActivity().finish();
				
			}
		});*/
		return contactView;
		
	}
	@Override
	public void onResume() {
		super.onResume();
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
//		contactView.onActivityResult(requestCode, resultCode, data);
		
	}
	
	
}

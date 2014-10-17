package com.minxing.client;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.minxing.uikit.conversation.ConversationListView;
import com.minxing.uikit.conversation.ConversationListView.ConversationEventListener;

public class ChatFragment extends BaseFragment{
	private ConversationListView conversationListView;
	private String[] mMenuNames;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mMenuNames = getResources().getStringArray(R.array.home_menu_names);
		setHasOptionsMenu(true);
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		conversationListView = new ConversationListView(getActivity());
		conversationListView.setConversationEventListener(new ConversationEventListener() {
			
			@Override
			public void startActivityForResult(Intent intent, int newMessageRequest) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onBack() {
				getActivity().finish();
				
			}
		});
		return conversationListView;
		
	}
	@Override
	public void onResume() {
		super.onResume();
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		conversationListView.onActivityResult(requestCode, resultCode, data);
		
	}
	
	
}

package com.minxing.api;

import android.content.Context;
import android.content.Intent;

import com.minxing.api.internal.APIConstant;

public class MXEngine {
	
	private static MXEngine instance;

	public static MXEngine getInstance() {
		if (instance == null) {
			instance = new MXEngine();
		}
		return instance;
	}

	public void startPlugin(Context context, String action) {
		Intent intent = new Intent();
		intent.setAction(action);
		try {
			context.startActivity(intent);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void startConversation(Context context, String[] userIds) {
		if (userIds == null) {
			return;
		}
		Intent intent = new Intent();
		intent.setAction(APIConstant.INTENT_ACTION);
		intent.putExtra(APIConstant.IntentType.MESSAGE_TYPE, APIConstant.IntentType.MESSAGE_TYPE_START_CONVERSATION);
		intent.putExtra(APIConstant.IntentValue.CONVERSATION_USERIDS, userIds);
		context.startService(intent);
	}

	public void sendToCircle(Context context, String tittle, String message) {
		Intent intent = new Intent();
		intent.setAction(APIConstant.INTENT_ACTION);
		intent.putExtra(APIConstant.IntentType.MESSAGE_TYPE, APIConstant.IntentType.MESSAGE_TYPE_SEND_CIRCEL);
		intent.putExtra(APIConstant.IntentValue.CIRCEL_TITTLE, tittle);
		intent.putExtra(APIConstant.IntentValue.CIRCEL_CONTENT, message);
		context.startService(intent);
	}

	public void viewMemberDetail(Context context, String memberId) {
		if (memberId == null) {
			return;
		}
		Intent intent = new Intent();
		intent.setAction(APIConstant.INTENT_ACTION);
		intent.putExtra(APIConstant.IntentType.MESSAGE_TYPE, APIConstant.IntentType.MESSAGE_TYPE_VIEW_MEMBER_DETAIL);
		intent.putExtra(APIConstant.IntentValue.VIEW_MEMBER_DETAIL_ID, memberId);
		context.startService(intent);
	}

}

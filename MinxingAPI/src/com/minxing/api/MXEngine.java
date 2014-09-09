package com.minxing.api;

import android.content.Context;
import android.content.Intent;

import com.minxing.api.internal.APIConstant;

public class MXEngine {

	private static MXEngine instance;

	private MXEngine() {
	}

	public static MXEngine getInstance() {
		if (instance == null) {
			instance = new MXEngine();
		}
		return instance;
	}

	public void chat(Context context, String[] loginNames) {
		if (loginNames == null) {
			return;
		}
		Intent intent = new Intent();
		intent.setAction(APIConstant.INTENT_ACTION);
		intent.putExtra(APIConstant.IntentType.OPERATION, APIConstant.IntentType.OPERATION_TYPE_CHAT);
		intent.putExtra(APIConstant.IntentValue.CHAT_USERIDS, loginNames);
		context.startService(intent);
	}

	public void personInfo(Context context, String personID) {
		if (personID == null) {
			return;
		}
		Intent intent = new Intent();
		intent.setAction(APIConstant.INTENT_ACTION);
		intent.putExtra(APIConstant.IntentType.OPERATION, APIConstant.IntentType.OPERATION_TYPE_PERSON_INFO);
		intent.putExtra(APIConstant.IntentValue.PERSON_ID, personID);
		context.startService(intent);
	}

	public void share(Context context, String pageTitle, String... shareParam) {
		if (shareParam != null) {
			Intent intent = new Intent();
			intent.setAction(APIConstant.INTENT_ACTION);
			intent.putExtra(APIConstant.IntentType.OPERATION, APIConstant.IntentType.OPERATION_TYPE_SHARE);
			intent.putExtra(APIConstant.IntentValue.SHARE_PAGE_TITLE, pageTitle);
			switch (shareParam.length) {
			case 2:
				if (APIConstant.ShareType.SHARE_TYPE_TEXT.equals(shareParam[0])) {
					intent.putExtra(APIConstant.IntentValue.SHARE_TEXT_CONTENT, shareParam[1]);
				} else if (APIConstant.ShareType.SHARE_TYPE_IMAGE.equals(shareParam[0])) {
					intent.putExtra(APIConstant.IntentValue.SHARE_IMAGE_DATA, shareParam[1]);
				}
				intent.putExtra(APIConstant.IntentValue.SHARE_TYPE, shareParam[0]);
				break;

			case 5:
				if (APIConstant.ShareType.SHARE_TYPE_GRAPH.equals(shareParam[0])) {
					intent.putExtra(APIConstant.IntentValue.SHARE_GRAPH_TITLE, shareParam[1]);
					intent.putExtra(APIConstant.IntentValue.SHARE_GRAPH_CONTENT, shareParam[2]);
					intent.putExtra(APIConstant.IntentValue.SHARE_GRAPH_THUMBDATA, shareParam[3]);
					intent.putExtra(APIConstant.IntentValue.SHARE_GRAPH_URL, shareParam[4]);
				}
				intent.putExtra(APIConstant.IntentValue.SHARE_TYPE, shareParam[0]);
				break;
			}
			context.startService(intent);
		}
	}

	public void selectUser(Context context, boolean isMult) {
		Intent intent = new Intent();
		intent.setAction(APIConstant.INTENT_ACTION);
		if (isMult) {
			intent.putExtra(APIConstant.IntentType.OPERATION, APIConstant.IntentType.OPERATION_TYPE_SELECT_USERS);
		} else {
			intent.putExtra(APIConstant.IntentType.OPERATION, APIConstant.IntentType.OPERATION_TYPE_SELECT_USER);
		}
		context.startService(intent);
	}
}

package com.minxing.api;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;

import com.minxing.api.aidl.MXInterface;
import com.minxing.api.internal.APIConstant;

public class MXEngine {

	private static MXEngine instance;
	private MXInterface mInterfaceService;

	private MXEngine() {
	}

	public static MXEngine getInstance() {
		if (instance == null) {
			instance = new MXEngine();
		}
		return instance;
	}

	public void init(Context context) {
		if (mInterfaceService == null) {
			Intent intent = new Intent(APIConstant.INTERFACE_SERVICE_ACTION);
			context.bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
		}
	}

	private ServiceConnection mConnection = new ServiceConnection() {
		public void onServiceConnected(ComponentName className, IBinder service) {
			mInterfaceService = MXInterface.Stub.asInterface(service);
		}

		public void onServiceDisconnected(ComponentName className) {
			mInterfaceService = null;
		}
	};

	public void chat(String loginNames) {
		if (loginNames == null || "".equals(loginNames)) {
			return;
		}

		if (mInterfaceService != null) {
			try {
				// mInterfaceService.registerCallback(new
				// MXInterfaceCallback.Stub() {
				//
				// @Override
				// public void sendResult(String result) throws RemoteException
				// {
				// callback.sendResult(result);
				// mInterfaceService.unregisterCallback(this);
				// }
				// });
				mInterfaceService.chat(loginNames);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}

	public void viewPersonInfo(String loginName) {
		if (loginName == null || "".equals(loginName)) {
			return;
		}
		if (mInterfaceService != null) {
			try {
				mInterfaceService.viewPersonInfo(loginName);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}

	public String currentUser() {
		if (mInterfaceService != null) {
			try {
				return mInterfaceService.currentUser();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public void share(Context context, String pageTitle, String... shareParam) {
		// if (shareParam != null) {
		// Intent intent = new Intent();
		// intent.setAction(APIConstant.INTENT_ACTION);
		// intent.putExtra(APIConstant.IntentType.OPERATION,
		// APIConstant.IntentType.OPERATION_TYPE_SHARE);
		// intent.putExtra(APIConstant.IntentValue.SHARE_PAGE_TITLE, pageTitle);
		// switch (shareParam.length) {
		// case 2:
		// if (APIConstant.ShareType.SHARE_TYPE_TEXT.equals(shareParam[0])) {
		// intent.putExtra(APIConstant.IntentValue.SHARE_TEXT_CONTENT,
		// shareParam[1]);
		// } else if
		// (APIConstant.ShareType.SHARE_TYPE_IMAGE.equals(shareParam[0])) {
		// intent.putExtra(APIConstant.IntentValue.SHARE_IMAGE_DATA,
		// shareParam[1]);
		// }
		// intent.putExtra(APIConstant.IntentValue.SHARE_TYPE, shareParam[0]);
		// break;
		//
		// case 5:
		// if (APIConstant.ShareType.SHARE_TYPE_GRAPH.equals(shareParam[0])) {
		// intent.putExtra(APIConstant.IntentValue.SHARE_GRAPH_TITLE,
		// shareParam[1]);
		// intent.putExtra(APIConstant.IntentValue.SHARE_GRAPH_CONTENT,
		// shareParam[2]);
		// intent.putExtra(APIConstant.IntentValue.SHARE_GRAPH_THUMBDATA,
		// shareParam[3]);
		// intent.putExtra(APIConstant.IntentValue.SHARE_GRAPH_URL,
		// shareParam[4]);
		// }
		// intent.putExtra(APIConstant.IntentValue.SHARE_TYPE, shareParam[0]);
		// break;
		// }
		// context.startService(intent);
		// }
	}

	public void selectUser(Context context, boolean isMult) {
		// Intent intent = new Intent();
		// intent.setAction(APIConstant.INTENT_ACTION);
		// if (isMult) {
		// intent.putExtra(APIConstant.IntentType.OPERATION,
		// APIConstant.IntentType.OPERATION_TYPE_SELECT_USERS);
		// } else {
		// intent.putExtra(APIConstant.IntentType.OPERATION,
		// APIConstant.IntentType.OPERATION_TYPE_SELECT_USER);
		// }
		// context.startService(intent);
	}

}

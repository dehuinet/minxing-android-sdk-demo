package com.minxing.api;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;

import com.minxing.api.aidl.MXInterface;
import com.minxing.api.aidl.MXInterfaceCallback;
import com.minxing.api.internal.APIConstant;
import com.minxing.api.internal.MXCallbackHost;

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
		MXCallbackHost.getInstance().init();
	}

	private ServiceConnection mConnection = new ServiceConnection() {
		public void onServiceConnected(ComponentName className, IBinder service) {
			mInterfaceService = MXInterface.Stub.asInterface(service);
		}

		public void onServiceDisconnected(ComponentName className) {
			mInterfaceService = null;
		}
	};

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

	public void chat(String[] loginNames) {
		if (loginNames == null || loginNames.length == 0) {
			return;
		}

		if (mInterfaceService != null) {
			try {
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < loginNames.length; i++) {
					sb.append(loginNames[i]).append(",");
				}
				String resultString = sb.toString();
				if (resultString.indexOf(",") != -1) {
					resultString = resultString.substring(0, resultString.length() - 1);
				}
				mInterfaceService.chat(resultString);
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

	public void personInfo(String loginName, final MXEngineCallback callback) {
		if (loginName == null || "".equals(loginName)) {
			return;
		}

		if (mInterfaceService != null) {
			try {
				String callbackKey = MXCallbackHost.getInstance().putCallback(callback);
				if (callbackKey == null) {
					return;
				}

				mInterfaceService.registerCallback(callbackKey, new MXInterfaceCallback.Stub() {

					@Override
					public void onResult(String result) throws RemoteException {
						callback.onResult(result);
						mInterfaceService.unregisterCallback(this);
					}
				});
				mInterfaceService.personInfo(loginName, callbackKey);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}

	public void selectUser(Context context, boolean isMult, final MXEngineCallback callback) {
		if (mInterfaceService != null) {
			try {
				String callbackKey = MXCallbackHost.getInstance().putCallback(callback);
				if (callbackKey == null) {
					return;
				}

				mInterfaceService.registerCallback(callbackKey, new MXInterfaceCallback.Stub() {

					@Override
					public void onResult(String result) throws RemoteException {
						callback.onResult(result);
						mInterfaceService.unregisterCallback(this);
					}
				});
				Intent intent = new Intent(APIConstant.APP2APP_BLANK_ACTION);
				intent.putExtra(APIConstant.IntentValue.APP2APP_TYPE, APIConstant.APP2APPType.APP2APP_TYPE_SELECT_USER);
				intent.putExtra(APIConstant.IntentValue.CALLBACK_KEY, callbackKey);
				intent.putExtra(APIConstant.IntentValue.SELECT_USER_ISMULTI, isMult);
				context.startActivity(intent);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}

	public void shareToChat(Context context, String shareTitle, String shareUrl, String shareThumb, String shareDescription,
			final MXEngineCallback callback) {
		if (mInterfaceService != null) {
			// try {
			// String callbackKey =
			// MXCallbackHost.getInstance().putCallback(callback);
			// if (callbackKey == null) {
			// return;
			// }
			//
			// mInterfaceService.registerCallback(callbackKey, new
			// MXInterfaceCallback.Stub() {
			//
			// @Override
			// public void onResult(String result) throws RemoteException {
			// callback.onResult(result);
			// mInterfaceService.unregisterCallback(this);
			// }
			// });

			Intent intent = new Intent(APIConstant.APP2APP_BLANK_ACTION);
			intent.putExtra(APIConstant.IntentValue.APP2APP_TYPE, APIConstant.APP2APPType.APP2APP_TYPE_SHARE_CONVERSATION);
			intent.putExtra(APIConstant.IntentValue.SHARE_GRAPH_TITLE, shareTitle);
			intent.putExtra(APIConstant.IntentValue.SHARE_GRAPH_CONTENT, shareDescription);
			intent.putExtra(APIConstant.IntentValue.SHARE_GRAPH_THUMBDATA, shareThumb);
			intent.putExtra(APIConstant.IntentValue.SHARE_GRAPH_URL, shareUrl);
			// intent.putExtra(APIConstant.IntentValue.CALLBACK_KEY,
			// callbackKey);
			//
			// switch (shareParam.length) {
			// case 2:
			// if (APIConstant.ShareType.SHARE_TYPE_TEXT.equals(shareParam[0]))
			// {
			// intent.putExtra(APIConstant.IntentValue.SHARE_TEXT_CONTENT,
			// shareParam[1]);
			// } else if
			// (APIConstant.ShareType.SHARE_TYPE_IMAGE.equals(shareParam[0])) {
			// intent.putExtra(APIConstant.IntentValue.SHARE_IMAGE_DATA,
			// shareParam[1]);
			// }
			// intent.putExtra(APIConstant.IntentValue.SHARE_TYPE,
			// shareParam[0]);
			// break;
			//
			// case 5:
			// if (APIConstant.ShareType.SHARE_TYPE_GRAPH.equals(shareParam[0]))
			// {
			// intent.putExtra(APIConstant.IntentValue.SHARE_GRAPH_TITLE,
			// shareParam[1]);
			// intent.putExtra(APIConstant.IntentValue.SHARE_GRAPH_CONTENT,
			// shareParam[2]);
			// intent.putExtra(APIConstant.IntentValue.SHARE_GRAPH_THUMBDATA,
			// shareParam[3]);
			// intent.putExtra(APIConstant.IntentValue.SHARE_GRAPH_URL,
			// shareParam[4]);
			// }
			// intent.putExtra(APIConstant.IntentValue.SHARE_TYPE,
			// shareParam[0]);
			// break;
			// }
			context.startActivity(intent);
			// } catch (RemoteException e) {
			// e.printStackTrace();
			// }
		}
	}

	public void shareToCircle(Context context, String shareTitle, String shareUrl, String shareThumb, String shareDescription,
			final MXEngineCallback callback) {
		if (mInterfaceService != null) {
			// try {
			// String callbackKey =
			// MXCallbackHost.getInstance().putCallback(callback);
			// if (callbackKey == null) {
			// return;
			// }
			//
			// mInterfaceService.registerCallback(callbackKey, new
			// MXInterfaceCallback.Stub() {
			//
			// @Override
			// public void onResult(String result) throws RemoteException {
			// callback.onResult(result);
			// mInterfaceService.unregisterCallback(this);
			// }
			// });

			Intent intent = new Intent(APIConstant.APP2APP_BLANK_ACTION);
			intent.putExtra(APIConstant.IntentValue.APP2APP_TYPE, APIConstant.APP2APPType.APP2APP_TYPE_SHARE_CIRCLE);
			intent.putExtra(APIConstant.IntentValue.SHARE_GRAPH_TITLE, shareTitle);
			intent.putExtra(APIConstant.IntentValue.SHARE_GRAPH_CONTENT, shareDescription);
			intent.putExtra(APIConstant.IntentValue.SHARE_GRAPH_THUMBDATA, shareThumb);
			intent.putExtra(APIConstant.IntentValue.SHARE_GRAPH_URL, shareUrl);
			// intent.putExtra(APIConstant.IntentValue.CALLBACK_KEY,
			// callbackKey);

			// switch (shareParam.length) {
			// case 2:
			// if (APIConstant.ShareType.SHARE_TYPE_TEXT.equals(shareParam[0]))
			// {
			// intent.putExtra(APIConstant.IntentValue.SHARE_TEXT_CONTENT,
			// shareParam[1]);
			// } else if
			// (APIConstant.ShareType.SHARE_TYPE_IMAGE.equals(shareParam[0])) {
			// intent.putExtra(APIConstant.IntentValue.SHARE_IMAGE_DATA,
			// shareParam[1]);
			// }
			// intent.putExtra(APIConstant.IntentValue.SHARE_TYPE,
			// shareParam[0]);
			// break;
			//
			// case 5:
			// if (APIConstant.ShareType.SHARE_TYPE_GRAPH.equals(shareParam[0]))
			// {
			// intent.putExtra(APIConstant.IntentValue.SHARE_GRAPH_TITLE,
			// shareParam[1]);
			// intent.putExtra(APIConstant.IntentValue.SHARE_GRAPH_CONTENT,
			// shareParam[2]);
			// intent.putExtra(APIConstant.IntentValue.SHARE_GRAPH_THUMBDATA,
			// shareParam[3]);
			// intent.putExtra(APIConstant.IntentValue.SHARE_GRAPH_URL,
			// shareParam[4]);
			// }
			// intent.putExtra(APIConstant.IntentValue.SHARE_TYPE,
			// shareParam[0]);
			// break;
			// }
			context.startActivity(intent);
			// } catch (RemoteException e) {
			// e.printStackTrace();
			// }
		}
	}
}

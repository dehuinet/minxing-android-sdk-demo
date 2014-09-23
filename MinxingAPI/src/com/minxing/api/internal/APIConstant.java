package com.minxing.api.internal;

public class APIConstant {
	public static final String INTERFACE_SERVICE_ACTION = "com.minxing.plugin.interface.service";
	public static final String APP2APP_BLANK_ACTION = "com.minxing.mxinterface.app2app.blank";
	public static final String APP2APP_RESULT_BROADCAST = "com.minxing.mxinterface.service.result";
	

	public static class APP2APPType {
		public static final int APP2APP_TYPE_SELECT_USER = 100;
		public static final int APP2APP_TYPE_SHARE_CONVERSATION = 101;
		public static final int APP2APP_TYPE_SHARE_CIRCLE = 102;
	}

	public static class IntentValue {
		public static final String APP2APP_TYPE = "app2appType";
		public static final String CALLBACK_KEY = "callbackKey";
		public static final String SELECT_USER_ISMULTI = "mxinterface_selectuser";
		public static final String SHARE_PAGE_TITLE = "SHARE_PAGE_TITLE";
		public static final String SHARE_TYPE = "SHARE_TYPE";
		public static final String SHARE_TEXT_CONTENT = "SHARE_TEXT_CONTENT";
		public static final String SHARE_IMAGE_DATA = "SHARE_IMAGE_DATA";
		public static final String SHARE_GRAPH_TITLE = "SHARE_GRAPH_TITLE";
		public static final String SHARE_GRAPH_CONTENT = "SHARE_GRAPH_CONTENT";
		public static final String SHARE_GRAPH_THUMBDATA = "SHARE_GRAPH_THUNBDATA";
		public static final String SHARE_GRAPH_URL = "SHARE_GRAPH_URL";
	}

//	public static class ShareType {
//		public static final String SHARE_TYPE_TEXT = "1";
//		public static final String SHARE_TYPE_IMAGE = "2";
//		public static final String SHARE_TYPE_GRAPH = "3";
//	}
}

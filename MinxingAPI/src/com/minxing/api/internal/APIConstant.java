package com.minxing.api.internal;

public class APIConstant {
	public static final String INTENT_ACTION = "com.minxing.plugin";

	public static class IntentType {
		public static final String OPERATION = "OPERATION";
		public static final int OPERATION_TYPE_CHAT = 100;
		public static final int OPERATION_TYPE_PERSON_INFO = 101;
		public static final int OPERATION_TYPE_SHARE = 102;
		public static final int OPERATION_TYPE_CLOSE = 103;
		public static final int OPERATION_TYPE_SELECT_USER = 104;
		public static final int OPERATION_TYPE_SELECT_USERS = 105;
		public static final int OPERATION_TYPE_LAUNCH_APP = 106;
	}

	public static class IntentValue {
		public static final String CHAT_USERIDS = "CHAT_USERIDS";
		public static final String PERSON_ID = "PERSON_ID";
		public static final String SHARE_PAGE_TITLE = "SHARE_PAGE_TITLE";
		public static final String SHARE_TYPE = "SHARE_TYPE";
		public static final String SHARE_TEXT_CONTENT = "SHARE_TEXT_CONTENT";
		public static final String SHARE_IMAGE_DATA = "SHARE_IMAGE_DATA";
		public static final String SHARE_GRAPH_TITLE = "SHARE_GRAPH_TITLE";
		public static final String SHARE_GRAPH_CONTENT = "SHARE_GRAPH_CONTENT";
		public static final String SHARE_GRAPH_THUMBDATA = "SHARE_GRAPH_THUNBDATA";
		public static final String SHARE_GRAPH_URL = "SHARE_GRAPH_URL";
	}

	public static class ShareType {
		public static final String SHARE_TYPE_TEXT = "1";
		public static final String SHARE_TYPE_IMAGE = "2";
		public static final String SHARE_TYPE_GRAPH = "3";
	}
}

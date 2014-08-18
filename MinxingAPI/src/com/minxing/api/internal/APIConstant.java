package com.minxing.api.internal;

public class APIConstant {
	public static final String  INTENT_ACTION = "com.minxing.plugin";
	public static class IntentType {
		public static final String MESSAGE_TYPE = "MESSAGE_TYPE";
		public static final int MESSAGE_TYPE_SEND_CIRCEL = 100;
		public static final int MESSAGE_TYPE_START_CONVERSATION = 101;
		public static final int MESSAGE_TYPE_VIEW_MEMBER_DETAIL = 102;
		public static final int MESSAGE_TYPE_LOGOUT = 200;
	}
	
	public static class IntentValue {
		public static final String CONVERSATION_USERIDS = "CONVERSATION_USERIDS";
		public static final String CIRCEL_TITTLE = "CIRCEL_TITTLE";
		public static final String CIRCEL_CONTENT = "CIRCEL_CONTENT";
		public static final String VIEW_MEMBER_DETAIL_ID = "VIEW_MEMBER_DETAIL_ID";
		
	}

}

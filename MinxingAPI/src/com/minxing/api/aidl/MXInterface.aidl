package com.minxing.api.aidl;

import com.minxing.api.aidl.MXInterfaceCallback;   

interface MXInterface{
	String currentUser();
	void chat(String loginNames);
	void viewPersonInfo(String loginName);
	void personInfo(String loginName, String callbackKey);
	void registerCallback(String callbackKey, MXInterfaceCallback cb);
    void unregisterCallback(MXInterfaceCallback cb);
}
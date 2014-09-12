package com.minxing.api.aidl;

import com.minxing.api.aidl.MXInterfaceCallback;   

interface MXInterface{
	String currentUser();
	void chat(String loginNames);
	void viewPersonInfo(String personID);
	void registerCallback(MXInterfaceCallback cb);
    void unregisterCallback(MXInterfaceCallback cb);
}
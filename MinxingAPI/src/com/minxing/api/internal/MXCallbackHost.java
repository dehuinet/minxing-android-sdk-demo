package com.minxing.api.internal;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.minxing.api.MXEngineCallback;

public class MXCallbackHost {

	private static MXCallbackHost instance;
	private Map<String, MXEngineCallback> mCallbacks = null;

	private MXCallbackHost() {
	}

	public static MXCallbackHost getInstance() {
		if (instance == null) {
			instance = new MXCallbackHost();
		}
		return instance;
	}

	public void init() {
		if (mCallbacks != null) {
			mCallbacks.clear();
			mCallbacks = null;
		}
		mCallbacks = new HashMap<String, MXEngineCallback>();
	}

	public String putCallback(MXEngineCallback callback) {
		if (mCallbacks != null && callback != null) {
			String callbackKey = generateCallbackKey();
			mCallbacks.put(callbackKey, callback);
			return callbackKey;
		}
		return null;
	}

	public MXEngineCallback popCallback(String key) {
		if (mCallbacks != null) {
			return mCallbacks.remove(key);
		}
		return null;
	}

	private String generateCallbackKey() {
		String nonce = String.valueOf(new Random().nextInt(999999));
		return nonce + System.currentTimeMillis();
	}
}

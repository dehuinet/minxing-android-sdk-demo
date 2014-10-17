package com.minxing.client;

import android.content.Intent;
import android.support.v4.app.Fragment;

public abstract class BaseFragment extends Fragment{
	
	public abstract void onActivityResult(int requestCode, int resultCode, Intent data); 

}

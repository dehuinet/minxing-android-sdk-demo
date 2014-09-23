package com.minxing.demo.widget;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.minxing.api.MXEngine;
import com.minxing.api.MXEngineCallback;
import com.minxing.demo.R;

public class CRMView extends FrameLayout {
	
	private Context context;

	public CRMView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.context = context;
		initView();
	}

	public CRMView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		initView();
	}

	public CRMView(Context context) {
		super(context);
		this.context = context;
		initView();
	}

	int count = 0;

	private void initView() {
		ViewGroup root = (ViewGroup) LayoutInflater.from(getContext()).inflate(R.layout.widget_crm, null);
		LinearLayout mutiConversationBtn = (LinearLayout) root.findViewById(R.id.muti_conversation);
		LinearLayout singleConversationBtn = (LinearLayout) root.findViewById(R.id.single_conversation);
		TextView crmContact1 = (TextView) root.findViewById(R.id.crm_contact_1);
		TextView crmContact2 = (TextView) root.findViewById(R.id.crm_contact_2);
		TextView crmContact3 = (TextView) root.findViewById(R.id.crm_contact_3);
		final TextView callback_result = (TextView) root.findViewById(R.id.callback_result);
		// setupActionBar();

		singleConversationBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// 聊天人员ID数组不包括本人
				String[] persons = new String[] { "58_0006" };
				// 调用接口发起聊天
				MXEngine.getInstance().chat(persons);
				// MXEngine.getInstance().test(new IResultCallback() {
				//
				// @Override
				// public void sendResult(final String result) {
				// ((Activity) getContext()).runOnUiThread(new Runnable() {
				// @Override
				// public void run() {
				// Toast.makeText(getContext(), result + ">>>>>>>>" + count,
				// Toast.LENGTH_LONG).show();
				// count++;
				// }
				// });
				// }
				// });
			}
		});

		mutiConversationBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String[] persons = new String[] { "58_0007", "58_0008" };
				MXEngine.getInstance().chat(persons);
			}
		});

		crmContact1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// 用户ID
				String memberId = "58_0006";
				// 调用API进入查看人员详细信息界面
				MXEngine.getInstance().viewPersonInfo(memberId);
			}
		});
		crmContact2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String memberId = "58_0007";
//				MXEngine.getInstance().viewPersonInfo(memberId);
				
				MXEngine.getInstance().personInfo(memberId, new MXEngineCallback() {
					
					@Override
					public void onResult(final String result) {
						((Activity)context).runOnUiThread(new Runnable() {
							@Override
							public void run() {
								callback_result.setText(result);
							}
						});
					}
				});
			}
		});
		crmContact3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
//				String memberId = "58_0008";
//				MXEngine.getInstance().viewPersonInfo(memberId);
				MXEngine.getInstance().selectUser(context, true, new MXEngineCallback() {
					@Override
					public void onResult(final String result) {
						((Activity)context).runOnUiThread(new Runnable() {
							@Override
							public void run() {
								callback_result.setText(result);
							}
						});
					}
				});
			}
		});
		addView(root);
	}
}

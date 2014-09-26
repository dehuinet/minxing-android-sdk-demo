package com.minxing.demo.widget;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.minxing.api.MXEngine;
import com.minxing.api.MXEngineCallback;
import com.minxing.api.test.R;

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

	private void initView() {
		ViewGroup root = (ViewGroup) LayoutInflater.from(getContext()).inflate(R.layout.widget_crm, null);
		final TextView callback_result = (TextView) root.findViewById(R.id.callback_result);
		Button currentUser = (Button) root.findViewById(R.id.currentUser);
		currentUser.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String result = MXEngine.getInstance().currentUser();
				callback_result.setText(result);
			}
		});

		final EditText view_person_loginname = (EditText) root.findViewById(R.id.view_person_loginname);
		Button start_view_person = (Button) root.findViewById(R.id.start_view_person);
		start_view_person.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String loginName = view_person_loginname.getText().toString().trim();
				if (loginName.length() == 0) {
					Toast.makeText(context, "请输入要查看的人员login name", Toast.LENGTH_SHORT).show();
					return;
				}
				MXEngine.getInstance().viewPersonInfo(loginName);
			}
		});

		final EditText person_loginname = (EditText) root.findViewById(R.id.person_loginname);
		Button start_person = (Button) root.findViewById(R.id.start_person);
		start_person.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String loginName = person_loginname.getText().toString().trim();
				if (loginName.length() == 0) {
					Toast.makeText(context, "请输入要查看的人员login name", Toast.LENGTH_SHORT).show();
					return;
				}
				MXEngine.getInstance().personInfo(loginName, new MXEngineCallback() {

					@Override
					public void onResult(final String result) {
						((Activity) context).runOnUiThread(new Runnable() {
							@Override
							public void run() {
								callback_result.setText(result);
							}
						});
					}
				});
			}
		});

		final EditText conversation_loginname = (EditText) root.findViewById(R.id.conversation_loginname);
		Button start_conversation = (Button) root.findViewById(R.id.start_conversation);
		start_conversation.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String loginName = conversation_loginname.getText().toString().trim();
				if (loginName.length() == 0) {
					Toast.makeText(context, "请输入要启动会话的人员login name", Toast.LENGTH_SHORT).show();
					return;
				}

				String[] loginNames = loginName.split(",");
				MXEngine.getInstance().chat(loginNames);
			}
		});

		Button selectUser = (Button) root.findViewById(R.id.selectUser);
		selectUser.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				MXEngine.getInstance().selectUser(context, false, new MXEngineCallback() {
					@Override
					public void onResult(final String result) {
						((Activity) context).runOnUiThread(new Runnable() {
							@Override
							public void run() {
								callback_result.setText(result);
							}
						});
					}
				});
			}
		});

		Button selectUsers = (Button) root.findViewById(R.id.selectUsers);
		selectUsers.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				MXEngine.getInstance().selectUser(context, true, new MXEngineCallback() {
					@Override
					public void onResult(final String result) {
						((Activity) context).runOnUiThread(new Runnable() {
							@Override
							public void run() {
								callback_result.setText(result);
							}
						});
					}
				});
			}
		});

		Button share_conversation = (Button) root.findViewById(R.id.share_conversation);
		share_conversation.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				MXEngine.getInstance().shareToChat(context, "title", "http://www.163.com", null, "description", null);
			}
		});

		Button share_circle = (Button) root.findViewById(R.id.share_circle);
		share_circle.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				MXEngine.getInstance().shareToCircle(context, "title", "http://www.163.com", null, "description", null);
			}
		});
		addView(root);
	}
}

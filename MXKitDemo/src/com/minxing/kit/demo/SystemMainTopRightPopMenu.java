package com.minxing.kit.demo;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.minxing.kit.MXUIEngine;
import com.minxing.kit.manager.ChatManager;

public class SystemMainTopRightPopMenu extends PopupWindow {

	private Context mContext;
	private View contentView = null;

	private LinearLayout btn_new_conversation;
	private LinearLayout btn_add_contact;
	private LinearLayout btn_scan;
	private ImageView btn_scan_divider;
	private LinearLayout btn_new_circle_message;
	private ChatManager chatManager;

	@SuppressWarnings("deprecation")
	public SystemMainTopRightPopMenu(Context context) {
		super(context);
		mContext = context;
		chatManager = MXUIEngine.getInstance().getChatManager();
		contentView = LayoutInflater.from(mContext).inflate(R.layout.system_top_right_menu, null);

		this.setContentView(contentView);
		this.setWidth(LayoutParams.WRAP_CONTENT);
		this.setHeight(LayoutParams.WRAP_CONTENT);
		this.setFocusable(true);
		this.setOutsideTouchable(true);
		this.setTouchable(true);
		this.setBackgroundDrawable(new BitmapDrawable());

		btn_new_conversation = (LinearLayout) contentView.findViewById(R.id.btn_new_conversation);
		btn_new_conversation.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				chatManager.startNewChat();
				dismiss();
			}
		});

		btn_add_contact = (LinearLayout) contentView.findViewById(R.id.btn_add_contact);
		btn_add_contact.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				chatManager.addContacts();
				dismiss();
			}
		});

		btn_scan = (LinearLayout) contentView.findViewById(R.id.btn_scan);
		btn_scan_divider = (ImageView) contentView.findViewById(R.id.btn_scan_divider);
		btn_scan.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				chatManager.startScan();
				dismiss();
			}
		});

		btn_new_circle_message = (LinearLayout) contentView.findViewById(R.id.btn_new_circle_message);
		btn_new_circle_message.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				chatManager.shareToCircle();
				dismiss();
			}
		});

		btn_scan.setVisibility(View.VISIBLE);
		btn_scan_divider.setVisibility(View.VISIBLE);
	}
}

package com.minxing.demo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.minxing.api.MXEngine;
import com.minxing.demo.R;

public class DemoView extends FrameLayout{

	public DemoView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initView();
	}

	public DemoView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public DemoView(Context context) {
		super(context);
		initView();
	}

		private void initView() {
			ViewGroup root = (ViewGroup) LayoutInflater.from(getContext()).inflate(R.layout.widget_demo, null);
			LinearLayout mutiConversationBtn = (LinearLayout) root.findViewById(R.id.muti_conversation);
			LinearLayout singleConversationBtn = (LinearLayout) root.findViewById(R.id.single_conversation);
			TextView crmContact1 = (TextView) root.findViewById(R.id.crm_contact_1);
			TextView crmContact2 = (TextView) root.findViewById(R.id.crm_contact_2);
			TextView crmContact3 = (TextView) root.findViewById(R.id.crm_contact_3);
			Button forwardBtn  = (Button) root.findViewById(R.id.demo_forward);
			
//			setupActionBar();

			singleConversationBtn.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					//聊天人员ID数组不包括本人
					String persons = "p_100";
					//调用接口发起聊天
					MXEngine.getInstance().chat(persons);
				}
			});

			mutiConversationBtn.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					String persons = "p_101,p_102";
					MXEngine.getInstance().chat(persons);
				}
			});

			crmContact1.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// 用户ID
					String memberId = "p_100";
					// 调用API进入查看人员详细信息界面
					MXEngine.getInstance().viewPersonInfo(memberId);
				}
			});
			crmContact2.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					String memberId = "p_101";
					MXEngine.getInstance().viewPersonInfo(memberId);
				}
			});
			crmContact3.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					String memberId = "p_102";
					MXEngine.getInstance().viewPersonInfo(memberId);
				}
			});
			
			forwardBtn.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					forwardToOthers();
				}
			});
			
			addView(root);
		}

		
		private void forwardToOthers() {
			//转发到工作圈,参数为标题和默认内容
			MXEngine.getInstance().share(getContext(), "分享", "测试转发到工作圈");

		}
}

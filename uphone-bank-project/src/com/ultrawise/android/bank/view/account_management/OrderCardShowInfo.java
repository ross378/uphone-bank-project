package com.ultrawise.android.bank.view.account_management;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ultrawise.android.bank.consum_webservices.AccManaConWebservices;
import com.ultrawise.android.bank.view.ABankMain;
import com.ultrawise.android.bank.view.FinancialConsultation;
import com.ultrawise.android.bank.view.transfer.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class OrderCardShowInfo extends Activity {

	private Intent intent;
	private String strAccountTypeValue;
	private String strAccountValue;
	private String strChangeReason;
	private String strNet;
	private Button btnOrder;
	protected boolean flag;
	private ListView lvContent;
	private String strAccNickName;
	private String strAddress;
	private String strCost;
	private TextView tvClassFirst;
	private TextView tvClassSecond;
	private TextView tvClassThrid;
	private ImageView btnReturn;
	private GestureDetector mGestureDetector;
	private ImageView btnMain;
	private ImageView btnHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);// 去标题栏
		this.setContentView(R.layout.account_order_card_info);
		intent = OrderCardShowInfo.this.getIntent();
		if (intent != null) {
			// 从预约换卡页面传来的数据中获取账户类型和账户号
			strAccountTypeValue = intent
					.getStringExtra(OrderCardSelect.ACCOUNT_TYPE);
			strAccountValue = intent.getStringExtra(OrderCardSelect.ACCOUNT);
			strChangeReason = intent
					.getStringExtra(OrderCardSelect.CHANGE_REASON);
			strNet = intent.getStringExtra(OrderCardSelect.NET);
			if (strAccountTypeValue != null && strAccountValue != null
					&& strChangeReason != null && strNet != null) {

			} else {
				// 进错页面了吧你
			}
		} else {
			// 错误的进入此界面
		}

		List<String> lstOut = new ArrayList<String>();
		lstOut.add(strAccountValue);
		strAccNickName = AccManaConWebservices
				.connectHttp(this, "0108", lstOut).get(0);
		lstOut.clear();
		lstOut.add(strNet);
		strAddress = AccManaConWebservices.connectHttp(this, "0111", lstOut)
				.get(0);
		strCost = AccManaConWebservices.connectHttp(this, "0109", null).get(0);
		// 显示文本
		lvContent = (ListView) this.findViewById(R.id.accOrderCardInfo_lv);
		// 生成内容
		ArrayList<HashMap<String, Object>> alContent = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> item01 = new HashMap<String, Object>();
		HashMap<String, Object> item02 = new HashMap<String, Object>();
		HashMap<String, Object> item03 = new HashMap<String, Object>();
		HashMap<String, Object> item04 = new HashMap<String, Object>();
		HashMap<String, Object> item05 = new HashMap<String, Object>();
		HashMap<String, Object> item06 = new HashMap<String, Object>();
		HashMap<String, Object> item07 = new HashMap<String, Object>();
		item01.put("name", "预约换卡的账户：");
		item01.put("content", strAccountValue);
		item02.put("name", "账户别名：");
		item02.put("content", strAccNickName);
		item03.put("name", "更换原因：");
		item03.put("content", strChangeReason);
		item04.put("name", "领卡网点：");
		item04.put("content", strNet);
		item05.put("name", "网点地址：");
		item05.put("content", strAddress);
		item06.put("name", "工本费用：");
		item06.put("content", strCost);
		alContent.add(item01);
		alContent.add(item02);
		alContent.add(item03);
		alContent.add(item04);
		alContent.add(item05);
		alContent.add(item06);
		alContent.add(item07);
		// 适配器
		SimpleAdapter lvAdapter = new SimpleAdapter(this, alContent,
				R.layout.account_order_card_info_adapter, new String[] {
						"name", "content" }, new int[] {
						R.id.accOrderCardInfo_tvName,
						R.id.accOrderCardInfo_tvInfo });
		lvContent.setAdapter(lvAdapter);
		lvContent.setClickable(false);

		btnOrder = (Button) this.findViewById(R.id.accOrderCardInfo_btnOrder);
		btnOrder.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO 发送服务器，确定是否能连上
				List<String> lstOut = new ArrayList<String>();
				lstOut.add(strAccountValue);
				List<String> lstIn = AccManaConWebservices.connectHttp(
						OrderCardShowInfo.this, "0112", lstOut);
				if (lstIn.get(0).equals("true"))
					flag = true;
				else
					flag = false;
				// 弹出对话框
				if (flag == true) {
					new AlertDialog.Builder(OrderCardShowInfo.this)
							.setMessage(R.string.accOrder_OrderSucess)
							.setPositiveButton("确认",
									new DialogInterface.OnClickListener() {
										public void onClick(
												DialogInterface dialog,
												int which) {
											// TODO Auto-generated
											dialog.dismiss();
											Toast.makeText(
													OrderCardShowInfo.this,
													"操作完成", Toast.LENGTH_SHORT)
													.show();
											intent = new Intent();
											intent.setClass(
													OrderCardShowInfo.this,
													AccountManagementList.class);
											OrderCardShowInfo.this
													.startActivity(intent);
										}
									})
							.setNegativeButton("取消",
									new DialogInterface.OnClickListener() {

										public void onClick(
												DialogInterface dialog,
												int which) {
											// TODO Auto-generated method stub
											dialog.dismiss();
										}
									}).show();
				} else {
					// 如果出现一些莫名其妙错误则报错
					Toast.makeText(OrderCardShowInfo.this, "连接服务器失败，请检查",
							Toast.LENGTH_SHORT).show();
				}

			}

		});

		// 向右滑动触发后退
		mGestureDetector = new GestureDetector(this,
				new GestureDetector.SimpleOnGestureListener() {
					@Override
					public boolean onScroll(MotionEvent e1, MotionEvent e2,
							float distanceX, float distanceY) {
						// TODO Auto-generated method stub
						if (distanceY == 0 && distanceX < 0)
							onBackPressed();

						return super.onScroll(e1, e2, distanceX, distanceY);
					}
				});

		// 设置层级关系
		tvClassFirst = (TextView) this.findViewById(R.id.class_first);
		tvClassFirst.setText("首页>");
		tvClassFirst.setVisibility(View.VISIBLE);
		tvClassFirst.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				intent = new Intent();
				intent.setClass(OrderCardShowInfo.this, ABankMain.class);
				OrderCardShowInfo.this.startActivity(intent);
			}
		});

		tvClassSecond = (TextView) this.findViewById(R.id.class_second);
		tvClassSecond.setText("账户管理>");
		tvClassSecond.setVisibility(View.VISIBLE);
		tvClassSecond.setClickable(true);
		tvClassSecond.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				intent = new Intent();
				intent.setClass(OrderCardShowInfo.this,
						AccountManagementList.class);
				OrderCardShowInfo.this.startActivity(intent);
			}
		});

		tvClassThrid = (TextView) this.findViewById(R.id.class_third);
		tvClassThrid.setText("预约换卡");
		tvClassThrid.setVisibility(View.VISIBLE);

		// 返回键设定
		btnReturn = (ImageView) this.findViewById(R.id.returnToPre);
		btnReturn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				onBackPressed();
				finish();
			}
		});
		// 底部按钮设置
		btnMain = (ImageView) this.findViewById(R.id.btnMain);
		btnMain.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				intent = new Intent();
				intent.setClass(OrderCardShowInfo.this, ABankMain.class);
				OrderCardShowInfo.this.startActivity(intent);
			}
		});

		btnHelper = (ImageView) this.findViewById(R.id.btnHelper);
		btnHelper.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				intent = new Intent();
				intent.setClass(OrderCardShowInfo.this,
						FinancialConsultation.class);
				OrderCardShowInfo.this.startActivity(intent);
			}
		});
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		return mGestureDetector.onTouchEvent(event);

	}
}

package com.ultrawise.android.bank.view.account_management;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ultrawise.android.bank.consum_webservices.AccManaConWebservices;
import com.ultrawise.android.bank.view.ABankMain;
import com.ultrawise.android.bank.view.FinancialConsultation;
import com.ultrawise.android.bank.view.transfer.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class OrderShowInfo2 extends Activity {

	private Intent intent;
	private String strAccountValue;
	private Button btnOrder;
	private ListView lvContent;
	private String strAccNickName;
	private String strChangeReason;
	private String strNet;
	private String strAddress;
	private String strCost;
	private GestureDetector mGestureDetector;
	private TextView tvClassFirst;
	private TextView tvClassSecond;
	private TextView tvClassThrid;
	private ImageView btnReturn;
	private ImageView btnMain;
	private ImageView btnHelper;

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		return mGestureDetector.onTouchEvent(event);

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);// 去标题栏
		this.setContentView(R.layout.account_order_card_info);
		intent = OrderShowInfo2.this.getIntent();
		if (intent != null) {
			// 从预约换卡页面传来的数据中获取账户类型和账户号
			strAccountValue = intent.getStringExtra(AccountInfo2.ACCOUNT);
			if (strAccountValue != null) {

			} else {
				// 进错页面了吧你
			}
		} else {
			// 错误的进入此界面
		}
		List<String> lstOut = new ArrayList<String>();
		lstOut.add(strAccountValue);
		List<String> lstIn = AccManaConWebservices.connectHttp(this, "0104",
				lstOut);
		strCost = lstIn.get(0);
		strChangeReason = lstIn.get(1);
		strAccNickName = lstIn.get(2);
		strAddress = lstIn.get(3);
		strNet = lstIn.get(4);
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
		btnOrder.setVisibility(View.GONE);

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
				intent.setClass(OrderShowInfo2.this, ABankMain.class);
				OrderShowInfo2.this.startActivity(intent);
			}
		});

		tvClassSecond = (TextView) this.findViewById(R.id.class_second);
		tvClassSecond.setText("账户管理>");
		tvClassSecond.setVisibility(View.VISIBLE);
		tvClassSecond.setClickable(true);
		tvClassSecond.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				intent = new Intent();
				intent.setClass(OrderShowInfo2.this,
						AccountManagementList.class);
				OrderShowInfo2.this.startActivity(intent);
			}
		});

		tvClassThrid = (TextView) this.findViewById(R.id.class_third);
		tvClassThrid.setText("账户信息");
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
				intent.setClass(OrderShowInfo2.this, ABankMain.class);
				OrderShowInfo2.this.startActivity(intent);
			}
		});

		btnHelper = (ImageView) this.findViewById(R.id.btnHelper);
		btnHelper.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				intent = new Intent();
				intent.setClass(OrderShowInfo2.this,
						FinancialConsultation.class);
				OrderShowInfo2.this.startActivity(intent);
			}
		});
	}

}

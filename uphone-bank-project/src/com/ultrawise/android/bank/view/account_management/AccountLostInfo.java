package com.ultrawise.android.bank.view.account_management;

import java.util.ArrayList;
import java.util.HashMap;

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
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class AccountLostInfo extends Activity {

	private Intent intent;
	private String strAccountTypeValue;
	private String strAccountValue;
	private ListView lvContent;
	private String strAccNickName;
	private String strCost;
	private Button btnLoss;
	protected boolean flag;
	private GestureDetector mGestureDetector;
	private TextView tvClassFirst;
	private TextView tvClassSecond;
	private TextView tvClassThrid;
	private ImageView btnReturn;
	private ImageView btnMain;
	private ImageView btnHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);// 去标题栏
		this.setContentView(R.layout.account_order_card_info);
		
		intent = AccountLostInfo.this.getIntent();
		if (intent != null) {
			// 从账户选择页面传来的数据中获取账户类型和账户号
			strAccountTypeValue = intent
					.getStringExtra(AccountLostSelect.ACCOUNT_TYPE);
			strAccountValue = intent.getStringExtra(AccountLostSelect.ACCOUNT);

			if (strAccountTypeValue != null && strAccountValue != null) {

			} else {
				// 进错页面了吧你
				finish();
			}
		} else {
			// 错误的进入此界面
			finish();
		}
		//从服务器获取数据
		strAccNickName = "我的信用卡";
		strCost = "10元";
		
		// 显示文本
		lvContent = (ListView) this.findViewById(R.id.accOrderCardInfo_lv);
		// 生成内容
		ArrayList<HashMap<String, Object>> alContent = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> item01 = new HashMap<String, Object>();
		HashMap<String, Object> item02 = new HashMap<String, Object>();
		HashMap<String, Object> item03 = new HashMap<String, Object>();
//		HashMap<String, Object> item04 = new HashMap<String, Object>();
//		HashMap<String, Object> item05 = new HashMap<String, Object>();
//		HashMap<String, Object> item06 = new HashMap<String, Object>();
//		HashMap<String, Object> item07 = new HashMap<String, Object>();
		item01.put("name", "挂失的账户：");
		item01.put("content", strAccountValue);
		item02.put("name", "账户别名：");
		item02.put("content", strAccNickName);
		item03.put("name", "挂失原因：");
		item03.put("content", "丢失");
//		item04.put("name", "领卡网点：");
//		item04.put("content", "惠新路");
//		item05.put("name", "网点地址：");
//		item05.put("content", "惠新路8号");
		
//		item06.put("name", "工本费用：");
//		item06.put("content", strCost);
		alContent.add(item01);
		alContent.add(item02);
		alContent.add(item03);
//		alContent.add(item04);
//		alContent.add(item05);
//		alContent.add(item06);
//		alContent.add(item07);
		// 适配器
		SimpleAdapter lvAdapter = new SimpleAdapter(this, alContent,
				R.layout.account_order_card_info_adapter, new String[] {
						"name", "content" }, new int[] {
						R.id.accOrderCardInfo_tvName,
						R.id.accOrderCardInfo_tvInfo });
		lvContent.setAdapter(lvAdapter);
		lvContent.setClickable(false);

		btnLoss = (Button) this.findViewById(R.id.accOrderCardInfo_btnOrder);
		btnLoss.setText("确认挂失");
		btnLoss.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO 发送服务器，确定是否能连上
				
				// 弹出对话框
				if (flag == true) {
					new AlertDialog.Builder(AccountLostInfo.this)
					.setTitle("提示")
							.setMessage("挂失成功，此挂失为口头挂失，请尽快前往银行柜台办理后续手续")
							.setPositiveButton("确认",
									new DialogInterface.OnClickListener() {
										public void onClick(
												DialogInterface dialog,
												int which) {
											// TODO Auto-generated
											dialog.dismiss();
											Toast.makeText(
													AccountLostInfo.this,
													"操作完成", Toast.LENGTH_SHORT)
													.show();
											intent = new Intent();
											intent.setClass(
													AccountLostInfo.this,
													AccountManagementList.class);
											AccountLostInfo.this
													.startActivity(intent);
										}
									})
							.show();
				} else {
					// 如果出现一些莫名其妙错误则报错
					Toast.makeText(
							AccountLostInfo.this,
							"连接服务器失败，请检查", Toast.LENGTH_SHORT)
							.show();
				}

			}

		});
		//向右滑动触发后退
		mGestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener(){
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
				intent=new Intent();
				intent.setClass(AccountLostInfo.this, ABankMain.class);
				AccountLostInfo.this.startActivity(intent);
			}
		});

		tvClassSecond = (TextView) this.findViewById(R.id.class_second);
		tvClassSecond.setText("账户管理>");
		tvClassSecond.setVisibility(View.VISIBLE);
		tvClassSecond.setClickable(true);
		tvClassSecond.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				intent=new Intent();
				intent.setClass(AccountLostInfo.this, AccountManagementList.class);
				AccountLostInfo.this.startActivity(intent);

			}
		});

		tvClassThrid = (TextView) this.findViewById(R.id.class_third);
		tvClassThrid.setText("账户挂失");
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
				intent.setClass(AccountLostInfo.this, ABankMain.class);
				AccountLostInfo.this.startActivity(intent);
			}
		});

		btnHelper = (ImageView) this.findViewById(R.id.btnHelper);
		btnHelper.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				intent = new Intent();
				intent.setClass(AccountLostInfo.this, FinancialConsultation.class);
				AccountLostInfo.this.startActivity(intent);
			}
		});
	}
	
	
	//触摸触发
	@Override
	public boolean onTouchEvent(MotionEvent event) {

		return mGestureDetector.onTouchEvent(event);

	}

}

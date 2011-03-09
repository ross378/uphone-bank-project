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
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class AccountDelInfo extends Activity {

	private Intent intent;
	private String strAccountTypeValue;
	private String strAccountValue;
	private ListView lvContent;
	private Object strAccNickName;
	private Button btnNext;
	protected boolean flag;
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
		// 去标题栏
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.setContentView(R.layout.account_order_card_info);

		((Button) this.findViewById(R.id.accOrderCardInfo_btnOrder))
				.setText("下一步");
		intent = AccountDelInfo.this.getIntent();
		if (intent != null) {
			// 从预约换卡页面传来的数据中获取账户类型和账户号
			strAccountTypeValue = intent
					.getStringExtra(OrderCardSelect.ACCOUNT_TYPE);
			strAccountValue = intent.getStringExtra(OrderCardSelect.ACCOUNT);

			if (strAccountTypeValue != null && strAccountValue != null) {

			} else {
				// 进错页面了吧你
			}
		} else {
			// 错误的进入此界面
		}

		List<String> lstOut = new ArrayList<String>();
		lstOut.add(strAccountValue);
		// 从服务器获取
		strAccNickName = AccManaConWebservices
				.connectHttp(this, "0108", lstOut).get(0);
		// 显示文本
		lvContent = (ListView) this.findViewById(R.id.accOrderCardInfo_lv);
		// 生成内容
		ArrayList<HashMap<String, Object>> alContent = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> item01 = new HashMap<String, Object>();
		HashMap<String, Object> item02 = new HashMap<String, Object>();
		HashMap<String, Object> item03 = new HashMap<String, Object>();
		item01.put("name", "账户：");
		item01.put("content", strAccountValue);
		item02.put("name", "账户别名：");
		item02.put("content", strAccNickName);
		item03.put("name", "账户类型：");
		item03.put("content", strAccountTypeValue);
		alContent.add(item01);
		alContent.add(item02);
		alContent.add(item03);
		// 适配器
		SimpleAdapter lvAdapter = new SimpleAdapter(this, alContent,
				R.layout.account_order_card_info_adapter, new String[] {
						"name", "content" }, new int[] {
						R.id.accOrderCardInfo_tvName,
						R.id.accOrderCardInfo_tvInfo });
		lvContent.setAdapter(lvAdapter);
		lvContent.setClickable(false);

		btnNext = (Button) this.findViewById(R.id.accOrderCardInfo_btnOrder);
		btnNext.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				new AlertDialog.Builder(AccountDelInfo.this)
						.setTitle("提示：")
						.setMessage("账户即将被删除，删除后该账户将不能再通过手机客户端操作！")
						.setPositiveButton("确定",
								new DialogInterface.OnClickListener() {

									public void onClick(DialogInterface dialog,
											int which) {
										// 发送删除指令
										List<String> lstOut = new ArrayList<String>();
										lstOut.add(strAccountValue);
										List<String> lstIn = AccManaConWebservices
												.connectHttp(
														AccountDelInfo.this,
														"0118", lstOut);
										if (lstIn.get(0).equals("true"))
											flag = true;
										else
											flag = false;
										if (flag) {
											dialog.dismiss();
											Toast.makeText(AccountDelInfo.this,
													"删除成功", Toast.LENGTH_SHORT)
													.show();

											intent = new Intent();
											intent.setClass(
													AccountDelInfo.this,
													AccountManagementList.class);
											AccountDelInfo.this
													.startActivity(intent);

											finish();
										} else {
											dialog.dismiss();
											Toast.makeText(AccountDelInfo.this,
													"没有删除成功",
													Toast.LENGTH_SHORT).show();
										}
									}
								})
						.setNegativeButton("取消",
								new DialogInterface.OnClickListener() {

									public void onClick(DialogInterface dialog,
											int which) {
										// TODO Auto-generated method stub
										dialog.dismiss();
									}
								}).show();
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
				intent.setClass(AccountDelInfo.this, ABankMain.class);
				AccountDelInfo.this.startActivity(intent);
			}
		});

		tvClassSecond = (TextView) this.findViewById(R.id.class_second);
		tvClassSecond.setText("账户管理>");
		tvClassSecond.setVisibility(View.VISIBLE);
		tvClassSecond.setClickable(true);
		tvClassSecond.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				intent = new Intent();
				intent.setClass(AccountDelInfo.this,
						AccountManagementList.class);
				AccountDelInfo.this.startActivity(intent);
			}
		});

		tvClassThrid = (TextView) this.findViewById(R.id.class_third);
		tvClassThrid.setText("删除账户");
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
				intent.setClass(AccountDelInfo.this, ABankMain.class);
				AccountDelInfo.this.startActivity(intent);
			}
		});

		btnHelper = (ImageView) this.findViewById(R.id.btnHelper);
		btnHelper.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				intent = new Intent();
				intent.setClass(AccountDelInfo.this,
						FinancialConsultation.class);
				AccountDelInfo.this.startActivity(intent);
			}
		});
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		return mGestureDetector.onTouchEvent(event);

	}

}

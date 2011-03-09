package com.ultrawise.android.bank.view.account_management;

import java.util.ArrayList;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class AccountNickName extends Activity {

	private String strAccNick;
	protected boolean flag = true;
	private String[] array;
	private TextView tvAccNickClick;
	private GestureDetector mGestureDetector;
	private ImageView btnReturn;
	private TextView tvClassFirst;
	protected Intent intent;
	private TextView tvClassSecond;
	private TextView tvClassThrid;
	private ImageView btnMain;
	private ImageView btnHelper;
	// 和服务器连接有关
	private AccManaConWebservices amConWebservice = new AccManaConWebservices();
	private String strAccountTypeValue;
	private String strAccountValue;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题栏
		setContentView(R.layout.account_preferred_select);

		intent = AccountNickName.this.getIntent();
		if (intent != null) {
			// 从账户选择页面传来的数据中获取账户类型和账户号
			strAccountTypeValue = intent
					.getStringExtra(AccountInfoSelect.ACCOUNT_TYPE);
			strAccountValue = intent.getStringExtra(AccountInfoSelect.ACCOUNT);

			if (strAccountTypeValue == null && strAccountValue == null) {
				// 进错了啦你
				finish();
			}
		} else {
			finish();
		}

		((TextView) this.findViewById(R.id.accPre_tvPreAcc))
				.setText("请设置账户的别名");
		((TextView) this.findViewById(R.id.accPre_tvPreAccClick3))
				.setVisibility(View.GONE);
		((Spinner) this.findViewById(R.id.accPre_tvPreAccClick))
				.setVisibility(View.GONE);

		tvAccNickClick = (TextView) this
				.findViewById(R.id.accPre_tvPreAccClick2);
		tvAccNickClick.setVisibility(View.VISIBLE);
		List<String> lstOut = new ArrayList<String>();
		lstOut.add(strAccountValue);
		tvAccNickClick.setText(AccManaConWebservices.connectHttp(this, "0108",
				lstOut).get(0));
		tvAccNickClick.setClickable(true);
		tvAccNickClick.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				// 改别名
				final EditText et = new EditText(AccountNickName.this);
				et.setBackgroundResource(R.drawable.main_et);
				AlertDialog myDialog = new AlertDialog.Builder(
						AccountNickName.this)
						.setTitle("设置别名：")
						.setView(et)
						.setPositiveButton("确定",
								new DialogInterface.OnClickListener() {

									public void onClick(DialogInterface dialog,
											int which) {
										if (et.getText().toString().trim()
												.length() > 0) {
											List<String> lstOut = new ArrayList<String>();
											lstOut.add(strAccountValue);
											lstOut.add(et.getText().toString());
											String isDone = AccManaConWebservices
													.connectHttp(
															AccountNickName.this,
															"0119", lstOut)
													.get(0);
											if (isDone.equals("true"))
												flag = true;
											else
												flag = false;

											if (flag) {
												tvAccNickClick.setText(et
														.getText().toString());
												dialog.dismiss();
												Toast.makeText(
														AccountNickName.this,
														"设置成功",
														Toast.LENGTH_SHORT)
														.show();
											} else {
												dialog.dismiss();
												Toast.makeText(
														AccountNickName.this,
														"没有设置成功",
														Toast.LENGTH_SHORT)
														.show();
											}
										} else {
											dialog.dismiss();
											Toast.makeText(
													AccountNickName.this,
													"请输入别名", Toast.LENGTH_SHORT)
													.show();
										}

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
		// 返回键设定
		btnReturn = (ImageView) this.findViewById(R.id.returnToPre);
		btnReturn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				onBackPressed();
				finish();
			}

		});
		// 设置层级关系
		tvClassFirst = (TextView) this.findViewById(R.id.class_first);
		tvClassFirst.setText("首页>");
		tvClassFirst.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				intent = AccountNickName.this.getIntent();
				intent.setClass(AccountNickName.this, ABankMain.class);
				AccountNickName.this.startActivity(intent);
			}
		});
		tvClassSecond = (TextView) this.findViewById(R.id.class_second);
		tvClassSecond.setText("账户管理>");
		tvClassSecond.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				intent = AccountNickName.this.getIntent();
				intent.setClass(AccountNickName.this,
						AccountManagementList.class);
				AccountNickName.this.startActivity(intent);
			}
		});
		tvClassThrid = (TextView) this.findViewById(R.id.class_third);
		tvClassThrid.setText("账户别名");

		tvClassFirst.setVisibility(View.VISIBLE);
		tvClassSecond.setVisibility(View.VISIBLE);
		tvClassThrid.setVisibility(View.VISIBLE);

		// 设置底部按钮
		btnMain = (ImageView) this.findViewById(R.id.btnMain);
		btnMain.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				intent = new Intent();
				intent.setClass(AccountNickName.this, ABankMain.class);
				AccountNickName.this.startActivity(intent);
			}
		});

		btnHelper = (ImageView) this.findViewById(R.id.btnHelper);
		btnHelper.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				intent = new Intent();
				intent.setClass(AccountNickName.this,
						FinancialConsultation.class);
				AccountNickName.this.startActivity(intent);
			}
		});
	}

	// 触摸触发
	@Override
	public boolean onTouchEvent(MotionEvent event) {

		return mGestureDetector.onTouchEvent(event);

	}

}

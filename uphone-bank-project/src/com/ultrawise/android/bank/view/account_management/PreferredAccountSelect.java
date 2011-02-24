package com.ultrawise.android.bank.view.account_management;

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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 
 * @author hosolo
 * 
 */
public class PreferredAccountSelect extends Activity {

	private String strPreAcc;
	protected boolean flag = true;
	private GestureDetector mGestureDetector;
	private ImageView btnReturn;
	private TextView tvClassFirst;
	private TextView tvClassSecond;
	private TextView tvClassThrid;
	protected Intent intent;
	private ImageView btnMain;
	private ImageView btnHelper;
	private TextView tvPreAccClick;
	private String[] array;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题栏
		setContentView(R.layout.account_preferred_select);

		// 从服务器获取账号
		array = getResources().getStringArray(R.array.accinfo_acc);

		tvPreAccClick = (TextView) this.findViewById(R.id.accPre_tvPreAccClick);
		tvPreAccClick.setText("123****0239");
		tvPreAccClick.setClickable(true);
		tvPreAccClick.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				// 弹出list对话框
				new AlertDialog.Builder(PreferredAccountSelect.this)
						.setTitle("请选择要设置为首选的账户")
						.setItems(R.array.accinfo_acc,
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int which) {
										// TODO 设置当前所选的账户
										strPreAcc = array[which];
										// 弹出对话框
										new AlertDialog.Builder(
												PreferredAccountSelect.this)
												.setTitle("确认对话框")
												.setMessage(array[which])
												.setPositiveButton(
														"确认",
														new DialogInterface.OnClickListener() {
															public void onClick(
																	DialogInterface dialog,
																	int which) {
																// TODO 连接服务器

																if (flag == true) {
																	tvPreAccClick
																			.setText(strPreAcc);
																	Toast.makeText(
																			PreferredAccountSelect.this,
																			"设置首选成功",
																			Toast.LENGTH_SHORT)
																			.show();

																} else {
																	// 如果设置出错
																	Toast.makeText(
																			PreferredAccountSelect.this,
																			"设置首选出错，未成功",
																			Toast.LENGTH_SHORT)
																			.show();
																}
																dialog.dismiss();
																//finish();
															}
														})
												.setNegativeButton(
														"取消",
														new DialogInterface.OnClickListener() {

															public void onClick(
																	DialogInterface dialog,
																	int which) {
																dialog.dismiss();
															}
														}).show();
									}
								})
						.setNegativeButton("取消",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int which) {

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
		tvClassFirst.setText("手机银行>");
		tvClassFirst.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				intent = PreferredAccountSelect.this.getIntent();
				intent.setClass(PreferredAccountSelect.this, ABankMain.class);
				PreferredAccountSelect.this.startActivity(intent);
			}
		});
		tvClassSecond = (TextView) this.findViewById(R.id.class_second);
		tvClassSecond.setText("账户管理>");
		tvClassSecond.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				intent = PreferredAccountSelect.this.getIntent();
				intent.setClass(PreferredAccountSelect.this,
						AccountManagement.class);
				PreferredAccountSelect.this.startActivity(intent);
			}
		});
		tvClassThrid = (TextView) this.findViewById(R.id.class_third);
		tvClassThrid.setText("首选账户");

		tvClassFirst.setVisibility(View.VISIBLE);
		tvClassSecond.setVisibility(View.VISIBLE);
		tvClassThrid.setVisibility(View.VISIBLE);

		// 设置底部按钮
		btnMain = (ImageView) this.findViewById(R.id.btnMain);
		btnMain.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				intent = new Intent();
				intent.setClass(PreferredAccountSelect.this, ABankMain.class);
				PreferredAccountSelect.this.startActivity(intent);
			}
		});

		btnHelper = (ImageView) this.findViewById(R.id.btnHelper);
		btnHelper.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				intent = new Intent();
				intent.setClass(PreferredAccountSelect.this,
						FinancialConsultation.class);
				PreferredAccountSelect.this.startActivity(intent);
			}
		});

	}

	// 触摸触发
	@Override
	public boolean onTouchEvent(MotionEvent event) {

		return mGestureDetector.onTouchEvent(event);

	}

}

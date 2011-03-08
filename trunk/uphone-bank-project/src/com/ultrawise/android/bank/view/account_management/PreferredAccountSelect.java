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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
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
	private Spinner tvPreAccClick;
	private List<String> array;
	private ArrayAdapter<String> adapterType;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题栏
		setContentView(R.layout.account_preferred_select);

		List<String> lstOut = new ArrayList<String>();
		// lstOut.add(UserLogin.userNO);
		// 用户号
		lstOut.add("Sun01");
		tvPreAccClick = (Spinner) this.findViewById(R.id.accPre_tvPreAccClick);
		adapterType = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item,
				AccManaConWebservices.connectHttp(PreferredAccountSelect.this,
						"0116", lstOut));
		// 设置下拉列表的风格
		adapterType
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// 将adapter 添加到spinner中
		tvPreAccClick.setAdapter(adapterType);
		// 添加事件Spinner事件监听
		tvPreAccClick.setOnItemSelectedListener(new OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				List<String> lstOut = new ArrayList<String>();
				// lstOut.add(UserLogin.userNO);
				// 用户号
				lstOut.add("Sun01");
				lstOut.add(tvPreAccClick.getSelectedItem().toString());
				List<String> lstIn = AccManaConWebservices.connectHttp(
						PreferredAccountSelect.this, "0115", lstOut);
				if (lstIn.get(0).equals("true"))
					flag = true;
				else
					flag = false;
				if (flag == true) {
					Toast.makeText(PreferredAccountSelect.this, "设置首选成功",
							Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(PreferredAccountSelect.this, "设置首选不成功",
							Toast.LENGTH_SHORT).show();
				}
			}

			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}

		});
		// 首选
		// AccManaConWebservices.connectHttp(PreferredAccountSelect.this,
		// "0114",
		// lstOut).get(0);

		// tvPreAccClick.setOnClickListener(new OnClickListener() {
		//
		// public void onClick(View v) {
		// // 从服务器获取账号
		// List<String> lstOut = new ArrayList<String>();
		// // lstOut.add(UserLogin.userNO);
		// // 用户号
		// lstOut.add("Sun01");
		// List<String> preAcc = AccManaConWebservices.connectHttp(
		// PreferredAccountSelect.this, "0116", lstOut);
		// array = preAcc;
		// String[] array2 = new String[1];
		// for (int i = 0; i < preAcc.size(); i++) {
		// array2[i] = preAcc.get(i);
		// }
		// // 弹出list对话框
		// new AlertDialog.Builder(PreferredAccountSelect.this)
		// .setTitle("请选择要设置为首选的账户")
		// .setItems(array2,
		// new DialogInterface.OnClickListener() {
		// public void onClick(DialogInterface dialog,
		// int which) {
		// // TODO 设置当前所选的账户
		// strPreAcc = array.get(which);
		// // 弹出对话框
		// new AlertDialog.Builder(
		// PreferredAccountSelect.this)
		// .setTitle("确认对话框")
		// .setMessage(array.get(which))
		// .setPositiveButton(
		// "确认",
		// new DialogInterface.OnClickListener() {
		// public void onClick(
		// DialogInterface dialog,
		// int which) {
		// // TODO 连接服务器
		// List<String> lstOut = new ArrayList<String>();
		// // lstOut.add(UserLogin.userNO);
		// // 用户号
		// lstOut.add("Sun01");
		// lstOut.add(array
		// .get(which));
		// List<String> lstIn = AccManaConWebservices
		// .connectHttp(
		// PreferredAccountSelect.this,
		// "0115",
		// lstOut);
		// if (lstIn
		// .get(0)
		// .equals("true"))
		// flag = true;
		// else
		// flag = false;
		// if (flag == true) {
		//
		// Toast.makeText(
		// PreferredAccountSelect.this,
		// "设置首选成功",
		// Toast.LENGTH_SHORT)
		// .show();
		//
		// } else {
		// // 如果设置出错
		// Toast.makeText(
		// PreferredAccountSelect.this,
		// "设置首选出错，未成功",
		// Toast.LENGTH_SHORT)
		// .show();
		// }
		// dialog.dismiss();
		// // finish();
		// }
		// })
		// .setNegativeButton(
		// "取消",
		// new DialogInterface.OnClickListener() {
		//
		// public void onClick(
		// DialogInterface dialog,
		// int which) {
		// dialog.dismiss();
		// }
		// }).show();
		// }
		// })
		// .setNegativeButton("取消",
		// new DialogInterface.OnClickListener() {
		// public void onClick(DialogInterface dialog,
		// int which) {
		//
		// dialog.dismiss();
		// }
		// }).show();
		//
		// }
		//
		// });

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
						AccountManagementList.class);
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

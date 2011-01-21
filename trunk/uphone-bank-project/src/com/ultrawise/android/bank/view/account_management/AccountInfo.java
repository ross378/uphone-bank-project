package com.ultrawise.android.bank.view.account_management;

import java.util.ArrayList;
import java.util.HashMap;

import com.ultrawise.android.bank.view.ABankMain;
import com.ultrawise.android.bank.view.FinancialConsultation;
import com.ultrawise.android.bank.view.transfer.R;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class AccountInfo extends ListActivity {
	private TextView tvAccInfo;
	private ImageView btnCoustom;
	private TextView tvClassFirst;
	private TextView tvClassSecond;
	private TextView tvClassThrid;
	private ImageView btnReturn;
	private ImageView btnMain;
	private ImageView btnHelper;
	Intent intent;

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		/**
		 * 业务功能
		 */
		/*
		 * tvAccInfo = (TextView) this.findViewById(R.id.tvAccInfo); intent =
		 * AccountInfo.this.getIntent(); //It will be updated account
		 * information if resume this activity if
		 * (intent.getStringExtra("AccInfo") != null) {
		 * tvAccInfo.setText(intent.getStringExtra("AccInfo")); }
		 */
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.account_info);

		intent = AccountInfo.this.getIntent();

		tvAccInfo = (TextView) this.findViewById(R.id.tvAccInfo);
		tvAccInfo.setText("账号：440301198810282152\n" + "别名：我的储蓄卡\n"
				+ "账户类型：活期储蓄一折（卡）\n" + "账户状态：正常\n" + "是否签约：未签约\n"
				+ "开户行：建设银行深圳市梅林支行" + "开户日：2006/07/09");
		tvAccInfo.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);// add bottom
																	// line

		// List View
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> map1 = new HashMap<String, String>();
		map1.put("name", "查询其他账户");
		map1.put("arrow", ">");
		HashMap<String, String> map2 = new HashMap<String, String>();
		map2.put("name", "增加账户");
		map2.put("arrow", ">");
		HashMap<String, String> map3 = new HashMap<String, String>();
		map3.put("name", "删除账户");
		map3.put("arrow", ">");
		list.add(map1);
		list.add(map2);
		list.add(map3);
		SimpleAdapter sa = new SimpleAdapter(this, list,
				R.layout.account_management_textview_list, new String[] {
						"name", "arrow" }, new int[] { R.id.name, R.id.arrow });
		this.setListAdapter(sa);

		// 设置层级关系
		tvClassFirst = (TextView) this.findViewById(R.id.class_first);
		tvClassFirst.setText("手机银行>");
		tvClassFirst.setVisibility(View.VISIBLE);
		tvClassFirst.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				intent.setClass(AccountInfo.this, ABankMain.class);
				AccountInfo.this.startActivity(intent);
			}
		});

		tvClassSecond = (TextView) this.findViewById(R.id.class_second);
		tvClassSecond.setText("账户管理>");
		tvClassSecond.setVisibility(View.VISIBLE);
		tvClassSecond.setClickable(true);
		tvClassSecond.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				intent = AccountInfo.this.getIntent();
				intent.setClass(AccountInfo.this, AccountManagement.class);
				AccountInfo.this.startActivity(intent);

			}
		});

		tvClassThrid = (TextView) this.findViewById(R.id.class_third);
		tvClassThrid.setText("账户信息");
		tvClassThrid.setVisibility(View.VISIBLE);

		// 底部按钮设置
		btnCoustom = (ImageView) this.findViewById(R.id.btnCoustom);
		btnCoustom.setImageResource(R.drawable.cardbg_zhgl_w);
		btnCoustom.setVisibility(View.VISIBLE);

		btnMain = (ImageView) this.findViewById(R.id.btnMain);
		btnMain.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				intent = AccountInfo.this.getIntent();
				intent.setClass(AccountInfo.this, ABankMain.class);
				AccountInfo.this.startActivity(intent);
			}
		});
		
		btnHelper = (ImageView) this.findViewById(R.id.btnHelper);
		btnHelper.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				intent = AccountInfo.this.getIntent();
				intent.setClass(AccountInfo.this, FinancialConsultation.class);
				AccountInfo.this.startActivity(intent);
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

	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		if (id == 0) {
			//
			intent.setClass(AccountInfo.this, QueryAccount.class);
			AccountInfo.this.startActivity(intent);
		} else if (id == 1) {
			//
			intent.setClass(AccountInfo.this, AddAccount.class);
			AccountInfo.this.startActivity(intent);
		} else if (id == 2) {
			//
			intent.setClass(AccountInfo.this, DeleteAccount.class);
			AccountInfo.this.startActivity(intent);
		}
	}

}

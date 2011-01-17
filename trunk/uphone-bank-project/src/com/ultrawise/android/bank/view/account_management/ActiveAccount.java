package com.ultrawise.android.bank.view.account_management;

import java.util.ArrayList;
import java.util.HashMap;

import com.ultrawise.android.bank.view.transfer.R;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class ActiveAccount extends ListActivity {
	private ImageView btnCoustom;
	Intent intent;
	private int flag = 0;
	private TextView tvClassFirst;
	private TextView tvClassSecond;
	private TextView tvClassThrid;
	private TextView tvClassFour;
	ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		/**
		 * 业务功能，暂时不考虑
		 */
		/*
		intent = ActiveAccount.this.getIntent();
		String activeAcc = intent.getStringExtra("activeAcc");
		// just active one account only
		if (activeAcc != null) {
			if (activeAcc.equalsIgnoreCase("9876543210123456"))
				list.remove(0);
			if (activeAcc.equalsIgnoreCase("5560654220320266"))
				list.remove(1);
			if (activeAcc.equalsIgnoreCase("1234567899876543"))
				list.remove(2);
		}*/
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.account_active);
		intent = ActiveAccount.this.getIntent();

		// 设置List View
		HashMap<String, String> map1 = new HashMap<String, String>();
		map1.put("name", "9876543210123456");
		map1.put("arrow", ">");
		HashMap<String, String> map2 = new HashMap<String, String>();
		map2.put("name", "5560654220320266");
		map2.put("arrow", ">");
		HashMap<String, String> map3 = new HashMap<String, String>();
		map3.put("name", "1234567899876543");
		map3.put("arrow", ">");
		list.add(map1);
		list.add(map2);
		list.add(map3);

		SimpleAdapter sa = new SimpleAdapter(this, list,
				R.layout.account_management_textview_list, new String[] {
						"name", "arrow" }, new int[] { R.id.name, R.id.arrow });
		this.setListAdapter(sa);

		//设置层级关系
		tvClassFirst = (TextView)this.findViewById(R.id.class_first);
		tvClassFirst.setText("手机银行>");
		tvClassFirst.setVisibility(View.VISIBLE);
		tvClassFirst.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				//intent.setClass(AccountInfo.this, DeleteAccount.class);
				//AccountInfo.this.startActivity(intent);
			}
		});
		
		tvClassSecond = (TextView)this.findViewById(R.id.class_second);
		tvClassSecond.setText("账户管理>");
		tvClassSecond.setVisibility(View.VISIBLE);
		tvClassSecond.setClickable(true);
		tvClassSecond.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				intent = ActiveAccount.this.getIntent();
				intent.setClass(ActiveAccount.this, AccountManagement.class);
				ActiveAccount.this.startActivity(intent);
				
			}
		});
		
		tvClassThrid = (TextView)this.findViewById(R.id.class_third);
		tvClassThrid.setText("激活账户");
		tvClassThrid.setVisibility(View.VISIBLE);
		
		// 设置底部自定义按钮显示
		btnCoustom = (ImageView) this.findViewById(R.id.btnCoustom);
		//btnCoustom.setImageResource(R.drawable.test2);
		btnCoustom.setVisibility(View.VISIBLE);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);

		// Eject dialog
		new AlertDialog.Builder(ActiveAccount.this).setTitle("确认对话框")
				.setMessage("激活账户？")
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {
						// delete account turn to account information
						flag = 1;// done
						Toast.makeText(ActiveAccount.this, "激活账户成功", Toast.LENGTH_SHORT).show();
						finish();
					}
				})
				.setNegativeButton("取消", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						flag = -1;
						
						//finish();
					}
				}).show();
		
		
		if (flag == 1) {
			/**
			 * 业务功能，暂时不考虑
			 */
			/*
			String activeAcc = "";
			intent = ActiveAccount.this.getIntent();

			if (id == 0)
				activeAcc = "9876543210123456";
			if (id == 1)
				activeAcc = "5560654220320266";
			if (id == 2)
				activeAcc = "1234567899876543";

			intent.putExtra("activeAcc", activeAcc);
			*/
			intent.setClass(ActiveAccount.this, ActiveAccount.class);
			ActiveAccount.this.startActivity(intent);
		}
	}
}

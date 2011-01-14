package com.ultrawise.android.bank.view.account_management;

import java.util.ArrayList;
import java.util.HashMap;

import com.ultrawise.android.bank.view.transfer.R;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class AccountInfo extends ListActivity {
	private TextView tvAccInfo;
	private Button btnCoustom;
	Intent intent;
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		tvAccInfo = (TextView) this.findViewById(R.id.tvAccInfo);
		intent = AccountInfo.this.getIntent();
		//It will be updated account information if resume this activity
		if (intent.getStringExtra("AccInfo") != null) {
			tvAccInfo.setText(intent.getStringExtra("AccInfo"));
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.account_info);
		intent = AccountInfo.this.getIntent();
		// 账户信息
		tvAccInfo = (TextView) this.findViewById(R.id.tvAccInfo);
		tvAccInfo.setText("账号：440301198810282152\n" + "别名：我的储蓄卡\n"
				+ "账户类型：活期储蓄一折（卡）\n" + "账户状态：正常\n是否签约：未签约\n"
				+ "开户行：建设银行深圳市梅林支行\n" + "开户日：2006/07/09");
		tvAccInfo.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG );//add bottom line
		
		
		// 设置List View
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
		
		//设置底部自定义按钮显示
		btnCoustom = (Button)this.findViewById(R.id.btnCoustom);
		btnCoustom.setText("账户信息");
		btnCoustom.setVisibility(View.VISIBLE);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		if (id == 0) {
			//查询账户
			
			intent.setClass(AccountInfo.this, QueryAccount.class);
			AccountInfo.this.startActivity(intent);
		}else if(id==1){
			//增加账户
			
			intent.setClass(AccountInfo.this, AddAccount.class);
			AccountInfo.this.startActivity(intent);
		}else if(id==2){
			//删除账户
			
			intent.setClass(AccountInfo.this, DeleteAccount.class);
			AccountInfo.this.startActivity(intent);
		}
	}

}

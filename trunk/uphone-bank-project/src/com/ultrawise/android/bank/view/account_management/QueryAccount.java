package com.ultrawise.android.bank.view.account_management;

import java.util.ArrayList;
import java.util.HashMap;

import com.ultrawise.android.bank.view.transfer.R;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class QueryAccount extends ListActivity {
	private Button btnCoustom;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.list_account);
		// 设置List View
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> map1 = new HashMap<String, String>();
		map1.put("name", "5560654220320266");
		map1.put("arrow", ">");
		HashMap<String, String> map2 = new HashMap<String, String>();
		map2.put("name", "1122065468210030");
		map2.put("arrow", ">");
		HashMap<String, String> map3 = new HashMap<String, String>();
		map3.put("name", "3322019830320266");
		map3.put("arrow", ">");
		list.add(map1);
		list.add(map2);
		list.add(map3);
		SimpleAdapter sa = new SimpleAdapter(this, list,
				R.layout.account_management_textview_list, new String[] {
						"name", "arrow" }, new int[] { R.id.name, R.id.arrow });
		this.setListAdapter(sa);

		// 设置底部自定义按钮显示
		btnCoustom = (Button) this.findViewById(R.id.btnCoustom);
		btnCoustom.setText("选择账户");
		btnCoustom.setVisibility(View.VISIBLE);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		String num = "";
		String nickName = "";
		String type = "";
		Intent intent = new Intent();
		if (id == 0) {
			num = "5560654220320266";
			nickName = "信用卡二号";
			type = "信用卡";
		} else if (id == 1) {
			num = "1122065468210030";
			nickName = "我的龙卡";
			type="储蓄卡";
		} else if (id == 2) {
			num = "3322019830320266";
			nickName = "我的第一个信用卡";
			type= "信用卡";
		}
		intent.putExtra("AccInfo", "账号：" + num + "\n" + "别名：" + nickName + "\n"
				+ "账户类型：" + type + "\n" + "账户状态：正常\n是否签约：签约\n"
				+ "开户行：建设银行深圳市梅林支行\n" + "开户日：2006/04/10");
		intent.setClass(QueryAccount.this, AccountInfo.class);
		QueryAccount.this.startActivity(intent);

	}
}

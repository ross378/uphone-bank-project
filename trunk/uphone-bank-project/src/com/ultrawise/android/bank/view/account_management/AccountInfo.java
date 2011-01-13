package com.ultrawise.android.bank.view.account_management;

import java.util.ArrayList;
import java.util.HashMap;

import com.ultrawise.android.bank.view.transfer.R;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class AccountInfo extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.account_info);
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
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		Toast.makeText(this, "来吧", Toast.LENGTH_SHORT);
	}

}

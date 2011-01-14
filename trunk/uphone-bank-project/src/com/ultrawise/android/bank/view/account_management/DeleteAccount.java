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
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class DeleteAccount extends ListActivity {
	private Button btnCoustom;
	Intent intent;
	private int flag = 0;
	HashMap<String, String> map4;
	HashMap<String, String> map5;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.list_account);

		// 设置List View
		intent = DeleteAccount.this.getIntent();
		String delAccNum = intent.getStringExtra("delAccNum");
		String addAccNum = intent.getStringExtra("addAccNum");
		String activeAcc = intent.getStringExtra("activeAcc");

		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> map1 = new HashMap<String, String>();
		map1.put("name", "5560654220320266");
		map1.put("arrow", ">");
		list.add(map1);

		HashMap<String, String> map2 = new HashMap<String, String>();
		map2.put("name", "1122065468210030");
		map2.put("arrow", ">");
		list.add(map2);

		HashMap<String, String> map3 = new HashMap<String, String>();
		map3.put("name", "3322019830320266");
		map3.put("arrow", ">");
		list.add(map3);
		// just add one account only
		if (addAccNum != null) {
			map4 = new HashMap<String, String>();
			map4.put("name", addAccNum);
			map4.put("arrow", ">");
			list.add(map4);
		}
		if (activeAcc != null) {
			map5 = new HashMap<String, String>();
			map5.put("name", addAccNum);
			map5.put("arrow", ">");
			list.add(map5);
		}

		// just delete one account only
		if (delAccNum != null) {
			if (delAccNum.equalsIgnoreCase("5560654220320266"))
				list.remove(0);
			if (delAccNum.equalsIgnoreCase("1122065468210030"))
				list.remove(1);
			if (delAccNum.equalsIgnoreCase("3322019830320266"))
				list.remove(2);
			if (addAccNum != null) {
				if (delAccNum.equalsIgnoreCase(addAccNum))
					list.remove(map4);
			}
			if (activeAcc != null) {
				if (delAccNum.equalsIgnoreCase(activeAcc))
					list.remove(map5);
			}
		}

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
		// Eject dialog
		new AlertDialog.Builder(DeleteAccount.this).setTitle("确认对话框")
				.setMessage("删除账户？")
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {
						// delete account turn to account information
						flag = 1;// done
						finish();
					}
				})
				.setNegativeButton("取消", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						flag = -1;
						finish();
					}
				}).show();
		if (flag == 1) {
			String num = "";

			if (id == 0) {
				num = "5560654220320266";

			} else if (id == 1) {
				num = "1122065468210030";

			} else if (id == 2) {
				num = "3322019830320266";
			} else if (id == 3) {
				TextView tv = (TextView) v;
				num = tv.getText().toString();
			} else if (id == 4) {
				TextView tv = (TextView) v;
				num = tv.getText().toString();
			}

			intent.putExtra("delAccNum", num);
			intent.setClass(DeleteAccount.this, AccountInfo.class);
			DeleteAccount.this.startActivity(intent);
		}
	}
}

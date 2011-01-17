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

public class OrderCard extends ListActivity {

	private ImageView btnCoustom;
	Intent intent;
	private int flag = 0;
	private TextView tvClassFirst;
	private TextView tvClassSecond;
	private TextView tvClassThrid;

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		// Eject list dialog
		new AlertDialog.Builder(OrderCard.this)
				.setTitle("请选择领卡网点")
				.setItems(R.array.list_bank_net,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub
								String[] array = getResources().getStringArray(
										R.array.list_bank_net);
								//Eject dialog
								new AlertDialog.Builder(OrderCard.this)
										.setTitle("您选择的网点是：")
										.setMessage(array[which])
										.setPositiveButton(
												"确认预约？",
												new DialogInterface.OnClickListener() {
													public void onClick(
															DialogInterface dialog,
															int which) {
														// TODO Auto-generated
														flag = 1;
														Toast.makeText(
																OrderCard.this,
																"预约成功",
																Toast.LENGTH_SHORT)
																.show();
														dialog.dismiss();
													}
												}).setNegativeButton("取消", new DialogInterface.OnClickListener() {

													public void onClick(
															DialogInterface dialog,
															int which) {
														// TODO Auto-generated method stub
														dialog.dismiss();
													}
												}).show();
							}
						})
				.setNegativeButton("取消", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						flag = -1;
						// finish();
					}
				}).show();
		if (flag == 1) {
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.order_card);

		// 设置List View
		intent = OrderCard.this.getIntent();
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

		SimpleAdapter sa = new SimpleAdapter(this, list,
				R.layout.account_management_textview_list, new String[] {
						"name", "arrow" }, new int[] { R.id.name, R.id.arrow });
		this.setListAdapter(sa);

		// 设置层级关系
		tvClassFirst = (TextView) this.findViewById(R.id.class_first);
		tvClassFirst.setText("手机银行>");
		tvClassFirst.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// intent = DeleteAccount.this.getIntent();
				// intent.setClass(DeleteAccount.this, AccountManagement.class);
				// DeleteAccount.this.startActivity(intent);
			}
		});
		tvClassSecond = (TextView) this.findViewById(R.id.class_second);
		tvClassSecond.setText("账户管理>");
		tvClassSecond.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				intent = OrderCard.this.getIntent();
				intent.setClass(OrderCard.this, AccountManagement.class);
				OrderCard.this.startActivity(intent);
			}
		});
		tvClassThrid = (TextView) this.findViewById(R.id.class_third);
		tvClassThrid.setText("预约换卡");

		tvClassFirst.setVisibility(View.VISIBLE);
		tvClassSecond.setVisibility(View.VISIBLE);
		tvClassThrid.setVisibility(View.VISIBLE);

		// 设置底部自定义按钮显示
		btnCoustom = (ImageView) this.findViewById(R.id.btnCoustom);
		//btnCoustom.setImageResource(R.drawable.test2);
		btnCoustom.setVisibility(View.VISIBLE);
	}

}

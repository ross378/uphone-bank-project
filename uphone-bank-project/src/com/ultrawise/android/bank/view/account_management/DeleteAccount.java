package com.ultrawise.android.bank.view.account_management;

import java.util.ArrayList;
import java.util.HashMap;

import com.ultrawise.android.bank.view.ABankMain;
import com.ultrawise.android.bank.view.FinancialConsultation;
import com.ultrawise.android.bank.view.transfer.R;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class DeleteAccount extends ListActivity {
	private ImageView btnCoustom;
	Intent intent;
	private int flag = 0;
	private TextView tvClassFirst;
	private TextView tvClassSecond;
	private TextView tvClassThrid;
	private TextView tvClassFour;
	private View btnReturn;
	private ImageView btnMain;
	private ImageView btnHelper;
	private GestureDetector mGestureDetector;

	// HashMap<String, String> map4;
	// HashMap<String, String> map5;
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {

		return mGestureDetector.onTouchEvent(event);

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.list_account);

		//向右滑动触发后退
		mGestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener(){
			@Override
			public boolean onScroll(MotionEvent e1, MotionEvent e2,
					float distanceX, float distanceY) {
				// TODO Auto-generated method stub
				if (distanceY == 0 && distanceX < 0)
					onBackPressed();

				return super.onScroll(e1, e2, distanceX, distanceY);
			}
		});
		
		//List View
		intent = DeleteAccount.this.getIntent();
//		String delAccNum = intent.getStringExtra("delAccNum");
//		String addAccNum = intent.getStringExtra("addAccNum");
//		String activeAcc = intent.getStringExtra("activeAcc");

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
		/**
		 * 
		 */
		/*
		 * if (addAccNum != null) { map4 = new HashMap<String, String>();
		 * map4.put("name", addAccNum); map4.put("arrow", ">"); list.add(map4);
		 * } if (activeAcc != null) { map5 = new HashMap<String, String>();
		 * map5.put("name", addAccNum); map5.put("arrow", ">"); list.add(map5);
		 * }
		 * 
		 * // just delete one account only if (delAccNum != null) { if
		 * (delAccNum.equalsIgnoreCase("5560654220320266")) list.remove(0); if
		 * (delAccNum.equalsIgnoreCase("1122065468210030")) list.remove(1); if
		 * (delAccNum.equalsIgnoreCase("3322019830320266")) list.remove(2); if
		 * (addAccNum != null) { if (delAccNum.equalsIgnoreCase(addAccNum))
		 * list.remove(map4); } if (activeAcc != null) { if
		 * (delAccNum.equalsIgnoreCase(activeAcc)) list.remove(map5); } }
		 */

		SimpleAdapter sa = new SimpleAdapter(this, list,
				R.layout.account_management_textview_list, new String[] {
						"name", "arrow" }, new int[] { R.id.name, R.id.arrow });
		this.setListAdapter(sa);

		//
		tvClassFirst = (TextView) this.findViewById(R.id.class_first);
		tvClassFirst.setText("手机银行>");
		tvClassFirst.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				 intent = DeleteAccount.this.getIntent();
				 intent.setClass(DeleteAccount.this, ABankMain.class);
				 DeleteAccount.this.startActivity(intent);
			}
		});
		tvClassSecond = (TextView) this.findViewById(R.id.class_second);
		tvClassSecond.setText("账户管理>");
		tvClassSecond.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				intent = DeleteAccount.this.getIntent();
				intent.setClass(DeleteAccount.this, AccountManagement.class);
				DeleteAccount.this.startActivity(intent);
			}
		});
		tvClassThrid = (TextView) this.findViewById(R.id.class_third);
		tvClassThrid.setText("账户信息>");
		tvClassThrid.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				intent = DeleteAccount.this.getIntent();
				intent.setClass(DeleteAccount.this, AccountInfo.class);
				DeleteAccount.this.startActivity(intent);
			}
		});
		tvClassFour = (TextView) this.findViewById(R.id.class_four);
		tvClassFour.setText("删除账户");

		tvClassFirst.setVisibility(View.VISIBLE);
		tvClassSecond.setVisibility(View.VISIBLE);
		tvClassThrid.setVisibility(View.VISIBLE);
		tvClassFour.setVisibility(View.VISIBLE);

		// set bottm button
		btnCoustom = (ImageView) this.findViewById(R.id.btnCoustom);
		btnCoustom.setImageResource(R.drawable.cardbg_zhgl_w);
		btnCoustom.setVisibility(View.VISIBLE);
		
		btnMain = (ImageView) this.findViewById(R.id.btnMain);
		btnMain.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				intent = DeleteAccount.this.getIntent();
				intent.setClass(DeleteAccount.this, ABankMain.class);
				DeleteAccount.this.startActivity(intent);
			}
		});
		
		btnHelper = (ImageView) this.findViewById(R.id.btnHelper);
		btnHelper.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				intent = DeleteAccount.this.getIntent();
				intent.setClass(DeleteAccount.this, FinancialConsultation.class);
				DeleteAccount.this.startActivity(intent);
			}
		});
		
		//返回键设定
		btnReturn = this.findViewById(R.id.returnToPre);
		btnReturn.setOnClickListener(new OnClickListener(){
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
		// Eject dialog
		new AlertDialog.Builder(DeleteAccount.this)
				.setTitle("确认对话框")
				.setMessage("删除账户？")
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {
						// delete account turn to account information
						flag = 1;// done
						Toast.makeText(DeleteAccount.this, "删除成功",
								Toast.LENGTH_SHORT).show();
						 finish();
					}
				})
				.setNegativeButton("取消", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						flag = -1;
						// finish();
					}
				}).show();
		if (flag == 1) {
			/**
			 * 
			 */
			/*
			 * String num = "";
			 * 
			 * if (id == 0) { num = "5560654220320266";
			 * 
			 * } else if (id == 1) { num = "1122065468210030";
			 * 
			 * } else if (id == 2) { num = "3322019830320266"; } else if (id ==
			 * 3) { TextView tv = (TextView) v; num = tv.getText().toString(); }
			 * else if (id == 4) { TextView tv = (TextView) v; num =
			 * tv.getText().toString(); }
			 * 
			 * intent.putExtra("delAccNum", num);
			 */
			intent.setClass(DeleteAccount.this, AccountInfo.class);
			DeleteAccount.this.startActivity(intent);
		}
	}
}

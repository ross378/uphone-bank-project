package com.ultrawise.android.bank.view.account_management;

import java.util.ArrayList;
import java.util.HashMap;

import com.ultrawise.android.bank.view.ABankMain;
import com.ultrawise.android.bank.view.transfer.R;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class LossRegister extends ListActivity {
	private ImageView btnCoustom;
	Intent intent;
	private int flag = 0;
	private TextView tvClassFirst;
	private TextView tvClassSecond;
	private TextView tvClassThrid;
	private Button btnConfirm;
	private ImageView btnReturn;
	private GestureDetector mGestureDetector;
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {

		return mGestureDetector.onTouchEvent(event);

	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		// Eject dialog
		new AlertDialog.Builder(LossRegister.this)
				.setTitle("确认对话框")
				.setMessage("账户挂失？")
				.setPositiveButton("确认", new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {
						// delete account turn to account information
						flag = 1;// done
						Toast.makeText(LossRegister.this, "账户挂失成功",
								Toast.LENGTH_SHORT).show();
						// finish();
					}
				})
				.setNegativeButton("取消", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						flag = -1;
						// finish();
					}
				}).show();
		if (flag == 1) {
//			intent.setClass(LossRegister.this, AccountInfo.class);
//			LossRegister.this.startActivity(intent);
		
		}
	}


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.loss_register);
		
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
		
		// List View
		intent = LossRegister.this.getIntent();

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

		// ���ò㼶��ϵ
		tvClassFirst = (TextView) this.findViewById(R.id.class_first);
		tvClassFirst.setText("手机银行>");
		tvClassFirst.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				 intent = LossRegister.this.getIntent();
				 intent.setClass(LossRegister.this, ABankMain.class);
				 LossRegister.this.startActivity(intent);
			}
		});
		tvClassSecond = (TextView) this.findViewById(R.id.class_second);
		tvClassSecond.setText("账户管理>");
		tvClassSecond.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				intent = LossRegister.this.getIntent();
				intent.setClass(LossRegister.this, AccountManagement.class);
				LossRegister.this.startActivity(intent);
			}
		});
		tvClassThrid = (TextView) this.findViewById(R.id.class_third);
		tvClassThrid.setText("账户挂失");

		tvClassFirst.setVisibility(View.VISIBLE);
		tvClassSecond.setVisibility(View.VISIBLE);
		tvClassThrid.setVisibility(View.VISIBLE);

		// 
		btnCoustom = (ImageView) this.findViewById(R.id.btnCoustom);
		btnCoustom.setImageResource(R.drawable.cardbg_zhgl_w);
		btnCoustom.setVisibility(View.VISIBLE);
		
		//返回键设定
		btnReturn = (ImageView)this.findViewById(R.id.returnToPre);
		btnReturn.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				// TODO Auto-generated method stub
				onBackPressed();
				finish();
			}
			
		});
		
	}

}

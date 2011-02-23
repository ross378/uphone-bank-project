package com.ultrawise.android.bank.view.account_query;

import java.util.ArrayList;
import java.util.HashMap;

import com.ultrawise.android.bank.view.ABankMain;
import com.ultrawise.android.bank.view.FinancialConsultation;
import com.ultrawise.android.bank.view.account_management.AccountInfo;
import com.ultrawise.android.bank.view.transfer.R;

import android.app.ListActivity;
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

public class QueryAccount extends ListActivity {
	private ImageView btnCoustom;
	Intent intent;
	private TextView tvClassFirst;
	private TextView tvClassSecond;
	private View btnReturn;
	private ImageView btnMain;
	private ImageView btnHelper;
	private GestureDetector mGestureDetector;


	// HashMap<String, String> map4;
	// HashMap<String, String> map5;
	
	//点击触发
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
		
		// List View
		intent = QueryAccount.this.getIntent();

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
						"name", "arrow" }, new int[] { R.id.accMana_tvName, R.id.accMana_tvArrow });
		this.setListAdapter(sa);

		//
		tvClassFirst = (TextView) this.findViewById(R.id.class_first);
		tvClassFirst.setText("手机银行>");
		tvClassFirst.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				 intent = QueryAccount.this.getIntent();
				 intent.setClass(QueryAccount.this, ABankMain.class);
				 QueryAccount.this.startActivity(intent);

			}
		});
		tvClassSecond = (TextView) this.findViewById(R.id.class_second);
		tvClassSecond.setText("查询账户");

		
		tvClassFirst.setVisibility(View.VISIBLE);
		tvClassSecond.setVisibility(View.VISIBLE);


		//
		btnCoustom = (ImageView) this.findViewById(R.id.btnCoustom);
		btnCoustom.setImageResource(R.drawable.cardbg_zhcx_w);
		btnCoustom.setVisibility(View.VISIBLE);
		
		btnMain = (ImageView) this.findViewById(R.id.btnMain);
		btnMain.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				intent = QueryAccount.this.getIntent();
				intent.setClass(QueryAccount.this, ABankMain.class);
				QueryAccount.this.startActivity(intent);
			}
		});
		
		btnHelper = (ImageView) this.findViewById(R.id.btnHelper);
		btnHelper.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				intent = QueryAccount.this.getIntent();
				intent.setClass(QueryAccount.this, FinancialConsultation.class);
				QueryAccount.this.startActivity(intent);
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

		intent.setClass(QueryAccount.this, AccountInfo.class);
		QueryAccount.this.startActivity(intent);
	}
}

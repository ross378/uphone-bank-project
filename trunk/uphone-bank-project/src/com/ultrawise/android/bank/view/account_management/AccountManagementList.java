package com.ultrawise.android.bank.view.account_management;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.ultrawise.android.bank.view.ABankMain;
import com.ultrawise.android.bank.view.FinancialConsultation;
import com.ultrawise.android.bank.view.transfer.R;

public class AccountManagementList extends ListActivity {

	ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
	private GestureDetector mGestureDetector;
	private ImageView btnMain;
	protected Intent intent;
	private ImageView btnHelper;
	private ImageView btnReturn;
	private TextView tvClassFirst;
	private TextView tvClassSecond;

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		if (id == 0) {
			// 账户信息
			intent = new Intent();
			intent.setClass(AccountManagementList.this, AccountInfoSelect.class);
			AccountManagementList.this.startActivity(intent);
		} else if (id == 1) {
			// 激活账户
			intent = new Intent();
			intent.setClass(AccountManagementList.this,
					ActiveAccountSelect.class);
			AccountManagementList.this.startActivity(intent);
		} else if (id == 2) {
			// 账户挂失
			intent = new Intent();
			intent.setClass(AccountManagementList.this, AccountLostSelect.class);
			AccountManagementList.this.startActivity(intent);
		} else if (id == 3) {
			// 预约换卡
			intent = new Intent();
			intent.setClass(AccountManagementList.this, OrderCardSelect.class);
			AccountManagementList.this.startActivity(intent);
		} else if (id == 4) {
			// 首选账户设置
			intent = new Intent();
			intent.setClass(AccountManagementList.this,
					PreferredAccountSelect.class);
			AccountManagementList.this.startActivity(intent);
		} else if (id == 5) {
			// 添加新账户
			intent = new Intent();
			intent.setClass(AccountManagementList.this, AccountAdd.class);
			AccountManagementList.this.startActivity(intent);
		} else if (id == 6) {
			// 删除已有账户
			intent = new Intent();
			intent.setClass(AccountManagementList.this, AccountDel.class);
			AccountManagementList.this.startActivity(intent);
		}else if(id==7){
			//设置账户别名
			intent =new Intent();
			intent.setClass(AccountManagementList.this, AccountNickName.class);
			AccountManagementList.this.startActivity(intent);
		}

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题栏
		this.setContentView(R.layout.account_management_list);

		// 设置宋体
		// Typeface face=Typeface.createFromAsset(getAssets(),"fonts/st.ttf");
		// ((TextView)this.findViewById(R.id.accMana_tvName)).setTypeface(face);
		// List View
		HashMap<String, Object> map1 = new HashMap<String, Object>();
		map1.put("star", R.drawable.accmana_cilun);
		map1.put("name", this.getString(R.string.accMana_AccInfo));
		map1.put("arrow", R.drawable.accmana_right);
		HashMap<String, Object> map2 = new HashMap<String, Object>();
		map2.put("star", R.drawable.accmana_cilun);
		map2.put("name", this.getString(R.string.accMana_AccActive));
		map2.put("arrow", R.drawable.accmana_right);
		HashMap<String, Object> map3 = new HashMap<String, Object>();
		map3.put("star", R.drawable.accmana_cilun);
		map3.put("name", this.getString(R.string.accMana_AccLoss));
		map3.put("arrow", R.drawable.accmana_right);
		HashMap<String, Object> map4 = new HashMap<String, Object>();
		map4.put("star", R.drawable.accmana_cilun);
		map4.put("name", this.getString(R.string.accMana_OrderCard));
		map4.put("arrow", R.drawable.accmana_right);
		HashMap<String, Object> map5 = new HashMap<String, Object>();
		map5.put("star", R.drawable.accmana_cilun);
		map5.put("name", this.getString(R.string.accMana_AccPre));
		map5.put("arrow", R.drawable.accmana_right);
		HashMap<String, Object> map6 = new HashMap<String, Object>();
		map6.put("star", R.drawable.accmana_cilun);
		map6.put("name", this.getString(R.string.accMana_AddAcc));
		map6.put("arrow", R.drawable.accmana_right);
		HashMap<String, Object> map7 = new HashMap<String, Object>();
		map7.put("star", R.drawable.accmana_cilun);
		map7.put("name", this.getString(R.string.accMana_DelAcc));
		map7.put("arrow", R.drawable.accmana_right);
		HashMap<String, Object> map8 = new HashMap<String, Object>();
		map8.put("star", R.drawable.accmana_cilun);
		map8.put("name", "设置账户别名");
		map8.put("arrow", R.drawable.accmana_right);
		

		list.add(map1);
		list.add(map2);
		list.add(map3);
		list.add(map4);
		list.add(map5);
		list.add(map6);
		list.add(map7);
		list.add(map8);

		SimpleAdapter sa = new SimpleAdapter(this, list,
				R.layout.account_management_textview_list, new String[] {
						"star", "name", "arrow" }, new int[] {
						R.id.accMana_ibStar, R.id.accMana_tvName,
						R.id.accMana_tvArrow });
		this.setListAdapter(sa);

		// 向右滑动触发后退
		mGestureDetector = new GestureDetector(this,
				new GestureDetector.SimpleOnGestureListener() {
					@Override
					public boolean onScroll(MotionEvent e1, MotionEvent e2,
							float distanceX, float distanceY) {
						// TODO Auto-generated method stub
						if (distanceY == 0 && distanceX < 0)
							onBackPressed();

						return super.onScroll(e1, e2, distanceX, distanceY);
					}
				});

		// 底部按钮设置
		btnMain = (ImageView) this.findViewById(R.id.btnMain);
		btnMain.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				intent = new Intent();
				intent.setClass(AccountManagementList.this, ABankMain.class);
				AccountManagementList.this.startActivity(intent);
			}
		});

		btnHelper = (ImageView) this.findViewById(R.id.btnHelper);
		btnHelper.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				intent = new Intent();
				intent.setClass(AccountManagementList.this,
						FinancialConsultation.class);
				AccountManagementList.this.startActivity(intent);
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

		// 设置层级关系
		tvClassFirst = (TextView) this.findViewById(R.id.class_first);
		tvClassFirst.setText("首页>");
		tvClassFirst.setVisibility(View.VISIBLE);
		tvClassFirst.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				intent.setClass(AccountManagementList.this, ABankMain.class);
				AccountManagementList.this.startActivity(intent);
			}
		});

		tvClassSecond = (TextView) this.findViewById(R.id.class_second);
		tvClassSecond.setText("账户管理");
		tvClassSecond.setVisibility(View.VISIBLE);
	}

	// 触摸触发
	@Override
	public boolean onTouchEvent(MotionEvent event) {

		return mGestureDetector.onTouchEvent(event);

	}

}

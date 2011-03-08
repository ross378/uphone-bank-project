package com.ultrawise.android.bank.view.account_management;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ultrawise.android.bank.consum_webservices.AccManaConWebservices;
import com.ultrawise.android.bank.view.ABankMain;
import com.ultrawise.android.bank.view.FinancialConsultation;
import com.ultrawise.android.bank.view.transfer.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class AccountInfo2 extends ListActivity {
	public final static String ACCOUNT = "account";

	private ListView lvContent;
	private Intent intent;
	private String strAccountTypeValue;
	private String strAccountValue;
	private String strCoin;
	private String strBalance;
	private String strIsActive;
	private String strOpenAdd;
	private String strOpenDate;
	private String strAccState;
	private TextView tvClassFirst;
	private TextView tvClassSecond;
	private TextView tvClassThrid;
	private ImageView btnMain;
	private ImageView btnHelper;
	private View btnReturn;
	private GestureDetector mGestureDetector;
	private TextView ivNickName;
	ViewHolder holder;
	private final static String CHECK = "（点击查看详情）";
	private String strNickName;

	// 触摸触发事件
	@Override
	public boolean onTouchEvent(MotionEvent event) {

		return mGestureDetector.onTouchEvent(event);

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);// 去标题栏
		this.setContentView(R.layout.account_info2);

		intent = AccountInfo2.this.getIntent();
		if (intent != null) {
			// 从账户选择页面传来的数据中获取账户类型和账户号
			strAccountTypeValue = intent
					.getStringExtra(AccountInfoSelect.ACCOUNT_TYPE);
			strAccountValue = intent.getStringExtra(AccountInfoSelect.ACCOUNT);

			if (strAccountTypeValue == null && strAccountValue == null) {
				// 进错了啦你
				finish();
			}
		}else{
			finish();
		}

		// 设置层级关系
		tvClassFirst = (TextView) this.findViewById(R.id.class_first);
		tvClassFirst.setText("首页>");
		tvClassFirst.setVisibility(View.VISIBLE);
		tvClassFirst.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				intent = new Intent();
				intent.setClass(AccountInfo2.this, ABankMain.class);
				AccountInfo2.this.startActivity(intent);
			}
		});

		tvClassSecond = (TextView) this.findViewById(R.id.class_second);
		tvClassSecond.setText("账户管理>");
		tvClassSecond.setVisibility(View.VISIBLE);
		tvClassSecond.setClickable(true);
		tvClassSecond.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				intent = new Intent();
				intent.setClass(AccountInfo2.this, AccountManagementList.class);
				AccountInfo2.this.startActivity(intent);

			}
		});

		tvClassThrid = (TextView) this.findViewById(R.id.class_third);
		tvClassThrid.setText("账户信息");
		tvClassThrid.setVisibility(View.VISIBLE);

		// 底部按钮设置
		btnMain = (ImageView) this.findViewById(R.id.btnMain);
		btnMain.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				intent = new Intent();
				intent.setClass(AccountInfo2.this, ABankMain.class);
				AccountInfo2.this.startActivity(intent);
			}
		});

		btnHelper = (ImageView) this.findViewById(R.id.btnHelper);
		btnHelper.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				intent = new Intent();
				intent.setClass(AccountInfo2.this, FinancialConsultation.class);
				AccountInfo2.this.startActivity(intent);
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

		List<String> lstOut = new ArrayList<String>();
		lstOut.add(strAccountValue);
		// 从服务器获取和此账号有关的信息
		List<String> lstIn = AccManaConWebservices.connectHttp(this, "0103", lstOut);

		strNickName = lstIn.get(6);
		strCoin = lstIn.get(15);
		strBalance = lstIn.get(14);
		strAccState = lstIn.get(7);
		strIsActive = lstIn.get(13);
		strOpenAdd = lstIn.get(5);
		strOpenDate = lstIn.get(0);
		
		// 显示文本
		// 生成内容
		ArrayList<HashMap<String, String>> alContent = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> item01 = new HashMap<String, String>();
		HashMap<String, String> item02 = new HashMap<String, String>();
		HashMap<String, String> item03 = new HashMap<String, String>();
		HashMap<String, String> item04 = new HashMap<String, String>();
		HashMap<String, String> item05 = new HashMap<String, String>();
		HashMap<String, String> item06 = new HashMap<String, String>();
		HashMap<String, String> item07 = new HashMap<String, String>();
		HashMap<String, String> item08 = new HashMap<String, String>();
		HashMap<String, String> item09 = new HashMap<String, String>();
		item01.put("name", "账号：");
		item01.put("content", strAccountValue);
		item01.put("check", "");

		item02.put("name", "账户别名：");
		item02.put("content", strNickName);
		item02.put("check", "");

		item03.put("name", "账户类型：");
		item03.put("content", strAccountTypeValue);
		item03.put("check", "");

		item04.put("name", "币种：");
		item04.put("content", strCoin);
		item04.put("check", "");

		item05.put("name", "余额：");
		item05.put("content", strBalance);
		item05.put("check", "");

		item06.put("name", "账户状态：");
		item06.put("content", strAccState);
		if(strAccState.equals("预约换卡")){
			item06.put("check", CHECK);	
		}else{
			item06.put("check", "");
		}
		
		// item06.put("right", "点击查看详情");
		item07.put("name", "是否激活");
		item07.put("content", strIsActive);
		item07.put("check", "");

		item08.put("name", "开户行：");
		item08.put("content", strOpenAdd);
		item08.put("check", "");

		item09.put("name", "开户日：");
		item09.put("content", strOpenDate);
		item09.put("check", "");

		alContent.add(item01);
		alContent.add(item02);
		alContent.add(item03);
		alContent.add(item04);
		alContent.add(item05);
		alContent.add(item06);
		alContent.add(item07);
		alContent.add(item08);
		alContent.add(item09);
		// 适配器
		MyAdapter lvAdapter = new MyAdapter(this, alContent,
				R.layout.account_info_adapter, new String[] { "name",
						"content", "check" }, new int[] { R.id.accInfo_tvName,
						R.id.accInfo_tvInfo, R.id.accInfo_ivCheck });
		this.setListAdapter(lvAdapter);

	}

	public final class ViewHolder {

		public TextView tvChangeNickName;
		public ImageView ivCheck;

	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		if (id == 5) {
			if(strAccState.equals("预约换卡")){
				intent = new Intent();
				intent.putExtra(ACCOUNT, strAccountValue);
				intent.setClass(AccountInfo2.this, OrderShowInfo2.class);
				AccountInfo2.this.startActivity(intent);
			}	
		}
	}

	class MyAdapter extends SimpleAdapter {
		private LayoutInflater mInflater;

		public MyAdapter(Context context, List<? extends Map<String, ?>> data,
				int resource, String[] from, int[] to) {
			super(context, data, resource, from, to);
			// TODO Auto-generated constructor stub
			this.mInflater = LayoutInflater.from(context);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			View v = super.getView(position, convertView, parent);
			// if (position == 1) {
			// v.findViewById(R.id.accInfo_tvInfo);
			// v.setBackgroundResource(R.drawable.accinfo_changename);
			// }
			// holder = new ViewHolder();
			// if (convertView == null) {
			//
			// holder = new ViewHolder();
			//
			// convertView = mInflater.inflate(R.layout.account_info_adapter,
			// null);
			//
			// if (position == 1){
			// holder.tvChangeNickName = (TextView) convertView
			// .findViewById(R.id.accInfo_tvInfo);
			//
			// }
			// } else {
			//
			// holder = (ViewHolder) convertView.getTag();
			//
			// }

			return v;

		}

	}

	// class BtnOnClickListener implements OnClickListener {
	//
	// public void onClick(View v) {
	// // TODO Auto-generated method stub
	// switch (v.getId()) {
	// case R.id.accInfo_tvChangeNickName:
	// // 改别名
	// final EditText et = new EditText(AccountInfo2.this);
	// et.setBackgroundResource(R.drawable.deposite_rate_two);
	// AlertDialog myDialog = new AlertDialog.Builder(
	// AccountInfo2.this)
	// .setTitle("设置别名：")
	// .setView(et)
	// .setPositiveButton("确定",
	// new DialogInterface.OnClickListener() {
	//
	// public void onClick(DialogInterface dialog,
	// int which) {
	//
	// Toast.makeText(AccountInfo2.this,
	// "设置成功", Toast.LENGTH_SHORT)
	// .show();
	// dialog.dismiss();
	// }
	// }).show();
	// break;
	// case R.id.accInfo_ivCheck:
	// // 查看预约
	// intent = new Intent();
	// intent.putExtra(AccountInfo2.ACCOUNT, strAccountValue);
	// intent.setClass(AccountInfo2.this, null);
	//
	// }
	// }
	// }

}

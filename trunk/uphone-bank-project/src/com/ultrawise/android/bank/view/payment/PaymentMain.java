package com.ultrawise.android.bank.view.payment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;

import com.ultrawise.android.bank.consum_webservices.PaymentWebservices;
import com.ultrawise.android.bank.view.ABankMain;
import com.ultrawise.android.bank.view.FinancialConsultation;
import com.ultrawise.android.bank.view.transfer.R;
import com.ultrawise.android.bank.view.transfer.TransAmtConfirm;

import android.R.string;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.view.View.OnClickListener;

public class PaymentMain extends ListActivity {// 自助缴费主页面

	/**
	 * @param funNo功能号
	 * @param value参数
	 * @return 返回从服务器接收回来的数据
	 */
	String funNo = null;
	TextView pay_name;
	SplitUtil spu = new SplitUtil();


	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.payment_main);
		TextView tvClassFirst = (TextView) this.findViewById(R.id.class_first);
		tvClassFirst.setText("首页>");
		tvClassFirst.setVisibility(View.VISIBLE);

		tvClassFirst.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(PaymentMain.this, ABankMain.class);
				PaymentMain.this.startActivity(intent);

			}
		});
		tvClassFirst.setVisibility(View.VISIBLE);

		TextView tvClassSecond = (TextView) this
				.findViewById(R.id.class_second);
		tvClassSecond.setText("自助缴费");
		tvClassSecond.setVisibility(View.VISIBLE);

		ArrayList<HashMap<String, Object>> mainlist = new ArrayList<HashMap<String, Object>>();

		HashMap<String, Object> paylist1 = new HashMap<String, Object>();

		paylist1.put("listimg1", R.drawable.trans_main);
		paylist1.put("payment_list", "待缴费项目");
		paylist1.put("listimg2", R.drawable.trans_main2);
		mainlist.add(paylist1);

		paylist1 = new HashMap<String, Object>();
		paylist1.put("listimg1", R.drawable.trans_main);
		paylist1.put("payment_list", "便捷服务");
		paylist1.put("listimg2", R.drawable.trans_main2);
		mainlist.add(paylist1);

		paylist1 = new HashMap<String, Object>();
		paylist1.put("listimg1", R.drawable.trans_main);
		paylist1.put("payment_list", "最近一个月缴费");
		paylist1.put("listimg2", R.drawable.trans_main2);
		mainlist.add(paylist1);

		paylist1 = new HashMap<String, Object>();
		paylist1.put("listimg1", R.drawable.trans_main);
		paylist1.put("payment_list", "历史缴费记录");
		paylist1.put("listimg2", R.drawable.trans_main2);
		mainlist.add(paylist1);

		paylist1 = new HashMap<String, Object>();
		paylist1.put("listimg1", R.drawable.trans_main);
		paylist1.put("payment_list", "缴费项目管理");
		paylist1.put("listimg2", R.drawable.trans_main2);
		mainlist.add(paylist1);

		SimpleAdapter MainListAdapter = new SimpleAdapter(this, mainlist,
				R.layout.payment_main_list, new String[] { "listimg1",
						"payment_list", "listimg2" }, new int[] {
						R.id.listimg1, R.id.payment_list, R.id.listimg2 });
		this.setListAdapter(MainListAdapter);

		ImageView iv_now = (ImageView) this.findViewById(R.id.btnCoustom);

		// 返回键设定
		ImageView btnReturn = (ImageView) this.findViewById(R.id.returnToPre);
		btnReturn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				onBackPressed();
				finish();
			}

		});

		// 底部两个按钮
		ImageView btnMain = (ImageView) this.findViewById(R.id.btnMain);
		btnMain.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(PaymentMain.this, ABankMain.class);
				PaymentMain.this.startActivity(intent);
			}
		});

		ImageView btnHelper = (ImageView) this.findViewById(R.id.btnHelper);
		btnHelper.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(PaymentMain.this, FinancialConsultation.class);
				PaymentMain.this.startActivity(intent);
			}
		});

	}

	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		if (id == 0) {// 待缴费
			Intent payment_intent = new Intent();
			pay_name = (TextView)findViewById(R.id.payment_list);
			String[] value;
			List<String> lstValue = new ArrayList<String>();
			lstValue.add(pay_name.getText().toString());
			PaymentWebservices pay = new PaymentWebservices();
			value = pay.connectHttp("601", lstValue);
			payment_intent.putExtra("pay_arr", value);
			payment_intent.setClass(PaymentMain.this, PaymentPend.class);
			PaymentMain.this.startActivity(payment_intent);
		} else if (id == 1) {// 便捷
			Intent payment_intent = new Intent();
			payment_intent.setClass(PaymentMain.this, PaymentSelfService.class);
			PaymentMain.this.startActivity(payment_intent);

		} else if (id == 2) {
			Intent payment_intent = new Intent();
			payment_intent.setClass(PaymentMain.this, PaymentLastMonth.class);
			PaymentMain.this.startActivity(payment_intent);
		} else if (id == 3) {
			Intent payment_intent = new Intent();
			payment_intent.setClass(PaymentMain.this, PaymentHistory.class);
			PaymentMain.this.startActivity(payment_intent);

		} else if (id == 4) {
			Intent payment_intent = new Intent();
			payment_intent.setClass(PaymentMain.this, PaymentManage.class);
			PaymentMain.this.startActivity(payment_intent);

		}
	}
}
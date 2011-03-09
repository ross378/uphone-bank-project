package com.ultrawise.android.bank.view.payment;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.ultrawise.android.bank.consum_webservices.PaymentWebservices;
import com.ultrawise.android.bank.view.ABankMain;
import com.ultrawise.android.bank.view.FinancialConsultation;
import com.ultrawise.android.bank.view.account_management.AccountManagement;
import com.ultrawise.android.bank.view.transfer.R;

import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;

public class AllPaymentSer extends Activity {
	private Spinner AccTypSpinner = null;
	private ArrayAdapter<String> AccTypAdapter = null;
	private Spinner Accspinner = null;
	private ArrayAdapter<String> Accadapter = null;
	private String ser_name1 = "";
	private EditText tv_ser_num;
	private String contract_no = ""; // 合同号

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pay_all_ser);

		TextView tvClassFirst = (TextView) this.findViewById(R.id.class_first);
		tvClassFirst.setText("首页>");
		tvClassFirst.setVisibility(View.VISIBLE);

		tvClassFirst.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(AllPaymentSer.this, ABankMain.class);
				AllPaymentSer.this.startActivity(intent);
			}
		});
		tvClassFirst.setVisibility(View.VISIBLE);

		TextView tvClassSecond = (TextView) this
				.findViewById(R.id.class_second);
		tvClassSecond.setText("自助缴费>");
		tvClassSecond.setVisibility(View.VISIBLE);

		tvClassSecond.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent payment_intent = new Intent();
				payment_intent.setClass(AllPaymentSer.this, PaymentMain.class);
				AllPaymentSer.this.startActivity(payment_intent);
			}
		});
		tvClassFirst.setVisibility(View.VISIBLE);

		TextView tvClassThird = (TextView) this.findViewById(R.id.class_third);
		tvClassThird.setText("便捷服务");
		tvClassThird.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent payment_intent = new Intent();
				payment_intent.setClass(AllPaymentSer.this,
						PaymentSelfService.class);
				AllPaymentSer.this.startActivity(payment_intent);

			}
		});
		tvClassThird.setVisibility(View.VISIBLE);

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
				intent.setClass(AllPaymentSer.this, ABankMain.class);
				AllPaymentSer.this.startActivity(intent);
			}
		});

		ImageView btnHelper = (ImageView) this.findViewById(R.id.btnHelper);
		btnHelper.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent
						.setClass(AllPaymentSer.this,
								FinancialConsultation.class);
				AllPaymentSer.this.startActivity(intent);
			}
		});

		// TextView tvClassFour = (TextView) this.findViewById(R.id.class_four);
		Intent paymentre_intent = getIntent();
		String ser = paymentre_intent.getStringExtra("ser_name");
		if (ser.equals("1")) {
			ser_name1 = "手机";
		} else {
			if (ser.equals("2")) {
				ser_name1 = "QQ";
			} else {
				ser_name1 = "网易";
			}

		}
		PaymentWebservices.paramsString = "payment";
		List<String> params = new ArrayList<String>();
		params.add(ser);
		String[] values = PaymentWebservices.connectHttp("60212", params);
		contract_no = values[1];
		String[] operator = values[0].split(",");

		// 运行商选择
		AccTypAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item);
		AccTypAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		for (String oper : operator) {
			AccTypAdapter.add(oper);
		}

		AccTypSpinner = (Spinner) findViewById(R.id.spinner_pay_typ);
		AccTypSpinner.setAdapter(AccTypAdapter);

		AccTypSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				Spinner spinner = (Spinner) parent;
				Log.v("Test", "id = " + id + "("
						+ spinner.getSelectedItem().toString() + ")");
			}

			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});

		// 面额选择
		Accadapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item);
		Accadapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		Accadapter.add("10");
		Accadapter.add("20");
		Accadapter.add("30");
		Accadapter.add("50");
		Accadapter.add("100");
		Accadapter.add("150");
		Accspinner = (Spinner) findViewById(R.id.spinner_pay_num);
		Accspinner.setAdapter(Accadapter);

		Accspinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				Spinner spinner = (Spinner) parent;
				Log.v("Test", "id = " + id + "("
						+ spinner.getSelectedItem().toString() + ")");
			}

			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});

		TextView tv_input_ser_num = (TextView) findViewById(R.id.input_ser_num);
		tv_input_ser_num.setText("请输入目标" + ser_name1 + "号：");
		tv_ser_num = (EditText) findViewById(R.id.tv_ser_num);
		  //无焦点时屏蔽软件盘的出现
        InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);     
        imm.hideSoftInputFromWindow(tv_ser_num.getWindowToken(), 0); 

		// 下一步的按钮选择
		Button btn_next = (Button) findViewById(R.id.btn_next);
		btn_next.setText("下一步");
		btn_next.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				String hh = tv_ser_num.getText().toString();
				System.out.println(hh);
				// TODO Auto-generated method stub
				if (hh == null || hh.trim().length() == 0) {

					Intent btnok_intent = new Intent();
					btnok_intent.putExtra("flag", "警告");
					btnok_intent.putExtra("info", "号码不能为空");
					btnok_intent.putExtra("btnText", "确定");
					btnok_intent.setClass(AllPaymentSer.this,
							PaymentFailResultOne.class);
					AllPaymentSer.this.startActivity(btnok_intent);
				} else {

					Random rad=new Random();
				int s=	rad.nextInt();
					Intent pay_ser_intent = new Intent();
					pay_ser_intent.putExtra("title", ser_name1);
					pay_ser_intent.putExtra("service_num",
							AllPaymentSer.this.tv_ser_num.getText().toString());
					pay_ser_intent.putExtra("amount", Accspinner
							.getSelectedItem().toString());
					pay_ser_intent.putExtra("inputed_peo", AccTypSpinner
							.getSelectedItem().toString());
					pay_ser_intent.putExtra("serialnum", "sT"+s);
					pay_ser_intent.setClass(AllPaymentSer.this,
							PaymentSerDetail.class);
					AllPaymentSer.this.startActivity(pay_ser_intent);
				}
			}
		});

	}
}

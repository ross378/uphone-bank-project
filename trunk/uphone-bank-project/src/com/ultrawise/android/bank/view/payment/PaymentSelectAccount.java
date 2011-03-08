package com.ultrawise.android.bank.view.payment;

import java.util.ArrayList;
import java.util.List;

import com.ultrawise.android.bank.consum_webservices.PaymentWebservices;
import com.ultrawise.android.bank.view.ABankMain;
import com.ultrawise.android.bank.view.FinancialConsultation;
import com.ultrawise.android.bank.view.account_management.AccountManagement;
import com.ultrawise.android.bank.view.transfer.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;

public class PaymentSelectAccount extends Activity {
	// 账户选择
	private Spinner AccTypSpinner = null;
	private ArrayAdapter<String> AccTypAdapter = null;
	private Spinner Accspinner = null;
	private ArrayAdapter<String> Accadapter = null;
	private TextView tvClassFirst = null;
	private TextView tvClassSecond = null;
	private ImageView btnReturn = null;
	private Intent intent = null;
	String[] inmation;
	String payName;
	String payNum;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pay_accounttype_select);
		
	
		List<String> lstValue = new ArrayList<String>();
		lstValue.add("dasdsa");
		PaymentWebservices pay=new PaymentWebservices();		
		inmation=pay.connectHttp("60201",lstValue); 

		TextView tvClassFirst = (TextView) this.findViewById(R.id.class_first);
		tvClassFirst.setText("首页>");
		tvClassFirst.setVisibility(View.VISIBLE);

		tvClassFirst.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(PaymentSelectAccount.this, ABankMain.class);
				PaymentSelectAccount.this.startActivity(intent);
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
				payment_intent.setClass(PaymentSelectAccount.this,
						PaymentMain.class);
				PaymentSelectAccount.this.startActivity(payment_intent);
			}
		});
		tvClassSecond.setVisibility(View.VISIBLE);

		TextView tvClassThird = (TextView) this.findViewById(R.id.class_third);
		tvClassThird.setText("其他账户选择");
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
				intent.setClass(PaymentSelectAccount.this, ABankMain.class);
				PaymentSelectAccount.this.startActivity(intent);
			}
		});

		ImageView btnHelper = (ImageView) this.findViewById(R.id.btnHelper);
		btnHelper.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(PaymentSelectAccount.this,
						FinancialConsultation.class);
				PaymentSelectAccount.this.startActivity(intent);
			}
		});
		
		// 获的上一个界面上请求后的数据
 	
		Intent up_intent = getIntent();
		payName=up_intent.getStringExtra("pay_name");
		payNum=up_intent.getStringExtra("pay_num");

		
		// 账户类型的添加
		AccTypAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item);
		AccTypAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		for (int i = 0; i < inmation.length; i += 2) {
			AccTypAdapter.add(inmation[i]);
		}
		AccTypSpinner = (Spinner) findViewById(R.id.spinnerAccTyp);
		AccTypSpinner.setAdapter(AccTypAdapter);
		// 添加完
		// 监听选的是什么
		AccTypSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				Spinner spinner = (Spinner) parent;
				for (int i = 0; i < inmation.length; i += 2) {
					if (spinner.getSelectedItem().toString() == inmation[i]) {
						//绑定自己的帐号
						Accadapter = new ArrayAdapter<String>(PaymentSelectAccount.this,android.R.layout.simple_spinner_item);
						Accadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
						String[] num = inmation[i + 1].split(",");
						//将每种每种类型的账户分离开来
						for (int j = 0; j < num.length; j++) {
							//为此类型添加自己的账户
							Accadapter.add(num[j]);
						}
						Accspinner = (Spinner) findViewById(R.id.spinnerAcc);
						Accspinner.setAdapter(Accadapter);
						Accspinner.setOnItemSelectedListener(new OnItemSelectedListener() {
									public void onItemSelected(
						            AdapterView<?> parent, View view,int position, long id) {
										Spinner spinner = (Spinner) parent;
									}

									public void onNothingSelected(
											AdapterView<?> arg0) {
									}
								});
						// 结束帐号绑定
					}
				}
			}

			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});
	
		
		// 下一步的按钮选择
		Button btn_next = (Button) findViewById(R.id.btn_next);
		btn_next.setText("下一步");
		btn_next.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				String[] value;
				List<String> lstValue = new ArrayList<String>();
				lstValue.add(Accspinner.getSelectedItem().toString());
				PaymentWebservices pay=new PaymentWebservices();		
			    value=	pay.connectHttp("60203",lstValue);  

				// TODO Auto-generated method stub
				Intent AccMation_intent = new Intent();
				System.out.println(value[0]);
				System.out.println(value[1]);
				System.out.println(value[2]);
				AccMation_intent.putExtra("Account", value[0]);
				AccMation_intent.putExtra("acc_balance",value[1]);
				AccMation_intent.putExtra("pwd",value[2]);
				AccMation_intent.putExtra("pay_name", payName);
				AccMation_intent.putExtra("pay_num",payNum);
				System.out.println(payNum);
				AccMation_intent.setClass(PaymentSelectAccount.this,PaymentInPwd.class);
				PaymentSelectAccount.this.startActivity(AccMation_intent);

			}
		});

	}
}

package com.ultrawise.android.bank.view.payment;

import com.ultrawise.android.bank.view.ABankMain;
import com.ultrawise.android.bank.view.transfer.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class PaymentManageDetail extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.payment_hisdtl);
		TextView tvClassFirst = (TextView)this.findViewById(R.id.class_first);
		tvClassFirst.setText("首页>");
		tvClassFirst.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent payment_intent = new Intent();
				payment_intent.setClass(PaymentManageDetail.this, ABankMain.class);
				PaymentManageDetail.this.startActivity(payment_intent);
			}
		});
		tvClassFirst.setVisibility(View.VISIBLE);
        TextView tvClassSecond = (TextView)this.findViewById(R.id.class_second);
        tvClassSecond.setText("自助缴费>");
        tvClassSecond.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent payment_intent = new Intent();
				payment_intent.setClass(PaymentManageDetail.this, PaymentMain.class);
				PaymentManageDetail.this.startActivity(payment_intent);
			}
		});
        tvClassSecond.setVisibility(View.VISIBLE);
		
		TextView tvClassThird = (TextView)this.findViewById(R.id.class_third);
		tvClassThird.setText("缴费项目管理");
		tvClassThird.setVisibility(View.VISIBLE);
		tvClassThird.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent payment_intent = new Intent();
				payment_intent.setClass(PaymentManageDetail.this, PaymentManage.class);
				PaymentManageDetail.this.startActivity(payment_intent);
			}
		});
	}
}

package com.ultrawise.android.bank.view.payment;

import java.util.Date;

import com.ultrawise.android.bank.view.transfer.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;

public class PaymentSetTimeDialog extends Activity {
	Button time_ok = null;
	Button time_cancel = null;
	DatePicker datePicker = null;
	String[] time=new String[3];
	Intent intent = null;
	String name = "";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.payment_settime);
		intent = this.getIntent();
		if(intent.hasExtra("start")){
			name = intent.getStringExtra("start");
		}
		if(intent.hasExtra("end")){
			name = intent.getStringExtra("end");
		}
		datePicker = (DatePicker)findViewById(R.id.datepick_payment_time);
		time_ok = (Button)findViewById(R.id.time_ok);
		time_ok.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				int year = datePicker.getYear();
				int month = datePicker.getMonth();
				int day = datePicker.getDayOfMonth();
				time[0]=String.valueOf(year);
				time[1]=String.valueOf(month+1);
				time[2]=String.valueOf(day);
				Intent intent = new Intent();
				intent.putExtra(PaymentSetTimeDialog.this.name, time);
				intent.setClass(PaymentSetTimeDialog.this, PaymentHistory.class);
				PaymentSetTimeDialog.this.startActivity(intent);
				PaymentSetTimeDialog.this.finish();
//				System.exit(0);
			}
		});
		time_cancel = (Button)findViewById(R.id.time_cancel);
		time_cancel.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(PaymentSetTimeDialog.this, PaymentHistory.class);
				PaymentSetTimeDialog.this.startActivity(intent);
				PaymentSetTimeDialog.this.finish();
//				System.exit(0);
			}
		});
	}
}

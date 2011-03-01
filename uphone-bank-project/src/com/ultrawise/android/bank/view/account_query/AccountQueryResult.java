package com.ultrawise.android.bank.view.account_query;

import java.util.ArrayList;
import java.util.HashMap;

import com.ultrawise.android.bank.view.payment.PaymentHistory;
import com.ultrawise.android.bank.view.payment.PaymentSetTimeDialog;
import com.ultrawise.android.bank.view.transfer.R;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class AccountQueryResult extends Activity{
	    String[] time=new String[3];
		Button time_ok = null;
		Button time_cancel = null;
		DatePicker datePicker = null;
		Intent intent = null;
		String name = "";
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			setContentView(R.layout.payment_settime);
			this.setTitle("");
			intent = this.getIntent();
			if(intent.hasExtra("start")){
				name = intent.getStringExtra("start");
				System.out.println(name);
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
					intent.putExtra(AccountQueryResult.this.name, time);
					intent.setClass(AccountQueryResult.this, inventory.class);
					AccountQueryResult.this.startActivity(intent);
				}
			});
			time_cancel = (Button)findViewById(R.id.time_cancel);
			time_cancel.setOnClickListener(new OnClickListener() {
				
				public void onClick(View v) {
					Intent intent = new Intent();
					intent.setClass(AccountQueryResult.this, inventory.class);
					AccountQueryResult.this.startActivity(intent);
				}
			});
		}
	}


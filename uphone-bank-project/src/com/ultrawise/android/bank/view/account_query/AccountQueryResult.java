package com.ultrawise.android.bank.view.account_query;

import com.ultrawise.android.bank.view.transfer.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;


public class AccountQueryResult extends Activity{
	    String[] time=new String[5];
		Button time_ok = null;
		Button time_cancel = null;
		DatePicker datePicker = null;
		Intent intent = null;
		String name = "";
		String nomber = "";
		String type = "";
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			setContentView(R.layout.query_set_time);
			this.setTitle("设置时间");
			intent = this.getIntent();
			if(intent.hasExtra("start")){
				name = intent.getStringExtra("start");
				/**
				 * 接收类型和账号
				 */
				nomber = intent.getStringExtra("nomber");
				type = intent.getStringExtra("type");
				System.out.println(name);
			}
			if(intent.hasExtra("end")){
				name = intent.getStringExtra("end");
				nomber = intent.getStringExtra("nomber");
				type = intent.getStringExtra("type");
				
				
			}
			datePicker = (DatePicker)findViewById(R.id.accountfrom_payment_time);
			time_ok = (Button)findViewById(R.id.accountfrom_ok);
			time_ok.setOnClickListener(new OnClickListener() {
				
				public void onClick(View v) {
					int year = datePicker.getYear();
					int month = datePicker.getMonth();
					int day = datePicker.getDayOfMonth();
					time[0]=String.valueOf(year);
					time[1]=String.valueOf(month+1);
					time[2]=String.valueOf(day);
					time[3]=nomber;
					time[4]=type;
					Intent intent = new Intent();
					intent.putExtra(AccountQueryResult.this.name, time);
					
					intent.setClass(AccountQueryResult.this, inventory.class);
					AccountQueryResult.this.startActivity(intent);
					AccountQueryResult.this.finish();
				}
			});
			time_cancel = (Button)findViewById(R.id.accountfrom_cancel);
			time_cancel.setOnClickListener(new OnClickListener() {			
				public void onClick(View v) {
					Intent intent = new Intent();
					intent.setClass(AccountQueryResult.this, inventory.class);
					AccountQueryResult.this.startActivity(intent);
					AccountQueryResult.this.finish();
				}
			});
		}
	}


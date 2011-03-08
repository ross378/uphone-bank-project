package com.ultrawise.android.bank.view.credit;

import com.ultrawise.android.bank.view.transfer.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;


public class CreditResult extends Activity{
	    String[] time=new String[5];
		Button time_ok = null;
		Button time_cancel = null;
		DatePicker datePicker = null;
		Intent intent = null;
		String name = "";
		String nomber = "";
		String type = "";
		String cardNo;
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			setContentView(R.layout.creditresult);
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
				cardNo = intent.getStringExtra("cardNo");
			}
			if(intent.hasExtra("end")){
				name = intent.getStringExtra("end");
				nomber = intent.getStringExtra("nomber");
				type = intent.getStringExtra("type");
				cardNo = intent.getStringExtra("cardNo");
				
			}
			datePicker = (DatePicker)findViewById(R.id.accountfrom_payment_timee);
			time_ok = (Button)findViewById(R.id.accountfrom_okk);
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
					intent.putExtra(CreditResult.this.name, time);
					intent.putExtra("cardNo", cardNo);
					intent.setClass(CreditResult.this, CreditInventory.class);
					CreditResult.this.startActivity(intent);
				}
			});
			time_cancel = (Button)findViewById(R.id.accountfrom_cancell);
			time_cancel.setOnClickListener(new OnClickListener() {			
				public void onClick(View v) {
					Intent intent = new Intent();
					intent.setClass(CreditResult.this, CreditInventory.class);
					CreditResult.this.startActivity(intent);
				}
			});
		}
	}


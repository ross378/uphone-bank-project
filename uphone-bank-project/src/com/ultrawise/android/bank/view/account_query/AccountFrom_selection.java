package com.ultrawise.android.bank.view.account_query;

import com.ultrawise.android.bank.view.transfer.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;

public class AccountFrom_selection extends Activity{
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
		setContentView(R.layout.query_set_time);
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.setTitle("设置时间");
		intent = this.getIntent();
		if(intent.hasExtra("start")){
			name = intent.getStringExtra("start");
			System.out.println(name);
		}
		if(intent.hasExtra("end")){
			name = intent.getStringExtra("end");
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
				Intent intent = new Intent();
				intent.putExtra(AccountFrom_selection.this.name, time);
				intent.setClass(AccountFrom_selection.this, AccountFrom.class);
				AccountFrom_selection.this.startActivity(intent);
			}
		});
		time_cancel = (Button)findViewById(R.id.accountfrom_cancel);
		time_cancel.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(AccountFrom_selection.this, AccountFrom.class);
				AccountFrom_selection.this.startActivity(intent);
			}
		});
	}
}

package com.ultrawise.android.bank.view.account_query;

import com.ultrawise.android.bank.view.ABankMain;
import com.ultrawise.android.bank.view.FinancialConsultation;
import com.ultrawise.android.bank.view.transfer.R;

import android.R.string;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

public class AccountFrom extends Activity{

	Intent intent = new Intent();
	Button timeStart;
	Button timeOver;
	static String start_time = "开始时间";
	static String end_time = "结束时间";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.inventory);
		
		
		
		TextView tvClassFirst = (TextView) this.findViewById(R.id.class_first);
		tvClassFirst.setText("首页>");
        tvClassFirst.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				 Intent intent = new Intent();
				 intent.setClass(AccountFrom.this, ABankMain.class);
				 AccountFrom.this.startActivity(intent);
			}
		});
        tvClassFirst.setVisibility(View.VISIBLE);

		TextView tvClassSecond = (TextView) this.findViewById(R.id.class_second);
		tvClassSecond.setText("账户查询>");
		tvClassSecond.setVisibility(View.VISIBLE);
        tvClassSecond.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				 intent.setClass(AccountFrom.this,AccountQuery.class);
				 startActivity(intent);
			}
		});
        tvClassSecond.setVisibility(View.VISIBLE);
		TextView tvClassSecond1 = (TextView) this.findViewById(R.id.class_third);
		tvClassSecond1.setText("来帐查询");
		tvClassSecond1.setVisibility(View.VISIBLE);
		
		//设定返回按钮
		ImageView btnReturn = (ImageView)this.findViewById(R.id.returnToPre);
		btnReturn.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				// TODO Auto-generated method stub
				onBackPressed();
				finish();
			}
		});
		
		  //设置底部按钮
		ImageView	btnCoustom = (ImageView) this.findViewById(R.id.btnMain);
		btnCoustom.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent=new Intent();
				intent.setClass(AccountFrom.this, ABankMain.class);
				AccountFrom.this.startActivity(intent);
				finish();
			}
		});
		
		ImageView btnMain = (ImageView) this.findViewById(R.id.btnHelper);
		btnMain.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent intent=new Intent();
				intent.setClass(AccountFrom.this,  FinancialConsultation.class);
				AccountFrom.this.startActivity(intent);
				finish();
			}
		});
		
		
		timeStart = (Button)findViewById(R.id.timechange_from);
		timeStart.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub;
//				intent.setClass(AccountFrom.this, Query_Settime.class);
//				startActivity(intent);
				Intent intent = new Intent();
				intent.setClass(AccountFrom.this, AccountFrom_selection.class);
				intent.putExtra("start", "start");
				AccountFrom.this.startActivity(intent);
			
			}
			});
		timeOver = (Button)findViewById(R.id.timechange_to);
		timeOver.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub		
		        
				Intent intent = new Intent();
				intent.setClass(AccountFrom.this, AccountFrom_selection.class);
				intent.putExtra("end", "end");
				AccountFrom.this.startActivity(intent);
			}
		});
		
		Button run = (Button)findViewById(R.id.Query_Run);
		run.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				intent.setClass(AccountFrom.this, AccountQueryEinnahme.class);
				startActivity(intent);
			}
		});
		Intent intent = this.getIntent();
        if(intent.hasExtra("start")){
        	String[] date = intent.getStringArrayExtra("start");
        	start_time = date[0]+"-"+date[1]+"-"+date[2];
        }
        timeStart.setText(start_time);
        if(intent.hasExtra("end")){
        	String[] date = intent.getStringArrayExtra("end");
        	end_time = date[0]+"-"+date[1]+"-"+date[2];
        }
        timeOver.setText(end_time);
	}
}

package com.ultrawise.android.bank.view.credit;

import java.util.ArrayList;
import java.util.List;

import com.ultrawise.android.bank.consum_webservices.CreditClient;
import com.ultrawise.android.bank.consum_webservices.QuerySever;
import com.ultrawise.android.bank.view.ABankMain;
import com.ultrawise.android.bank.view.FinancialConsultation;
import com.ultrawise.android.bank.view.account_query.Inventorylist;
import com.ultrawise.android.bank.view.account_query.inventory;
import com.ultrawise.android.bank.view.transfer.R;
import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;

import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;

import android.widget.ImageView;
import android.widget.TextView;

public class CreditInventory extends Activity {

	Button timeStart, timeOver;
	String sql = null;
	DatePicker d;
	static String start_time = "开始时间";
	static String end_time = "结束时间";
	private static  String nomber=null;
	private static  String type=null;
	String cardNo;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.creditinventory);
		 Intent receive_intent = getIntent();
		 cardNo = receive_intent.getStringExtra("cardNo");
		 System.out.println("----"+cardNo);
		TextView tvClassFirst = (TextView) this.findViewById(R.id.class_first);
		tvClassFirst.setText("首页>");
		tvClassFirst.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(CreditInventory.this, ABankMain.class);
				CreditInventory.this.startActivity(intent);
			}
		});
		tvClassFirst.setVisibility(View.VISIBLE);

		TextView tvClassSecond = (TextView) this
				.findViewById(R.id.class_second);
		tvClassSecond.setText("信用卡>");
		tvClassSecond.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent=new Intent();
				intent.setClass(CreditInventory.this,CreditView.class);
				CreditInventory.this.startActivity(intent);
			}
		});
		tvClassSecond.setVisibility(View.VISIBLE);
		TextView tvClassSecond1 = (TextView) this.findViewById(R.id.class_third);
		tvClassSecond1.setText("帐户查询");
		tvClassSecond1.setVisibility(View.VISIBLE);

		// 设定返回按钮
		ImageView btnReturn = (ImageView) this.findViewById(R.id.returnToPre);
		btnReturn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				
				onBackPressed();
				finish();
			}
		});
		
		  //设置底部按钮
		ImageView	btnCoustom = (ImageView) this.findViewById(R.id.btnMain);
		btnCoustom.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent=new Intent();
				intent.setClass(CreditInventory.this, ABankMain.class);
				CreditInventory.this.startActivity(intent);
				finish();
			}
		});
		
		ImageView btnMain = (ImageView) this.findViewById(R.id.btnHelper);
		btnMain.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent intent=new Intent();
				intent.setClass(CreditInventory.this,  FinancialConsultation.class);
				CreditInventory.this.startActivity(intent);
				finish();
			}
		});
		
		/**
		 * 
		 * 起始时间按钮
		 */
		
		timeStart = (Button) findViewById(R.id.timechange_fromm);
		timeStart.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				Intent intent = new Intent();
				intent.putExtra("start", "start");
				
				/**
				 * 第一次传
				 */
				intent.putExtra("nomber", nomber);
				intent.putExtra("type",type);
				intent.putExtra("cardNo",cardNo);
				
				intent.setClass(CreditInventory.this, CreditResult.class);
				CreditInventory.this.startActivity(intent);
			}
		});
		timeOver = (Button) findViewById(R.id.timechange_too);
		timeOver.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				
				Intent intent = new Intent();
				intent.putExtra("end", "end");
				/**
				 * 第二次传
				 */
				intent.putExtra("nomber", nomber);
				intent.putExtra("type",type);
				intent.putExtra("cardNo",cardNo);
				intent.setClass(CreditInventory.this, CreditResult.class);
				CreditInventory.this.startActivity(intent);
			}
		});
		Button run = (Button) findViewById(R.id.Query_Runn);
		
		
		
		
		run.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				
				Intent intent=new Intent();
				intent.putExtra("cardNo",cardNo);
				String start=timeStart.getText().toString().trim();
				String end=timeOver.getText().toString().trim();
			    List<String> jiaoyi=new ArrayList<String>();
			    jiaoyi.add(cardNo);
			    System.out.println("123aa"+cardNo);
			    jiaoyi.add(start);
			    jiaoyi.add(end);
			    List<String> accuss=CreditClient.connectHttp("420", jiaoyi);
			    intent.putExtra("result", accuss.get(0));
			    intent.putExtra("start", start);
				intent.putExtra("end", end);
				intent.setClass(CreditInventory.this, CreditInventorylist.class);
				CreditInventory.this.startActivity(intent);
			}
		});
		
		Intent intent = this.getIntent();
        if(intent.hasExtra("start")){
        	String[] date = intent.getStringArrayExtra("start");
        	nomber=date[3];
        	type=date[4];
        	
        	start_time = date[0]+"-"+date[1]+"-"+date[2];
        	
        }
        timeStart.setText(start_time);
        
        if(intent.hasExtra("end")){
        	String[] date = intent.getStringArrayExtra("end");
        	end_time = date[0]+"-"+date[1]+"-"+date[2];
        	
        	nomber=date[3];
        	type=date[4];
        }
        timeOver.setText(end_time);
	}
}

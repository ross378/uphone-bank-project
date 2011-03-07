package com.ultrawise.android.bank.view.payment;

import java.util.ArrayList;
import java.util.HashMap;

import com.ultrawise.android.bank.view.ABankMain;
import com.ultrawise.android.bank.view.FinancialConsultation;
import com.ultrawise.android.bank.view.transfer.R;

import android.app.ListActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class PaymentSerDetail extends ListActivity {
	String pay_title;
	String ser_num;
	String pay_amount;
	String inputed_peo;
	String pay_sernum;
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_details);
        
        TextView tvClassFirst = (TextView)this.findViewById(R.id.class_first);
		tvClassFirst.setText("首页>");
		tvClassFirst.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				 intent.setClass(PaymentSerDetail.this, ABankMain.class);
				 PaymentSerDetail.this.startActivity(intent);	
			}
		});
		
		tvClassFirst.setVisibility(View.VISIBLE);
		
        TextView tvClassSecond = (TextView)this.findViewById(R.id.class_second);
        tvClassSecond.setText("自助缴费>");
        tvClassSecond.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent payment_intent = new Intent();
				payment_intent.setClass(PaymentSerDetail.this, PaymentMain.class);
				PaymentSerDetail.this.startActivity(payment_intent);	
			}
		});
		
        tvClassSecond.setVisibility(View.VISIBLE);
		
		
		
		
		
		TextView tvClassThird = (TextView)this.findViewById(R.id.class_third);
		tvClassThird.setText("便捷服务");	
		tvClassThird.setVisibility(View.VISIBLE);
		
		

		
		
		 Intent paymentre_intent = getIntent();
		
		  pay_title = paymentre_intent.getStringExtra("title");
		  ser_num=paymentre_intent.getStringExtra("service_num");
		  
	      pay_amount = paymentre_intent.getStringExtra("amount");
	        inputed_peo= paymentre_intent.getStringExtra("inputed_peo");
	        pay_sernum = paymentre_intent.getStringExtra("serialnum");
//	        String pay_deadline=paymentre_intent.getStringExtra("deadline");
	     ArrayList<HashMap<String,Object>> mainlist = new ArrayList<HashMap<String,Object>>();
	        
	        HashMap<String,Object> paylist1 = new HashMap<String,Object>();
	       
	        paylist1.put("listimg1","项目名称：");
	        paylist1.put("payment_list",pay_title+"充值");
	        mainlist.add(paylist1);
	       
	        
	        paylist1 = new HashMap<String,Object>();
		       
	        paylist1.put("listimg1","目标"+pay_title+"号：");
	        paylist1.put("payment_list",ser_num);
	        mainlist.add(paylist1);
	        
	        paylist1 = new HashMap<String,Object>();
		       
	        paylist1.put("listimg1","缴费金额：");
	        paylist1.put("payment_list",pay_amount);
	        mainlist.add(paylist1);
	        
	        
	        paylist1 = new HashMap<String,Object>();
		       
	        paylist1.put("listimg1","收费方：");
	        paylist1.put("payment_list",inputed_peo);
	        mainlist.add(paylist1);
	        
	        
	        paylist1 = new HashMap<String,Object>();
		       
	        paylist1.put("listimg1","缴费合同号：");
	        paylist1.put("payment_list",pay_sernum);
	        mainlist.add(paylist1);
	        
//	        paylist1 = new HashMap<String,Object>();
//		       
//	        paylist1.put("listimg1","缴费期限:");
//	        paylist1.put("payment_list",pay_deadline);
//	        mainlist.add(paylist1);
	        
	        
	       
	        SimpleAdapter MainListAdapter = new SimpleAdapter(
	        		this, mainlist,R.layout.payment_details_list, new String[]{
	        				"listimg1","payment_list"},new int[]{R.id.payment_list,R.id.payment_list2} );
	        this.setListAdapter(MainListAdapter);
        
 
        
        Button btn_pay_ok = (Button)findViewById(R.id.btn_pay_ok);
       btn_pay_ok.setText("前往缴费");
        
        
       //返回键设定
       ImageView    btnReturn = (ImageView)this.findViewById(R.id.returnToPre);
       btnReturn.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				// TODO Auto-generated method stub
				onBackPressed();
				finish();
			}
			
		});
		
		//底部两个按钮
       ImageView	btnMain = (ImageView) this.findViewById(R.id.btnMain);
       btnMain.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(PaymentSerDetail.this, ABankMain.class);
				PaymentSerDetail.this.startActivity(intent);
			}
		});
		
       ImageView	btnHelper = (ImageView) this.findViewById(R.id.btnHelper);
       btnHelper.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent	intent = new Intent();
				intent.setClass(PaymentSerDetail.this, FinancialConsultation.class);
				PaymentSerDetail.this.startActivity(intent);
			}
		});
        
        
        
        
        ImageView iv_now = (ImageView)this.findViewById(R.id.btnCoustom);
//        iv_now.setVisibility(View.VISIBLE);
        
        btn_pay_ok.setOnClickListener(new View.OnClickListener(){
        	public void onClick(View v){
        		Intent transconok_intent = new Intent();
        		//将项目名字，号码，收费方 ，合同号，  	
        		transconok_intent.putExtra("pay_name", pay_title+"#"+ser_num+"#"+inputed_peo+"#"+pay_sernum);	
        		transconok_intent.putExtra("pay_num", pay_amount);//将项目缴费金额传递
        		
        		transconok_intent.setClass(PaymentSerDetail.this, PaymentSelectAccountType.class);
        		startActivity(transconok_intent);
        	}
        });
	}
}
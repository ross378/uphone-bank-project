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

public class PaymentDetail extends ListActivity {
	String[] inmation;
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_details);
        
        TextView tvClassFirst = (TextView)this.findViewById(R.id.class_first);
		tvClassFirst.setText("首页>");
		tvClassFirst.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				 intent.setClass(PaymentDetail.this, ABankMain.class);
				 PaymentDetail.this.startActivity(intent);	
			}
		});
		
		tvClassFirst.setVisibility(View.VISIBLE);
		
        TextView tvClassSecond = (TextView)this.findViewById(R.id.class_second);
        tvClassSecond.setText("自助缴费>");
        tvClassSecond.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent payment_intent = new Intent();
				payment_intent.setClass(PaymentDetail.this, PaymentMain.class);
				PaymentDetail.this.startActivity(payment_intent);	
			}
		});
		
        tvClassSecond.setVisibility(View.VISIBLE);
		
		
		
		
		
		TextView tvClassThird = (TextView)this.findViewById(R.id.class_third);
		tvClassThird.setText("待缴费项目");
		tvClassThird.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Intent payment_intent = new Intent();
				payment_intent.setClass(PaymentDetail.this, PaymentPend.class);
				PaymentDetail.this.startActivity(payment_intent);	
				
			}
		});
		
		
		tvClassThird.setVisibility(View.VISIBLE);
	       
	        /*从上一个界面传过来
	         * 其中的过程是先从上一个页面将参数和功能号传递到服务器，
	         * 并经过处理后返回一个String过来
	         * 
	        */  
	        Intent get_intent = getIntent();
	      inmation=get_intent.getStringArrayExtra("pay_arr");
	     // 用到了inmation中的各个项      其中inmation【1】和inmation【3】中分别表示的是项目名和金额的值
	      //后面也需要这两个参数 
		 ArrayList<HashMap<String,Object>> mainlist = new ArrayList<HashMap<String,Object>>();		        
	      for(int i=0;i<inmation.length;i=i+2){
	      HashMap<String,Object> paylist1 = new HashMap<String,Object>();
	      paylist1.put("listimg1",inmation[i]+":");
	      paylist1.put("payment_list",inmation[i+1]);
	      mainlist.add(paylist1);
	      }      
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
				intent.setClass(PaymentDetail.this, ABankMain.class);
				PaymentDetail.this.startActivity(intent);
			}
		});
		
       ImageView	btnHelper = (ImageView) this.findViewById(R.id.btnHelper);
       btnHelper.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent	intent = new Intent();
				intent.setClass(PaymentDetail.this, FinancialConsultation.class);
				PaymentDetail.this.startActivity(intent);
			}
		});
        
        
        
        
        ImageView iv_now = (ImageView)this.findViewById(R.id.btnCoustom);
//        iv_now.setVisibility(View.VISIBLE);
        
        btn_pay_ok.setOnClickListener(new View.OnClickListener(){
        	public void onClick(View v){
        		//取出inmation中的连个关键值 传到后面
        		
        		Intent transconok_intent = new Intent();
        		transconok_intent.putExtra("pay_name", inmation[1]);//将项目名字传递
        		transconok_intent.putExtra("pay_num", inmation[3]);//将项目缴费金额传递
        		transconok_intent.setClass(PaymentDetail.this, PaymentSelectAccountType.class);
        		startActivity(transconok_intent);
        	}
        });
	}
}
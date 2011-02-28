package com.ultrawise.android.bank.view.payment;

import java.util.ArrayList;
import java.util.HashMap;

import com.ultrawise.android.bank.view.ABankMain;
import com.ultrawise.android.bank.view.FinancialConsultation;
import com.ultrawise.android.bank.view.transfer.R;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class PaymentSelfService extends ListActivity {
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_main);
        
        TextView tvClassFirst = (TextView)this.findViewById(R.id.class_first);
		tvClassFirst.setText("首页>");
		tvClassFirst.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				
				
				Intent intent = new Intent();
				 intent.setClass(PaymentSelfService.this, ABankMain.class);
				 PaymentSelfService.this.startActivity(intent);
			}
		});
		tvClassFirst.setVisibility(View.VISIBLE);
		
		TextView tvClassSecond = (TextView)this.findViewById(R.id.class_second);
		tvClassSecond.setText("自助缴费>");
		tvClassSecond.setVisibility(View.VISIBLE);
		tvClassSecond.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Intent payment_intent = new Intent();
				payment_intent.setClass(PaymentSelfService.this, PaymentMain.class);
				PaymentSelfService.this.startActivity(payment_intent);
				
				
			}
		});
		
		
		
		
		TextView tvClassThird= (TextView)this.findViewById(R.id.class_third);
		tvClassThird.setText("便捷服务");
		tvClassThird.setVisibility(View.VISIBLE);
		
		tvClassThird.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		
		
		
      
	      ArrayList<HashMap<String,Object>> mainlist = new ArrayList<HashMap<String,Object>>();
	        
	        HashMap<String,Object> paylist1 = new HashMap<String,Object>();
	       

	        paylist1.put("payment_list","手机充值");
	        paylist1.put("listimg2", R.drawable.trans_main2);
	        mainlist.add(paylist1);
	        
	        
	        paylist1 = new HashMap<String,Object>();
	        paylist1.put("payment_list","Q币充值");
	        paylist1.put("listimg2", R.drawable.trans_main2);
	        mainlist.add(paylist1);
	        
	        paylist1 = new HashMap<String,Object>();
	        paylist1.put("payment_list","网易点卡充值");
	        paylist1.put("listimg2", R.drawable.trans_main2);
	        mainlist.add(paylist1);
        
        
      
        
 
        
        
        SimpleAdapter MainListAdapter = new SimpleAdapter(this, mainlist,R.layout.payment_main_list, new String[]{
        		"payment_list","listimg2"},new int[]{R.id.payment_list,R.id.listimg2 } );
        this.setListAdapter(MainListAdapter);
        
        ImageView iv_now = (ImageView)this.findViewById(R.id.btnCoustom);
//        iv_now.setVisibility(View.VISIBLE);
        
	}
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);

       if(id==0){
			Intent payment_intent = new Intent();
			payment_intent.putExtra("ser_name", "手机充值");
			payment_intent.setClass(PaymentSelfService.this, AllPaymentSer.class);
			PaymentSelfService.this.startActivity(payment_intent);
		}else if(id==1){
			Intent payment_intent = new Intent();
			payment_intent.putExtra("ser_name", "QQ");
			payment_intent.setClass(PaymentSelfService.this, AllPaymentSer.class);
			PaymentSelfService.this.startActivity(payment_intent);
		}else if(id==2){			
			Intent payment_intent = new Intent();
			payment_intent.putExtra("ser_name", "网易");
			payment_intent.setClass(PaymentSelfService.this, AllPaymentSer.class);
			PaymentSelfService.this.startActivity(payment_intent);
//			System.out.println("id----------------"+id);
//	    	System.out.println("position----------"+position);
			//Intent payment_intent = new Intent();
			//payment_intent.setClass(PaymentSelfService.this, PaymentDefAcc.class);
		}
	}
}
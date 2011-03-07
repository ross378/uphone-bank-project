package com.ultrawise.android.bank.view.payment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ultrawise.android.bank.consum_webservices.PaymentWebservices;
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
				intent.setClass(PaymentSelfService.this, ABankMain.class);
				PaymentSelfService.this.startActivity(intent);
			}
		});
		
        ImageView	btnHelper = (ImageView) this.findViewById(R.id.btnHelper);
        btnHelper.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent	intent = new Intent();
				intent.setClass(PaymentSelfService.this, FinancialConsultation.class);
				PaymentSelfService.this.startActivity(intent);
			}
		});
		
	      ArrayList<HashMap<String,Object>> mainlist = new ArrayList<HashMap<String,Object>>();
	        
	        HashMap<String,Object> paylist1 = null;
	       
	        PaymentWebservices.paramsString="payment";
	        List<String> params = new ArrayList<String>();
	        String[] values = PaymentWebservices.connectHttp("60201", params);
	        for(String val:values){
	        	paylist1 = new HashMap<String,Object>();
	        	paylist1.put("payment_list",val);
		        paylist1.put("listimg2", R.drawable.trans_main2);
		        mainlist.add(paylist1);
	        }
        
        SimpleAdapter MainListAdapter = new SimpleAdapter(this, mainlist,R.layout.payment_list, new String[]{
        		"payment_list","listimg2"},new int[]{R.id.payment_11,R.id.listimg33} );
        this.setListAdapter(MainListAdapter);
        
        ImageView iv_now = (ImageView)this.findViewById(R.id.btnCoustom);
//        iv_now.setVisibility(View.VISIBLE);
        
	}
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		
		Intent payment_intent = new Intent();
		payment_intent.putExtra("ser_name", String.valueOf(id + 1));
		payment_intent.setClass(PaymentSelfService.this, AllPaymentSer.class);
		PaymentSelfService.this.startActivity(payment_intent);
		
//       if(id==0){
//			Intent payment_intent = new Intent();
//			payment_intent.putExtra("ser_name", "手机");
//			payment_intent.setClass(PaymentSelfService.this, AllPaymentSer.class);
//			PaymentSelfService.this.startActivity(payment_intent);
//		}else if(id==1){
//			Intent payment_intent = new Intent();
//			payment_intent.putExtra("ser_name", "QQ");
//			payment_intent.setClass(PaymentSelfService.this, AllPaymentSer.class);
//			PaymentSelfService.this.startActivity(payment_intent);
//		}else if(id==2){			
//			Intent payment_intent = new Intent();
//			payment_intent.putExtra("ser_name", "网易");
//			payment_intent.setClass(PaymentSelfService.this, AllPaymentSer.class);
//			PaymentSelfService.this.startActivity(payment_intent);
//		}
	}
}
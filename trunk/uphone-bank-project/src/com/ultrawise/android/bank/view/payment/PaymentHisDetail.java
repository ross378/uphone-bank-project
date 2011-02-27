package com.ultrawise.android.bank.view.payment;

import java.util.ArrayList;
import java.util.HashMap;

import com.ultrawise.android.bank.view.ABankMain;
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

public class PaymentHisDetail extends ListActivity {	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_main);
        
        Intent payhisdtl_intent = getIntent();
        
        String tvtitle = payhisdtl_intent.getStringExtra("title");
        String tvamount = payhisdtl_intent.getStringExtra("amount");
        String tvtime = payhisdtl_intent.getStringExtra("time");
        String tvsernum = payhisdtl_intent.getStringExtra("serialnum");
        String tvacc = payhisdtl_intent.getStringExtra("acc");
        
        ArrayList<HashMap<String,Object>> mainlist = new ArrayList<HashMap<String,Object>>();
        
        HashMap<String,Object> paylist1 = new HashMap<String,Object>();
        
        paylist1 = new HashMap<String,Object>();  
        paylist1.put("listimg1","缴费时间：");
        paylist1.put("payment_list",tvtime);
        mainlist.add(paylist1);
        
        paylist1.put("listimg1","缴费项目：");
        paylist1.put("payment_list",tvtitle);
        mainlist.add(paylist1);
        
        paylist1 = new HashMap<String,Object>();  
        paylist1.put("listimg1","缴费账号：");
        paylist1.put("payment_list",tvacc);
        mainlist.add(paylist1);
        
        paylist1 = new HashMap<String,Object>();  
        paylist1.put("listimg1","缴费金额：");
        paylist1.put("payment_list",tvamount);
        mainlist.add(paylist1);
        
        paylist1 = new HashMap<String,Object>();  
        paylist1.put("listimg1","项目流水号：");
        paylist1.put("payment_list",tvsernum);
        mainlist.add(paylist1);
        
        SimpleAdapter MainListAdapter = new SimpleAdapter(
        		this, mainlist,R.layout.payment_details_list, new String[]{
        				"listimg1","payment_list"},new int[]{R.id.payment_list,R.id.payment_list2} );
        
       /* hisDetail.setAdapter(MainListAdapter);*/
        this.setListAdapter(MainListAdapter);
        TextView tvClassFirst = (TextView)this.findViewById(R.id.class_first);
		tvClassFirst.setText("首页>");
		tvClassFirst.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(PaymentHisDetail.this, ABankMain.class);
				PaymentHisDetail.this.startActivity(intent);
			}
		});
		tvClassFirst.setVisibility(View.VISIBLE);
        TextView tvClassSecond = (TextView)this.findViewById(R.id.class_second);
        tvClassSecond.setText("自助缴费>");
        tvClassSecond.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(PaymentHisDetail.this, PaymentMain.class);
				PaymentHisDetail.this.startActivity(intent);
			}
		});
        tvClassSecond.setVisibility(View.VISIBLE);
		
		TextView tvClassThird = (TextView)this.findViewById(R.id.class_third);
		tvClassThird.setText("历史缴费记录");
		tvClassThird.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(PaymentHisDetail.this, PaymentHistory.class);
				PaymentHisDetail.this.startActivity(intent);
			}
		});
		tvClassThird.setVisibility(View.VISIBLE);
        
        ImageView iv_now = (ImageView)this.findViewById(R.id.btnCoustom);
        iv_now.setVisibility(View.VISIBLE);
        
        //btn_payhisdl_ok.setOnClickListener(new BtnHistlCL());
	}
	class BtnHistlCL implements OnClickListener{
		public void onClick(View v){
			Intent payhisbk_intent = new Intent();
			payhisbk_intent.setClass(PaymentHisDetail.this, PaymentMain.class);
			startActivity(payhisbk_intent);
		}
	}
}
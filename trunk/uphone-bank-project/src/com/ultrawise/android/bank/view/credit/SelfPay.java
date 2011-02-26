package com.ultrawise.android.bank.view.credit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.ultrawise.android.bank.view.ABankMain;
import com.ultrawise.android.bank.view.transfer.R;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class SelfPay extends ListActivity {
	TextView  tvCredit;
	Intent intent;
	ImageView btnCoustom;
	ImageView btnMain;
	HashMap<String,Object> map1;
	HashMap<String,Object> map2;
	 ArrayList<HashMap<String,Object>> list;
	 
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.selepay);
	        
	        ImageView iv_now = (ImageView)this.findViewById(R.id.btnCoustom);
	        iv_now.setVisibility(View.GONE);
		    	intent = new Intent();
		         tvCredit= (TextView)this.findViewById(R.id.class_first);
		        tvCredit.setText("首页>信用卡>信用卡还款 ");
		        tvCredit.setTextSize(10);
		        tvCredit.setOnClickListener(new OnClickListener() {
					public void onClick(View v) {
						 intent.setClass(SelfPay.this, ABankMain.class);
						 SelfPay.this.startActivity(intent);
					}
				});
		        tvCredit.setVisibility(View.VISIBLE);
	         list=new ArrayList<HashMap<String,Object>>();
	         map1=new HashMap<String,Object>();
	         map2=new HashMap<String,Object>();
	        //已绑定信用卡还款
	      ImageView img1=(ImageView)findViewById(R.id.creditimgpay);
	        img1.setOnClickListener(new OnClickListener() {
				
				public void onClick(View v) {
					// TODO Auto-generated method stub
					  map1.put("creditNo_key","11111111111111111111");
				        map1.put("selfPay_value", R.id.selfPay_value);
				        map2.put("creditNo_key","22222222222222222222");
				        map2.put("selfPay_value",R.id.selfPay_value);
				        list.add(map1);
				        list.add(map2);
				      //  SimpleAdapter listAdapter=new SimpleAdapter(this,list,R.layout.selfpaylist,new String[]{"creditNo_key","selfPay_value"},new int[]{R.id.creditNo_key,R.id.selfPay_value});
				       
				      //  setListAdapter(listAdapter);
				}
			});
	        TextView pay1=(TextView)findViewById(R.id.selfPayText);
	        pay1.setOnClickListener(new OnClickListener() {
				
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
				}
			});
	        Button returnpay=(Button)findViewById(R.id.selfPay_value);
	        returnpay.setOnClickListener(new OnClickListener() {
				
				public void onClick(View v) {
					// TODO Auto-generated method stub
		
				}
			});
	        //设置底部按钮
			btnCoustom = (ImageView) this.findViewById(R.id.btnMain);
			btnCoustom.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					// TODO Auto-generated method stub
					intent.setClass(SelfPay.this, ABankMain.class);
					SelfPay.this.startActivity(intent);
				}
			});
			//btnCoustom.setVisibility(View.VISIBLE);
			
		btnMain = (ImageView) this.findViewById(R.id.btnHelper);
			btnMain.setOnClickListener(new OnClickListener() {

				public void onClick(View v) {
					// TODO Auto-generated method stub
					intent.setClass(SelfPay.this, ABankMain.class);
					SelfPay.this.startActivity(intent);
				}
			});
	    }
	 public void onListItemClick(ListView l,View v,int position,long id){
	    	super.onListItemClick(l, v, position, id);
	    	HashMap<String,String> map=(HashMap<String,String>)l.getItemAtPosition(position);
	    	System.out.println(map);
	    	String selfPayBal=map.get(1);
	    	System.out.print(selfPayBal);
	    	String selfPayActNo=null;
	    	Iterator<String> iter=map.keySet().iterator();
	    	if(iter.hasNext())selfPayActNo=iter.next();
	    	System.out.print(selfPayActNo);
	    	Intent intent=new Intent();
	    	intent.putExtra("selfPayActNo",selfPayBal);
	    	intent.putExtra("selfPayBal",selfPayActNo);
	    	intent.setClass(SelfPay.this,SelfPayAct.class);
	    	SelfPay.this.startActivity(intent);
	    }
	 	
}

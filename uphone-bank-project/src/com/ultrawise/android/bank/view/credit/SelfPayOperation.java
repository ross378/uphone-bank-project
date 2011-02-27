package com.ultrawise.android.bank.view.credit;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.ultrawise.android.bank.view.ABankMain;
import com.ultrawise.android.bank.view.transfer.R;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class SelfPayOperation extends ListActivity {
	Intent intent;
	ImageView btnCoustom;
	ImageView btnMain;
	private Spinner pakitSpinner = null;
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.selfpayoperation);
	        
	        ImageView iv_now = (ImageView)this.findViewById(R.id.btnCoustom);
	        iv_now.setVisibility(View.GONE);
		    	intent = new Intent();
		        TextView tvCredit= (TextView)this.findViewById(R.id.class_first);
		        tvCredit.setText("首页>信用卡>信用卡还款 ");
		        tvCredit.setOnClickListener(new OnClickListener() {
					public void onClick(View v) {
						 intent.setClass(SelfPayOperation.this, ABankMain.class);
						 SelfPayOperation.this.startActivity(intent);
					}
				});
		        tvCredit.setVisibility(View.VISIBLE);
	        ArrayList<HashMap<String,String>> list=new ArrayList<HashMap<String,String>>();
	        HashMap<String,String> map1=new HashMap<String,String>();
	        HashMap<String,String> map2=new HashMap<String,String>();
	        map1.put("creditNo_key", "还款账户：");
	        map1.put("selfPay_value", "11111111111111111111");
	        map2.put("creditNo_key","账户余额：");
	        map2.put("selfPay_value", "￥100000");
	        list.add(map1);
	        list.add(map2);
	        SimpleAdapter listAdapter=new SimpleAdapter(this,list,R.layout.selfpayoperationlist,new String[]{"creditNo_key","selfPay_value"},new int[]{R.id.creditNo_key2,R.id.selfPay_value2});
	        setListAdapter(listAdapter);
	        pakitSpinner=(Spinner)findViewById(R.id.spinnerpay);
	        //初始化证件类型控件值
		       final String[] arrs=new String[]{"500","1000","2000"};
		        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,arrs);

		        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);   

		        pakitSpinner.setAdapter(adapter);  
		        pakitSpinner.setSelection(1,true);

		         pakitSpinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){

		                public void onItemSelected(AdapterView<?> parent, View arg1, int position, long arg3){


		                	//pakitPostion=parent.getSelectedItemPosition();
		                	parent.setVisibility(View.VISIBLE);

		                }
		                public void onNothingSelected(AdapterView<?> parent){
		                		
		                }

		            });

	        
	        //设置底部按钮
			btnCoustom = (ImageView) this.findViewById(R.id.btnMain);
			btnCoustom.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					// TODO Auto-generated method stub
					intent.setClass(SelfPayOperation.this, ABankMain.class);
					SelfPayOperation.this.startActivity(intent);
				}
			});
			//btnCoustom.setVisibility(View.VISIBLE);
			
		btnMain = (ImageView) this.findViewById(R.id.btnHelper);
			btnMain.setOnClickListener(new OnClickListener() {

				public void onClick(View v) {
					// TODO Auto-generated method stub
					intent.setClass(SelfPayOperation.this, ABankMain.class);
					SelfPayOperation.this.startActivity(intent);
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
	    	intent.setClass(SelfPayOperation.this,SelfPayAct.class);
	    	SelfPayOperation.this.startActivity(intent);
	    }
	 	
}

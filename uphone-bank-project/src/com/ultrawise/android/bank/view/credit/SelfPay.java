package com.ultrawise.android.bank.view.credit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.ultrawise.android.bank.view.ABankMain;
import com.ultrawise.android.bank.view.account_query.AccountQuery;
import com.ultrawise.android.bank.view.credit.ActivateCard.ActivateCardButtonListener;
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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class SelfPay extends ListActivity {
	TextView  tvCredit;
	Intent intent;
	ImageView btnCoustom;
	ImageView btnMain;
	Button nextButton=null;
	
	//证件类型下拉框
	private Spinner pakitSpinner=null;
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
						intent=new Intent();
						 intent.setClass(SelfPay.this, ABankMain.class);
						 SelfPay.this.startActivity(intent);
					}
				});
		        tvCredit.setVisibility(View.VISIBLE);

		        //获得证件类型控件对象
		        pakitSpinner=(Spinner)findViewById(R.id.pakitSpinnercard);
	        //已绑定信用卡还款
		        ArrayList<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();
			      
		        HashMap<String,Object> map = new HashMap<String,Object>();
		        map.put("creditNo_key","12212133232323232");
		        map.put("selfPay_value","还款");
		        list.add(map);
		        map=new HashMap<String, Object>();
		        map.put("creditNo_key","12212133232323345");
		        map.put("selfPay_value","还款");
		        list.add(map);
		        SimpleAdapter TransMainAdapter = new SimpleAdapter(this,list,R.layout.selfpaylist,new String[]{"creditNo_key","selfPay_value"},new int[]{R.id.creditNo_key,R.id.selfPay_value});
		        this.setListAdapter(TransMainAdapter);
	        //设置底部按钮
			btnCoustom = (ImageView) this.findViewById(R.id.btnMain);
			btnCoustom.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					// TODO Auto-generated method stub
					intent=new Intent();
					intent.setClass(SelfPay.this, ABankMain.class);
					SelfPay.this.startActivity(intent);
				}
			});
		               	//btnCoustom.setVisibility(View.VISIBLE);
		btnMain = (ImageView) this.findViewById(R.id.btnHelper);
			btnMain.setOnClickListener(new OnClickListener() {

				public void onClick(View v) {
					// TODO Auto-generated method stub
					intent=new Intent();
					intent.setClass(SelfPay.this, ABankMain.class);
					SelfPay.this.startActivity(intent);
				}
			});
			  //获得下一步按钮对象，并设置其鼠标单击事件监听
		       nextButton=(Button)this.findViewById(R.id.selfPay_next);
		        nextButton.setOnClickListener(new OnClickListener() {
					public void onClick(View v) {
						// TODO Auto-generated method stub
						intent = new Intent();
						intent.setClass(SelfPay.this, SelfPayDetail.class);
						SelfPay.this.startActivity(intent);
					}
				});
			 //初始化证件类型控件值
		       final String[] arrs=new String[]{"34343545454550","34343545454554","34343545454556","34343545454557"};
		        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,arrs);

		        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);   

		        pakitSpinner.setAdapter(adapter);  
		        pakitSpinner.setSelection(1,true);

		         pakitSpinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){

		                public void onItemSelected(AdapterView<?> parent, View arg1, int position, long arg3){


		                	//pakitPostion=parent.getSelectedItemPosition();
		                	parent.setVisibility(View.VISIBLE);
		                }

						public void onNothingSelected(AdapterView<?> arg0) {
							// TODO Auto-generated method stub
							
						}
		         });
	    }
	 public void onListItemClick(ListView l, View v, int position, long id) {
			
		 super.onListItemClick(l, v, position, id);
			
			if (id == 0) {//第一个还款
				intent= new Intent();
				intent.setClass(SelfPay.this, AccountQuery.class);
				SelfPay.this.startActivity(intent);
			}else if(id==1){//第二个还款
				intent = new Intent();
				intent.setClass(SelfPay.this, AccountQuery.class);
				SelfPay.this.startActivity(intent);
			}
	 }
}

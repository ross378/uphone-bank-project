package com.ultrawise.android.bank.view.credit;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.ultrawise.android.bank.view.ABankMain;
import com.ultrawise.android.bank.view.FinancialConsultation;
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
	Button queren;
	Button cancle;
	EditText password;
	EditText paymuch;
	String cardDetail;
	String creditNo;
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.selfpayoperation);
	        Intent receive_intent = getIntent();
	        cardDetail = receive_intent.getStringExtra("cardDetail");
	        ImageView iv_now = (ImageView)this.findViewById(R.id.btnCoustom);
	        iv_now.setVisibility(View.GONE);
		    	intent = new Intent();
		        TextView tvCredit= (TextView)this.findViewById(R.id.class_first);
		        tvCredit.setText("首页>信用卡>信用卡还款 ");
		        tvCredit.setTextSize(13);
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
	        String[] str=cardDetail.split(":");
	        map1.put("creditNo_key", "还款账户：");
	        map1.put("selfPay_value", str[0]);
	        map2.put("creditNo_key","账户余额：");
	        map2.put("selfPay_value", str[2]);
	        list.add(map1);
	        list.add(map2);
	        SimpleAdapter listAdapter=new SimpleAdapter(this,list,R.layout.selfpayoperationlist,new String[]{"creditNo_key","selfPay_value"},new int[]{R.id.creditNo_key2,R.id.selfPay_value2});
	        setListAdapter(listAdapter);
	     paymuch=(EditText)findViewById(R.id.paymuch);
	     
           password=(EditText)findViewById(R.id.paypassword);
	        creditNo=str[0];
		  //确定，取消按钮
		         queren=(Button)findViewById(R.id.btn_paymentOk);
		         cancle=(Button)findViewById(R.id.btn_paymentCancle);
		         queren.setOnClickListener(new payButtonListener());
		         cancle.setOnClickListener(new OnClickListener() {
						public void onClick(View v) {
							// TODO Auto-generated method stub
							intent.setClass(SelfPayOperation.this, SelfPay.class);
							SelfPayOperation.this.startActivity(intent);
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
					intent.setClass(SelfPayOperation.this, FinancialConsultation.class);
					SelfPayOperation.this.startActivity(intent);
				}
			});
	    }
	 public void onListItemClick(ListView l,View v,int position,long id){
	    	super.onListItemClick(l, v, position, id);
	    }
	 class payButtonListener implements OnClickListener{

			public void onClick(View arg0) {
				int cancelFlag=0;
				String info=null;
				String flag=null;
				String creditPasswd;
				double money;
				try{
					money=Double.parseDouble(paymuch.getText().toString());
				}catch(Exception e)
				{
					Toast.makeText(SelfPayOperation.this, "请输入正确的还款金额", Toast.LENGTH_SHORT).show();
					return;
				}
				
				creditPasswd=password.getText().toString();
				
				if(creditPasswd.trim().equals("123")==false)
				{
					cancelFlag=3;
					flag="失败提示：";
					info="        还款失败!请确认所               \n       填密码是否正确!            ";
				}else{
					cancelFlag=4;
					flag="成功提示：";
					info="         还款成功！                \n";
				}
				Intent intent = new Intent();
				intent.putExtra("flag", flag);
				intent.putExtra("info",info);
				intent.putExtra("cancelFlag",cancelFlag+"");
	    		intent.setClass(SelfPayOperation.this, CancelCardDialog.class);
	    		SelfPayOperation.this.startActivity(intent);
				
			}
	 }
}

package com.ultrawise.android.bank.view.credit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ultrawise.android.bank.consum_webservices.CreditClient;
import com.ultrawise.android.bank.view.ABankMain;
import com.ultrawise.android.bank.view.FinancialConsultation;
import com.ultrawise.android.bank.view.account_query.AccountQueryDetail;
import com.ultrawise.android.bank.view.account_query.AccountQueryType;
import com.ultrawise.android.bank.view.transfer.R;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class SelfPayDetail extends ListActivity {
	TextView  tvCredit;
	ImageView btnCoustom;
	ImageView btnMain;
	Button btnContinue;
	Button detail;
	ImageView btnReturn;
	String payDetail;
	String[] cardDetail;
	 List<String> params=new ArrayList<String>();
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.selepaydetail);
	        Intent receive_intent = getIntent();
	        payDetail = receive_intent.getStringExtra("payDetail");
		         tvCredit= (TextView)this.findViewById(R.id.class_first);
		        tvCredit.setText("首页>信用卡>信用卡还款 ");
		        tvCredit.setTextSize(13);
		        tvCredit.setOnClickListener(new OnClickListener() {
					public void onClick(View v) {
						Intent intent = new Intent();
						 intent.setClass(SelfPayDetail.this, CreditView.class);
						 SelfPayDetail.this.startActivity(intent);
					}
				});
		        tvCredit.setVisibility(View.VISIBLE);
		        // 返回键设定
				btnReturn = (ImageView) this.findViewById(R.id.returnToPre);
				btnReturn.setOnClickListener(new OnClickListener() {
					public void onClick(View v) {
						// TODO Auto-generated method stub
						onBackPressed();
						finish();
					}
				});
		        //设置底部按钮
				btnCoustom = (ImageView) this.findViewById(R.id.btnMain);
				btnCoustom.setOnClickListener(new OnClickListener() {
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent intent = new Intent();
						intent.setClass(SelfPayDetail.this, ABankMain.class);
						SelfPayDetail.this.startActivity(intent);
					}
				});
				btnMain = (ImageView) this.findViewById(R.id.btnHelper);
				btnMain.setOnClickListener(new OnClickListener() {

					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent intent = new Intent();
						intent.setClass(SelfPayDetail.this, FinancialConsultation.class);
						SelfPayDetail.this.startActivity(intent);
					}
				});
	         cardDetail=payDetail.split(":");
	        ArrayList<HashMap<String,Object>> list=new ArrayList<HashMap<String,Object>>();
	        HashMap<String,Object> map1=new HashMap<String,Object>();
	        HashMap<String,Object> map2=new HashMap<String,Object>();
	        HashMap<String,Object> map3=new HashMap<String,Object>();
	        HashMap<String,Object> map4=new HashMap<String,Object>();
	        HashMap<String,Object> map5=new HashMap<String,Object>();
	        map1.put("creditNo_key2", "信用卡帐户：");
	        map1.put("selfPay_value2", cardDetail[0]);
	        map2.put("creditNo_key2","持卡人姓名：");
	        map2.put("selfPay_value2", cardDetail[1]);
	        map3.put("creditNo_key2","本期应还款额：");
	        map3.put("selfPay_value2", cardDetail[2]);
	        map4.put("creditNo_key2", "本期最低还款额：");
	        map4.put("selfPay_value2", cardDetail[3]);
	        map5.put("creditNo_key2","本期到期还款日：");
	        map5.put("selfPay_value2",  cardDetail[4]);
	        list.add(map1);
	        list.add(map2);
	        list.add(map3);
	        list.add(map4);
	        list.add(map5);
	        SimpleAdapter listAdapter=new SimpleAdapter(this,list,R.layout.selepaydetailist,new String[]{"creditNo_key2","selfPay_value2"},new int[]{R.id.paylistlable,R.id.paylistid});
	        setListAdapter(listAdapter);
	        //明细查看
	        detail=(Button)findViewById(R.id.selfPay_next2);
	        detail.setOnClickListener(new OnClickListener() {

				public void onClick(View v) {
					Intent intent = new Intent();
					intent.setClass(SelfPayDetail.this, AccountQueryDetail.class);
					SelfPayDetail.this.startActivity(intent);
				}
			});
	        
	     // 继续按钮跳转
			btnContinue = (Button) findViewById(R.id.selfPay_next1);
			btnContinue.setTextColor(Color.BLACK);
			btnContinue.setOnClickListener(new OnClickListener() {

				public void onClick(View v) {
					List<String> accuss=CreditClient.connectHttp("410", params);
					Intent payment_intent = new Intent();
					payment_intent.putExtra("accountpyte", accuss.get(0));
					payment_intent.setClass(SelfPayDetail.this, SelfPayAct.class);
					SelfPayDetail.this.startActivity(payment_intent);
				}
			});
	    }
	 public void onListItemClick(ListView l,View v,int position,long id){
		 
	    }
	 	
}

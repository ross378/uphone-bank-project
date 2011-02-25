package com.ultrawise.android.bank.view.credit;

import java.util.ArrayList;
import java.util.HashMap;

import com.ultrawise.android.bank.view.ABankMain;
import com.ultrawise.android.bank.view.transfer.R;
import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;


public class CreditView extends ListActivity  {
		TextView  tvCredit;
		Intent intent;
		ImageView btnCoustom;
		ImageView btnMain;
		@Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);  
	        
	        setContentView(R.layout.credit);  
	        ImageView iv_now = (ImageView)this.findViewById(R.id.btnCoustom);
        iv_now.setVisibility(View.GONE);
	    	intent = new Intent();
	        TextView tvCredit= (TextView)this.findViewById(R.id.class_first);
	        tvCredit.setText("首页>信用卡 ");
	        tvCredit.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					 intent.setClass(CreditView.this, ABankMain.class);
					 CreditView.this.startActivity(intent);
				}
			});
	        tvCredit.setVisibility(View.VISIBLE);
	        ArrayList<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();
	      
	        HashMap<String,Object> map = new HashMap<String,Object>();
	        map.put("creditimg1",R.drawable.zhen);
	        map.put("credit_list","帐户信息");
	        map.put("creditimg2", R.drawable.righticon);
	        list.add(map);
	        
	        map = new HashMap<String,Object>();
	        map.put("creditimg1",R.drawable.zhen);
	        map.put("credit_list","交易明细查看");
	        map.put("creditimg2", R.drawable.righticon);
	        list.add(map);
	        map = new HashMap<String,Object>();
	        map.put("creditimg1",R.drawable.zhen);
	        map.put("credit_list","帐户来帐查看");
	        map.put("creditimg2", R.drawable.righticon);
	        list.add(map);
	        map = new HashMap<String,Object>();
	        map.put("creditimg1",R.drawable.zhen);
	        map.put("credit_list","开卡");
	        map.put("creditimg2", R.drawable.righticon);
	        list.add(map);
	        map = new HashMap<String,Object>();
	        map.put("creditimg1",R.drawable.zhen);
	        map.put("credit_list","销卡");
	        map.put("creditimg2", R.drawable.righticon);
	        list.add(map);
	        map = new HashMap<String,Object>();
	        map.put("creditimg1",R.drawable.zhen);
	        map.put("credit_list","信用卡绑定");
	        map.put("creditimg2", R.drawable.righticon);
	        list.add(map);
	        map = new HashMap<String,Object>();
	        map.put("creditimg1",R.drawable.zhen);
	        map.put("credit_list","信用卡还款");
	        map.put("creditimg2", R.drawable.righticon);
	        list.add(map);
	        map = new HashMap<String,Object>();
	        map.put("creditimg1","");
	        map.put("credit_list","");
	        map.put("creditimg2", "");
	        list.add(map);
	        SimpleAdapter TransMainAdapter = new SimpleAdapter(this,list,R.layout.credit_list,new String[]{"creditimg1","credit_list","creditimg2"},new int[]{R.id.creditimg1,R.id.credit_list,R.id.creditimg2});
	        this.setListAdapter(TransMainAdapter);
	        //设置底部按钮
			btnCoustom = (ImageView) this.findViewById(R.id.btnMain);
			btnCoustom.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					// TODO Auto-generated method stub
					intent.setClass(CreditView.this, ABankMain.class);
					CreditView.this.startActivity(intent);
				}
			});
			//btnCoustom.setVisibility(View.VISIBLE);
			
		btnMain = (ImageView) this.findViewById(R.id.btnHelper);
			btnMain.setOnClickListener(new OnClickListener() {

				public void onClick(View v) {
					// TODO Auto-generated method stub
					intent.setClass(CreditView.this, ABankMain.class);
					CreditView.this.startActivity(intent);
				}
			});
		}
		protected void onListItemClick(ListView l, View v, int position, long id) {
			super.onListItemClick(l, v, position, id);
			if (id == 0) {//帐户信息
				Intent payment_intent = new Intent();
				payment_intent.setClass(CreditView.this, CreditDetail.class);
				CreditView.this.startActivity(payment_intent);
			}else if(id==1){//交易明细查看
				Intent payment_intent = new Intent();
				payment_intent.setClass(CreditView.this, CreditDetail.class);
				CreditView.this.startActivity(payment_intent);
			}else if(id==2){//帐户来帐查看
			    Intent payment_intent = new Intent();
				payment_intent.setClass(CreditView.this, CreditDetail.class);
				CreditView.this.startActivity(payment_intent);	
			}
			else if(id==3){//开卡
				Intent payment_intent = new Intent();
				payment_intent.setClass(CreditView.this,ActivateCard.class);
				CreditView.this.startActivity(payment_intent);
			}else if(id==4){//销卡
				Intent payment_intent = new Intent();
				payment_intent.setClass(CreditView.this, CancelTheCard.class);
				CreditView.this.startActivity(payment_intent);
			}else if(id==5){//信用卡绑定
				Intent payment_intent = new Intent();
				payment_intent.setClass(CreditView.this, CreditCardBind.class);
				CreditView.this.startActivity(payment_intent);
			}else if(id==6){//信用卡还款
				Intent payment_intent = new Intent();
				payment_intent.setClass(CreditView.this,SelfPay.class);
				CreditView.this.startActivity(payment_intent);
		}
		}
}
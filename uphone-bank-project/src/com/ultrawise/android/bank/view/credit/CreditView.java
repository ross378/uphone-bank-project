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
	        iv_now.setVisibility(View.VISIBLE);
	    	intent = new Intent();
	        TextView tvCredit= (TextView)this.findViewById(R.id.class_first);
	        tvCredit.setBackgroundColor(Color.BLUE);
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
			btnCoustom = (ImageView) this.findViewById(R.id.btnCoustom);
			btnCoustom.setImageResource(R.drawable.cardbg_sy_b);
			btnCoustom.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					// TODO Auto-generated method stub
					intent.setClass(CreditView.this, ABankMain.class);
					CreditView.this.startActivity(intent);
				}
			});
			btnCoustom.setVisibility(View.VISIBLE);
			
			btnMain = (ImageView) this.findViewById(R.id.btnMain);
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
	
	       /* //帐户信息
	        creditDetailButton=(Button)findViewById(R.id.creditDetail);
	        creditDetailButton.setOnClickListener(new CreditDetailButtonListener());
	        creditDetailButton1=(Button)findViewById(R.id.creditDetail1);
	        creditDetailButton1.setOnClickListener(new CreditDetailButtonListener());
	        //开卡
	        activateCardButton=(Button)findViewById(R.id.activateCard);
	        activateCardButton.setOnClickListener(new ActivateCardButtonListener());
	        activateCardButton1=(Button)findViewById(R.id.activateCard1);
	        activateCardButton1.setOnClickListener(new ActivateCardButtonListener());
		     //销卡
	        cancelTheCardButton=(Button)findViewById(R.id.cancelTheCard);
	        cancelTheCardButton.setOnClickListener(new CancelTheCardButtonListener());
	        cancelTheCardButton1=(Button)findViewById(R.id.cancelTheCard1);
	        cancelTheCardButton1.setOnClickListener(new CancelTheCardButtonListener());
	        
	        creditCardBindButton=(Button)findViewById(R.id.comelook);
	        creditCardBindButton.setOnClickListener(new CreditCardBindButtonListener());
	        //还款
	        creditpayButton=(Button)findViewById(R.id.creditpay);
	        creditpayButton.setOnClickListener(new CreditPayButtonListener());
	        creditpayButton1=(Button)findViewById(R.id.creditpay1);
	        creditpayButton1.setOnClickListener(new CreditPayButtonListener());
	        
	        
		}
		//帐户信息事件
		class CreditDetailButtonListener implements OnClickListener{

			public void onClick(View arg0) {
				Intent intent=new Intent();
				intent.setClass(CreditView.this,CreditDetail.class);
				CreditView.this.startActivity(intent);
				
			}
		}
		
		class ActivateCardButtonListener implements OnClickListener{

			public void onClick(View arg0) {
				Intent intent=new Intent();
				intent.setClass(CreditView.this,ActivateCard.class);
				CreditView.this.startActivity(intent);
				
			}
		}
		
		class CancelTheCardButtonListener implements OnClickListener{

			public void onClick(View arg0) {
				Intent intent=new Intent();
				intent.setClass(CreditView.this,CancelTheCard.class);
				CreditView.this.startActivity(intent);
				
			}
			
		}
		
		class CreditCardBindButtonListener implements OnClickListener{

			public void onClick(View arg0) {
				Intent intent=new Intent();
				intent.setClass(CreditView.this,CreditCardBind.class);
				CreditView.this.startActivity(intent);
				
			}
			
		}
		
		class CreditPayButtonListener implements OnClickListener{

			public void onClick(View arg0) {
				Intent intent=new Intent();
				intent.setClass(CreditView.this,SelfPay.class);
				CreditView.this.startActivity(intent);
				
			}
			
		}*/

package com.ultrawise.android.bank.view.credit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ultrawise.android.bank.consum_webservices.CreditClient;
import com.ultrawise.android.bank.view.ABankMain;
import com.ultrawise.android.bank.view.FinancialConsultation;
import com.ultrawise.android.bank.view.account_query.Query_Settime;
import com.ultrawise.android.bank.view.transfer.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;

import android.widget.Button;
import android.widget.EditText;

import android.widget.ImageView;

import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class SelfPay extends Activity {
	TextView  tvCredit;
	Intent intent;
	ImageView btnCoustom;
	ImageView btnMain;
	Button nextButton=null;
	Button paya;
	Button payaa;
	TextView creditpaya;
	TextView creditpayaa;
	ImageView btnReturn;
	EditText creditname;
	int length=5;
	ArrayList<String> array=new ArrayList<String>();
	String bindCardNo;
	 List<String> ll=new ArrayList<String>();
	 String[] card;
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.selepay);
	        Intent receive_intent = getIntent();
	        bindCardNo = receive_intent.getStringExtra("bindCardNo");
	        ImageView iv_now = (ImageView)this.findViewById(R.id.btnCoustom);
	        iv_now.setVisibility(View.GONE);
		    	intent = new Intent();
		         tvCredit= (TextView)this.findViewById(R.id.class_first);
		        tvCredit.setText("首页>信用卡>信用卡还款 ");
		        tvCredit.setTextSize(13);
		        tvCredit.setOnClickListener(new OnClickListener() {
					public void onClick(View v) {
						intent=new Intent();
						 intent.setClass(SelfPay.this, CreditView.class);
						 SelfPay.this.startActivity(intent);
					}
				});
		        tvCredit.setVisibility(View.VISIBLE);
               
		         card=bindCardNo.split(":");
		        //绑定Layout里面的ListView   
		        ListView list = (ListView) findViewById(R.id.listViewpan);   
		        //生成动态数组，加入数据   
		        ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();   
		       for(int i=0;i<card.length;i++)   
		        {   
		            HashMap<String, Object> map = new HashMap<String, Object>();   
		           map.put("creditNo", card[i]); 
		            map.put("pay", "还款");   
		           listItem.add(map);   
		         }   
		         //生成适配器的Item和动态数组对应的元素   
		        SimpleAdapter listItemAdapter = new SimpleAdapter(this,listItem,//数据源    
		            R.layout.selepay1,//ListItem的XML实现   
		            //动态数组与ImageItem对应的子项           
		            new String[] {"creditNo","pay"},    
		          //ImageItem的XML文件里面的一个ImageView,两个TextView ID   
		            new int[] {R.id.creditpaya,R.id.selfPaya}   
		        );   
		          
		        //添加并且显示   
		      list.setAdapter(listItemAdapter);    
		      list.setOnItemClickListener(new OnItemClickListener() {

				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					// TODO Auto-generated method stub
					System.out.println("sdfsfdfdfd"+arg2);
					ll.clear();
					Log.v("sys", card[arg2]);
					ll.add(card[arg2]);
					System.out.println("---------------------------------"+card[arg2]);
					List<String> accuss=CreditClient.connectHttp("461", ll);
					System.out.println(accuss.get(0));
					Intent payment_intent = new Intent();
					payment_intent.putExtra("payDetail", accuss.get(0));
					payment_intent.setClass(SelfPay.this, SelfPayDetail.class);
					SelfPay.this.startActivity(payment_intent);
				}
			});      
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
			btnMain = (ImageView) this.findViewById(R.id.btnHelper);
			btnMain.setOnClickListener(new OnClickListener() {

				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent=new Intent();
					intent.setClass(SelfPay.this,  FinancialConsultation.class);
					SelfPay.this.startActivity(intent);
					finish();
				}
			});
			   // 返回键设定
			btnReturn = (ImageView) this.findViewById(R.id.returnToPre);
			btnReturn.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					// TODO Auto-generated method stub
					onBackPressed();
				//	SelfPay.this.finish();
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
						creditname=(EditText)findViewById(R.id.creditNameEdit);
						if(creditname.getText().toString().length()>0){
						ll.clear();
						ll.add(creditname.getText().toString());
						//System.out.println("---------------------------------"+card[arg2]);
						List<String> accuss=CreditClient.connectHttp("461", ll);
						System.out.println(accuss.get(0));
						if(accuss.get(0).contains(":")){
						Intent payment_intent = new Intent();
						payment_intent.putExtra("payDetail", accuss.get(0));
						payment_intent.setClass(SelfPay.this, SelfPayDetail.class);
						SelfPay.this.startActivity(payment_intent);
						}else 
						{
							Toast.makeText(SelfPay.this, "没有此帐号", Toast.LENGTH_SHORT).show();
						}
						}else
						{
							Toast.makeText(SelfPay.this, "请输入", Toast.LENGTH_SHORT).show();
						}
					}
				});
	    }
}

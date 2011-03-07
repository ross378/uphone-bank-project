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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.view.View.OnClickListener;

public class PaymentSelectAccountType extends ListActivity {//自助缴费主页面

	String  payName;
	String  payNum;
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    setContentView(R.layout.payment_main);

        
        TextView tvClassFirst = (TextView)this.findViewById(R.id.class_first);
		tvClassFirst.setText("首页>");
		tvClassFirst.setVisibility(View.VISIBLE);
		
		tvClassFirst.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				 intent.setClass(PaymentSelectAccountType.this, ABankMain.class);
				 PaymentSelectAccountType.this.startActivity(intent);	
			}
		});
		tvClassFirst.setVisibility(View.VISIBLE);
		
		
		TextView tvClassSecond = (TextView)this.findViewById(R.id.class_second);
		tvClassSecond.setText("自助缴费>");
		tvClassSecond.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Intent payment_intent = new Intent();
				payment_intent.setClass(PaymentSelectAccountType.this, PaymentMain.class);
				PaymentSelectAccountType.this.startActivity(payment_intent);	
				
			}
		});
		tvClassSecond.setVisibility(View.VISIBLE);
		
		
		
		TextView tvClassThird = (TextView)this.findViewById(R.id.class_third);
		tvClassThird.setText("待缴费项目");
		tvClassThird.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Intent payment_intent = new Intent();
				payment_intent.setClass(PaymentSelectAccountType.this, PaymentPend.class);
				PaymentSelectAccountType.this.startActivity(payment_intent);	
				
			}
		});
		tvClassThird.setVisibility(View.VISIBLE);
		
		
		TextView tv_acc_typ = (TextView)this.findViewById(R.id.paymenthistory);
		tv_acc_typ.setText("缴费账户类型选择");
    	tv_acc_typ.setVisibility(View.VISIBLE);
		
		
       ArrayList<HashMap<String,Object>> mainlist = new ArrayList<HashMap<String,Object>>();
        
        HashMap<String,Object> paylist1 = new HashMap<String,Object>();
        paylist1.put("payment_list","首选账户");
        paylist1.put("listimg2", R.drawable.trans_main2);
        mainlist.add(paylist1);
        
        paylist1 = new HashMap<String,Object>();
        paylist1.put("payment_list","其他账户");
        paylist1.put("listimg2", R.drawable.trans_main2);
        mainlist.add(paylist1);
        
        
        
        //接受上一个界面传来的缴费项目和金额
        Intent up_intent=getIntent();
      payName=up_intent.getStringExtra("pay_name");
      payNum=up_intent.getStringExtra("pay_num");

        
        SimpleAdapter MainListAdapter = new SimpleAdapter(this, mainlist,R.layout.payment_main_list, new String[]{"payment_list","listimg2"},new int[]{R.id.payment_list,R.id.listimg2 } );
        this.setListAdapter(MainListAdapter);
        
        ImageView iv_now = (ImageView)this.findViewById(R.id.btnCoustom);
//        iv_now.setVisibility(View.VISIBLE);
        
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
				intent.setClass(PaymentSelectAccountType.this, ABankMain.class);
				PaymentSelectAccountType.this.startActivity(intent);
			}
		});
		
        ImageView	btnHelper = (ImageView) this.findViewById(R.id.btnHelper);
        btnHelper.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent	intent = new Intent();
				intent.setClass(PaymentSelectAccountType.this, FinancialConsultation.class);
				PaymentSelectAccountType.this.startActivity(intent);
			}
		});
        
	}

	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		if (id == 0) {//首选账户
			String[] value;
			List<String> lstValue = new ArrayList<String>();
			lstValue.add("dasdsa");
			PaymentWebservices pay=new PaymentWebservices();		
		    value=	pay.connectHttp("6020"+(int)id,lstValue);  
			Intent payment_intent = new Intent();
			System.out.println(value[0]);
			System.out.println(value[1]);
			System.out.println(value[2]);
			payment_intent.putExtra("Account", value[0]);
			payment_intent.putExtra("acc_balance",value[1]);
			payment_intent.putExtra("pwd",value[2]);
			payment_intent.putExtra("pay_name", payName);
			payment_intent.putExtra("pay_num",payNum);
			System.out.println(payNum);
			payment_intent.setClass(PaymentSelectAccountType.this,PaymentInPwd.class);
			PaymentSelectAccountType.this.startActivity(payment_intent);
		}else if(id==1){//其他账户	    
		    Intent payment_intent = new Intent();
			payment_intent.putExtra("pay_name", payName);
			payment_intent.putExtra("pay_num",payNum);		    
			payment_intent.setClass(PaymentSelectAccountType.this,PaymentSelectAccount.class);
			PaymentSelectAccountType.this.startActivity(payment_intent);
		}
	}
}
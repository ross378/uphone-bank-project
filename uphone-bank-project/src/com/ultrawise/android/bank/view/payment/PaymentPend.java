package com.ultrawise.android.bank.view.payment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import com.ultrawise.android.bank.consum_webservices.PaymentWebservices;
import com.ultrawise.android.bank.view.ABankMain;
import com.ultrawise.android.bank.view.FinancialConsultation;
import com.ultrawise.android.bank.view.payment.PaymentResult.BtnOkCL;
import com.ultrawise.android.bank.view.transfer.R;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class PaymentPend extends ListActivity {
	/*
	 *此类需要传递的参数有两个
	 *1.缴费项目名
	 *2.缴费金额
	 *需要的数据就是一系列的缴费项目名和具体的金额* （通过上一个界面传递一个数组过来解析并显示）
	 * */
	TextView pay_name;
	TextView pay_num;
	Intent payment_intent;
	Intent return_intent;
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_main);        
        
        TextView tvClassFirst = (TextView)this.findViewById(R.id.class_first);
		tvClassFirst.setText("首页>");
		tvClassFirst.setVisibility(View.VISIBLE);
		
		tvClassFirst.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				 intent.setClass(PaymentPend.this, ABankMain.class);
				 PaymentPend.this.startActivity(intent);
			}
		});        

        TextView tvClassSecond = (TextView)this.findViewById(R.id.class_second);
        tvClassSecond.setText("自助缴费>");
        tvClassSecond.setVisibility(View.VISIBLE);
		
        tvClassSecond.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent payment_intent = new Intent();
				payment_intent.setClass(PaymentPend.this, PaymentMain.class);
				PaymentPend.this.startActivity(payment_intent);	
			}
		});          
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
				intent.setClass(PaymentPend.this, ABankMain.class);
				PaymentPend.this.startActivity(intent);
			}
		});
		
        ImageView	btnHelper = (ImageView) this.findViewById(R.id.btnHelper);
        btnHelper.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent	intent = new Intent();
				intent.setClass(PaymentPend.this, FinancialConsultation.class);
				PaymentPend.this.startActivity(intent);
			}
		});
        
		
		
		
		TextView tvClassThree = (TextView)this.findViewById(R.id.class_third);
		tvClassThree.setText("待缴费项目");
		tvClassThree.setVisibility(View.VISIBLE);
  
        ArrayList<HashMap<String,Object>> mainlist = new ArrayList<HashMap<String,Object>>();
        
        
        /*从上一个界面传过来
         * 其中的过程是先从上一个页面将参数和功能号传递到服务器，
         * 并经过处理后返回一个String过来
        */  
        Intent get_intent = getIntent();
      String[] inmation=get_intent.getStringArrayExtra("pay_arr");
     // 用到了inmation中的各个项      
      for(int i=0;i<inmation.length;i=i+2){
      HashMap<String,Object> paylist1 = new HashMap<String,Object>();
      paylist1.put("payment_list",inmation[i]);
      paylist1.put("payment_list2",inmation[i+1]+"元");
      paylist1.put("listimg2", R.drawable.trans_main2);
      mainlist.add(paylist1);
      }
        /*
         * 将分离好的数据传到当前界面，只要取出数组中间的相应的值就好了
        */
        
        SimpleAdapter MainListAdapter = new SimpleAdapter(this, mainlist,R.layout.payment_list, new String[]{
        		"payment_list","payment_list2","listimg2"},new int[]{R.id.payment_11,R.id.payment_22,R.id.listimg33} );
        this.setListAdapter(MainListAdapter);
        
        ImageView iv_now = (ImageView)this.findViewById(R.id.btnCoustom);
//        iv_now.setVisibility(View.VISIBLE);
        
	}
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		pay_name=(TextView)v.findViewById(R.id.payment_11);
		String[] value;
		List<String> lstValue = new ArrayList<String>();
		lstValue.add(pay_name.getText().toString());
		PaymentWebservices pay=new PaymentWebservices();		
	    value=	pay.connectHttp("6010"+(int)id,lstValue); 
	    
	    Intent payment_intent = new Intent();
	    payment_intent.putExtra("pay_arr",value);
		payment_intent.setClass(PaymentPend.this, PaymentDetail.class);
		PaymentPend.this.startActivity(payment_intent);
	}

}
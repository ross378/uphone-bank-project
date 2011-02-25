package com.ultrawise.android.bank.view.payment;

import java.util.ArrayList;
import java.util.HashMap;

import com.ultrawise.android.bank.view.payment.PaymentResult.BtnOkCL;
import com.ultrawise.android.bank.view.transfer.R;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class PaymentPend extends ListActivity {
	
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
//				Intent payment_intent = new Intent();
//				payment_intent.setClass(PaymentPend.this, PaymentMain.class);
//				PaymentPend.this.startActivity(payment_intent);	
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
		
		
		
		TextView tvClassThree = (TextView)this.findViewById(R.id.class_third);
		tvClassThree.setText("待缴费项目");
		tvClassThree.setVisibility(View.VISIBLE);
		

		
		
        
        ArrayList<HashMap<String,String>> mainlist = new ArrayList<HashMap<String,String>>();
        
        HashMap<String,String> paylist1 = new HashMap<String,String>();
        HashMap<String,String> paylist2 = new HashMap<String,String>();
        HashMap<String,String> paylist3 = new HashMap<String,String>();
        HashMap<String,String> paylist4 = new HashMap<String,String>();
        
        paylist1.put("payment_list", "  三月份水费");
        paylist1.put("payment_list_info", "50元 >");
        paylist2.put("payment_list", "  三月份电费");
        paylist2.put("payment_list_info", "100元 >");
        paylist3.put("payment_list", "  三月份煤气");
        paylist3.put("payment_list_info", "90元 >");
        paylist4.put("payment_list", "  三月份房租");
        paylist4.put("payment_list_info", "300元 >");
        
        
        mainlist.add(paylist1);
        mainlist.add(paylist2);
        mainlist.add(paylist3);
        mainlist.add(paylist4);
        
        SimpleAdapter MainListAdapter = new SimpleAdapter(this, mainlist,R.layout.payment_list, new String[] {
				"payment_list", "payment_list_info" }, new int[] { R.id.payment_list, R.id.payment_list_info } );
        this.setListAdapter(MainListAdapter);
        
        ImageView iv_now = (ImageView)this.findViewById(R.id.btnCoustom);
        iv_now.setVisibility(View.VISIBLE);
        
	}
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		if (id == 0) {
			Intent payment_intent = new Intent();
			payment_intent.putExtra("title", "三月份水费");
			payment_intent.putExtra("amount", "50元");
			payment_intent.putExtra("inputed_peo", "无锡自来水公司");
			payment_intent.putExtra("serialnum", "111111");
			payment_intent.putExtra("deadline", "2011年1月15号");
			payment_intent.setClass(PaymentPend.this, PaymentDetail.class);
			PaymentPend.this.startActivity(payment_intent);
		}else if(id==1){
			Intent payment_intent = new Intent();
			payment_intent.putExtra("title", "三月份电费");
			payment_intent.putExtra("amount", "100元");
			payment_intent.putExtra("inputed_peo", "无锡电力公司");
			payment_intent.putExtra("serialnum", "222222");
			payment_intent.putExtra("deadline", "2011年2月15号");
			payment_intent.setClass(PaymentPend.this, PaymentDetail.class);
			PaymentPend.this.startActivity(payment_intent);
		}else if(id==2){
			Intent payment_intent = new Intent();
			payment_intent.putExtra("title", "三月份煤气费");
			payment_intent.putExtra("amount", "90元");
			payment_intent.putExtra("inputed_peo", "无锡能源供给公司");
			payment_intent.putExtra("serialnum", "3333333");
			payment_intent.putExtra("deadline", "2011年3月15号");
			payment_intent.setClass(PaymentPend.this, PaymentDetail.class);
			PaymentPend.this.startActivity(payment_intent);
		}else if(id==3){
			Intent payment_intent = new Intent();
			payment_intent.putExtra("title", "三月份房租");
			payment_intent.putExtra("amount", "3000元");
			payment_intent.putExtra("inputed_peo", "无锡**房地产");
			payment_intent.putExtra("serialnum", "4444444");
			payment_intent.putExtra("deadline", "2011年4月15号");
			payment_intent.setClass(PaymentPend.this, PaymentDetail.class);
			PaymentPend.this.startActivity(payment_intent);
		}
	}

}
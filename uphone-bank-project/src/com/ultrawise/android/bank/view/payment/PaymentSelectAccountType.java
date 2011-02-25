package com.ultrawise.android.bank.view.payment;

import java.util.ArrayList;
import java.util.HashMap;

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

	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    setContentView(R.layout.payment_main);

        
        TextView tvClassFirst = (TextView)this.findViewById(R.id.class_first);
		tvClassFirst.setText("首页>");
		tvClassFirst.setVisibility(View.VISIBLE);
		
		tvClassFirst.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
//				Intent payment_intent = new Intent();
//				payment_intent.setClass(PaymentSelectAccountType.this, PaymentMain.class);
//				PaymentSelectAccountType.this.startActivity(payment_intent);	
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
//		tvClassThree.setVisibility(View.VISIBLE);
		
		
        ArrayList<HashMap<String,String>> selectlist = new ArrayList<HashMap<String,String>>();
        
        HashMap<String,String> paylist1 = new HashMap<String,String>();
        HashMap<String,String> paylist2 = new HashMap<String,String>();
        
        paylist1.put("payment_list", "首选账户");
        paylist1.put("payment_list_info", ">");

        paylist2.put("payment_list", "其他账户");
        paylist2.put("payment_list_info", ">");

        selectlist.add(paylist1);
      
        selectlist.add(paylist2);

        
        SimpleAdapter MainListAdapter = new SimpleAdapter(this, selectlist,R.layout.payment_list, new String[] {
				"payment_list", "payment_list_info" }, new int[] { R.id.payment_list, R.id.payment_list_info } );
        this.setListAdapter(MainListAdapter);
        
        ImageView iv_now = (ImageView)this.findViewById(R.id.btnCoustom);
        iv_now.setVisibility(View.VISIBLE);
	}

	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		if (id == 0) {//首选账户
			Intent payment_intent = new Intent();
			payment_intent.putExtra("Account", "67324623461");
			payment_intent.setClass(PaymentSelectAccountType.this,PaymentInPwd.class);
			PaymentSelectAccountType.this.startActivity(payment_intent);
		}else if(id==1){//其他账户
			Intent payment_intent = new Intent();
			payment_intent.setClass(PaymentSelectAccountType.this,PaymentSelectAccount.class);
			PaymentSelectAccountType.this.startActivity(payment_intent);
		}
	}
}
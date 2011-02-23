package com.ultrawise.android.bank.view.payment;

import java.util.ArrayList;
import java.util.HashMap;

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

public class PaymentSelfService extends ListActivity {
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_main);
        
        TextView tvClassFirst = (TextView)this.findViewById(R.id.class_first);
		tvClassFirst.setText("手机缴费>");
		tvClassFirst.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// intent = QueryAccount.this.getIntent();
				// intent.setClass(QueryAccount.this, AccountManagement.class);
				// QueryAccount.this.startActivity(intent);
			}
		});
		tvClassFirst.setVisibility(View.VISIBLE);
		
		TextView tvClassSecond = (TextView)this.findViewById(R.id.class_second);
		tvClassSecond.setText("自助缴费>");
		tvClassSecond.setVisibility(View.VISIBLE);
		
		TextView tvClassThird= (TextView)this.findViewById(R.id.class_third);
		tvClassThird.setText("便捷服务>");
		tvClassThird.setVisibility(View.VISIBLE);
        
      
		ArrayList<HashMap<String,String>> mainlist = new ArrayList<HashMap<String,String>>();
        
//        HashMap<String,String> paylist1 = new HashMap<String,String>();
//        HashMap<String,String> paylist2 = new HashMap<String,String>();
        HashMap<String,String> paylist1 = new HashMap<String,String>();
        HashMap<String,String> paylist2 = new HashMap<String,String>();
        HashMap<String,String> paylist3 = new HashMap<String,String>();
        
//        paylist1.put("payment_list", "待缴费项目");
//        paylist1.put("payment_list_info", ">");
//        paylist2.put("payment_list", "便捷服务");
//        paylist2.put("payment_list_info", "");
        paylist1.put("payment_list", "  --手机充值");
        paylist1.put("payment_list_info", ">");
        paylist2.put("payment_list", "  --平安保险");
        paylist2.put("payment_list_info", ">");
        paylist3.put("payment_list", "  --交通罚款");
        paylist3.put("payment_list_info", ">");
        
//        mainlist.add(paylist1);
//        mainlist.add(paylist2);
        mainlist.add(paylist1);
        mainlist.add(paylist2);
        mainlist.add(paylist3);
        
        SimpleAdapter MainListAdapter = new SimpleAdapter(this, mainlist,R.layout.payment_list, new String[] {
				"payment_list", "payment_list_info" }, new int[] { R.id.payment_list, R.id.payment_list_info } );
        this.setListAdapter(MainListAdapter);
        
        ImageView iv_now = (ImageView)this.findViewById(R.id.btnCoustom);
        iv_now.setVisibility(View.VISIBLE);
        
	}
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
//		if (id == 0) {//待缴费项目的原来
//			Intent payment_intent = new Intent();
//			payment_intent.setClass(PaymentSelfService.this, PaymentPend.class);
//			PaymentSelfService.this.startActivity(payment_intent);
//		}else 

       if(id==0){
			Intent payment_intent = new Intent();
			payment_intent.putExtra("title", "手机充值");
			payment_intent.putExtra("amount", "50元");
			payment_intent.putExtra("serialnum", "111115");
			payment_intent.setClass(PaymentSelfService.this, PaymentDetail.class);
			PaymentSelfService.this.startActivity(payment_intent);
		}else if(id==1){
			Intent payment_intent = new Intent();
			payment_intent.putExtra("title", "平安保险");
			payment_intent.putExtra("amount", "2000元");
			payment_intent.putExtra("serialnum", "21376564");
			payment_intent.setClass(PaymentSelfService.this, PaymentDetail.class);
			PaymentSelfService.this.startActivity(payment_intent);
			System.out.println("id----------------"+id);
	    	System.out.println("position----------"+position);
			//Intent payment_intent = new Intent();
			//payment_intent.setClass(PaymentSelfService.this, PaymentHistory.class);
		}else if(id==2){			
			Intent payment_intent = new Intent();
			payment_intent.putExtra("title", "交通罚款");
			payment_intent.putExtra("amount", "500元");
			payment_intent.putExtra("serialnum", "767865786");
			payment_intent.setClass(PaymentSelfService.this, PaymentDetail.class);
			PaymentSelfService.this.startActivity(payment_intent);
			System.out.println("id----------------"+id);
	    	System.out.println("position----------"+position);
			//Intent payment_intent = new Intent();
			//payment_intent.setClass(PaymentSelfService.this, PaymentDefAcc.class);
		}
	}
}
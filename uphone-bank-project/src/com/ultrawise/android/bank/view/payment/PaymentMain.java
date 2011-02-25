package com.ultrawise.android.bank.view.payment;

import java.util.ArrayList;
import java.util.HashMap;

import com.ultrawise.android.bank.view.ABankMain;
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

public class PaymentMain extends ListActivity {//自助缴费主页面

	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_main);
        
        TextView tvClassFirst = (TextView)this.findViewById(R.id.class_first);
		tvClassFirst.setText("首页>");
		tvClassFirst.setVisibility(View.VISIBLE);
		
		tvClassFirst.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

//				 Intent intent = new Intent();
//				 intent.setClass(PaymentMain.this, ABankMain.class);
//				 PaymentMain.this.startActivity(intent);
//
			}
		});
		tvClassFirst.setVisibility(View.VISIBLE);
		
		
		TextView tvClassSecond = (TextView)this.findViewById(R.id.class_second);
		tvClassSecond.setText("自助缴费");
		tvClassSecond.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
//				Intent payment_intent = new Intent();
//				payment_intent.setClass(PaymentMain.this, PaymentMain.class);
//				PaymentMain.this.startActivity(payment_intent);	
				
			}
		});
		tvClassSecond.setVisibility(View.VISIBLE);
		
        ArrayList<HashMap<String,Object>> mainlist = new ArrayList<HashMap<String,Object>>();
        
        HashMap<String,Object> paylist1 = new HashMap<String,Object>();
       
        paylist1.put("listimg1",R.drawable.trans_main);
        paylist1.put("payment_list","待缴费项目");
        paylist1.put("listimg2", R.drawable.trans_main2);
        mainlist.add(paylist1);
        
        
        paylist1 = new HashMap<String,Object>();
        paylist1.put("listimg1",R.drawable.trans_main);
        paylist1.put("payment_list","便捷服务");
        paylist1.put("listimg2", R.drawable.trans_main2);
        mainlist.add(paylist1);
        
        paylist1 = new HashMap<String,Object>();
        paylist1.put("listimg1",R.drawable.trans_main);
        paylist1.put("payment_list","最近一个月缴费");
        paylist1.put("listimg2", R.drawable.trans_main2);
        mainlist.add(paylist1);
        
        

        
        paylist1 = new HashMap<String,Object>();
        paylist1.put("listimg1",R.drawable.trans_main);
        paylist1.put("payment_list","历史缴费记录");
        paylist1.put("listimg2", R.drawable.trans_main2);
        mainlist.add(paylist1);
        
        paylist1 = new HashMap<String,Object>();
        paylist1.put("listimg1",R.drawable.trans_main);
        paylist1.put("payment_list","缴费项目管理");
        paylist1.put("listimg2", R.drawable.trans_main2);
        mainlist.add(paylist1);
        
      
        
        SimpleAdapter MainListAdapter = new SimpleAdapter(this, mainlist,R.layout.payment_main_list, new String[]{"listimg1","payment_list","listimg2"},new int[]{R.id.listimg1,R.id.payment_list,R.id.listimg2 } );
        this.setListAdapter(MainListAdapter);
        
        ImageView iv_now = (ImageView)this.findViewById(R.id.btnCoustom);
//        iv_now.setVisibility(View.VISIBLE);
	}

	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		if (id == 0) {//待缴费
			Intent payment_intent = new Intent();
			payment_intent.setClass(PaymentMain.this, PaymentPend.class);
			PaymentMain.this.startActivity(payment_intent);
		}else if(id==1){//便捷
			Intent payment_intent = new Intent();
			payment_intent.setClass(PaymentMain.this, PaymentSelfService.class);
			PaymentMain.this.startActivity(payment_intent);
		}else if(id==2){
		    Intent payment_intent = new Intent();
			payment_intent.setClass(PaymentMain.this, PaymentLastMonth.class);
			PaymentMain.this.startActivity(payment_intent);	
		}
		else if(id==3){
			Intent payment_intent = new Intent();
			payment_intent.setClass(PaymentMain.this, PaymentHistory.class);
			PaymentMain.this.startActivity(payment_intent);
		}else if(id==4){
			Intent payment_intent = new Intent();
			payment_intent.setClass(PaymentMain.this, PaymentManage.class);
			PaymentMain.this.startActivity(payment_intent);

		}
	}
}
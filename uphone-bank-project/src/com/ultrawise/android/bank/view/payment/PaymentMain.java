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

public class PaymentMain extends ListActivity {//自助缴费主页面

	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_main);
        
        TextView tvClassFirst = (TextView)this.findViewById(R.id.class_first);
		tvClassFirst.setText("自助缴费");
		tvClassFirst.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// intent = QueryAccount.this.getIntent();
				// intent.setClass(QueryAccount.this, AccountManagement.class);
				// QueryAccount.this.startActivity(intent);
			}
		});
		tvClassFirst.setVisibility(View.VISIBLE);
		
        ArrayList<HashMap<String,String>> mainlist = new ArrayList<HashMap<String,String>>();
        
        HashMap<String,String> paylist1 = new HashMap<String,String>();
        HashMap<String,String> paylist2 = new HashMap<String,String>();
        HashMap<String,String> paylist3 = new HashMap<String,String>();
        HashMap<String,String> paylist4 = new HashMap<String,String>();
        HashMap<String,String> paylist5 = new HashMap<String,String>();
        HashMap<String,String> paylist6 = new HashMap<String,String>();
        
        paylist1.put("payment_list", "待缴费项目");
        paylist1.put("payment_list_info", ">");
        paylist2.put("payment_list", "便捷服务");
        paylist2.put("payment_list_info", ">");
        paylist3.put("payment_list", "最近一个月缴费");
        paylist3.put("payment_list_info", ">");
        paylist4.put("payment_list", "历史缴费记录");
        paylist4.put("payment_list_info", ">");
        paylist5.put("payment_list", "默认缴费账户设置");
        paylist5.put("payment_list_info", ">");
        paylist6.put("payment_list", "缴费项目管理");
        paylist6.put("payment_list_info", ">");
        
        mainlist.add(paylist1);
        mainlist.add(paylist2);
        mainlist.add(paylist3);
        mainlist.add(paylist4);
        mainlist.add(paylist5);
        mainlist.add(paylist6);
        
        SimpleAdapter MainListAdapter = new SimpleAdapter(this, mainlist,R.layout.payment_list, new String[] {
				"payment_list", "payment_list_info" }, new int[] { R.id.payment_list, R.id.payment_list_info } );
        this.setListAdapter(MainListAdapter);
        
        ImageView iv_now = (ImageView)this.findViewById(R.id.btnCoustom);
        iv_now.setVisibility(View.VISIBLE);
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
		}else if(id==2){//缴费项目管理
		    Intent payment_intent = new Intent();
			payment_intent.setClass(PaymentMain.this, PaymentLastMonth.class);
			PaymentMain.this.startActivity(payment_intent);	
		}
		else if(id==3){//最近一个月
			Intent payment_intent = new Intent();
			payment_intent.setClass(PaymentMain.this, PaymentHistory.class);
			PaymentMain.this.startActivity(payment_intent);
		}else if(id==4){//历史缴费记录
			Intent payment_intent = new Intent();
			payment_intent.setClass(PaymentMain.this, PaymentDefAcc.class);
			PaymentMain.this.startActivity(payment_intent);
		}else if(id==5){//默认账户管理
			Intent payment_intent = new Intent();
			payment_intent.setClass(PaymentMain.this, PaymentManage.class);
			PaymentMain.this.startActivity(payment_intent);
		}
	}
}
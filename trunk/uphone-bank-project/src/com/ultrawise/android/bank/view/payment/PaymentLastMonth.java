package com.ultrawise.android.bank.view.payment;

import java.util.ArrayList;
import java.util.HashMap;

import com.ultrawise.android.bank.view.ABankMain;
import com.ultrawise.android.bank.view.FinancialConsultation;
import com.ultrawise.android.bank.view.transfer.R;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class PaymentLastMonth extends ListActivity {
	String start_time = "20110101";
	String end_time = "20110130";
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_main);
        
        TextView tvClassFirst = (TextView)this.findViewById(R.id.class_first);
		tvClassFirst.setText("首页>");
		tvClassFirst.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(PaymentLastMonth.this, ABankMain.class);
				PaymentLastMonth.this.startActivity(intent);
			}
		});
		tvClassFirst.setVisibility(View.VISIBLE);
		
        TextView tvClassSecond = (TextView)this.findViewById(R.id.class_second);
		tvClassSecond.setText("自助缴费>");
		tvClassSecond.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(PaymentLastMonth.this, PaymentMain.class);
				PaymentLastMonth.this.startActivity(intent);
			}
		});
		tvClassSecond.setVisibility(View.VISIBLE);
		
		TextView tvClassThird = (TextView)this.findViewById(R.id.class_third);
		tvClassThird.setText("历史缴费记录");
		tvClassThird.setVisibility(View.VISIBLE);
        
		Intent intent = this.getIntent();
		if(intent.hasExtra("start_time")){
			start_time = intent.getStringExtra("start_time");
        }
        if(intent.hasExtra("end_time")){
        	end_time = intent.getStringExtra("end_time");
        }
		
        TextView titel = (TextView)findViewById(R.id.paymenthistory);
        titel.setText("从"+start_time+"到"+end_time+"的历史缴费记录如下：");
        titel.setVisibility(View.VISIBLE);
        
        ArrayList<HashMap<String,Object>> mainlist = new ArrayList<HashMap<String,Object>>();
        
        HashMap<String,Object> paylist1 = new HashMap<String,Object>();
       
         
        paylist1.put("payment_list","2011/2/1");
        paylist1.put("payment_list2","房租");
        paylist1.put("listimg2", R.drawable.trans_main2);
        mainlist.add(paylist1);
        
        
        paylist1 = new HashMap<String,Object>();
        
        paylist1.put("payment_list","2011/1/26");
        paylist1.put("payment_list2","电费");
        paylist1.put("listimg2", R.drawable.trans_main2);
        mainlist.add(paylist1);
        
        paylist1 = new HashMap<String,Object>();
        
        paylist1.put("payment_list","2011/1/25");
        paylist1.put("payment_list2","煤气费");
        paylist1.put("listimg2", R.drawable.trans_main2);
        mainlist.add(paylist1);
        
        paylist1 = new HashMap<String,Object>();
        
        paylist1.put("payment_list","2011/1/25");
        paylist1.put("payment_list2","水费");
        paylist1.put("listimg2", R.drawable.trans_main2);
        mainlist.add(paylist1);
        
        paylist1 = new HashMap<String,Object>();
        
        paylist1.put("payment_list","2011/1/20");
        paylist1.put("payment_list2","手机充值");
        paylist1.put("listimg2", R.drawable.trans_main2);
        mainlist.add(paylist1);

        SimpleAdapter MainListAdapter = new SimpleAdapter(this, mainlist,R.layout.payment_main_list, new String[]{
        		"payment_list","payment_list2","listimg2"},new int[]{R.id.payment_list,R.id.payment_list2,R.id.listimg2 } );
        this.setListAdapter(MainListAdapter);
        
        ImageView iv_now = (ImageView)this.findViewById(R.id.btnCoustom);
        //iv_now.setVisibility(View.VISIBLE)
        
        
        
        
	}
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		if (id == 0) {
			Intent payment_intent = new Intent();
			payment_intent.putExtra("title", "房租");
			payment_intent.putExtra("amount", "300元");
			payment_intent.putExtra("time", "2011-2-1");
			payment_intent.putExtra("serialnum", "11115");
			payment_intent.putExtra("acc", "222222222222");
			payment_intent.setClass(PaymentLastMonth.this, PaymentHisDetail.class);
			PaymentLastMonth.this.startActivity(payment_intent);
		}else if(id==1){
			Intent payment_intent = new Intent();
			payment_intent.putExtra("title", "电费");
			payment_intent.putExtra("amount", "100元");
			payment_intent.putExtra("time", "2011-1-26");
			payment_intent.putExtra("serialnum", "11117");
			payment_intent.putExtra("acc", "222222222222");
			payment_intent.setClass(PaymentLastMonth.this, PaymentHisDetail.class);
			PaymentLastMonth.this.startActivity(payment_intent);
		}else if(id==2){
			Intent payment_intent = new Intent();
			payment_intent.putExtra("title", "煤气费");
			payment_intent.putExtra("amount", "90元");
			payment_intent.putExtra("time", "2011-1-25");
			payment_intent.putExtra("serialnum", "11119");
			payment_intent.putExtra("acc", "333333333333");
			payment_intent.setClass(PaymentLastMonth.this, PaymentHisDetail.class);
			PaymentLastMonth.this.startActivity(payment_intent);
		}else if(id==3){
			Intent payment_intent = new Intent();
			payment_intent.putExtra("title", "水费");
			payment_intent.putExtra("amount", "50元");
			payment_intent.putExtra("time", "2011-1-25");
			payment_intent.putExtra("serialnum", "11121");
			payment_intent.putExtra("acc", "222222222222");
			payment_intent.setClass(PaymentLastMonth.this, PaymentHisDetail.class);
			PaymentLastMonth.this.startActivity(payment_intent);
		}else if(id==4){
			Intent payment_intent = new Intent();
			payment_intent.putExtra("title", "手机充值");
			payment_intent.putExtra("amount", "50元");
			payment_intent.putExtra("time", "2011-1-20");
			payment_intent.putExtra("serialnum", "11122");
			payment_intent.putExtra("acc", "222222222222");
			payment_intent.setClass(PaymentLastMonth.this, PaymentHisDetail.class);
			PaymentLastMonth.this.startActivity(payment_intent);
		}
	}
}
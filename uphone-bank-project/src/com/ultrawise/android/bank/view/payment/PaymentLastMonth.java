package com.ultrawise.android.bank.view.payment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

public class PaymentLastMonth extends ListActivity {
	String start_time = "2011-1-1";
	String end_time = "2011-1-30";
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_main);
        
        TextView tvClassFirst = (TextView)this.findViewById(R.id.class_first);
		tvClassFirst.setText("自助缴费>");
		tvClassFirst.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// intent = QueryAccount.this.getIntent();
				// intent.setClass(QueryAccount.this, AccountManagement.class);
				// QueryAccount.this.startActivity(intent);
			}
		});
		tvClassFirst.setVisibility(View.VISIBLE);
		
		TextView tvClassSecond = (TextView)this.findViewById(R.id.class_second);
		tvClassSecond.setText("历史缴费记录");
		tvClassSecond.setVisibility(View.VISIBLE);
        
		Intent intent = this.getIntent();
		if(intent.hasExtra("start_time")){
			start_time = intent.getStringExtra("start_time");
        }
        if(intent.hasExtra("end_time")){
        	end_time = intent.getStringExtra("end_time");
        }
		
        TextView titel = (TextView)findViewById(R.id.paymenthistory);
        titel.setText("从"+start_time+"到"+end_time+"的缴费记录如下：");
        
        
        
        ArrayList<HashMap<String,String>> mainlist = new ArrayList<HashMap<String,String>>();
        
        HashMap<String,String> paylist1 = new HashMap<String,String>();
        HashMap<String,String> paylist2 = new HashMap<String,String>();
        HashMap<String,String> paylist3 = new HashMap<String,String>();
        HashMap<String,String> paylist4 = new HashMap<String,String>();
        HashMap<String,String> paylist5 = new HashMap<String,String>();
        
        paylist1.put("payment_list", "  房租");
        paylist1.put("payment_list_info", "2011/2/1  >");
        paylist2.put("payment_list", "  电费");
        paylist2.put("payment_list_info", "2011/1/26>");
        paylist3.put("payment_list", "  煤气费");
        paylist3.put("payment_list_info", "2011/1/25>");
        paylist4.put("payment_list", "  水费");
        paylist4.put("payment_list_info", "2011/1/25>");
        paylist5.put("payment_list", "  手机充值");
        paylist5.put("payment_list_info", "2011/1/20>");
        
        mainlist.add(paylist1);
        mainlist.add(paylist2);
        mainlist.add(paylist3);
        mainlist.add(paylist4);
        mainlist.add(paylist5);
        
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
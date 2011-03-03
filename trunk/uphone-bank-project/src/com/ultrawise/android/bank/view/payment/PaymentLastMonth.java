package com.ultrawise.android.bank.view.payment;

import it.sauronsoftware.base64.Base64;

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

import com.ultrawise.android.bank.view.ABankMain;
import com.ultrawise.android.bank.view.FinancialConsultation;
import com.ultrawise.android.bank.view.transfer.R;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class PaymentLastMonth extends ListActivity {
	String start_time = "20110101";
	String end_time = "20110130";
	
	private String serviceAddress = "http://10.1.1.115:8080/item/services";
	private String requestParameters;
	
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
        titel.setText("从"+start_time+"到"+end_time+"的历史缴费记录：");
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
				intent.setClass(PaymentLastMonth.this, ABankMain.class);
				PaymentLastMonth.this.startActivity(intent);
			}
		});
		
        ImageView	btnHelper = (ImageView) this.findViewById(R.id.btnHelper);
        btnHelper.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent	intent = new Intent();
				intent.setClass(PaymentLastMonth.this, FinancialConsultation.class);
				PaymentLastMonth.this.startActivity(intent);
			}
		});
        
        
	}
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		if (id == 0) {
//			JSONArray json = Login("72:Test:2011-11-11");
//			try {
//				System.out.println(json.get(0));
//			} catch (JSONException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
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
	
	private JSONArray Login(String msg) {
		// Get the public interface's value by path
		// It's look like http://localhost:8080/hello/login/solo/123
		String strMi = Base64.encode(msg);
		System.out.println("加密后："+strMi);
		requestParameters = "/payment/historydetail/"+strMi+"/";
		HttpGet httpget = new HttpGet(serviceAddress + requestParameters);
		
		HttpClient httpclient = new DefaultHttpClient();
		HttpResponse response;
		try {
			
			response = httpclient.execute(httpget);

			// Just log,don't focus on it
			Log.i("REST:Response Status line", response.getStatusLine()
					.toString());
			
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				InputStream instream = entity.getContent();
				String result = convertStreamToString(instream);
				JSONObject json = new JSONObject(result);

				// Parsing
				JSONArray nameArray = json.names();
				JSONArray valArray = json.toJSONArray(nameArray);

				instream.close();
				return valArray;

			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			httpget.abort();
			//httppost.abort();
			httpclient.getConnectionManager().shutdown();
		}
		return null;
	}

	// Convert InputStream to String
	private static String convertStreamToString(InputStream is) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
}
package com.ultrawise.android.bank.view.payment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ultrawise.android.bank.consum_webservices.PaymentWebservices;
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
	String start_time = "2011-3-1";
	String end_time = "2011-3-31";
	List<String> service = new ArrayList<String>();
	String[] values = null;
	
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
        
        HashMap<String,Object> paylist1 = null;
 
        service.add("水费");
        service.add("电费");
        service.add("天然气");
        service.add("房租");
        service.add("手机充值");
        service.add("Q币充值");
        service.add("网易充值");
        
        List<String> params = new ArrayList<String>();
		params.add("zhangsan");
		params.add(end_time);
		params.add(start_time);
        PaymentWebservices.paramsString="payment";
		values = PaymentWebservices.connectHttp("60301", params);
		for(int i = 0 ;i < values.length;i+=2){
			paylist1 = new HashMap<String,Object>();
			paylist1.put("payment_list",values[i]);
	        paylist1.put("payment_list2",values[i+1]);
	        paylist1.put("listimg2", R.drawable.trans_main2);
	        mainlist.add(paylist1);
		}

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
	
	String[]  titles = new String[]{"time","acc","amount","serialnum","title"};
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		List<String> params = new ArrayList<String>();
		params.add("zhangsan");
		params.add(end_time);
		params.add(start_time);
		int index = service.indexOf(values[(int)id*2+1]);
		params.add(String.valueOf(index));
		if(index>3){
			params.add("2");
		}else{
			params.add("1");
		}
		Intent payment_intent = new Intent();
		payment_intent = StringToIntent(payment_intent,params);
		payment_intent.setClass(PaymentLastMonth.this, PaymentHisDetail.class);
		PaymentLastMonth.this.startActivity(payment_intent);

	}
	
	private Intent StringToIntent(Intent intent,List<String> params){
		PaymentWebservices.paramsString="payment";
		String[] values = PaymentWebservices.connectHttp("60302", params);
		for(int i = 0;i<values.length;i++){
			intent.putExtra(titles[i], values[i]);
		}
		return intent;
	}
	
}
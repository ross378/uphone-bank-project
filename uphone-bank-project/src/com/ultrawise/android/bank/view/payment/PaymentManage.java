package com.ultrawise.android.bank.view.payment;

import java.util.ArrayList;
import java.util.HashMap;

import com.ultrawise.android.bank.view.ABankMain;
import com.ultrawise.android.bank.view.transfer.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.view.View.OnClickListener;

public class PaymentManage extends Activity {//缴费项目管理
	
	Intent payment_intent = new Intent();
	ListView payment_manage_list = null;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_manage);
        
        //Button btn_paymanage_ok = (Button)findViewById(R.id.btn_paymana_ok);
        
        ImageView iv_now = (ImageView)this.findViewById(R.id.btnCoustom);
        //iv_now.setVisibility(View.VISIBLE);
        
        TextView tvClassFirst = (TextView)this.findViewById(R.id.class_first);
		tvClassFirst.setText("首页>");
		tvClassFirst.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent payment_intent = new Intent();
				payment_intent.setClass(PaymentManage.this, ABankMain.class);
				PaymentManage.this.startActivity(payment_intent);
			}
		});
		tvClassFirst.setVisibility(View.VISIBLE);
        TextView tvClassSecond = (TextView)this.findViewById(R.id.class_second);
        tvClassSecond.setText("自助缴费>");
        tvClassSecond.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent payment_intent = new Intent();
				payment_intent.setClass(PaymentManage.this, PaymentMain.class);
				PaymentManage.this.startActivity(payment_intent);
			}
		});
        tvClassSecond.setVisibility(View.VISIBLE);
		
		TextView tvClassThird = (TextView)this.findViewById(R.id.class_third);
		tvClassThird.setText("缴费项目管理");
		tvClassThird.setVisibility(View.VISIBLE);
        
        //btn_paymanage_ok.setOnClickListener(new BtnManaOK());
		
		//payment_manage_list = (ListView)findViewById(R.id.open_list);
		
		ArrayList<HashMap<String,String>> mainlist = new ArrayList<HashMap<String,String>>();
        
        HashMap<String,String> paylist1 = new HashMap<String,String>();
        HashMap<String,String> paylist2 = new HashMap<String,String>();
        HashMap<String,String> paylist3 = new HashMap<String,String>();
        HashMap<String,String> paylist4 = new HashMap<String,String>();
        HashMap<String,String> paylist5 = new HashMap<String,String>();
        
        paylist1.put("payment_list", "待缴费项目");
        paylist1.put("payment_list_info", ">");
        paylist2.put("payment_list", "便捷服务");
        paylist2.put("payment_list_info", ">");
        paylist3.put("payment_list", "最近一个月缴费");
        paylist3.put("payment_list_info", ">");
        paylist4.put("payment_list", "历史缴费记录");
        paylist4.put("payment_list_info", ">");
        paylist5.put("payment_list", "缴费项目管理");
        paylist5.put("payment_list_info", ">");

        
        mainlist.add(paylist1);
        mainlist.add(paylist2);
        mainlist.add(paylist3);
        mainlist.add(paylist4);
        mainlist.add(paylist5);
        
        SimpleAdapter MainListAdapter = new SimpleAdapter(this, mainlist,R.layout.payment_manage_list, new String[] {
				"payment_list", "payment_list_info" }, new int[] { R.id.pay_manage_list, R.id.pay_manage_list_but } );
        //payment_manage_list.setAdapter(MainListAdapter);
	}

	class BtnManaOK implements OnClickListener{
		public void onClick(View v){
			Intent btnok_intent = new Intent();
			btnok_intent.putExtra("flag", "设置成功");
			btnok_intent.putExtra("info", "缴费项目管理设置成功");
			btnok_intent.setClass(PaymentManage.this, PaymentResult.class);
			PaymentManage.this.startActivity(btnok_intent);
		}
	}
}
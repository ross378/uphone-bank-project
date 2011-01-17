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
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class PaymentSelfService extends ListActivity {
	
	private Button btn_main = null;
	private Button btn_help = null;
	private Button btn_now = null;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_main);
        
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
        paylist3.put("payment_list", "  --手机充值");
        paylist3.put("payment_list_info", ">");
        paylist4.put("payment_list", "  --平安保险");
        paylist4.put("payment_list_info", ">");
        paylist5.put("payment_list", "  --交通罚款");
        paylist5.put("payment_list_info", ">");
        
        mainlist.add(paylist1);
        mainlist.add(paylist2);
        mainlist.add(paylist3);
        mainlist.add(paylist4);
        mainlist.add(paylist5);
        
        SimpleAdapter MainListAdapter = new SimpleAdapter(this, mainlist,R.layout.payment_list, new String[] {
				"payment_list", "payment_list_info" }, new int[] { R.id.payment_list, R.id.payment_list_info } );
        this.setListAdapter(MainListAdapter);
        
        btn_help = (Button)this.findViewById(R.id.btnCoustom);
        btn_help.setText("账户信息");
        btn_help.setVisibility(View.VISIBLE);
        
	}
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		if (id == 0) {
			Intent payment_intent = new Intent();
			payment_intent.setClass(PaymentSelfService.this, PaymentPend.class);
			PaymentSelfService.this.startActivity(payment_intent);
		}else if(id==1){
			System.out.println("id----------------"+id);
	    	System.out.println("position----------"+position);
			//Intent payment_intent = new Intent();
			//payment_intent.setClass(PaymentSelfService.this, PaymentManage.class);
		}else if(id==2){
			Intent payment_intent = new Intent();
			payment_intent.putExtra("title", "手机充值");
			payment_intent.putExtra("amount", "50元");
			payment_intent.putExtra("serialnum", "111115");
			payment_intent.setClass(PaymentSelfService.this, PaymentDetail.class);
			PaymentSelfService.this.startActivity(payment_intent);
		}else if(id==3){
			System.out.println("id----------------"+id);
	    	System.out.println("position----------"+position);
			//Intent payment_intent = new Intent();
			//payment_intent.setClass(PaymentSelfService.this, PaymentHistory.class);
		}else if(id==4){
			System.out.println("id----------------"+id);
	    	System.out.println("position----------"+position);
			//Intent payment_intent = new Intent();
			//payment_intent.setClass(PaymentSelfService.this, PaymentDefAcc.class);
		}
	}
}
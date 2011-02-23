package com.ultrawise.android.bank.view.payment;

import java.util.ArrayList;
import java.util.HashMap;

import com.ultrawise.android.bank.view.payment.PaymentResult.BtnOkCL;
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

public class PaymentPend extends ListActivity {
	
	Intent payment_intent;
	Intent return_intent;

	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_main);

        TextView tvClassFirst = (TextView)this.findViewById(R.id.class_first);
		tvClassFirst.setText("手机缴费>");
		tvClassFirst.setVisibility(View.VISIBLE);
		
		
		TextView tvClassThree = (TextView)this.findViewById(R.id.class_third);
		tvClassThree.setText("待缴费项目");
		tvClassThree.setVisibility(View.VISIBLE);
        
        ArrayList<HashMap<String,String>> mainlist = new ArrayList<HashMap<String,String>>();
        
        HashMap<String,String> paylist1 = new HashMap<String,String>();
        HashMap<String,String> paylist2 = new HashMap<String,String>();
        HashMap<String,String> paylist3 = new HashMap<String,String>();
        HashMap<String,String> paylist4 = new HashMap<String,String>();
        
        paylist1.put("payment_list", "  --水费");
        paylist1.put("payment_list_info", "50元 >");
        paylist2.put("payment_list", "  --电费");
        paylist2.put("payment_list_info", "100元 >");
        paylist3.put("payment_list", "  --煤气");
        paylist3.put("payment_list_info", "90元 >");
        paylist4.put("payment_list", "  --房租");
        paylist4.put("payment_list_info", "300元 >");
        
        
        mainlist.add(paylist1);
        mainlist.add(paylist2);
        mainlist.add(paylist3);
        mainlist.add(paylist4);
        
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
			payment_intent.putExtra("title", "水费");
			payment_intent.putExtra("amount", "50元");
			payment_intent.putExtra("serialnum", "111111");
			payment_intent.setClass(PaymentPend.this, PaymentDetail.class);
			PaymentPend.this.startActivity(payment_intent);
		}else if(id==1){
			Intent payment_intent = new Intent();
			payment_intent.putExtra("title", "电费");
			payment_intent.putExtra("amount", "100元");
			payment_intent.putExtra("serialnum", "111112");
			payment_intent.setClass(PaymentPend.this, PaymentDetail.class);
			PaymentPend.this.startActivity(payment_intent);
		}else if(id==2){
			Intent payment_intent = new Intent();
			payment_intent.putExtra("title", "煤气费");
			payment_intent.putExtra("amount", "90元");
			payment_intent.putExtra("serialnum", "111113");
			payment_intent.setClass(PaymentPend.this, PaymentDetail.class);
			PaymentPend.this.startActivity(payment_intent);
		}else if(id==3){
			Intent payment_intent = new Intent();
			payment_intent.putExtra("title", "房租");
			payment_intent.putExtra("amount", "300元");
			payment_intent.putExtra("serialnum", "111114");
			payment_intent.setClass(PaymentPend.this, PaymentDetail.class);
			PaymentPend.this.startActivity(payment_intent);
		}
	}

}
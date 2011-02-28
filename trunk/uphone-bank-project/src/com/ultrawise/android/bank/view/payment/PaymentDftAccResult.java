package com.ultrawise.android.bank.view.payment;

import com.ultrawise.android.bank.view.payment.PaymentResult.BtnOkCL;
import com.ultrawise.android.bank.view.transfer.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import android.view.View.OnClickListener;

public class PaymentDftAccResult extends Activity {
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.payment_result);
	        
	     
	    	TextView tvshow = (TextView)findViewById(R.id.tv_paymentdl_info);
	    	Button btnok = (Button)findViewById(R.id.btn_paymentdl_ok);
	    	
	        Intent receive_intent = getIntent();
	        String flag = receive_intent.getStringExtra("flag");
	        String info = receive_intent.getStringExtra("info");
	  
	        tvshow.setText(info);
	        
	        btnok.setOnClickListener(new BtnOkCL());
	 }
	 class BtnOkCL implements OnClickListener{
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent transinfo_intent = new Intent();
	    		transinfo_intent.setClass(PaymentDftAccResult.this, PaymentMain.class);
	    		startActivity(transinfo_intent);
			}
	    }
}
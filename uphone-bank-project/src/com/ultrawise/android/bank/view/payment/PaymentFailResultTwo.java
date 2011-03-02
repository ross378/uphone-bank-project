	package com.ultrawise.android.bank.view.payment;
	import com.ultrawise.android.bank.view.transfer.R;
	import android.app.Activity;
	import android.content.Intent;
	import android.os.Bundle;
	import android.widget.Button;
	import android.widget.TextView;
	import android.view.View;
	import android.view.View.OnClickListener;

	public class PaymentFailResultTwo extends Activity {
		 @Override
		    public void onCreate(Bundle savedInstanceState) {
		        super.onCreate(savedInstanceState);
		        setContentView(R.layout.payment_fail_result_two);
		        
		        TextView tvflag = (TextView)findViewById(R.id.tv_paymentdl_flag);
		    	TextView tvshow = (TextView)findViewById(R.id.tv_paymentdl_info);
		    	Button btn_ok = (Button)findViewById(R.id.btn_paymentdl_cancle);
		    	Button btna_gain = (Button)findViewById(R.id.btn_paymentdl_again);
		    	
		        Intent receive_intent = getIntent();
		        String flag = receive_intent.getStringExtra("flag");
		        String info = receive_intent.getStringExtra("info");
		        tvflag.setText(flag);
		        tvshow.setText(info);
		        
		        btn_ok.setOnClickListener(new BtnOkCL());
		        
		        btna_gain.setOnClickListener(new OnClickListener() {//重选账户
					
					public void onClick(View v) {
						Intent transinfo_intent = new Intent();
						
			    		transinfo_intent.setClass(PaymentFailResultTwo.this, PaymentSelectAccount.class);
			    		startActivity(transinfo_intent);
					}
				});
		        
		 }
		 class BtnOkCL implements OnClickListener{
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent transinfo_intent = new Intent();
		    		transinfo_intent.setClass(PaymentFailResultTwo.this, PaymentMain.class);
		    		startActivity(transinfo_intent);
				}
		    }
	}


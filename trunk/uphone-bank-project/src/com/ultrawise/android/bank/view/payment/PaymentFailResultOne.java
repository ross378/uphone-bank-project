package com.ultrawise.android.bank.view.payment;
import com.ultrawise.android.bank.view.transfer.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import android.view.View.OnClickListener;

public class PaymentFailResultOne extends Activity {
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.payment_result_one);
	        
//	        TextView tvflag = (TextView)findViewById(R.id.tv_paymentdl_flag);
	    	TextView tvshow = (TextView)findViewById(R.id.tv_paymentdl_info);
	    	Button btnok = (Button)findViewById(R.id.btn_paymentdl_ok);
	    	
	        Intent receive_intent = getIntent();
//	        String flag = receive_intent.getStringExtra("flag");
	        String info = receive_intent.getStringExtra("info");
//	        tvflag.setText(flag);
	        tvshow.setText(info);
	        btnok.setText("重输密码");
	        btnok.setOnClickListener(new BtnOkCL());
	 }
	 class BtnOkCL implements OnClickListener{
			public void onClick(View v) {
				
				PaymentFailResultOne.this.finish();
				// TODO Auto-generated method stub
//				Intent transinfo_intent = new Intent();
//				transinfo_intent.putExtra("Account", "123455467347");
//	    		transinfo_intent.setClass(PaymentFailResultOne.this, PaymentInPwd.class);
//	    		startActivity(transinfo_intent);
			}
	    }
}
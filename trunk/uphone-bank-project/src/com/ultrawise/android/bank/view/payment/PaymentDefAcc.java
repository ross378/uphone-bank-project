package com.ultrawise.android.bank.view.payment;

import com.ultrawise.android.bank.view.transfer.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.view.View;
import android.view.View.OnClickListener;

public class PaymentDefAcc extends Activity {
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_defacc);
        
        RadioGroup rg_paymentdft = (RadioGroup)findViewById(R.id.rg_paydft_acc);
        RadioButton rb_paymentdft1 = (RadioButton)findViewById(R.id.rb_paydft_acc1);
        RadioButton rb_paymentdft2 = (RadioButton)findViewById(R.id.rb_paydft_acc2);
        RadioButton rb_paymentdft3 = (RadioButton)findViewById(R.id.rb_paydft_acc3);
        
        rb_paymentdft1.setText("111111111111");
        rb_paymentdft2.setText("222222222222");
        rb_paymentdft3.setText("333333333333");
        
        TextView tvClassFirst = (TextView)this.findViewById(R.id.class_first);
		tvClassFirst.setText("手机缴费>");
		tvClassFirst.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// intent = QueryAccount.this.getIntent();
				// intent.setClass(QueryAccount.this, AccountManagement.class);
				// QueryAccount.this.startActivity(intent);
			}
		});
		tvClassFirst.setVisibility(View.VISIBLE);
		
		TextView tvClassSecond = (TextView)this.findViewById(R.id.class_second);
		tvClassSecond.setText("默认缴费账户");
		tvClassSecond.setVisibility(View.VISIBLE);
        
        ImageView iv_now = (ImageView)this.findViewById(R.id.btnCoustom);
        iv_now.setVisibility(View.VISIBLE);
        
        Button btn_paydftacc_ok = (Button)findViewById(R.id.btn_paydftacc_ok);
        btn_paydftacc_ok.setOnClickListener(new BtnPayDftAccCL());
	}
	class BtnPayDftAccCL implements OnClickListener{
		public void onClick(View v){
			Intent paydftacc_intent = new Intent();
			paydftacc_intent.putExtra("flag", "设置成功");
			paydftacc_intent.putExtra("info", "默认账户设置成功!");
			paydftacc_intent.setClass(PaymentDefAcc.this, PaymentDftAccResult.class);
			PaymentDefAcc.this.startActivity(paydftacc_intent);
		}
	}
}
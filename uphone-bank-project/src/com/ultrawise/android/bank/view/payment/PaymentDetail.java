package com.ultrawise.android.bank.view.payment;

import com.ultrawise.android.bank.view.transfer.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class PaymentDetail extends Activity {
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_details);
        
        TextView tvClassFirst = (TextView)this.findViewById(R.id.class_first);
		tvClassFirst.setText("首页>");
		tvClassFirst.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
//				Intent payment_intent = new Intent();
//				payment_intent.setClass(PaymentDetail.this, PaymentMain.class);
//				PaymentDetail.this.startActivity(payment_intent);	
			}
		});
		
		tvClassFirst.setVisibility(View.VISIBLE);
		
        TextView tvClassSecond = (TextView)this.findViewById(R.id.class_second);
        tvClassSecond.setText("自助缴费>");
        tvClassSecond.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent payment_intent = new Intent();
				payment_intent.setClass(PaymentDetail.this, PaymentMain.class);
				PaymentDetail.this.startActivity(payment_intent);	
			}
		});
		
        tvClassSecond.setVisibility(View.VISIBLE);
		
		
		
		
		
		TextView tvClassThird = (TextView)this.findViewById(R.id.class_third);
		tvClassThird.setText("待缴费项目");
		tvClassThird.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Intent payment_intent = new Intent();
				payment_intent.setClass(PaymentDetail.this, PaymentPend.class);
				PaymentDetail.this.startActivity(payment_intent);	
				
			}
		});
		
		
		tvClassThird.setVisibility(View.VISIBLE);
		

        
        TextView tv_pay_title = (TextView)findViewById(R.id.tv_pay_title);
        TextView tv_pay_amount = (TextView)findViewById(R.id.tv_pay_amount);
        TextView tv_inputed_peo=(TextView)findViewById(R.id.tv_inputed_peo);
        TextView tv_pay_sernum = (TextView)findViewById(R.id.tv_pay_sernum);
        TextView tv_pay_deadline = (TextView)findViewById(R.id.tv_pay_deadline);
        
        Button btn_pay_ok = (Button)findViewById(R.id.btn_pay_ok);
        
        Intent paymentre_intent = getIntent();
        String pay_title = paymentre_intent.getStringExtra("title");
        String pay_amount = paymentre_intent.getStringExtra("amount");
        String inputed_peo= paymentre_intent.getStringExtra("inputed_peo");
        String pay_sernum = paymentre_intent.getStringExtra("serialnum");
        String pay_deadline=paymentre_intent.getStringExtra("deadline");
        
    
        
        tv_pay_title.setText("项目名称："+ pay_title);
        tv_pay_amount.setText("缴费金额："+ pay_amount);
        tv_inputed_peo.setText("收费方："+inputed_peo);
        tv_pay_sernum.setText("缴费流水号："+ pay_sernum);
        tv_pay_deadline.setText("缴费期限:"+pay_deadline);
        btn_pay_ok.setText("前往缴费"+">");
        
        ImageView iv_now = (ImageView)this.findViewById(R.id.btnCoustom);
        iv_now.setVisibility(View.VISIBLE);
        
        btn_pay_ok.setOnClickListener(new View.OnClickListener(){
        	public void onClick(View v){
        		Intent transconok_intent = new Intent();
        		transconok_intent.setClass(PaymentDetail.this, PaymentSelectAccountType.class);
        		startActivity(transconok_intent);
        	}
        });
	}
}
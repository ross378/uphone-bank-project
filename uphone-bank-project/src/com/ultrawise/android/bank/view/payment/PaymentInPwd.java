package com.ultrawise.android.bank.view.payment;

import com.ultrawise.android.bank.view.transfer.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class PaymentInPwd extends Activity {//账户信息显示和密码输入
	EditText tv_pasword;
	TextView tv_balance_num;
	int acc_balance;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_account_inpwd);
        TextView tvClassFirst = (TextView)this.findViewById(R.id.class_first);
		tvClassFirst.setText("首页>");
		tvClassFirst.setVisibility(View.VISIBLE);
		
		tvClassFirst.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent payment_intent = new Intent();
				payment_intent.setClass(PaymentInPwd.this, PaymentMain.class);
				PaymentInPwd.this.startActivity(payment_intent);	
			}
		});
        
        TextView tvClassSecond = (TextView)this.findViewById(R.id.class_second);
        tvClassSecond.setText("自助缴费>");
        tvClassSecond.setVisibility(View.VISIBLE);
		
        tvClassSecond.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent payment_intent = new Intent();
				payment_intent.setClass(PaymentInPwd.this, PaymentMain.class);
				PaymentInPwd.this.startActivity(payment_intent);	
			}
		});
		tvClassFirst.setVisibility(View.VISIBLE);
		
		
		TextView tvClassThird = (TextView)this.findViewById(R.id.class_third);
		tvClassThird.setText("待缴费项目");
		tvClassThird.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
//				Intent payment_intent = new Intent();
//				payment_intent.setClass(PaymentInPwd.this, PaymentPend.class);
//				PaymentInPwd.this.startActivity(payment_intent);	
				
			}
		});
		
		TextView tv_select_acc = (TextView)findViewById(R.id.select_acc);
        TextView tv_pay_num = (TextView)findViewById(R.id.pay_num);
        TextView tv_acc_balance = (TextView)findViewById(R.id.acc_balance);   
        tv_balance_num = (TextView)findViewById(R.id.balance_num);
        TextView tv_input_psswrd=(TextView)findViewById(R.id.input_psswrd);
        tv_pasword=(EditText)findViewById(R.id.password);
        
        Button btn_pay_ok = (Button)findViewById(R.id.rg_pay_ok);    
        Intent paymentre_intent = getIntent();
         
        
        String pay_num = paymentre_intent.getStringExtra("Account");    
        acc_balance =780;
        
        tv_pay_num.setText(pay_num); 
        
        
        tv_balance_num.setText(acc_balance+"元");
        
        btn_pay_ok.setText("确认缴费");
        
        ImageView iv_now = (ImageView)this.findViewById(R.id.btnCoustom);
        iv_now.setVisibility(View.VISIBLE);
	
        btn_pay_ok.setOnClickListener(new View.OnClickListener(){
        	
//        	Intent trans_intent = new Intent();
        	public void onClick(View v){
        		if(PaymentInPwd.this.tv_pasword.getText().toString()=="324"){
        		
        			
        			if(Integer.parseInt(PaymentInPwd.this.tv_balance_num.getText().toString())>=600){
        			Intent btnok_intent = new Intent();
        			btnok_intent.putExtra("flag", "成功！");
        			btnok_intent.putExtra("info", "缴费成功，余额为:"+(PaymentInPwd.this.acc_balance-600));
        			btnok_intent.setClass(PaymentInPwd.this, PaymentResult.class);
        			PaymentInPwd.this.startActivity(btnok_intent);
        			}	else {
            			Intent btnok_intent = new Intent();
            			btnok_intent.putExtra("flag", "失败！");
            			btnok_intent.putExtra("info", "余额不足！");
            			btnok_intent.setClass(PaymentInPwd.this, PaymentResult.class);
            			PaymentInPwd.this.startActivity(btnok_intent);
            			
            			
            		}
        			
        			
        			
        		}
        		else{
        			Intent btnok_intent = new Intent();
        			btnok_intent.putExtra("flag", "失败！");
        			btnok_intent.putExtra("info", "密码错误！");
        			btnok_intent.setClass(PaymentInPwd.this, PaymentResult.class);
        			PaymentInPwd.this.startActivity(btnok_intent);
        			
        			
        		}
        		
        		
        	}
        });
	
}
}
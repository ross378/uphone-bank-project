package com.ultrawise.android.bank.view.payment;

import com.ultrawise.android.bank.view.transfer.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class PaymentDetail extends Activity {	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_details);
        
        TextView tv_pay_title = (TextView)findViewById(R.id.tv_pay_title);
        TextView tv_pay_amount = (TextView)findViewById(R.id.tv_pay_amount);
        TextView tv_pay_sernum = (TextView)findViewById(R.id.tv_pay_sernum);
        TextView tv_pay_acc = (TextView)findViewById(R.id.tv_pay_acc);
        Button btn_pay_ok = (Button)findViewById(R.id.btn_pay_ok);
        Button btn_pay_cancle = (Button)findViewById(R.id.btn_pay_cancle);
        
        Intent paymentre_intent = getIntent();
        String pay_title = paymentre_intent.getStringExtra("title");
        String pay_amount = paymentre_intent.getStringExtra("amount");
        String pay_sernum = paymentre_intent.getStringExtra("serialnum");
        
        tv_pay_title.setText("项目名称："+ pay_title);
        tv_pay_amount.setText("缴费金额："+ pay_amount);
        tv_pay_sernum.setText("缴费流水号："+ pay_sernum);
        btn_pay_ok.setText("确认缴费");
        
        btn_pay_ok.setOnClickListener(new View.OnClickListener(){
        	public void onClick(View v){
        		Intent transconok_intent = new Intent();
        		transconok_intent.putExtra("flag", "success");
        		transconok_intent.putExtra("info", "The payment is successful");
        		transconok_intent.setClass(PaymentDetail.this, PaymentResult.class);
        		startActivity(transconok_intent);
        	}
        });
        btn_pay_cancle.setOnClickListener(new View.OnClickListener(){
        	public void onClick(View v){
        		Intent transconcancle_intent = new Intent();
        		transconcancle_intent.putExtra("flag", "failed");
        		transconcancle_intent.putExtra("info", "The payment is canceled");
        		transconcancle_intent.setClass(PaymentDetail.this, PaymentResult.class);
        		startActivity(transconcancle_intent);
        	}
        });
	}
}
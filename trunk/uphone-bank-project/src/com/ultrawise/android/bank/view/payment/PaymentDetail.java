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
		tvClassSecond.setText("自助缴费>");
		tvClassSecond.setVisibility(View.VISIBLE);
		
		TextView tvClassThree = (TextView)this.findViewById(R.id.class_third);
		tvClassThree.setText("便捷服务>");
		tvClassThree.setVisibility(View.VISIBLE);
		
		TextView tvClassFour = (TextView)this.findViewById(R.id.class_four);
		tvClassFour.setText("确认缴费");
		tvClassFour.setVisibility(View.VISIBLE);
        
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
        //外加的收费方
        String pay_sern = paymentre_intent.getStringExtra("serialnum");
        
        tv_pay_title.setText("项目名称："+ pay_title);
        tv_pay_amount.setText("缴费金额："+ pay_amount);
        tv_pay_sernum.setText("缴费流水号："+ pay_sernum);
        btn_pay_ok.setText("前往缴费");
        
        ImageView iv_now = (ImageView)this.findViewById(R.id.btnCoustom);
        iv_now.setVisibility(View.VISIBLE);
        
        btn_pay_ok.setOnClickListener(new View.OnClickListener(){
        	public void onClick(View v){
        		System.out.println("fdsfsdf");
        		Intent transconok_intent = new Intent();
        		transconok_intent.putExtra("flag", "success");
        		transconok_intent.putExtra("info", "The payment is successful");
        		transconok_intent.setClass(PaymentDetail.this, PaymentSelectAccountType.class);
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
package com.ultrawise.android.bank.view.payment;

import com.ultrawise.android.bank.view.transfer.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class PaymentHisDetail extends Activity {	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_hisdtl);
        
        TextView tv_title = (TextView)findViewById(R.id.tv_payhis_title);
        TextView tv_amount = (TextView)findViewById(R.id.tv_payhis_amount);
        TextView tv_time = (TextView)findViewById(R.id.tv_payhis_tim);
        TextView tv_sernum = (TextView)findViewById(R.id.tv_payhis_sernum);
        TextView tv_acc = (TextView)findViewById(R.id.tv_payhis_acc);
        TextView tv_acc2 = (TextView)findViewById(R.id.tv_payhis_acc2);
        
        Button btn_payhisdl_ok = (Button)findViewById(R.id.btn_payhisdt_ok);
        
        Intent payhisdtl_intent = getIntent();
        
        String tvtitle = payhisdtl_intent.getStringExtra("title");
        String tvamount = payhisdtl_intent.getStringExtra("amount");
        String tvtime = payhisdtl_intent.getStringExtra("time");
        String tvsernum = payhisdtl_intent.getStringExtra("serialnum");
        String tvacc = payhisdtl_intent.getStringExtra("acc");
        
        tv_title.setText("项目名称："+tvtitle);
        tv_amount.setText("缴费金额："+tvamount);
        tv_time.setText("缴费时间："+tvtime);
        tv_sernum.setText("项目流水号："+tvsernum);
        tv_acc.setText("缴费账号：");
        tv_acc2.setText(tvacc);
        
        btn_payhisdl_ok.setOnClickListener(new BtnHistlCL());
	}
	class BtnHistlCL implements OnClickListener{
		public void onClick(View v){
			Intent payhisbk_intent = new Intent();
			payhisbk_intent.setClass(PaymentHisDetail.this, PaymentMain.class);
			startActivity(payhisbk_intent);
		}
	}
}
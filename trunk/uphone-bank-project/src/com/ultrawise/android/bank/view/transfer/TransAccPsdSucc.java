package com.ultrawise.android.bank.view.transfer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class TransAccPsdSucc extends Activity {
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trans_acc_psdsucc);
        
        Intent receive_intent = getIntent();
        String transtype = receive_intent.getStringExtra("transtype");
        
        TextView tvClassFirst = (TextView)this.findViewById(R.id.class_first);
		tvClassFirst.setText("首页");
		tvClassFirst.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(TransAccPsdSucc.this, TransferMain.class);
				TransAccPsdSucc.this.startActivity(intent);
			}
		});
		tvClassFirst.setVisibility(View.VISIBLE);
		
		TextView tvClassSecond = (TextView)this.findViewById(R.id.class_second);
		tvClassSecond.setText(">转账汇款");
		tvClassSecond.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(TransAccPsdSucc.this, TransferMain.class);
				TransAccPsdSucc.this.startActivity(intent);
			}
		});
		tvClassSecond.setVisibility(View.VISIBLE);
		
		TextView tvClassThird = (TextView)this.findViewById(R.id.class_third);
		tvClassThird.setText(transtype);
		tvClassThird.setVisibility(View.VISIBLE);
        
        Button btn_trans_reselect = (Button)findViewById(R.id.btn_trans_reselect);
        Button btn_trans_next = (Button)findViewById(R.id.btn_trans_next);
        
        btn_trans_reselect.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent receive_intent = getIntent();
		        String transtype = receive_intent.getStringExtra("transtype");
				Intent trans_intent = new Intent();
				trans_intent.putExtra("transtype", transtype);
				trans_intent.setClass(TransAccPsdSucc.this,TransAccSelect.class);
				TransAccPsdSucc.this.startActivity(trans_intent);
			}
        });
        btn_trans_next.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent receive_intent = getIntent();
		        String transtype = receive_intent.getStringExtra("transtype");
				Intent trans_intent = new Intent();
				trans_intent.putExtra("transtype", transtype);
				trans_intent.setClass(TransAccPsdSucc.this,TransAmtInput.class);
				TransAccPsdSucc.this.startActivity(trans_intent);
			}
        });
	}
}
package com.ultrawise.android.bank.view.transfer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class TransAmtConfirm extends Activity {
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trans_amt_confirm);
        
        Intent receive_intent = getIntent();
        String transtype = receive_intent.getStringExtra("transtype");
        
        TextView tvClassFirst = (TextView)this.findViewById(R.id.class_first);
		tvClassFirst.setText("首页");
		tvClassFirst.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(TransAmtConfirm.this, TransferMain.class);
				TransAmtConfirm.this.startActivity(intent);
			}
		});
		tvClassFirst.setVisibility(View.VISIBLE);
		
		TextView tvClassSecond = (TextView)this.findViewById(R.id.class_second);
		tvClassSecond.setText(">转账汇款");
		tvClassSecond.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(TransAmtConfirm.this, TransferMain.class);
				TransAmtConfirm.this.startActivity(intent);
			}
		});
		tvClassSecond.setVisibility(View.VISIBLE);
		
		TextView tvClassThird = (TextView)this.findViewById(R.id.class_third);
		tvClassThird.setText(transtype);
		tvClassThird.setVisibility(View.VISIBLE);
        
        Button btn_trans_cofok = (Button)findViewById(R.id.btn_trans_confok);
        Button btn_trans_cofcan = (Button)findViewById(R.id.btn_trans_confcancle);
        btn_trans_cofok.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent trans_amt_cofcan = new Intent();
				trans_amt_cofcan.setClass(TransAmtConfirm.this, TransSuccDialog.class);
				TransAmtConfirm.this.startActivity(trans_amt_cofcan);
				TransAmtConfirm.this.startActivity(trans_amt_cofcan);
			}
        	
        });
        btn_trans_cofcan.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent trans_amt_cofcan = new Intent();
				trans_amt_cofcan.setClass(TransAmtConfirm.this, TransferMain.class);
				TransAmtConfirm.this.startActivity(trans_amt_cofcan);
			}
        	
        });
	}
}
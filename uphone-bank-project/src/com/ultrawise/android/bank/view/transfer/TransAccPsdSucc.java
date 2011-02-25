package com.ultrawise.android.bank.view.transfer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class TransAccPsdSucc extends Activity {
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trans_acc_psdsucc);
        
        Button btn_trans_reselect = (Button)findViewById(R.id.btn_trans_reselect);
        Button btn_trans_next = (Button)findViewById(R.id.btn_trans_next);
        
        btn_trans_reselect.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent trans_intent = new Intent();
				trans_intent.setClass(TransAccPsdSucc.this,TransAccSelect.class);
				TransAccPsdSucc.this.startActivity(trans_intent);
			}
        });
        btn_trans_next.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent trans_intent = new Intent();
				trans_intent.setClass(TransAccPsdSucc.this,TransAmtInput.class);
				TransAccPsdSucc.this.startActivity(trans_intent);
			}
        });
	}
}
package com.ultrawise.android.bank.view.transfer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class TransAmtInput extends Activity {
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trans_amt_input);
        
        Button btn_trans_amtnext = (Button)findViewById(R.id.btn_trans_amtnext);
        btn_trans_amtnext.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent trans_amtnext = new Intent();
				trans_amtnext.setClass(TransAmtInput.this, TransAmtConfirm.class);
				TransAmtInput.this.startActivity(trans_amtnext);
			}
        	
        });
	}
}
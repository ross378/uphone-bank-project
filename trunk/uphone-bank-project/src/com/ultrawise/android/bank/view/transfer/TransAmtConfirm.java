package com.ultrawise.android.bank.view.transfer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class TransAmtConfirm extends Activity {
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trans_amt_confirm);
        
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
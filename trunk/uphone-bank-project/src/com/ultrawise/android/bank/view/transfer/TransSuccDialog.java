package com.ultrawise.android.bank.view.transfer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class TransSuccDialog extends Activity {
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trans_succ_dialog);
        
        Button btn_trans_dilgok = (Button)findViewById(R.id.btn_trans_dilgok);
        btn_trans_dilgok.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent_trans_dilg = new Intent();
				intent_trans_dilg.setClass(TransSuccDialog.this, TransferMain.class);
				TransSuccDialog.this.startActivity(intent_trans_dilg);
			}
        	
        });
	}
}
package com.ultrawise.android.bank.view.transfer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;


public class TransAccInput extends Activity {
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trans_acc_input);
        
        Spinner sp_trans_inptype = (Spinner)findViewById(R.id.sp_trans_inptype);
        ArrayAdapter spadapter1 = ArrayAdapter.createFromResource(this, R.array.currencydw, android.R.layout.simple_spinner_item);
        spadapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_trans_inptype.setAdapter(spadapter1);
        
        Spinner sp_trans_inpacc = (Spinner)findViewById(R.id.sp_trans_inpacc);
        ArrayAdapter spadapter2 = ArrayAdapter.createFromResource(this, R.array.account, android.R.layout.simple_spinner_item);
        spadapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_trans_inpacc.setAdapter(spadapter2);
        
        Button btn_trans_next = (Button)findViewById(R.id.btn_trans_inpacc);
        btn_trans_next.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(TransAccInput.this, TransAccActive.class);
				TransAccInput.this.startActivity(intent);
			}
        });
	}
}
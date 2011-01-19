package com.ultrawise.android.bank.view.credit;


import com.ultrawise.android.bank.view.transfer.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class CancelCardQR extends Activity {
	private Button okBtnButton=null;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cancelcardqr);
        
        okBtnButton=(Button)findViewById(R.id.okBtn);
        okBtnButton.setOnClickListener(new OKBtnButtonListener());
	}
	class OKBtnButtonListener implements OnClickListener{

		public void onClick(View arg0) {
			Intent intent = new Intent();
    		intent.setClass(CancelCardQR.this, CreditView.class);
    		CancelCardQR.this.startActivity(intent);
			
		}
		
	}
}

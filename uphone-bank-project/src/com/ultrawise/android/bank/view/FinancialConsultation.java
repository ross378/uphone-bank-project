package com.ultrawise.android.bank.view;

import com.ultrawise.android.bank.view.ABankMain.CreditButtonListener;
import com.ultrawise.android.bank.view.credit.CreditView;
import com.ultrawise.android.bank.view.transfer.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class FinancialConsultation extends Activity {
	private ImageButton aBankButton=null;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.financialconsultation);
        aBankButton=(ImageButton)findViewById(R.id.aBank);
        aBankButton.setOnClickListener(new ABankButtonListener());
    }
	class ABankButtonListener implements OnClickListener{

		public void onClick(View arg0) {
			Intent intent=new Intent();
			intent.setClass(FinancialConsultation.this,ABankMain.class);
			FinancialConsultation.this.startActivity(intent);
			
		}
		
	}
}

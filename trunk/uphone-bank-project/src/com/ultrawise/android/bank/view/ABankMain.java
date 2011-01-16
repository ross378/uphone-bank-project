package com.ultrawise.android.bank.view;


import com.ultrawise.android.bank.view.credit.CreditView;
import com.ultrawise.android.bank.view.transfer.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class ABankMain extends Activity {
    /** Called when the activity is first created. */
    private ImageButton creditButton=null;
    private ImageButton accountManagerButton=null;
    private ImageButton actActivationButton=null;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        creditButton=(ImageButton)findViewById(R.id.credit);
        creditButton.setOnClickListener(new CreditButtonListener());
        
        accountManagerButton=(ImageButton)findViewById(R.id.ActManager);
        accountManagerButton.setOnClickListener(new AccountManagerButtonListener());
        
        actActivationButton=(ImageButton)findViewById(R.id.ActActivation);
        actActivationButton.setOnClickListener(new ActActivationButtonListener());
    }
	class CreditButtonListener implements OnClickListener{

		public void onClick(View arg0) {
			Intent intent=new Intent();
			intent.setClass(ABankMain.this,CreditView.class);
			ABankMain.this.startActivity(intent);
			
		}
		
	}
	class AccountManagerButtonListener implements OnClickListener{

		public void onClick(View arg0) {
			Intent intent=new Intent();
			intent.setClass(ABankMain.this,FinancialConsultation.class);
			ABankMain.this.startActivity(intent);
			
		}
		
	}
	class ActActivationButtonListener implements OnClickListener{

		public void onClick(View arg0) {
			Intent intent=new Intent();
			intent.setClass(ABankMain.this,UserLogin.class);
			ABankMain.this.startActivity(intent);
			
		}
		
	}
}
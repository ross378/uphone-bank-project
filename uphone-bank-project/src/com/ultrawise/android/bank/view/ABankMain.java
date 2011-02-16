package com.ultrawise.android.bank.view;


import com.ultrawise.android.bank.view.account_management.AccountManagement;
import com.ultrawise.android.bank.view.account_management.ActiveAccount;
import com.ultrawise.android.bank.view.account_query.AccountQuery;
import com.ultrawise.android.bank.view.account_query.QueryAccount;
import com.ultrawise.android.bank.view.credit.CreditView;
import com.ultrawise.android.bank.view.payment.PaymentMain;
import com.ultrawise.android.bank.view.transfer.R;
import com.ultrawise.android.bank.view.transfer.TransferMain;

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
    private ImageButton actQueryButton=null;
    private ImageButton transferMoneyButton=null;
    private ImageButton paymentButton=null;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.abank);
        creditButton=(ImageButton)findViewById(R.id.credit);
        creditButton.setOnClickListener(new CreditButtonListener());
        
        accountManagerButton=(ImageButton)findViewById(R.id.actManager);
        accountManagerButton.setOnClickListener(new AccountManagerButtonListener());
        
        actActivationButton=(ImageButton)findViewById(R.id.actActivate);
        actActivationButton.setOnClickListener(new ActActivationButtonListener());
        
        actQueryButton=(ImageButton)findViewById(R.id.actQuery);
        actQueryButton.setOnClickListener(new ActQueryButtonListener());
        
        transferMoneyButton=(ImageButton)findViewById(R.id.transferMoney);
        transferMoneyButton.setOnClickListener(new TransferMoneyButtonListener());
        
        paymentButton=(ImageButton)findViewById(R.id.payment);
        paymentButton.setOnClickListener(new PaymentButtonListener());
    }
	class CreditButtonListener implements OnClickListener{

		public void onClick(View arg0) {
			Intent intent=new Intent();
			intent.setClass(ABankMain.this,CreditView.class);
			ABankMain.this.startActivity(intent);
			
		}
		
	}
	class TransferMoneyButtonListener implements OnClickListener{

		public void onClick(View arg0) {
			Intent intent=new Intent();
			intent.setClass(ABankMain.this,TransferMain.class);
			ABankMain.this.startActivity(intent);
			
		}
		
	}
	class ActActivationButtonListener implements OnClickListener{

		public void onClick(View arg0) {
			Intent intent=new Intent();
			intent.setClass(ABankMain.this,ActiveAccount.class);
			ABankMain.this.startActivity(intent);
			
		}
		
	}
	
	class ActQueryButtonListener implements OnClickListener{

		public void onClick(View arg0) {
			Intent intent=new Intent();
			intent.setClass(ABankMain.this,AccountQuery.class);
			ABankMain.this.startActivity(intent);
			
		}
		
	}
	class AccountManagerButtonListener implements OnClickListener{

		public void onClick(View arg0) {
			Intent intent=new Intent();
			intent.setClass(ABankMain.this,AccountManagement.class);
			ABankMain.this.startActivity(intent);
			
		}
		
	}
	class PaymentButtonListener implements OnClickListener{

		public void onClick(View arg0) {
			Intent intent=new Intent();
			intent.setClass(ABankMain.this,PaymentMain.class);
			ABankMain.this.startActivity(intent);
			
		}
		
	}
	
}
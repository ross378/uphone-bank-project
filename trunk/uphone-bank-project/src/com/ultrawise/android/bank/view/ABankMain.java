package com.ultrawise.android.bank.view;


import java.util.ArrayList;
import java.util.List;

import com.ultrawise.android.bank.consum_webservices.AccManaConWebservices;
import com.ultrawise.android.bank.consum_webservices.QuerySever;
import com.ultrawise.android.bank.view.DepositeRates.BackImageViewListener;
import com.ultrawise.android.bank.view.DepositeRates.PhoneBankImageViewListener;
import com.ultrawise.android.bank.view.account_management.AccountManagement;
import com.ultrawise.android.bank.view.account_management.AccountManagementList;
import com.ultrawise.android.bank.view.account_management.ActiveAccount;
import com.ultrawise.android.bank.view.account_management.ActiveAccountSelect;
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
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;

public class ABankMain extends Activity {
    /** Called when the activity is first created. */
    private ImageButton creditButton=null;
    private ImageButton accountManagerButton=null;
    private ImageButton actActivationButton=null;
    private ImageButton actQueryButton=null;
    private ImageButton transferMoneyButton=null;
    private ImageButton paymentButton=null;
    private ImageView phoneBank = null;
	 private ImageView helper = null;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
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
        
        phoneBank = (ImageView)findViewById(R.id.btnMain);      
        phoneBank.setOnClickListener(new PhoneBankImageViewListener());
        helper = (ImageView)findViewById(R.id.btnHelper);
        helper.setOnClickListener(new HelperImageViewListener());
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
			intent.setClass(ABankMain.this,ActiveAccountSelect.class);
			ABankMain.this.startActivity(intent);
			
		}
		
	}
	
	class ActQueryButtonListener implements OnClickListener{

		public void onClick(View arg0) {
			Intent intent=new Intent();
			
			/**
        	 * 从服务器上取得所需要的数据
        	 * 
        	 * @author gsm
        	 * @param 功能号
        	 * @return 返回卡的类型
        	 */
			List<String> result=QuerySever.connectHttp("021", null);
			for(String g:result)
			{
			System.out.println("服务器上取得所需要的数据-明文======"+g.toString());	
			}
			
			
			String[] arrResult=new String[result.size()];
			for(int i=0;i<result.size();i++)
			{   
				 arrResult[i]= result.get(i);
			}
			intent.putExtra("result", arrResult);
			
			intent.setClass(ABankMain.this,AccountQuery.class);
			ABankMain.this.startActivity(intent);
			
		}
		
	}
	class AccountManagerButtonListener implements OnClickListener{

		public void onClick(View arg0) {
			Intent intent=new Intent();
			intent.setClass(ABankMain.this,AccountManagementList.class);
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
	
	class PhoneBankImageViewListener implements OnClickListener{
		 
		 public void onClick(View args0){
			 
		 }
	 }
	 
	 class HelperImageViewListener implements OnClickListener{
		 
		 public void onClick(View args0){
			 Intent intent = new Intent();
			 intent.setClass(ABankMain.this, FinancialConsultation.class);
			 ABankMain.this.startActivity(intent);
		 }
	 }
}
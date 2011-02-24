package com.ultrawise.android.bank.view.credit;

import com.ultrawise.android.bank.view.transfer.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class CreditView extends Activity {
	    
		private Button creditDetailButton=null;
		private Button creditDetailButton1=null;
		private Button activateCardButton=null;
		private Button activateCardButton1=null;
		private Button cancelTheCardButton=null;
		private Button cancelTheCardButton1=null;
		private Button creditCardBindButton=null;
		private Button creditpayButton=null;
		private Button creditpayButton1=null;
		@Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);  
	        setContentView(R.layout.credit);  
	        //帐户信息
	        creditDetailButton=(Button)findViewById(R.id.creditDetail);
	        creditDetailButton.setOnClickListener(new CreditDetailButtonListener());
	        creditDetailButton1=(Button)findViewById(R.id.creditDetail1);
	        creditDetailButton1.setOnClickListener(new CreditDetailButtonListener());
	        //开卡
	        activateCardButton=(Button)findViewById(R.id.activateCard);
	        activateCardButton.setOnClickListener(new ActivateCardButtonListener());
	        activateCardButton1=(Button)findViewById(R.id.activateCard1);
	        activateCardButton1.setOnClickListener(new ActivateCardButtonListener());
		     //销卡
	        cancelTheCardButton=(Button)findViewById(R.id.cancelTheCard);
	        cancelTheCardButton.setOnClickListener(new CancelTheCardButtonListener());
	        cancelTheCardButton1=(Button)findViewById(R.id.cancelTheCard1);
	        cancelTheCardButton1.setOnClickListener(new CancelTheCardButtonListener());
	        
	        creditCardBindButton=(Button)findViewById(R.id.comelook);
	        creditCardBindButton.setOnClickListener(new CreditCardBindButtonListener());
	        //还款
	        creditpayButton=(Button)findViewById(R.id.creditpay);
	        creditpayButton.setOnClickListener(new CreditPayButtonListener());
	        creditpayButton1=(Button)findViewById(R.id.creditpay1);
	        creditpayButton1.setOnClickListener(new CreditPayButtonListener());
	        
	        
		}
		//帐户信息事件
		class CreditDetailButtonListener implements OnClickListener{

			public void onClick(View arg0) {
				Intent intent=new Intent();
				intent.setClass(CreditView.this,CreditDetail.class);
				CreditView.this.startActivity(intent);
				
			}
		}
		
		class ActivateCardButtonListener implements OnClickListener{

			public void onClick(View arg0) {
				Intent intent=new Intent();
				intent.setClass(CreditView.this,ActivateCard.class);
				CreditView.this.startActivity(intent);
				
			}
		}
		
		class CancelTheCardButtonListener implements OnClickListener{

			public void onClick(View arg0) {
				Intent intent=new Intent();
				intent.setClass(CreditView.this,CancelTheCard.class);
				CreditView.this.startActivity(intent);
				
			}
			
		}
		
		class CreditCardBindButtonListener implements OnClickListener{

			public void onClick(View arg0) {
				Intent intent=new Intent();
				intent.setClass(CreditView.this,CreditCardBind.class);
				CreditView.this.startActivity(intent);
				
			}
			
		}
		
		class CreditPayButtonListener implements OnClickListener{

			public void onClick(View arg0) {
				Intent intent=new Intent();
				intent.setClass(CreditView.this,SelfPay.class);
				CreditView.this.startActivity(intent);
				
			}
			
		}
}

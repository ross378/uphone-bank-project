package com.ultrawise.android.bank.view.credit;

import com.ultrawise.android.bank.view.transfer.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;


public class CreditView extends Activity {
		private ImageButton creditDetailButton=null;
		private ImageButton activateCardButton=null;
		private ImageButton cancelTheCardButton=null;
		private ImageButton creditCardBindButton=null;
		@Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.credit);
	        creditDetailButton=(ImageButton)findViewById(R.id.creditDetail);
	        creditDetailButton.setOnClickListener(new CreditDetailButtonListener());
	        
	        activateCardButton=(ImageButton)findViewById(R.id.activateCard);
	        activateCardButton.setOnClickListener(new ActivateCardButtonListener());
		
	        cancelTheCardButton=(ImageButton)findViewById(R.id.cancelTheCard);
	        cancelTheCardButton.setOnClickListener(new CancelTheCardButtonListener());
	        
	        
	        creditCardBindButton=(ImageButton)findViewById(R.id.creditCardBind);
	        creditCardBindButton.setOnClickListener(new CreditCardBindButtonListener());
		}
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
}

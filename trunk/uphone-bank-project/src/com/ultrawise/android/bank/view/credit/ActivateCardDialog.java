package com.ultrawise.android.bank.view.credit;
import com.ultrawise.android.bank.view.transfer.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ActivateCardDialog extends Activity {
	private int activateFlag=0;
	TextView tvflag;
	TextView tvflag1;
	String flag;
	String flag1;
	 String info ;
	 TextView tvshow;
	 Button btnok ;
	 String info1;
	 TextView tvshow1;
	 Button btnok1 ;
	 Button btnok2 ;
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        Intent receive_intent = getIntent();
	        setContentView(R.layout.dialog);
	        activateFlag=Integer.parseInt(receive_intent.getStringExtra("activateFlag"));
	        tvflag = (TextView)findViewById(R.id.flag);
	        tvshow = (TextView)findViewById(R.id.info);
	        btnok = (Button)findViewById(R.id.okBtn);
	          flag = receive_intent.getStringExtra("flag");
	         info = receive_intent.getStringExtra("info");
	        tvflag.setText(flag);
	        tvshow.setText(info);
	       if(activateFlag==2)
	        {
	        	setContentView(R.layout.dialogbig);
	        	tvflag1 = (TextView)findViewById(R.id.flag1);
		        tvshow1 = (TextView)findViewById(R.id.info1);
		       btnok1 = (Button)findViewById(R.id.okBtn1);
		       btnok2 = (Button)findViewById(R.id.okBtn2); 
		         flag1 = receive_intent.getStringExtra("flag");
		         info1 = receive_intent.getStringExtra("info");
		        tvflag1.setText(flag1);
		        tvshow1.setText(info1);
		        btnok1.setOnClickListener(new BtnOkCL());
		        btnok2.setOnClickListener(new BtnOkCL());
	        }
	        btnok.setOnClickListener(new BtnOkCL());
	        
	    }
	    
	    class BtnOkCL implements OnClickListener{
	    	
			public void onClick(View v) {
				
				Intent intent = new Intent();
				if(activateFlag==2){
					intent.setClass(ActivateCardDialog.this, CreditView.class);
				}else{
					intent.setClass(ActivateCardDialog.this, ActivateCard.class);
				}
	    		ActivateCardDialog.this.startActivity(intent);
			}
	    }
}

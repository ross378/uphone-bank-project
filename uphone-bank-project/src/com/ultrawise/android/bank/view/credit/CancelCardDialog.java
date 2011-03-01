package com.ultrawise.android.bank.view.credit;

import com.ultrawise.android.bank.view.transfer.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CancelCardDialog extends Activity {
	private int cancelFlag=0;
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.canclecarddialog);
	        
	        TextView tvflag = (TextView)findViewById(R.id.flagdia);
	    	TextView tvshow = (TextView)findViewById(R.id.infodia);
	    	Button btnok = (Button)findViewById(R.id.okBtndia);
	    	
	        Intent receive_intent = getIntent();
	        String flag = receive_intent.getStringExtra("flag");
	        String info = receive_intent.getStringExtra("info");
	        tvflag.setText(flag);
	        tvshow.setText(info);
	        cancelFlag=Integer.parseInt(receive_intent.getStringExtra("cancelFlag"));
	        
	        btnok.setOnClickListener(new BtnOkCL());
	    }
	    
	    class BtnOkCL implements OnClickListener{
			public void onClick(View v) {
				System.out.print(cancelFlag);
				Intent intent = new Intent();
				if(cancelFlag==2){
					intent.setClass(CancelCardDialog.this, CreditView.class);
				}else if(cancelFlag==1){
					intent.setClass(CancelCardDialog.this, CancelTheCard.class);
				}else if(cancelFlag==4){
					intent.setClass(CancelCardDialog.this, SelfPay.class);
				}
				else if(cancelFlag==3)
				{
					intent.setClass(CancelCardDialog.this, SelfPayOperation.class);
				}
	    		CancelCardDialog.this.startActivity(intent);
			}
	    }
}

package com.ultrawise.android.bank.view.credit;

import com.ultrawise.android.bank.view.transfer.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class CancelCardDialog extends Activity {
	private int cancelFlag=0;
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.dialog);
	        
	        TextView tvflag = (TextView)findViewById(R.id.flag);
	    	TextView tvshow = (TextView)findViewById(R.id.info);
	    	Button btnok = (Button)findViewById(R.id.okBtn);
	    	
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
				
				Intent intent = new Intent();
				if(cancelFlag==2){
					intent.setClass(CancelCardDialog.this, CancelCardQR.class);
				}else{
					intent.setClass(CancelCardDialog.this, CancelTheCard.class);
				}
	    		CancelCardDialog.this.startActivity(intent);
			}
	    }
}

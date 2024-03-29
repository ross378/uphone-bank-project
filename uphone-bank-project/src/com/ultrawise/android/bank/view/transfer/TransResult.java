package com.ultrawise.android.bank.view.transfer;

import com.ultrawise.android.bank.view.transfer.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import android.view.View.OnClickListener;

public class TransResult extends Activity {
    /** Called when the activity is first created. */
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
        
        btnok.setOnClickListener(new BtnOkCL());
    }
    
    class BtnOkCL implements OnClickListener{
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent transinfo_intent = new Intent();
    		transinfo_intent.setClass(TransResult.this, TransferMain.class);
    		startActivity(transinfo_intent);
		}
    }
}
package com.ultrawise.android.bank.view.credit;


import com.ultrawise.android.bank.view.transfer.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class SelfPayOperation extends Activity {
	private Button selfPayOperationButton=null;
	private EditText selfPayBalEditText=null;
	private TextView selfPayOperationCreditNoText=null;
	private TextView selfPayBalTextValue=null;//本期应还款额
	private TextView selfPayOperationPayActText=null;
	
	private String creditNo=null;
	private String selfPayActNo=null;
	private String selfPayBal=null;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selfpayoperation);
        Intent intent=this.getIntent();
        creditNo=intent.getStringExtra("creditNo");
        selfPayActNo=this.getIntent().getStringExtra("selfPayActNo");
        selfPayBal=this.getIntent().getStringExtra("selfPayBal");
        selfPayOperationButton=(Button)findViewById(R.id.selfPayOperationButton);
        selfPayOperationButton.setOnClickListener(new SelfPayOperationButtonListener());
        
        selfPayOperationCreditNoText=(TextView)findViewById(R.id.selfPayOperationCreditNoText);
        selfPayOperationCreditNoText.setText(creditNo);
        selfPayBalEditText=(EditText)findViewById(R.id.selfPayBalEdit);
        
        
        selfPayBalTextValue=(TextView)findViewById(R.id.selfPayBalTextValue);
        selfPayBalTextValue.setText(selfPayBal);
        
        selfPayOperationPayActText=(TextView)findViewById(R.id.selfPayOperationPayActText);
        selfPayOperationPayActText.setText(selfPayActNo);
	}
	class SelfPayOperationButtonListener implements OnClickListener{

		public void onClick(View arg0) {
			Intent intent=new Intent();
			String selfPayBal=selfPayBalEditText.getText().toString();
			intent.putExtra("selfPayBal", selfPayBal);
			intent.putExtra("creditNo", creditNo);
			intent.putExtra("selfPayActNo", selfPayActNo);
			intent.setClass(SelfPayOperation.this,SelfPayOperationDetail.class);
			SelfPayOperation.this.startActivity(intent);
			
		}
		
	}
}

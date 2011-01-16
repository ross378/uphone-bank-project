package com.ultrawise.android.bank.view.credit;

import com.ultrawise.android.bank.view.transfer.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class CreditDetail extends Activity {
	private RadioGroup rg=null;
	private RadioButton rb=null;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.creditdetail);
        rg=(RadioGroup)findViewById(R.id.creditNumber);
        
        rg.setOnCheckedChangeListener (new RadioGroup.OnCheckedChangeListener(){

			public void onCheckedChanged(RadioGroup rg1, int checkedId) {
				rb=(RadioButton)findViewById(checkedId);
				String creditNo=rb.getText().toString();
				Intent intent=new Intent();
				intent.putExtra("creditNo", creditNo);
				intent.setClass(CreditDetail.this,CreditInfo_List.class);
				CreditDetail.this.startActivity(intent);
			}
        });
	}
}

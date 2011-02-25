package com.ultrawise.android.bank.view;

import com.ultrawise.android.bank.view.transfer.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
/*
 *@author weijuan
 *@date 2011-1-18
 *贷款利率Activity-LoanRates
 * 
 */
public class LoanRates extends Activity {
	//界面级别显示文本试图
	TextView firstText = null;
	TextView secondText = null;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loanrates);
        firstText = (TextView)findViewById(R.id.class_first);
        firstText.setText(R.string.loan_rates);
        firstText.setVisibility(View.VISIBLE);
       
	}
}

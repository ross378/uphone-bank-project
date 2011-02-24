package com.ultrawise.android.bank.view;

import com.ultrawise.android.bank.view.transfer.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
/*
 * @author weijuan
 * @date 2011-1-18
 * 定期存款利率Activity-DingQiDepositeRatesDetail
 */
public class DingQiDepositeRatesDetail extends Activity {
	//界面级别显示文本试图
	TextView firstText = null;
	TextView secondText = null;
	TextView thirdText = null;
	TextView fourText = null;
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dingqidepositeratesdetail);
        firstText = (TextView)findViewById(R.id.class_first);
        firstText.setText(R.string.helper);
        firstText.setVisibility(View.VISIBLE);
        secondText = (TextView)findViewById(R.id.class_second);
        secondText.setText(R.string.deposite_rates);
        secondText.setVisibility(View.VISIBLE);
        thirdText = (TextView)findViewById(R.id.class_third);
        thirdText.setText( R.string.self_company_deposite);
        thirdText.setVisibility(View.VISIBLE);
        fourText = (TextView)findViewById(R.id.class_four);
        fourText.setText( R.string.dingqi_deposite);
        fourText.setVisibility(View.VISIBLE);
	}
}

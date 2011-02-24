package com.ultrawise.android.bank.view;

import com.ultrawise.android.bank.view.transfer.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
/*
 * @author weijjuan
 * @date 2011-1-18
 * 其他存款利率Activity-OtherDepositeRates
 */
public class OtherDepositeRates extends Activity {
	//界面级别显示文本试图
	TextView firstText = null;
	TextView secondText = null;
	TextView thirdText = null;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.otherdepositerates);
        firstText = (TextView)findViewById(R.id.class_first);
        firstText.setText(R.string.information);
        firstText.setVisibility(View.VISIBLE);
        secondText = (TextView)findViewById(R.id.class_second);
        secondText.setText(R.string.deposite_rates);
        secondText.setVisibility(View.VISIBLE);
        thirdText = (TextView)findViewById(R.id.class_second);
        thirdText.setText(R.string.other_deposite_rates);
        thirdText.setVisibility(View.VISIBLE);
	}
    
}

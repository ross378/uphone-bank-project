package com.ultrawise.android.bank.view;

import java.util.ArrayList;
import java.util.List;

import com.ultrawise.android.bank.view.DepositeRates.BackImageViewListener;
import com.ultrawise.android.bank.view.DepositeRates.FirstTextViewListener;
import com.ultrawise.android.bank.view.DepositeRates.PhoneBankImageViewListener;
import com.ultrawise.android.bank.view.transfer.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
/*
 *@author weijuan
 *@date 2011-1-18
 *贷款利率Activity-LoanRates
 * 
 */
public class LoanRates extends Activity {
	
	private ImageView back = null;
	 private ImageView phoneBank = null;
	 private ImageView helper = null;
	//界面级别显示文本试图
	TextView firstText = null;
	TextView secondText = null;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.loanrates);
        firstText = (TextView)findViewById(R.id.class_first);
        firstText.setText(R.string.helper);
        firstText.setVisibility(View.VISIBLE);
        firstText.setOnClickListener(new FirstTextViewListener());
        secondText = (TextView)findViewById(R.id.class_second);
        secondText.setText(R.string.loan_rates);
        secondText.setVisibility(View.VISIBLE);
        back = (ImageView)findViewById(R.id.returnToPre);
        back.setOnClickListener(new BackImageViewListener());
        phoneBank = (ImageView)findViewById(R.id.buttonBank);
        phoneBank.setOnClickListener(new PhoneBankImageViewListener());
        helper = (ImageView)findViewById(R.id.buttonHelper);
        helper.setOnClickListener(new BackImageViewListener());
       
	}
	
	class BackImageViewListener implements OnClickListener{
		 
		 public void onClick(View args0){
			 LoanRates.this.finish();
		 }
	 }
	 
	class PhoneBankImageViewListener implements OnClickListener{
		 
		 public void onClick(View args0){
			 LoanRates.this.finish();
			 Intent intent = new Intent();
			 intent.setClass(LoanRates.this, ABankMain.class);
			 LoanRates.this.startActivity(intent);
		 }
	 }
	 
	 class HelperImageViewListener implements OnClickListener{
		 
		 public void onClick(View args0){
			 LoanRates.this.finish();
			 Intent intent = new Intent();
			 intent.setClass(LoanRates.this, FinancialConsultation.class);
			 LoanRates.this.startActivity(intent);
		 }
	 }
	 
	 class FirstTextViewListener implements OnClickListener{
		 
			public void onClick(View args0){
				LoanRates.this.finish();
				 Intent intent = new Intent();
				 intent.setClass(LoanRates.this, FinancialConsultation.class);
				 LoanRates.this.startActivity(intent);
			 }
	 }
	 
	 private void showDataToView(){
		 
		 List<String> data = new ArrayList<String>();
		 
		 List<TextView> ratesText = new ArrayList<TextView>();
		 
		 ratesText.add((TextView)findViewById(R.id.loan_one_1));
		 ratesText.add((TextView)findViewById(R.id.loan_one_2));
		 ratesText.add((TextView)findViewById(R.id.loan_two_1));
		 ratesText.add((TextView)findViewById(R.id.loan_two_2));
		 
		 for(int i = 0; i < ratesText.size(); i ++)
		 {
			ratesText.get(i).setText(data.get(i));
		 }
	 }
}

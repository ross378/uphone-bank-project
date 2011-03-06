package com.ultrawise.android.bank.view;

import java.util.ArrayList;
import java.util.List;


import java.util.HashMap;

import com.ultrawise.android.bank.view.credit.SelfPay;
import com.ultrawise.android.bank.view.credit.SelfPayAct;
import com.ultrawise.android.bank.view.transfer.R;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.content.pm.FeatureInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
/*
 * @author weijuan 
 * @date 2011-1-18
 * 存款利率Activity DepositeRates
 */
public class DepositeRates extends Activity {
	
	 private ImageView back = null;
	 private ImageView phoneBank = null;
	 private ImageView helper = null;
	 TextView firstText = null;
	 TextView secondText = null;
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	       
	        setContentView(R.layout.depositereates);
	       
	        firstText = (TextView)findViewById(R.id.class_first);
	        firstText.setText(R.string.helper);
	        firstText.setVisibility(View.VISIBLE);
	        firstText.setOnClickListener(new FirstTextViewListener());
	        secondText = (TextView)findViewById(R.id.class_second);
	        secondText.setText(R.string.deposite_rates);
	        secondText.setVisibility(View.VISIBLE);
	        back = (ImageView)findViewById(R.id.returnToPre);
	        back.setOnClickListener(new BackImageViewListener());
	        phoneBank = (ImageView)findViewById(R.id.buttonBank);
	        phoneBank.setOnClickListener(new PhoneBankImageViewListener());
	        helper = (ImageView)findViewById(R.id.buttonBank);
	        helper.setOnClickListener(new BackImageViewListener());
	    }
	 
	 class BackImageViewListener implements OnClickListener{
		 
		 public void onClick(View args0){
			 DepositeRates.this.finish();
		 }
	 }
	 
	 class PhoneBankImageViewListener implements OnClickListener{
		 
		 public void onClick(View args0){
			 DepositeRates.this.finish();
			 Intent intent = new Intent();
			 intent.setClass(DepositeRates.this, ABankMain.class);
			 DepositeRates.this.startActivity(intent);
		 }
	 }
	 
	 class HelperImageViewListener implements OnClickListener{
		 
		 public void onClick(View args0){
			 DepositeRates.this.finish();
			 Intent intent = new Intent();
			 intent.setClass(DepositeRates.this, FinancialConsultation.class);
			 DepositeRates.this.startActivity(intent);
		 }
	 }
	 
	 class FirstTextViewListener implements OnClickListener{
		 
		 public void onClick(View args0)
		 {
			 DepositeRates.this.finish();
			 Intent intent = new Intent();
			 intent.setClass(DepositeRates.this, FinancialConsultation.class);
			 DepositeRates.this.startActivity(intent);
		 }
	 }
}

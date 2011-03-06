package com.ultrawise.android.bank.view;

import java.util.ArrayList;
import java.util.List;

import java.util.HashMap;

import com.ultrawise.android.bank.view.ABankMain.CreditButtonListener;
import com.ultrawise.android.bank.view.ABankMain.HelperImageViewListener;
import com.ultrawise.android.bank.view.ABankMain.PhoneBankImageViewListener;
import com.ultrawise.android.bank.view.credit.CreditView;
import com.ultrawise.android.bank.view.transfer.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;

/*
 * @author weijuan
 * @date 2011-1-18
 * 手机银行首页-金融助手
 */
public class FinancialConsultation extends Activity {
	//手机银行按钮
	private ImageButton aBankButton=null;
	//存款利率按钮
	private ImageButton depositRatesButton=null;
	//贷款利率按钮
	private ImageButton loanRateButton=null;
	//外汇汇率按钮
	private ImageButton exchangeRateButton=null;
	//返回按钮
	private ImageView backButton = null;
	//手机银行底部按钮
	private ImageView aBankBottomButton = null;
	//金融助手
	private ImageView financialHelButton = null;
	
	private ImageView phoneBank = null;
	 private ImageView helper = null;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        //设置布局
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.financialconsultation);
        //获得手机银行按钮，并设置其鼠标单击事件监听器
        aBankButton=(ImageButton)findViewById(R.id.aBank);
        aBankButton.setOnClickListener(new ABankButtonListener());
        //获得存款利率按钮，并设置其鼠标单击事件监听器
        depositRatesButton=(ImageButton)findViewById(R.id.depositRates);
        depositRatesButton.setOnClickListener(new DepositeRatesButtonListener());
        //获得贷款利率按钮，并设置其鼠标单击事件监听器
        loanRateButton=(ImageButton)findViewById(R.id.loanRate);
        loanRateButton.setOnClickListener(new LoanRatekButtonListener());
        //获得外汇汇率按钮，并设置其鼠标单击事件监听器
        exchangeRateButton=(ImageButton)findViewById(R.id.exchangeRate);
        exchangeRateButton.setOnClickListener(new ExchangeRateButtonListener());       
        phoneBank = (ImageView)findViewById(R.id.buttonBank);       
        phoneBank.setOnClickListener(new PhoneBankImageViewListener());
        helper = (ImageView)findViewById(R.id.buttonHelper);
        helper.setOnClickListener(new HelperImageViewListener());
	}
	/*
	 * 手机银行按钮响应事件
	 * 跳转到用户登录Activity-UserLogin
	 */
	class ABankButtonListener implements OnClickListener{

		public void onClick(View arg0) {
			
			Intent intent=new Intent();
			List<String> result = WebTools.connectHttp(0, null);
			Log.d("xiao", result.get(0));
			intent.putStringArrayListExtra("key", (ArrayList<String>)result);
			intent.setClass(FinancialConsultation.this,UserLogin.class);
			FinancialConsultation.this.startActivity(intent);
			
		}
		
	}
	/*
	 * 存款利率按钮响应事件
	 * 跳转到存款利率Activity-DepositeRates
	 */
	class DepositeRatesButtonListener implements OnClickListener{

		public void onClick(View arg0) {
			List<String> result = WebTools.connectHttp(1, null);
			Intent intent=new Intent();
			intent.putStringArrayListExtra("key", (ArrayList<String>)result);
			intent.setClass(FinancialConsultation.this,DepositeRates.class);
			FinancialConsultation.this.startActivity(intent);
			
		}
		
	}
	/*
	 * 贷款利率按钮响应事件
	 * 跳转到贷款利率Activity-LoanRates
	 */
	class LoanRatekButtonListener implements OnClickListener{

		public void onClick(View arg0) {
			List<String> result = WebTools.connectHttp(1, null);
			Intent intent=new Intent();
			intent.putStringArrayListExtra("key", (ArrayList<String>)result);
			intent.setClass(FinancialConsultation.this,LoanRates.class);
			FinancialConsultation.this.startActivity(intent);
			
		}
		
	}
	/*
	 * 外汇汇率按钮响应
	 * 跳转到外汇汇率Activity-ExchangeRates
	 */
	class ExchangeRateButtonListener implements OnClickListener{

		public void onClick(View arg0) {
			Intent intent=new Intent();
			intent.setClass(FinancialConsultation.this,ExchangeRates.class);
			FinancialConsultation.this.startActivity(intent);
			
		}
		
	}
	
	
	
	/*
	 * 手机银行登录底层按钮
	 * 
	 */
	class ABankBottomButtonListener implements OnClickListener{
		
		public void onClick(View args0){
			//暂时留着
			Log.d("debug", "aBank");
		}
	}
	
	/*
	 * 金融助手按钮响应
	 * 跳转到金融助手Activity，也就是当前Activity
	 */
	
	class FinancialHelButtonListener implements OnClickListener{
		
		public void onClick(View args0){
			Log.d("debug", "financial");
		}
	}
	
	class PhoneBankImageViewListener implements OnClickListener{
		 
		 public void onClick(View args0){
			 Intent intent = new Intent();
			 intent.setClass(FinancialConsultation.this, UserLogin.class);
			 FinancialConsultation.this.startActivity(intent);
		 }
	 }
	 
	 class HelperImageViewListener implements OnClickListener{
		 
		 public void onClick(View args0){
			 
		 }
	 }
}

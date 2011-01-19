package com.ultrawise.android.bank.view;

import java.util.ArrayList;
import java.util.HashMap;

import com.ultrawise.android.bank.view.ABankMain.CreditButtonListener;
import com.ultrawise.android.bank.view.credit.CreditView;
import com.ultrawise.android.bank.view.transfer.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

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
	@Override
    public void onCreate(Bundle savedInstanceState) {
        //设置布局
		super.onCreate(savedInstanceState);
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
        
	}
	/*
	 * 手机银行按钮响应事件
	 * 跳转到用户登录Activity-UserLogin
	 */
	class ABankButtonListener implements OnClickListener{

		public void onClick(View arg0) {
			Intent intent=new Intent();
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
			Intent intent=new Intent();
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
			Intent intent=new Intent();
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
}

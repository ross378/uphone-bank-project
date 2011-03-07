package com.ultrawise.android.bank.view;

import com.ultrawise.android.bank.view.credit.ActivateCardDialog;
import com.ultrawise.android.bank.view.credit.CreditView;
import com.ultrawise.android.bank.view.transfer.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
/**
 * 
 * @author weijuan
 * @date 2010-1-18
 * 外汇汇率对话框布局Activity-ExchangeRatesDialog
 *
 */
public class ExchangeRatesDialog extends Activity {
	//货币面值是否有值的标志
	private int currencyFlag=0;
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        requestWindowFeature(Window.FEATURE_NO_TITLE);
	        setContentView(R.layout.dialog);
	       
	        //获得外汇汇率Activity传入的信息值并显示
	        TextView tvflag = (TextView)findViewById(R.id.flag);
	    	TextView tvshow = (TextView)findViewById(R.id.info);
	    	Button btnok = (Button)findViewById(R.id.okBtn);
	    	
	        Intent receive_intent = getIntent();
	        String flag = receive_intent.getStringExtra("flag");
	        String info = receive_intent.getStringExtra("info");
	        tvflag.setText(flag);
	        tvshow.setText(info);
	        //获得货币面值是否有值标志
	        currencyFlag=Integer.parseInt(receive_intent.getStringExtra("currencyFlag"));
	        btnok.setOnClickListener(new BtnOkCL());
	        
	        
	 }
	 class BtnOkCL implements OnClickListener{
			public void onClick(View v) {
				//System.out.println(currencyFlag);
				Intent intent = new Intent();
				//货币面值不为孔
				if(currencyFlag==2){
					intent.setClass(ExchangeRatesDialog.this, FinancialConsultation.class);
				//货币面值为空
				}else if(currencyFlag==1){
					intent.setClass(ExchangeRatesDialog.this, ExchangeRates.class);
				}
	    		ExchangeRatesDialog.this.startActivity(intent);
			}
	    }
}

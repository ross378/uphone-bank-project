package com.ultrawise.android.bank.view;

import java.util.ArrayList;
import java.util.List;

import com.ultrawise.android.bank.view.DepositeRates.BackImageViewListener;
import com.ultrawise.android.bank.view.DepositeRates.FirstTextViewListener;
import com.ultrawise.android.bank.view.DepositeRates.PhoneBankImageViewListener;
import com.ultrawise.android.bank.view.credit.ActivateCard;
import com.ultrawise.android.bank.view.credit.ActivateCardDialog;
import com.ultrawise.android.bank.view.transfer.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
/**
 * 
 * @author weijuan 
 * @date 2011-1-18
 * 外汇汇率Activity-ExchangeRates
 *
 */
public class ExchangeRates extends Activity {
	//货币面值输入框
	private EditText currencyInputEdit=null;
	//原始货币单位选择下拉框
	private Spinner sourceCurrencySpinner=null;
	//目标货币单位选择下拉框
	private Spinner delatinCurrencySpinner=null;
	
	private Button currencyConCulateButton=null;
	
	private ImageView back = null;
	private ImageView phoneBank = null;
	 private ImageView helper = null;
	//界面级别显示文本试图
	TextView firstText = null;
	TextView secondText = null;
	
	private List<String> params = new ArrayList<String>();
	private List<String> exchangeRates = new ArrayList<String>();
	@Override
    public void onCreate(Bundle savedInstanceState) {
        //设置布局
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.exchangerates);
        firstText = (TextView)findViewById(R.id.class_first);
        firstText.setText(R.string.helper);
        firstText.setVisibility(View.VISIBLE);
        firstText.setOnClickListener(new FirstTextViewListener());
        secondText = (TextView)findViewById(R.id.class_second);
        secondText.setText(R.string.exchange_rates);
        secondText.setVisibility(View.VISIBLE);
        back = (ImageView)findViewById(R.id.returnToPre);
        back.setOnClickListener(new BackImageViewListener());
        phoneBank = (ImageView)findViewById(R.id.buttonBank);
        phoneBank.setOnClickListener(new PhoneBankImageViewListener());
        helper = (ImageView)findViewById(R.id.buttonHelper);
        helper.setOnClickListener(new BackImageViewListener());
        //获得货币面值输入框对象
        currencyInputEdit=(EditText)findViewById(R.id.currencyInputEdit);
        //获得原始货币单位下拉框对象
        sourceCurrencySpinner=(Spinner)findViewById(R.id.sourceCurrencySpinner);
      //获得目标货币单位下拉框对象
        delatinCurrencySpinner=(Spinner)findViewById(R.id.delatinCurrencySpinner);
        //获得开始计算按钮对象，并设置按钮的鼠标单击响应事件
        currencyConCulateButton=(Button)findViewById(R.id.currencyConCulate);
        currencyConCulateButton.setOnClickListener(new CurrencyConCulateButtonListener());
        
        //设置原始货币单位下拉框的选项值
        final String[] arrs=new String[]{"美元(US)","人民币(CHI)","欧元()","日元(JPA)"};
         
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrs);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);   

        sourceCurrencySpinner.setAdapter(adapter);  

        sourceCurrencySpinner.setSelection(0,true);

        sourceCurrencySpinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){

                public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3){

                    arg0.setVisibility(View.VISIBLE);
                    if(arg2 == 0)
                    {
                    	params.add("dollar");
                    }else if(arg2 == 1)
                    {
                    	params.add("rmb");
                    }else if(arg2 == 2)
                    {
                    	params.add("ouyuan");
                    }else 
                    {
                    	params.add("yen");
                    }
                    Log.d("out", params.get(0));
                }
                public void onNothingSelected(AdapterView<?> arg0){
                }
            });
        
      //设置目标货币单位下拉框的选项值
         
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrs);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);   

        delatinCurrencySpinner.setAdapter(adapter1);  

        delatinCurrencySpinner.setSelection(0,true);

        delatinCurrencySpinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){

                public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3){

                    arg0.setVisibility(View.VISIBLE);
                    if(arg2 == 0)
                    {
                    	params.add("dollar");
                    }else if(arg2 == 1)
                    {
                    	params.add("rmb");
                    }else if(arg2 == 2)
                    {
                    	params.add("ouyuan");
                    }else 
                    {
                    	params.add("yen");
                    }
                   
                    Log.d("out", params.get(1));
                }
                public void onNothingSelected(AdapterView<?> arg0){
                }
            });
	}
	/**
	 * 计算货币外汇按钮响应
	 * 跳转到显示兑换金额结果的对话框Activity
	 * @author Administrator
	 *
	 */
	class CurrencyConCulateButtonListener implements OnClickListener{

		public void onClick(View arg0) {
			String flag="外汇汇率";
			String info=null;
			int currencyFlag=0;
			Intent intent=new Intent();
			String currencyBal=currencyInputEdit.getText().toString();
			System.out.println(currencyBal);
			
			String sourceCurrencyDW=sourceCurrencySpinner.getSelectedItem().toString();
			System.out.println(sourceCurrencyDW);
			
			String delatinCurrencyDW=delatinCurrencySpinner.getSelectedItem().toString();
			System.out.println(delatinCurrencyDW);
			//判断货币面值为空
			Log.d("out", "one");
			if(currencyBal==null ||currencyBal.trim().length()==0){
				currencyFlag=1;
				info="货币面值不能为空";
			}else{
				Log.d("out", "one-" + params.toString());
				exchangeRates = WebTools.connectHttp(3, params);
				double value = Double.valueOf(currencyInputEdit.getText().toString());
				double oneExchange = Double.valueOf(exchangeRates.get(0));
				double twoExchange = Double.valueOf(exchangeRates.get(1));
				
				currencyFlag=2;
				//计算兑换结果
				String jieguo= String.valueOf(value * (twoExchange / oneExchange));
				flag=currencyBal+" "+sourceCurrencyDW+"兑换\n";
				info=jieguo+" "+delatinCurrencyDW+"\n";
			}
			
			
			intent.putExtra("flag",flag);
			intent.putExtra("info", info);
			intent.putExtra("currencyFlag", currencyFlag+"");
			intent.setClass(ExchangeRates.this,ExchangeRatesDialog.class);
			ExchangeRates.this.startActivity(intent);
			
		}
		
	}
	class BackImageViewListener implements OnClickListener{
		 
		 public void onClick(View args0){
			 ExchangeRates.this.finish();
		 }
	}
	
	class PhoneBankImageViewListener implements OnClickListener{
		 
		 public void onClick(View args0){
			 ExchangeRates.this.finish();
			 Intent intent = new Intent();
			 intent.setClass(ExchangeRates.this, ABankMain.class);
			 ExchangeRates.this.startActivity(intent);
		 }
	 }
	 
	 class HelperImageViewListener implements OnClickListener{
		 
		 public void onClick(View args0){
			 ExchangeRates.this.finish();
			 Intent intent = new Intent();
			 intent.setClass(ExchangeRates.this, FinancialConsultation.class);
			 ExchangeRates.this.startActivity(intent);
		 }
	 }
	 
class FirstTextViewListener implements OnClickListener{
		 
	public void onClick(View args0){
		 ExchangeRates.this.finish();
		 Intent intent = new Intent();
		 intent.setClass(ExchangeRates.this, FinancialConsultation.class);
		 ExchangeRates.this.startActivity(intent);
	 }
	 }
}

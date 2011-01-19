package com.ultrawise.android.bank.view.credit;

import com.ultrawise.android.bank.view.transfer.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
/**
 * 
 * @author weijuan
 * @date 2011-1-18
 * 信用卡绑定Activity-CreditCardBind
 *
 */
public class CreditCardBind extends Activity {
	//绑定按钮
	private Button creditCardBindButton=null;
	//绑定手机号输入框
	private EditText mobileNoEditText=null;
	//卡号选择下拉框
	private Spinner creditNoSpinner=null;
	//卡号
	private String creditNo=null;
	//绑定手机号
	private String mobileNo=null;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.creditcardbind);
        
        creditCardBindButton=(Button)findViewById(R.id.creditCardBindButton);
        creditCardBindButton.setOnClickListener(new ActivateCardButtonListener());
        
        mobileNoEditText=(EditText)findViewById(R.id.mobileNoEdit);
        
        
        creditNoSpinner=(Spinner)findViewById(R.id.creditNoSpinner);
       
        
        final String[] arrs=new String[10];
        for(int i=0;i<10;i++){
        	arrs[i]="11223344556"+i;
        }
        creditNoSpinner= (Spinner)findViewById(R.id.creditNoSpinner); 
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrs);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);   

        creditNoSpinner.setAdapter(adapter);  

        creditNoSpinner.setSelection(0,true);

        creditNoSpinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){

                public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3){


                    arg0.setVisibility(View.VISIBLE);

                }
                public void onNothingSelected(AdapterView<?> arg0){
                }

            });
        

	}
	class ActivateCardButtonListener implements OnClickListener{

		public void onClick(View arg0) {
			String flag=null;
			String info=null;
			int mobileNoFlag=0;
			//获得选择的卡号和绑定手机号信息
			creditNo=creditNoSpinner.getSelectedItem().toString();
	        mobileNo=mobileNoEditText.getText().toString();
	        
	        if(creditNo==null || creditNo.trim().length()==0 || mobileNo==null || mobileNo.trim().length()==0){
	        	flag="对不起，绑定失败";
	        	info="卡号，手机号不能为空";
	        	mobileNoFlag=1;
	        }else{
	        	mobileNoFlag=2;
	        	flag="恭喜您，绑定成功！";
	        	info="您已经将您的卡号为"+creditNo+"的信用卡绑定到手机号为"+mobileNo+"的手机了！\n";
	        }
	        Intent intent=new Intent();
			intent.putExtra("flag", flag);
			intent.putExtra("info", info);
			intent.putExtra("mobileNoFlag", mobileNoFlag+"");
			intent.setClass(CreditCardBind.this,CreditCardBindDialog.class);
			CreditCardBind.this.startActivity(intent);
			
		}
		
	}
}

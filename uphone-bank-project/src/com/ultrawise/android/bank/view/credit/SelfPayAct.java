package com.ultrawise.android.bank.view.credit;

import com.ultrawise.android.bank.view.ABankMain;
import com.ultrawise.android.bank.view.account_query.AccountQuery;
import com.ultrawise.android.bank.view.account_query.AccountQueryType;
import com.ultrawise.android.bank.view.transfer.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;

public class SelfPayAct extends Activity {
	private Spinner spinner = null;
	private ArrayAdapter<String> adapter = null;
	private Spinner pakitSpinner = null;
	private Spinner pakitSpinner1 = null;
	private ArrayAdapter<String> adapter2 = null;
	private TextView tvClassFirst = null;
	private ImageView btnReturn = null;
	private Intent intent = null;
	private Button btnContinue = null;
	TextView  tvCredit;
	ImageView btnCoustom;
	ImageView btnMain;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.selfpayact);
		  pakitSpinner=(Spinner)findViewById(R.id.spinnerAccTyp1);
		 //初始化证件类型控件值
	       final String[] arrs=new String[]{"农业银行","建设银行","工商银行"};
	        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,arrs);

	        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);   

	        pakitSpinner.setAdapter(adapter);  
	        pakitSpinner.setSelection(1,true);

	         pakitSpinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){

	                public void onItemSelected(AdapterView<?> parent, View arg1, int position, long arg3){


	                	//pakitPostion=parent.getSelectedItemPosition();
	                	parent.setVisibility(View.VISIBLE);

	                }
	                public void onNothingSelected(AdapterView<?> parent){
	                		
	                }

	            });

	         pakitSpinner1=(Spinner)findViewById(R.id.spinnerAcc1);
	         //初始化号码控件值
		       final String[] arrs1=new String[]{"23424343434","45646454545","6564534534"};
		        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,arrs1);

		        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);   

		        pakitSpinner1.setAdapter(adapter1);  
		        pakitSpinner1.setSelection(1,true);

		         pakitSpinner1.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){

		                public void onItemSelected(AdapterView<?> parent, View arg1, int position, long arg3){


		                	//pakitPostion=parent.getSelectedItemPosition();
		                	parent.setVisibility(View.VISIBLE);

		                }
		                public void onNothingSelected(AdapterView<?> parent){
		                		
		                }

		            });
		
		  ImageView iv_now = (ImageView)this.findViewById(R.id.btnCoustom);
	        iv_now.setVisibility(View.GONE);
		    	intent = new Intent();
		         tvCredit= (TextView)this.findViewById(R.id.class_first);
		        tvCredit.setText("首页>信用卡>信用卡还款 ");
		        tvCredit.setTextSize(13);
		        tvCredit.setOnClickListener(new OnClickListener() {
					public void onClick(View v) {
						 intent.setClass(SelfPayAct.this, ABankMain.class);
						 SelfPayAct.this.startActivity(intent);
					}
				});
		        tvCredit.setVisibility(View.VISIBLE);

		        //设置底部按钮
				btnCoustom = (ImageView) this.findViewById(R.id.btnMain);
				btnCoustom.setOnClickListener(new OnClickListener() {
					public void onClick(View v) {
						// TODO Auto-generated method stub
						intent.setClass(SelfPayAct.this, ABankMain.class);
						SelfPayAct.this.startActivity(intent);
					}
				});
		// 继续按钮跳转
		btnContinue = (Button) findViewById(R.id.btnContinue1);
		btnContinue.setTextColor(Color.BLACK);
		btnContinue.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(SelfPayAct.this, SelfPayOperation.class);
				SelfPayAct.this.startActivity(intent);
			}
		});
	}
}

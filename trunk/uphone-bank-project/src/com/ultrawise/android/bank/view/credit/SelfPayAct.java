package com.ultrawise.android.bank.view.credit;

import java.util.ArrayList;
import java.util.List;

import com.ultrawise.android.bank.consum_webservices.CreditClient;
import com.ultrawise.android.bank.view.ABankMain;
import com.ultrawise.android.bank.view.FinancialConsultation;
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
	private Spinner spinner2= null;
	private Spinner pakitSpinner1 = null;
	private ArrayAdapter<String> adapter2 = null;
	private TextView tvClassFirst = null;
	private ImageView btnReturn = null;
	private Intent intent = null;
	private Button btnContinue = null;
	TextView  tvCredit;
	ImageView btnCoustom;
	ImageView btnMain;
	String accountpyte;
	String accountNo;
	String no;
	private String SelectAcc="411";
	public String commit="412";
	 List<String> ll=new ArrayList<String>();
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.selfpayact);
		 Intent receive_intent = getIntent();
	        accountpyte = receive_intent.getStringExtra("accountpyte");
		 //初始化证件类型控件值
		  String[] str=accountpyte.split(":");
		  adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item);
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			for(int i=0;i<str.length;i++)
			{
				adapter.add(str[i]);
			}
			spinner = (Spinner) findViewById(R.id.spinnerAccTyp1);
			spinner.setAdapter(adapter);
			spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
				public void onItemSelected(AdapterView<?> parent, View view,
						int position, long id) {
					Spinner spinner = (Spinner) parent;
					Log.v("Test", "id = " + id + "("
							+ spinner.getSelectedItem().toString() + ")");
					ll.add(spinner.getSelectedItem().toString());
					//请求服务器
					List<String> accuss=CreditClient.connectHttp(SelectAcc, ll);
					ll.clear();
					String[] creditNo=accuss.get(0).split(":");
					adapter2 = new ArrayAdapter<String>(SelfPayAct.this,android.R.layout.simple_spinner_item);
					adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
					for(int i=0;i<creditNo.length;i++)
					{
						adapter2.add(creditNo[i]);
					}
					spinner2 = (Spinner) findViewById(R.id.spinnerAcc1);
					spinner2.setAdapter(adapter2);
					spinner2.setOnItemSelectedListener(new OnItemSelectedListener() {
							public void onItemSelected(AdapterView<?> parent, View view,int position, long id) {
									Spinner spinner = (Spinner) parent;
									System.out.println(spinner.getSelectedItem().toString());
						}
							public void onNothingSelected(AdapterView<?> arg0) {}});
				
				}
						    public void onNothingSelected(AdapterView<?> arg0) {}
			});
		  
		  
		
		  ImageView iv_now = (ImageView)this.findViewById(R.id.btnCoustom);
	        iv_now.setVisibility(View.GONE);
		    	intent = new Intent();
		         tvCredit= (TextView)this.findViewById(R.id.class_first);
		        tvCredit.setText("首页>信用卡>信用卡还款 ");
		        tvCredit.setTextSize(13);
		        tvCredit.setOnClickListener(new OnClickListener() {
					public void onClick(View v) {
						 intent.setClass(SelfPayAct.this, CreditView.class);
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
				btnMain = (ImageView) this.findViewById(R.id.btnHelper);
				btnMain.setOnClickListener(new OnClickListener() {

					public void onClick(View v) {
						// TODO Auto-generated method stub
						intent.setClass(SelfPayAct.this, FinancialConsultation.class);
						SelfPayAct.this.startActivity(intent);
					}
				});
		// 继续按钮跳转
		btnContinue = (Button) findViewById(R.id.btnContinue1);
		btnContinue.setTextColor(Color.BLACK);
		btnContinue.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				//请求服务器
				no=spinner2.getSelectedItem().toString();
				ll.clear();
				ll.add(no);
				List<String> accuss=CreditClient.connectHttp(commit, ll);
				Intent intent = new Intent();
				intent.putExtra("cardDetail", accuss.get(0));
				intent.setClass(SelfPayAct.this, SelfPayOperation.class);
				SelfPayAct.this.startActivity(intent);
			}
		});
	}
}

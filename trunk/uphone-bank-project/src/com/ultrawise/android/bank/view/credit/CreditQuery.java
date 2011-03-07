package com.ultrawise.android.bank.view.credit;
import java.util.ArrayList;
import java.util.List;

import com.ultrawise.android.bank.consum_webservices.CreditClient;
import com.ultrawise.android.bank.view.ABankMain;
import com.ultrawise.android.bank.view.FinancialConsultation;
import com.ultrawise.android.bank.view.credit.CreditView;
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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;

public class CreditQuery extends Activity {
	private String SelectAcc="411";
	public String commit="412";
	private Spinner spinner = null;
	private ArrayAdapter<String> adapter = null;
	private Spinner spinner2 = null;
	private ArrayAdapter<String> adapter2 = null;
	private TextView tvClassFirst = null;
	private TextView tvClassSecond = null;
	private ImageView btnReturn = null;
	private Button btnContinue = null;
	private ImageView btnCoustom =null;
	private ImageView btnMain =null;
 String accountpyte;
 String accountNo;
 List<String> ll=new ArrayList<String>();
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.account_querypan);
		 Intent receive_intent = getIntent();
	        accountpyte = receive_intent.getStringExtra("accountpyte");
		// 跳转到主界面
		tvClassFirst = (TextView) this.findViewById(R.id.class_first);
        tvClassFirst.setText("首页>信用卡>");
        tvClassFirst.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				 Intent intent = new Intent();
				 intent.setClass(CreditQuery.this, CreditView.class);
				 CreditQuery.this.startActivity(intent);
			}
		});
        tvClassFirst.setVisibility(View.VISIBLE);
        
     // 返回键设定
		btnReturn = (ImageView) this.findViewById(R.id.returnToPre);
		btnReturn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				onBackPressed();
				finish();
			}
		});	
		
		tvClassSecond = (TextView) this.findViewById(R.id.class_second);
		tvClassSecond.setText("账户查询");
		tvClassSecond.setVisibility(View.VISIBLE);
		
        //设置底部按钮
		btnCoustom = (ImageView) this.findViewById(R.id.btnMain);
		btnCoustom.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setClass(CreditQuery.this, ABankMain.class);
				CreditQuery.this.startActivity(intent);
				finish();
			}
		});
		//btnCoustom.setVisibility(View.VISIBLE);
		
	btnMain = (ImageView) this.findViewById(R.id.btnHelper);
		btnMain.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent intent=new Intent();
				intent.setClass(CreditQuery.this,  FinancialConsultation.class);
				CreditQuery.this.startActivity(intent);
				finish();
			}
		});
	

		// 确定按钮跳转
		btnContinue = (Button) findViewById(R.id.btnContinuepan);
		btnContinue.setTextColor(Color.BLACK);
		btnContinue.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(CreditQuery.this,AccountQueryBalancepan.class);
				CreditQuery.this.startActivity(intent);
			}
		});
        String[] card=accountpyte.split(":");
		adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		for(int i=0;i<card.length;i++)
		{
			adapter.add(card[i]);
		}
		spinner = (Spinner) findViewById(R.id.spinnerAccTyppan);
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
				adapter2 = new ArrayAdapter<String>(CreditQuery.this,android.R.layout.simple_spinner_item);
				adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				for(int i=0;i<creditNo.length;i++)
				{
					adapter2.add(creditNo[i]);
				}
				spinner2 = (Spinner) findViewById(R.id.spinnerAccpan);
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
	}

}





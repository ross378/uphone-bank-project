package com.ultrawise.android.bank.view.account_query;

import com.ultrawise.android.bank.view.ABankMain;
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

public class AccountQuery extends Activity {
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

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.account_query);
		// 跳转到主界面
		tvClassFirst = (TextView) this.findViewById(R.id.class_first);
        tvClassFirst.setText("首页>");
        tvClassFirst.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				 Intent intent = new Intent();
				 intent.setClass(AccountQuery.this, ABankMain.class);
				 AccountQuery.this.startActivity(intent);
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
				intent.setClass(AccountQuery.this, ABankMain.class);
				AccountQuery.this.startActivity(intent);
			}
		});
		//btnCoustom.setVisibility(View.VISIBLE);
		
	btnMain = (ImageView) this.findViewById(R.id.btnHelper);
		btnMain.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent intent=new Intent();
				intent.setClass(AccountQuery.this, ABankMain.class);
				AccountQuery.this.startActivity(intent);
			}
		});
	

		// 确定按钮跳转
		btnContinue = (Button) findViewById(R.id.btnContinue);
		btnContinue.setTextColor(Color.BLACK);
		btnContinue.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(AccountQuery.this, AccountQueryType.class);
				AccountQuery.this.startActivity(intent);
			}
		});
        
		adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		adapter.add("储蓄卡");
		adapter.add("信用卡");
		spinner = (Spinner) findViewById(R.id.spinnerAccTyp);
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				Spinner spinner = (Spinner) parent;
				Log.v("Test", "id = " + id + "("
						+ spinner.getSelectedItem().toString() + ")");

				if (spinner.getSelectedItem().toString() == "信用卡") {
					Log.v("Test", "信用卡");
					// 储蓄账户加载
					adapter2 = new ArrayAdapter<String>(AccountQuery.this,android.R.layout.simple_spinner_item);
					adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
					adapter2.add("信用卡99xxxx");
					adapter2.add("dddd");
					adapter2.add("eeeee");
					adapter2.add("ppppppp");
					spinner2 = (Spinner) findViewById(R.id.spinnerAcc);
					spinner2.setAdapter(adapter2);
					spinner2.setOnItemSelectedListener(new OnItemSelectedListener() {
							public void onItemSelected(AdapterView<?> parent, View view,int position, long id) {
									Spinner spinner = (Spinner) parent;
									System.out.println(spinner.getSelectedItem().toString());
						}
							public void onNothingSelected(AdapterView<?> arg0) {}});
					// 结束储蓄用户绑定
					
					
				} else if (spinner.getSelectedItem().toString() == "储蓄卡") {
					// 储蓄账户加载
					adapter2 = new ArrayAdapter<String>(AccountQuery.this,android.R.layout.simple_spinner_item);
					adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
					adapter2.add("611111111111");
					adapter2.add("222222222222222");
					adapter2.add("333333333");
					adapter2.add("444444444");
					spinner2 = (Spinner) findViewById(R.id.spinnerAcc);
					spinner2.setAdapter(adapter2);
					spinner2.setOnItemSelectedListener(new OnItemSelectedListener() {
								public void onItemSelected(AdapterView<?> parent, View view,int position, long id) {
										Spinner spinner = (Spinner) parent;
										System.out.println(spinner.getSelectedItem().toString());}
								public void onNothingSelected(AdapterView<?> arg0) {}});
					// 结束信用卡用户绑定
				}
			}
					    public void onNothingSelected(AdapterView<?> arg0) {}
		});
	}

}

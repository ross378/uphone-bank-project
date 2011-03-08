package com.ultrawise.android.bank.view.account_query;

import java.util.List;

import com.ultrawise.android.bank.consum_webservices.QuerySever;
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
	private String[] reslut=null;

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
				finish();
			}
		});
		//btnCoustom.setVisibility(View.VISIBLE);
		
	btnMain = (ImageView) this.findViewById(R.id.btnHelper);
		btnMain.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent intent=new Intent();
				intent.setClass(AccountQuery.this,  FinancialConsultation.class);
				AccountQuery.this.startActivity(intent);
				finish();
			}
		});
	

		// 确定按钮跳转
		btnContinue = (Button) findViewById(R.id.btnContinue);
		btnContinue.setTextColor(Color.BLACK);
		btnContinue.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent intent = new Intent();
				System.out.println("type=="+spinner.getSelectedItem().toString().trim());
				System.out.println("nomber=="+spinner2.getSelectedItem().toString().trim());
				
				String type=spinner.getSelectedItem().toString().trim();
				String nomber=spinner2.getSelectedItem().toString().trim();
				intent.putExtra("type", type);
				intent.putExtra("nomber", nomber);
				intent.setClass(AccountQuery.this, AccountQueryType.class);
				AccountQuery.this.startActivity(intent);
			}
		});
		adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		
		/**
		 * 讲前一个Activity传入的只放入文本框zho
		 */
		Intent intent=getIntent();
		reslut=intent.getStringArrayExtra("result");
		for(int i=1;i<reslut.length;i+=2){
			adapter.add(reslut[i]);
		}
//			adapter.add(reslut[2]);
//			adapter.add(reslut[4]);
//			adapter.add(reslut[1]);
		/*
		 *
list=paypal01
list=活期储蓄卡
list=paypal02
list=定期储蓄卡
list=paypal03
list=活期储蓄
		 */
		
		spinner = (Spinner) findViewById(R.id.spinnerAccTyp);
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				Spinner spinner = (Spinner) parent;
				Log.v("Test", "id = " + id + "("
						+ spinner.getSelectedItem().toString() + ")");

				if (id==0) {
					Log.v("Test", "定期储蓄卡");
					// 储蓄账户加载
					adapter2 = new ArrayAdapter<String>(AccountQuery.this,android.R.layout.simple_spinner_item);
					adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
					

					/**
					 * 从后台获取数据
					 * 
					 * @param 功能号为022  卡的类型为：定期储蓄卡
					 * @return定期储蓄卡的卡号
					 */
//					adapter2.add(reslut[3]);
//					adapter2.add(reslut[5]);
//					adapter2.add(reslut[0]);
					String[] str=new String[]{reslut[0]};
					List<String> result=QuerySever.connectHttp("022", str);
					for(int i=0;i<result.size();i++)
					{
					System.out.println("明文022---"+result.get(i));	
					adapter2.add(result.get(i));
					}
					
					spinner2 = (Spinner) findViewById(R.id.spinnerAcc);
					spinner2.setAdapter(adapter2);
					spinner2.setOnItemSelectedListener(new OnItemSelectedListener() {
							public void onItemSelected(AdapterView<?> parent, View view,int position, long id) {
									Spinner spinner = (Spinner) parent;
									System.out.println("默认第一项="+spinner.getSelectedItem().toString());
						}
							public void onNothingSelected(AdapterView<?> arg0) {}});
					// 结束储蓄用户绑定
					
					
				} else if (id==1) {
					// 储蓄账户加载
					adapter2 = new ArrayAdapter<String>(AccountQuery.this,android.R.layout.simple_spinner_item);
					adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
					/**
					 * 从后台获取数据
					 * 
					 * @param 功能号为022  卡的类型为：定期储蓄卡
					 * @return定期储蓄卡的卡号
					 */
					String[] str=new String[]{reslut[2]};
					List<String> result=QuerySever.connectHttp("022", str);
					for(int i=0;i<result.size();i++)
					{
					adapter2.add(result.get(i));
					}
					
					
					
					
					spinner2 = (Spinner) findViewById(R.id.spinnerAcc);
					spinner2.setAdapter(adapter2);
					spinner2.setOnItemSelectedListener(new OnItemSelectedListener() {
								public void onItemSelected(AdapterView<?> parent, View view,int position, long id) {
										Spinner spinner = (Spinner) parent;
										System.out.println(spinner.getSelectedItem().toString());}
								public void onNothingSelected(AdapterView<?> arg0) {}});
					// 结束信用卡用户绑定
				}
				else if (id==2) {
					// 储蓄账户加载
					adapter2 = new ArrayAdapter<String>(AccountQuery.this,android.R.layout.simple_spinner_item);
					adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
					

					
					
					
					/**
					 * 从后台获取数据
					 * 
					 * @param 功能号为022  卡的类型为：定期储蓄卡
					 * @return定期储蓄卡的卡号
					 */
					String[] str=new String[]{reslut[4]};
					List<String> result=QuerySever.connectHttp("022", str);
					for(int i=0;i<result.size();i++)
					{
					adapter2.add(result.get(i));
					}
					
					
					
					
					
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

package com.ultrawise.android.bank.view.payment;
	import com.ultrawise.android.bank.view.ABankMain;
	import com.ultrawise.android.bank.view.account_management.AccountManagement;
	import com.ultrawise.android.bank.view.transfer.R;

import android.R.integer;
	import android.app.Activity;
	import android.content.Intent;
	import android.os.Bundle;
	import android.util.Log;
	import android.view.View;
	import android.view.View.OnClickListener;
	import android.widget.AdapterView;
	import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
	import android.widget.ImageView;
	import android.widget.Spinner;
	import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;

	public class AllPaymentSer extends Activity {
		 private Spinner AccTypSpinner=null;
		 private ArrayAdapter< String> AccTypAdapter=null;
		 private Spinner Accspinner=null;
		 private  ArrayAdapter< String> Accadapter=null;
		 private TextView tvClassFirst=null;
		 private TextView tvClassSecond=null;
		 private ImageView btnReturn=null;
		 private Intent intent=null;
		 private Intent paymentre_intent=null;
		 private String  ser_name="";
		 private EditText tv_ser_num;
		@Override
		public void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			setContentView(R.layout.pay_all_ser);
			
		    TextView tvClassFirst = (TextView)this.findViewById(R.id.class_first);
			tvClassFirst.setText("首页>");
			tvClassFirst.setVisibility(View.VISIBLE);
			
			tvClassFirst.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					Intent intent = new Intent();
					 intent.setClass(AllPaymentSer.this, ABankMain.class);
					 AllPaymentSer.this.startActivity(intent);
				}
			});
			tvClassFirst.setVisibility(View.VISIBLE);
			
			
			
			 TextView tvClassSecond = (TextView)this.findViewById(R.id.class_second);
			 tvClassSecond.setText("自助缴费>");
			 tvClassSecond.setVisibility(View.VISIBLE);
				
			 tvClassSecond.setOnClickListener(new OnClickListener() {
					public void onClick(View v) {
						Intent payment_intent = new Intent();
						payment_intent.setClass(AllPaymentSer.this, PaymentMain.class);
						AllPaymentSer.this.startActivity(payment_intent);	
					}
				});
				tvClassFirst.setVisibility(View.VISIBLE);
			
			
			TextView tvClassThird = (TextView)this.findViewById(R.id.class_third);
			tvClassThird.setText("便捷服务>");
			tvClassThird.setOnClickListener(new OnClickListener() {
				
				public void onClick(View v) {
					Intent payment_intent = new Intent();
					payment_intent.setClass(AllPaymentSer.this, PaymentSelfService.class);
					AllPaymentSer.this.startActivity(payment_intent);	
					
				}
			});
			tvClassThird.setVisibility(View.VISIBLE);
			
			TextView tvClassFour = (TextView)this.findViewById(R.id.class_four);
			
			paymentre_intent = getIntent();
          ser_name= paymentre_intent.getStringExtra("ser_name");
          tvClassFour.setText(ser_name);
          tvClassFour.setVisibility(View.VISIBLE);
			
			//运行商选择
			AccTypAdapter= new ArrayAdapter< String>(  this,  android.R.layout.simple_spinner_item);  
			AccTypAdapter.setDropDownViewResource(  android.R.layout.simple_spinner_dropdown_item);  
			if(ser_name=="手机充值"){
			AccTypAdapter.add("中国移动"); 
			AccTypAdapter.add("中国联通"); 
			AccTypAdapter.add("中国电信");
			}else if(ser_name=="QQ充值"){
				AccTypAdapter.add("腾讯QQ"); 
				
			}else if(ser_name=="网易帐号"){
				AccTypAdapter.add("网易"); 
				
				
			}
			AccTypSpinner= (Spinner)findViewById(R.id.spinner_pay_typ); 
			AccTypSpinner.setAdapter(AccTypAdapter);  
	        
			AccTypSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

				public void onItemSelected(AdapterView< ?> parent,  View view, 
						int position, long id) {
					Spinner spinner = (Spinner) parent;  
					Log.v("Test", "id = " + id + "("  + spinner.getSelectedItem().toString() + ")");
				}

				public void onNothingSelected(AdapterView<?> arg0) {
				} 
	      }); 
	        
	        //面额选择
	        Accadapter= new ArrayAdapter< String>(  this,  android.R.layout.simple_spinner_item);  
	        Accadapter.setDropDownViewResource(  android.R.layout.simple_spinner_dropdown_item);  
	        Accadapter.add("10"); 
	        Accadapter.add("20"); 
	        Accadapter.add("30"); 
	        Accadapter.add("50"); 
	        Accadapter.add("100");
	        Accadapter.add("150");
	        Accspinner= (Spinner)findViewById(R.id.spinner_pay_num); 
	        Accspinner.setAdapter(Accadapter);  
	        
	        Accspinner.setOnItemSelectedListener(new OnItemSelectedListener() {

				public void onItemSelected(AdapterView< ?> parent,  View view, 
						int position, long id) {
					Spinner spinner = (Spinner) parent;  
					Log.v("Test", "id = " + id + "("  + spinner.getSelectedItem().toString() + ")");
				}

				public void onNothingSelected(AdapterView<?> arg0) {
				} 
	      });     
	        
	        TextView tv_input_ser_num=(TextView)findViewById(R.id.input_ser_num);
	        tv_input_ser_num.setText("请输入目标"+ser_name+"号：");
	        
	        tv_ser_num=(EditText)findViewById(R.id.tv_ser_num);
	       	        
	        
	        //下一步的按钮选择
	        Button btn_next=(Button)findViewById(R.id.btn_next);
	        btn_next.setText("下一步");
	        btn_next.setOnClickListener(new OnClickListener() {
				
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
				Intent	pay_ser_intent=new Intent();
				
				pay_ser_intent.putExtra("title",AllPaymentSer.this.tv_ser_num.getText().toString()+ser_name);
				pay_ser_intent.putExtra("amount",Accspinner.getSelectedItem().toString());
				pay_ser_intent.putExtra("inputed_peo",AccTypSpinner.getSelectedItem().toString());
				pay_ser_intent.putExtra("serialnum", "365246245236714");
				pay_ser_intent.putExtra("deadline", "无");			
				pay_ser_intent.setClass(AllPaymentSer.this,PaymentDetail.class);
				AllPaymentSer.this.startActivity(pay_ser_intent);
					
				}
			});
	        
	        
		}

	}

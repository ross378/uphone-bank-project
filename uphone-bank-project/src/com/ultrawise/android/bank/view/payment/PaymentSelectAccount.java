package com.ultrawise.android.bank.view.payment;
	import com.ultrawise.android.bank.view.ABankMain;
	import com.ultrawise.android.bank.view.account_management.AccountManagement;
	import com.ultrawise.android.bank.view.transfer.R;

	import android.app.Activity;
	import android.content.Intent;
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

	public class PaymentSelectAccount extends Activity {
		 private Spinner AccTypSpinner=null;
		 private ArrayAdapter< String> AccTypAdapter=null;
		 private Spinner Accspinner=null;
		 private  ArrayAdapter< String> Accadapter=null;
		 private TextView tvClassFirst=null;
		 private TextView tvClassSecond=null;
		 private ImageView btnReturn=null;
		 private Intent intent=null;
		@Override
		public void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			setContentView(R.layout.pay_accounttype_select);
			
		    TextView tvClassFirst = (TextView)this.findViewById(R.id.class_first);
			tvClassFirst.setText("首页>");
			tvClassFirst.setVisibility(View.VISIBLE);
			
			tvClassFirst.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
//					Intent payment_intent = new Intent();
//					payment_intent.setClass(PaymentSelectAccount.this, PaymentMain.class);
//					PaymentSelectAccount.this.startActivity(payment_intent);	
				}
			});
			tvClassFirst.setVisibility(View.VISIBLE);
			
			
			
			
		    TextView tvClassSecond = (TextView)this.findViewById(R.id.class_second);
		    tvClassSecond.setText("自助缴费>");
		    tvClassSecond.setVisibility(View.VISIBLE);
			
		    tvClassSecond.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					Intent payment_intent = new Intent();
					payment_intent.setClass(PaymentSelectAccount.this, PaymentMain.class);
					PaymentSelectAccount.this.startActivity(payment_intent);	
				}
			});
		    tvClassSecond.setVisibility(View.VISIBLE);
			
			
			TextView tvClassThird = (TextView)this.findViewById(R.id.class_third);
			tvClassThird.setText("待缴费项目");
			tvClassThird.setVisibility(View.VISIBLE);
			tvClassThird.setOnClickListener(new OnClickListener() {
				
				public void onClick(View v) {
					Intent payment_intent = new Intent();
					payment_intent.setClass(PaymentSelectAccount.this, PaymentPend.class);
					PaymentSelectAccount.this.startActivity(payment_intent);	
					
				}
			});
			
			
			
			//账户类型选择
			AccTypAdapter= new ArrayAdapter< String>(  this,  android.R.layout.simple_spinner_item);  
			AccTypAdapter.setDropDownViewResource(  android.R.layout.simple_spinner_dropdown_item);  
			AccTypAdapter.add("储蓄卡"); 
			AccTypAdapter.add("信用卡"); 
			AccTypSpinner= (Spinner)findViewById(R.id.spinnerAccTyp); 
			AccTypSpinner.setAdapter(AccTypAdapter);  
	        
			AccTypSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

				public void onItemSelected(AdapterView< ?> parent,  View view, 
						int position, long id) {
					Spinner spinner = (Spinner) parent;  
					
					Log.v("Test", "id = " + id + "("  + spinner.getSelectedItem().toString() + ")");
					
					
					if(spinner.getSelectedItem().toString()=="储蓄卡"){
						 Log.v("Test","fdsjjjj");
					        //储蓄账户加载
					        Accadapter= new ArrayAdapter< String>(  PaymentSelectAccount.this,  android.R.layout.simple_spinner_item);  
					        Accadapter.setDropDownViewResource(  android.R.layout.simple_spinner_dropdown_item);  
					        Accadapter.add("611111111111"); 
					        Accadapter.add("222222222222222"); 
					        Accadapter.add("333333333"); 
					        Accadapter.add("444444444");
					        Accspinner= (Spinner)findViewById(R.id.spinnerAcc); 
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
					        //结束储蓄用户绑定
						
					}else if(spinner.getSelectedItem().toString()=="信用卡"){
				        	
					        
					        Log.v("Test","fdshh4345235fsd");
					        
					        //信用卡账户加载
					        Accadapter= new ArrayAdapter< String>(PaymentSelectAccount.this,  android.R.layout.simple_spinner_item);  
					        Accadapter.setDropDownViewResource(  android.R.layout.simple_spinner_dropdown_item);  
					        Accadapter.add("62434345345"); 
					        Accadapter.add("4234113423241"); 
					        Accadapter.add("624848494945"); 
					        Accadapter.add("42348987693241");
					        Accspinner= (Spinner)findViewById(R.id.spinnerAcc); 
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
					        //结束信用卡用户绑定
				        
				             }
				}

				public void onNothingSelected(AdapterView<?> arg0) {
				} 
	      }); 
			
	        
        
   

	        
	        
	        
	        //下一步的按钮选择
	        Button btn_next=(Button)findViewById(R.id.btn_next);
	        btn_next.setText("下一步"+">");
	        btn_next.setOnClickListener(new OnClickListener() {
				
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
				Intent	AccMation_intent=new Intent();
				
				AccMation_intent.putExtra("Account",Accspinner.getSelectedItem().toString());
				AccMation_intent.setClass(PaymentSelectAccount.this,PaymentInPwd.class);
				PaymentSelectAccount.this.startActivity(AccMation_intent);
					
				}
			});
	        
	        
		}

	}

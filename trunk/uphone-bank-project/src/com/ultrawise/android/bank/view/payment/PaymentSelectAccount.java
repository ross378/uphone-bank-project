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
	import android.widget.ImageView;
	import android.widget.Spinner;
	import android.widget.TextView;
	import android.widget.AdapterView.OnItemSelectedListener;

	public class PaymentSelectAccount extends Activity {
		 private Spinner spinner=null;
		 private ArrayAdapter< String> adapter=null;
		 private Spinner spinner2=null;
		 private  ArrayAdapter< String> adapter2=null;
		 private TextView tvClassFirst=null;
		 private TextView tvClassSecond=null;
		 private ImageView btnReturn=null;
		 private Intent intent=null;
		@Override
		public void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			setContentView(R.layout.pay_accounttype_select);
			adapter= new ArrayAdapter< String>(  this,  android.R.layout.simple_spinner_item);  
	        adapter.setDropDownViewResource(  android.R.layout.simple_spinner_dropdown_item);  
	        adapter.add("储蓄卡"); 
	        adapter.add("信用卡"); 
	        spinner= (Spinner)findViewById(R.id.spinnerAccTyp); 
	        spinner.setAdapter(adapter);  
	        
	        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

				public void onItemSelected(AdapterView< ?> parent,  View view, 
						int position, long id) {
					Spinner spinner = (Spinner) parent;  
					Log.v("Test", "id = " + id + "("  + spinner.getSelectedItem().toString() + ")");
				}

				public void onNothingSelected(AdapterView<?> arg0) {
				} 
	      }); 
	        
	        
	        intent = new Intent();
//	        tvClassFirst = (TextView) this.findViewById(R.id.class_first);
//			tvClassFirst.setText("下一步");
//			tvClassFirst.setVisibility(View.VISIBLE);

			//返回键设定
			btnReturn = (ImageView)this.findViewById(R.id.returnToPre);
			btnReturn.setOnClickListener(new OnClickListener(){
				public void onClick(View v) {
					// TODO Auto-generated method stub
					onBackPressed();
					finish();
				}
			});
		}

	}

package com.ultrawise.android.bank.view.transfer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;


public class TransTest extends Activity {
	private Button button1;
	private Button button2;
	private String title = "test title";
	private String content = "test content /n test huanhang";
	private String buttontext = "button text";
	
	OnClickListener onClick = new MyButtonListener();
	
	class MyButtonListener implements OnClickListener{
    	public void onClick(View v){
    		Intent intent = new Intent();
    		intent.setClass(TransTest.this, TransferMain.class);
    	}
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.transtest);
		
		button1 = (Button)findViewById(R.id.button1);
		button1.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CommonDialog commonDialog = new CommonDialog();
				commonDialog.showDialog(title, content, buttontext);	
			}
		});
		button2 = (Button)findViewById(R.id.button2);
		button2.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CommonDialog2 commonDialog2 = new CommonDialog2();
				commonDialog2.showDialog(title, content, buttontext, onClick);	
			}
		});
	}
}
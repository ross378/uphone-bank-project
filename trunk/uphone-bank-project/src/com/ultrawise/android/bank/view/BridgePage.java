package com.ultrawise.android.bank.view;


import com.ultrawise.android.bank.view.transfer.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class BridgePage extends Activity{
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bridge_page);
		
		TimeThread time = new TimeThread();
		time.start();
	}
	
	class TimeThread extends Thread{
		
		public void run(){
			long l = 5000;
			try{
				this.sleep(l);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
			Intent intent = new Intent();
			intent.setClass(BridgePage.this, FinancialConsultation.class);
			BridgePage.this.startActivity(intent);										
			
		}
	}

}

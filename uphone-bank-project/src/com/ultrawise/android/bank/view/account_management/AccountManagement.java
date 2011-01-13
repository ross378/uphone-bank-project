package com.ultrawise.android.bank.view.account_management;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TableLayout.LayoutParams;

import com.ultrawise.android.bank.view.transfer.R;

public class AccountManagement extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.account_management);
		
//		LayoutInflater lif = LayoutInflater.from(getApplicationContext());
//		View view = lif.inflate(R.layout.account_management, null);
//		
//		this.addContentView(view, new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
//		
	}
}

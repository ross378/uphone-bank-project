package com.ultrawise.android.bank.view.account_management;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TableLayout.LayoutParams;

import com.ultrawise.android.bank.view.transfer.R;

public class AccountManagement extends Activity {
	private Button btnCoustom;
	private Button btnAccInfo;
	private Button btnActiveAcc;
	private Button btnLossRegister;
	
	Intent intent;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.account_management);
		//设置底部自定义按钮显示
		btnCoustom = (Button)this.findViewById(R.id.btnCoustom);
		btnCoustom.setText("账户管理");
		btnCoustom.setVisibility(View.VISIBLE);
		
		
		
		//Button Account Information
		btnAccInfo = (Button)this.findViewById(R.id.btnAccInfo);
		btnAccInfo.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				// TODO Auto-generated method stub
				intent = AccountManagement.this.getIntent();
				intent.setClass(AccountManagement.this, AccountInfo.class);
				AccountManagement.this.startActivity(intent);
			}
		});
		//Button Active Account
		btnActiveAcc = (Button)this.findViewById(R.id.btnActiveAcc);
		btnActiveAcc.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				intent = AccountManagement.this.getIntent();
				intent.setClass(AccountManagement.this, ActiveAccount.class);
				AccountManagement.this.startActivity(intent);
			}
		});
	}
}

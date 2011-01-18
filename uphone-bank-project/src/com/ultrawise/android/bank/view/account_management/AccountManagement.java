package com.ultrawise.android.bank.view.account_management;

import android.R.drawable;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import android.widget.TextView;

import com.ultrawise.android.bank.view.transfer.R;

public class AccountManagement extends Activity {
	private ImageView btnCoustom;
	private Button btnAccInfo;
	private Button btnActiveAcc;
	private Button btnLossRegister;
	private Button btnOrderCard;
	private Button btnPreAcc;
	private Button btnDefAcc;
	private TextView tvClassFirst;
	private TextView tvClassSecond;
	
	Intent intent;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.account_management);
		
		intent = new Intent();
		
		//���ò㼶��ϵ
		tvClassFirst = (TextView)this.findViewById(R.id.class_first);
		tvClassFirst.setText("�ֻ�����>");
		tvClassFirst.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// intent = QueryAccount.this.getIntent();
				// intent.setClass(QueryAccount.this, AccountManagement.class);
				// QueryAccount.this.startActivity(intent);

			}
		});
		tvClassFirst.setVisibility(View.VISIBLE);
		
		tvClassSecond = (TextView)this.findViewById(R.id.class_second);
		tvClassSecond.setText("�˻�����");
		
		tvClassSecond.setVisibility(View.VISIBLE);
		
		//���õײ��Զ��尴ť��ʾ
		btnCoustom = (ImageView)this.findViewById(R.id.btnCoustom);
		btnCoustom.setImageResource(R.drawable.cardbg_jrzx_w);
		btnCoustom.setVisibility(View.VISIBLE);
		
		
		//Button Account Information
		btnAccInfo = (Button)this.findViewById(R.id.btnAccInfo);
		btnAccInfo.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				intent.setClass(AccountManagement.this, AccountInfo.class);
				AccountManagement.this.startActivity(intent);
			}
		});
		//Button Active Account
		btnActiveAcc = (Button)this.findViewById(R.id.btnActiveAcc);
		btnActiveAcc.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				
				intent.setClass(AccountManagement.this, ActiveAccount.class);
				AccountManagement.this.startActivity(intent);
			}
		});
		
		//Button Loss Register
		btnLossRegister = (Button)this.findViewById(R.id.btnLossRegister);
		btnLossRegister.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				
				intent.setClass(AccountManagement.this, LossRegister.class);
				AccountManagement.this.startActivity(intent);
			}
		});
		
		//Button Order Card
		btnOrderCard = (Button)this.findViewById(R.id.btnOrder);
		btnOrderCard.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				
				intent.setClass(AccountManagement.this, OrderCard.class);
				AccountManagement.this.startActivity(intent);
			}
		});
		
		//Button Pre Account
		btnPreAcc = (Button)this.findViewById(R.id.btnPreferredAcc);
		btnPreAcc.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				intent = AccountManagement.this.getIntent();
				intent.setClass(AccountManagement.this, PreferredAccount.class);
				AccountManagement.this.startActivity(intent);
			}
		});
		
		//Button default Account
		btnDefAcc = (Button)this.findViewById(R.id.btnDefaultAcc);
		btnDefAcc.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				intent = AccountManagement.this.getIntent();
				intent.setClass(AccountManagement.this, DefaultAccount.class);
				AccountManagement.this.startActivity(intent);
			}
		});
		
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}
}

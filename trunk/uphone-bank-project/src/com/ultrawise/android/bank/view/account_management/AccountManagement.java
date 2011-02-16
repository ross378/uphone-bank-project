package com.ultrawise.android.bank.view.account_management;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import android.widget.TextView;

import com.ultrawise.android.bank.view.ABankMain;
import com.ultrawise.android.bank.view.FinancialConsultation;
import com.ultrawise.android.bank.view.transfer.R;

public class AccountManagement extends Activity {
	private ImageView btnCoustom;
	private ImageView btnAccInfo;
	private ImageView btnActiveAcc;
	private ImageView btnLossRegister;
	private ImageView btnOrderCard;
	private ImageView btnPreAcc;
	private ImageView btnDefAcc;
	private TextView tvClassFirst;
	private TextView tvClassSecond;

	Intent intent;
	private ImageView btnReturn;
	private ImageView btnMain;
	private ImageView btnHelper;
	private GestureDetector mGestureDetector;
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {

		return mGestureDetector.onTouchEvent(event);

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.account_management);

		intent = new Intent();
		
		//向右滑动触发后退
		mGestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener(){
			@Override
			public boolean onScroll(MotionEvent e1, MotionEvent e2,
					float distanceX, float distanceY) {
				// TODO Auto-generated method stub
				if (distanceY == 0 && distanceX < 0)
					onBackPressed();

				return super.onScroll(e1, e2, distanceX, distanceY);
			}
		});
		
		// 
		tvClassFirst = (TextView) this.findViewById(R.id.class_first);
		tvClassFirst.setText("手机银行>");
		tvClassFirst.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				 intent.setClass(AccountManagement.this, ABankMain.class);
				 AccountManagement.this.startActivity(intent);

			}
		});
		tvClassFirst.setVisibility(View.VISIBLE);

		tvClassSecond = (TextView) this.findViewById(R.id.class_second);
		tvClassSecond.setText("账户管理");

		tvClassSecond.setVisibility(View.VISIBLE);

		//设置底部按钮
		btnCoustom = (ImageView) this.findViewById(R.id.btnCoustom);
		btnCoustom.setImageResource(R.drawable.cardbg_zhgl_w);
		btnCoustom.setVisibility(View.VISIBLE);
		
		btnMain = (ImageView) this.findViewById(R.id.btnMain);
		btnMain.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				intent = AccountManagement.this.getIntent();
				intent.setClass(AccountManagement.this, ABankMain.class);
				AccountManagement.this.startActivity(intent);
			}
		});
		
		btnHelper = (ImageView) this.findViewById(R.id.btnHelper);
		btnHelper.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				intent = AccountManagement.this.getIntent();
				intent.setClass(AccountManagement.this, FinancialConsultation.class);
				AccountManagement.this.startActivity(intent);
			}
		});

		// Button Account Information
		btnAccInfo = (ImageView) this.findViewById(R.id.btnAccInfo);
		btnAccInfo.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub

				intent.setClass(AccountManagement.this, AccountInfo.class);
				AccountManagement.this.startActivity(intent);
			}
		});
		// Button Active Account
		btnActiveAcc = (ImageView) this.findViewById(R.id.btnActiveAcc);
		btnActiveAcc.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				intent.setClass(AccountManagement.this, ActiveAccount.class);
				AccountManagement.this.startActivity(intent);
			}
		});

		// Button Loss Register
		btnLossRegister = (ImageView) this.findViewById(R.id.btnLossRegister);
		btnLossRegister.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				intent.setClass(AccountManagement.this, LossRegister.class);
				AccountManagement.this.startActivity(intent);
			}
		});

		// Button Order Card
		btnOrderCard = (ImageView) this.findViewById(R.id.btnOrder);
		btnOrderCard.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				intent.setClass(AccountManagement.this, OrderCard.class);
				AccountManagement.this.startActivity(intent);
			}
		});

		// Button Pre Account
		btnPreAcc = (ImageView) this.findViewById(R.id.btnPreferredAcc);
		btnPreAcc.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				intent = AccountManagement.this.getIntent();
				intent.setClass(AccountManagement.this, PreferredAccount.class);
				AccountManagement.this.startActivity(intent);
			}
		});

		// Button default Account
		btnDefAcc = (ImageView) this.findViewById(R.id.btnDefaultAcc);
		btnDefAcc.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				intent = AccountManagement.this.getIntent();
				intent.setClass(AccountManagement.this, DefaultAccount.class);
				AccountManagement.this.startActivity(intent);
			}
		});
		
		
		
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

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}
}

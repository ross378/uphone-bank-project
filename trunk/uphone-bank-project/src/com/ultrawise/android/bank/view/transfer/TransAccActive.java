package com.ultrawise.android.bank.view.transfer;

import com.ultrawise.android.bank.view.ABankMain;
import com.ultrawise.android.bank.view.FinancialConsultation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


public class TransAccActive extends Activity {
	private GestureDetector mGestureDetector;
	private ImageView btnReturn;
	private ImageView btnMain;
	private ImageView btnHelper;
	private TextView tv_acctype;
	private EditText tv_trans_pasd;
	private String transtype;
	private String username;
	private String serviceAddress = "http://10.1.111.192:8080/uphone_webservices/transfer";
	private String requestParameters;
	Intent receive_intent;
	Intent intent;
	
	//触摸触发
	@Override
	public boolean onTouchEvent(MotionEvent event) {

		return mGestureDetector.onTouchEvent(event);

	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trans_acc_active);
        
        receive_intent = getIntent();
        intent = new Intent();
        transtype = receive_intent.getStringExtra("transtype");
        username  = receive_intent.getStringExtra(username);
        
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
  		
      //顶部导航文本
        transtype = receive_intent.getStringExtra("transtype");
        TextView tvClassFirst = (TextView)this.findViewById(R.id.class_first);
		tvClassFirst.setText("首页");
		tvClassFirst.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(TransAccActive.this, ABankMain.class);
				TransAccActive.this.startActivity(intent);
			}
		});
		tvClassFirst.setVisibility(View.VISIBLE);
		
		TextView tvClassSecond = (TextView)this.findViewById(R.id.class_second);
		tvClassSecond.setText(">转账汇款");
		tvClassSecond.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(TransAccActive.this, TransferMain.class);
				TransAccActive.this.startActivity(intent);
			}
		});
		tvClassSecond.setVisibility(View.VISIBLE);
		
		TextView tvClassThird = (TextView)this.findViewById(R.id.class_third);
		tvClassThird.setText(transtype);
		tvClassThird.setVisibility(View.VISIBLE);
		
		//next
		Button btn_trans_act = (Button)findViewById(R.id.btn_trans_act);
		btn_trans_act.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				// TODO Auto-generated method stub
		        transtype = receive_intent.getStringExtra("transtype");
		        username = receive_intent.getStringExtra(username);
				Intent intent = new Intent();
				intent.putExtra("transtype", transtype);
				intent.setClass(TransAccActive.this, TransAccPsdSucc.class);
				TransAccActive.this.startActivity(intent);
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
		
		//底部两个按钮
		btnMain = (ImageView) this.findViewById(R.id.btnMain);
		btnMain.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				intent.setClass(TransAccActive.this, ABankMain.class);
				TransAccActive.this.startActivity(intent);
			}
		});
		
		btnHelper = (ImageView) this.findViewById(R.id.btnHelper);
		btnHelper.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				intent.setClass(TransAccActive.this, FinancialConsultation.class);
				TransAccActive.this.startActivity(intent);
			}
		});
	}
}
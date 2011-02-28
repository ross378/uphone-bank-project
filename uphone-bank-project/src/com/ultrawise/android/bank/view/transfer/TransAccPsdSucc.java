package com.ultrawise.android.bank.view.transfer;

import com.ultrawise.android.bank.view.ABankMain;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class TransAccPsdSucc extends Activity {
	private GestureDetector mGestureDetector;
	private Button btn_trans_reselect;
	private Button btn_trans_next;
	private ImageView btnReturn;
	private ImageView btnMain;
	private ImageView btnHelper;
	Intent intent;
	
	//触摸触发
	@Override
	public boolean onTouchEvent(MotionEvent event) {

		return mGestureDetector.onTouchEvent(event);

	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trans_acc_psdsucc);
        
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
          
  		//顶部导航文本
        Intent receive_intent = getIntent();
        String transtype = receive_intent.getStringExtra("transtype");
        TextView tvClassFirst = (TextView)this.findViewById(R.id.class_first);
		tvClassFirst.setText("首页");
		tvClassFirst.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(TransAccPsdSucc.this, ABankMain.class);
				TransAccPsdSucc.this.startActivity(intent);
			}
		});
		tvClassFirst.setVisibility(View.VISIBLE);
		
		TextView tvClassSecond = (TextView)this.findViewById(R.id.class_second);
		tvClassSecond.setText(">转账汇款");
		tvClassSecond.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(TransAccPsdSucc.this, TransferMain.class);
				TransAccPsdSucc.this.startActivity(intent);
			}
		});
		tvClassSecond.setVisibility(View.VISIBLE);
		
		TextView tvClassThird = (TextView)this.findViewById(R.id.class_third);
		tvClassThird.setText(transtype);
		tvClassThird.setVisibility(View.VISIBLE);
        
		//重新选择账户
		btn_trans_reselect = (Button)findViewById(R.id.btn_trans_reselect);
        btn_trans_reselect.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent receive_intent = getIntent();
		        String transtype = receive_intent.getStringExtra("transtype");
				Intent trans_intent = new Intent();
				trans_intent.putExtra("transtype", transtype);
				trans_intent.setClass(TransAccPsdSucc.this,TransAccSelect.class);
				TransAccPsdSucc.this.startActivity(trans_intent);
			}
        });
        
        //next
        btn_trans_next = (Button)findViewById(R.id.btn_trans_next);
        btn_trans_next.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent receive_intent = getIntent();
		        String transtype = receive_intent.getStringExtra("transtype");
				Intent trans_intent = new Intent();
				trans_intent.putExtra("transtype", transtype);
				trans_intent.setClass(TransAccPsdSucc.this,TransAmtInput.class);
				TransAccPsdSucc.this.startActivity(trans_intent);
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
				intent = new Intent();
				intent.setClass(TransAccPsdSucc.this, ABankMain.class);
				TransAccPsdSucc.this.startActivity(intent);
			}
		});
		
		btnHelper = (ImageView) this.findViewById(R.id.btnHelper);
		btnHelper.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				intent = new Intent();
				intent.setClass(TransAccPsdSucc.this, TransAccPsdSucc.class);
				TransAccPsdSucc.this.startActivity(intent);
			}
		});
	}
}
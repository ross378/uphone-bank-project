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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;


public class TransAccInput extends Activity {
	private GestureDetector mGestureDetector;
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
        setContentView(R.layout.trans_acc_input);
        
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
				intent.setClass(TransAccInput.this, ABankMain.class);
				TransAccInput.this.startActivity(intent);
			}
		});
		tvClassFirst.setVisibility(View.VISIBLE);
		
		TextView tvClassSecond = (TextView)this.findViewById(R.id.class_second);
		tvClassSecond.setText(">转账汇款");
		tvClassSecond.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(TransAccInput.this, TransferMain.class);
				TransAccInput.this.startActivity(intent);
			}
		});
		tvClassSecond.setVisibility(View.VISIBLE);
		
		TextView tvClassThird = (TextView)this.findViewById(R.id.class_third);
		tvClassThird.setText(transtype);
		tvClassThird.setVisibility(View.VISIBLE);
        
		//Spinner
        Spinner sp_trans_inptype = (Spinner)findViewById(R.id.sp_trans_inptype);
        ArrayAdapter spadapter1 = ArrayAdapter.createFromResource(this, R.array.acctype, android.R.layout.simple_spinner_item);
        spadapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_trans_inptype.setAdapter(spadapter1);
        
        Spinner sp_trans_inpacc = (Spinner)findViewById(R.id.sp_trans_inpacc);
        ArrayAdapter spadapter2 = ArrayAdapter.createFromResource(this, R.array.account, android.R.layout.simple_spinner_item);
        spadapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_trans_inpacc.setAdapter(spadapter2);
        
        //next
        Button btn_trans_next = (Button)findViewById(R.id.btn_trans_inpacc);
        btn_trans_next.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent receive_intent = getIntent();
		        String transtype = receive_intent.getStringExtra("transtype");
				Intent intent = new Intent();
				intent.putExtra("transtype", transtype);
				intent.setClass(TransAccInput.this, TransAccActive.class);
				TransAccInput.this.startActivity(intent);
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
				intent.setClass(TransAccInput.this, ABankMain.class);
				TransAccInput.this.startActivity(intent);
			}
		});
		
		btnHelper = (ImageView) this.findViewById(R.id.btnHelper);
		btnHelper.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				intent = new Intent();
				intent.setClass(TransAccInput.this, FinancialConsultation.class);
				TransAccInput.this.startActivity(intent);
			}
		});
        
	}
}
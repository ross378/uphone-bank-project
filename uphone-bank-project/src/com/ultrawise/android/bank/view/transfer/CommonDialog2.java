package com.ultrawise.android.bank.view.transfer;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class CommonDialog2 extends Activity {
	private TextView title;
	private TextView content1;
	private Button button1;
	private Button button2;
	
	public void showDialog(String titletext, String contenttext1, String buttontxt1,String buttontxt2) {
		onCreate(null);
		title = (TextView) findViewById(R.id.tv_comdlog_title);
		title.setText(titletext);
		content1 = (TextView) findViewById(R.id.tv_comdlog_con1);
		content1.setText(contenttext1);
		button1 = (Button)findViewById(R.id.btn_comdlog_btn1);
		button1.setText(buttontxt1);
		//button1.setOnClickListener(onClick1);
		button2 = (Button)findViewById(R.id.btn_comdlog_btn2);
		button2.setText(buttontxt2);
		//button2.setOnClickListener(onClick2);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.commondialog2);
	}
}
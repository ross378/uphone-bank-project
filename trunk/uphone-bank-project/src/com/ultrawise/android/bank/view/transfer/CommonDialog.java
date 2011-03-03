package com.ultrawise.android.bank.view.transfer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class CommonDialog extends Activity {
	private TextView title;
	private TextView content1;
	private Button button;
	protected Intent intent;

	public void showDialog(String titletext, String contenttext1,String buttontxt, Context con, Class<?> cls) {
		onCreate(null);
		title = (TextView) findViewById(R.id.tv_comdlog_title);
		title.setText(titletext);
		content1 = (TextView) findViewById(R.id.tv_comdlog_con1);
		content1.setText(contenttext1);
		button.setText(buttontxt);
		button.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				// TODO Auto-generated method stub
				intent=new Intent();
				//intent.setClass(con, cls);
				//con.startActivity(intent);
			}
			
		});
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.commondialog);
	}
}
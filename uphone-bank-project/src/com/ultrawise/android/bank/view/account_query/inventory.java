package com.ultrawise.android.bank.view.account_query;

import com.ultrawise.android.bank.view.ABankMain;
import com.ultrawise.android.bank.view.transfer.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

public class inventory extends Activity{

	Intent intent = new Intent();
	Button timeStart,timeOver;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.inventory);
		
		
		TextView tvClassFirst = (TextView) this.findViewById(R.id.class_first);
		tvClassFirst.setText("首页>");
		tvClassFirst.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				 intent.setClass(inventory.this, ABankMain.class);
				 inventory.this.startActivity(intent);
			}
		});
		tvClassFirst.setVisibility(View.VISIBLE);

		TextView tvClassSecond = (TextView) this.findViewById(R.id.class_second);
		tvClassSecond.setText("账户查询");
		 tvClassSecond.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					 intent.setClass(inventory.this,AccountQuery.class);
					 startActivity(intent);
				}
			});
		tvClassSecond.setVisibility(View.VISIBLE);
		
		
		//设定返回按钮
		ImageView btnReturn = (ImageView)this.findViewById(R.id.returnToPre);
		btnReturn.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				// TODO Auto-generated method stub
				onBackPressed();
				finish();
			}
		});
		timeStart = (Button)findViewById(R.id.timechange_from);
		timeStart.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub;

				LayoutInflater inflater = getLayoutInflater();
				final DatePicker layout = (DatePicker) inflater.inflate(R.layout.timechanger,(ViewGroup) findViewById(R.id.timechanger));
				   AlertDialog.Builder builder = new Builder(inventory.this);			   
				   builder.setTitle("设置时间").setView(layout).setNeutralButton("完成", new DialogInterface.OnClickListener(){

					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						String sql=null;
						int year = layout.getYear();
						int month = layout.getMonth();
						int day = layout.getDayOfMonth();

                        sql=String.valueOf(year)+String.valueOf(month+1)+String.valueOf(day);
                        timeStart.setText(sql);
					}
					   
				   })
				   .setNegativeButton("取消", null)
				   .show();
			}
		});
		timeOver = (Button)findViewById(R.id.timechange_to);
		timeOver.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub		
		        
				LayoutInflater inflater = getLayoutInflater();
				final DatePicker layout = (DatePicker) inflater.inflate(R.layout.timechanger,(ViewGroup) findViewById(R.id.timechanger));
				   AlertDialog.Builder builder = new Builder(inventory.this);			   
				   builder.setTitle("设置时间").setView(layout).setNeutralButton("完成", new DialogInterface.OnClickListener(){

					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						String sql=null;
						int year = layout.getYear();
						int month = layout.getMonth();
						int day = layout.getDayOfMonth();

                        sql=String.valueOf(year)+String.valueOf(month+1)+String.valueOf(day);
                        timeOver.setText(sql);
					}
					   
				   })
				   .setNegativeButton("取消", null)
				   .show();
			}
		});
		
		Button run = (Button)findViewById(R.id.Query_Run);
		run.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				intent.setClass(inventory.this, AccountQueryEinnahme.class);
				startActivity(intent);
			}
		});
	}
}

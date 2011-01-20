package com.ultrawise.android.bank.view.account_management;

import com.ultrawise.android.bank.view.transfer.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class AddAccount extends Activity {
	private Button btnConfirm;
	private ImageView btnCoustom;
	private EditText etAccount;
	private EditText etCoustomPwd;
	private TextView tvClassFirst;
	private TextView tvClassSecond;
	private TextView tvClassThrid;
	private TextView tvClassFour;
	Intent intent;
	private int flag = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.add_account);

		btnConfirm = (Button) this.findViewById(R.id.btnAddAccConfrim);
		etAccount = (EditText) this.findViewById(R.id.etAcc);
		etCoustomPwd = (EditText) this.findViewById(R.id.etCoustomPwd);
		// Confirm to add account
		btnConfirm.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// Eject dialog
				new AlertDialog.Builder(AddAccount.this)
						.setTitle("确认对话框")
						.setMessage("增加账户？")
						.setPositiveButton("确认",
								new DialogInterface.OnClickListener() {

									public void onClick(DialogInterface dialog,
											int which) {
										// Turn to AccountInfo
										flag = 1;// done
										Toast.makeText(AddAccount.this, "增加账户成功", Toast.LENGTH_SHORT).show();
										finish();
									}
								})
						.setNegativeButton("取消",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int which) {
										// cancel
										flag = -1;
										//finish();
									}
								}).show();
				// done
				if (flag == 1) {
					intent = AddAccount.this.getIntent();
					/**
					 *
					 */
					/*
					String strAccount = etAccount.getText().toString();
					String strCoustomPwd = etCoustomPwd.getText().toString();
					
					intent.putExtra("addAccNum", strAccount);
					intent.putExtra("CoustomPwd", strCoustomPwd);
					*/
					intent.setClass(AddAccount.this, AccountInfo.class);
					AddAccount.this.startActivity(intent);
				}
			}
		});
		
		//
		tvClassFirst = (TextView)this.findViewById(R.id.class_first);
		tvClassFirst.setText("手机银行>");
		tvClassFirst.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
//				 intent = AddAccount.this.getIntent();
//				 intent.setClass(AddAccount.this, AccountInfo.class);
//				 AddAccount.this.startActivity(intent);
			}
		});
		tvClassSecond = (TextView)this.findViewById(R.id.class_second);
		tvClassSecond.setText("账户管理>");
		tvClassSecond.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				 intent = AddAccount.this.getIntent();
				 intent.setClass(AddAccount.this, AccountManagement.class);
				 AddAccount.this.startActivity(intent);
			}
		});
		tvClassThrid = (TextView)this.findViewById(R.id.class_third);
		tvClassThrid.setText("账户信息>");
		tvClassThrid.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				 intent = AddAccount.this.getIntent();
				 intent.setClass(AddAccount.this, AccountInfo.class);
				 AddAccount.this.startActivity(intent);
			}
		});
		tvClassFour = (TextView)this.findViewById(R.id.class_four);
		tvClassFour.setText("增加账户");

		tvClassFirst.setVisibility(View.VISIBLE);
		tvClassSecond.setVisibility(View.VISIBLE);
		tvClassThrid.setVisibility(View.VISIBLE);
		tvClassFour.setVisibility(View.VISIBLE);

		// 
		btnCoustom = (ImageView) this.findViewById(R.id.btnCoustom);
		btnCoustom.setImageResource(R.drawable.cardbg_zhgl_w);
		btnCoustom.setVisibility(View.VISIBLE);
	}
	
}

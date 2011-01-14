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
import android.widget.Toast;

public class AddAccount extends Activity {
	private Button btnConfirm;
	private Button btnCoustom;
	private EditText etAccount;
	private EditText etCoustomPwd;

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
						.setTitle("Confirm Dialog")
						.setMessage("Add Account success!")
						.setPositiveButton("OK",
								new DialogInterface.OnClickListener() {

									public void onClick(DialogInterface dialog,
											int which) {
										// Turn to AccountInfo
										String strAccount = etAccount.getText()
												.toString();
										String strCoustomPwd = etCoustomPwd
												.getText().toString();
										Intent intent = new Intent();
										intent.putExtra("Account", strAccount);
										intent.putExtra("CoustomPwd",
												strCoustomPwd);
										intent.setClass(AddAccount.this,
												AccountInfo.class);
										AddAccount.this.startActivity(intent);
										finish();
									}
								}).show();
			}
		});

		// 设置底部自定义按钮显示
		btnCoustom = (Button) this.findViewById(R.id.btnCoustom);
		btnCoustom.setText("增加账户");
		btnCoustom.setVisibility(View.VISIBLE);
	}

}

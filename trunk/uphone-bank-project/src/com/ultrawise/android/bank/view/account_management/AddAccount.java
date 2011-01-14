package com.ultrawise.android.bank.view.account_management;

import com.ultrawise.android.bank.view.transfer.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class AddAccount extends Activity {
	private Button btnConfrim;
	private Button btnCoustom;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.add_account);
		btnConfrim = (Button)this.findViewById(R.id.btnAddAccConfrim);
		btnConfrim.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				//弹出对话框提示用户已经成功添加
				Intent intent = new Intent();
				intent.setClass(AddAccount.this, AccountInfo.class);
				//AddAccount.this.startActivity(intent);
			}
		});
		//设置底部自定义按钮显示
		btnCoustom = (Button)this.findViewById(R.id.btnCoustom);
		btnCoustom.setText("增加账户");
		btnCoustom.setVisibility(View.VISIBLE);
	}

}

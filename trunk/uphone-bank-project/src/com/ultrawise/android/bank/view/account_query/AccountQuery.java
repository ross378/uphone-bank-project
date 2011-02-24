package com.ultrawise.android.bank.view.account_query;

import com.ultrawise.android.bank.view.transfer.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;

public class AccountQuery extends Activity {
	private Spinner spinner = null;
	private ArrayAdapter<String> adapter = null;
	private Spinner spinner2 = null;
	private ArrayAdapter<String> adapter2 = null;
	private TextView tvClassFirst = null;
	private TextView tvClassSecond = null;
	private ImageView btnReturn = null;
	private Intent intent = null;
	private Button btnContinue = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.account_query);
		adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		adapter.add("储蓄卡");
		adapter.add("信用卡");
		spinner = (Spinner) findViewById(R.id.spinnerAccTyp);
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				Spinner spinner = (Spinner) parent;
				Log.v("Test", "id = " + id + "("
						+ spinner.getSelectedItem().toString() + ")");

				if (spinner.getSelectedItem().toString() == "信用卡") {
					Log.v("Test", "信用卡");
					// 储蓄账户加载
					adapter2 = new ArrayAdapter<String>(AccountQuery.this,android.R.layout.simple_spinner_item);
					adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
					adapter2.add("信用卡99xxxx");
					adapter2.add("dddd");
					adapter2.add("eeeee");
					adapter2.add("ppppppp");
					spinner2 = (Spinner) findViewById(R.id.spinnerAcc);
					spinner2.setAdapter(adapter2);
					spinner2.setOnItemSelectedListener(new OnItemSelectedListener() {

								public void onItemSelected(
										AdapterView<?> parent, View view,
										int position, long id) {
									Spinner spinner = (Spinner) parent;
									Log.v("Test", "id = "+ id+ "("+ spinner.getSelectedItem().toString() + ")");
								}

								public void onNothingSelected(
										AdapterView<?> arg0) {
								}
							});
					// 结束储蓄用户绑定
				} else if (spinner.getSelectedItem().toString() == "储蓄卡") {
					Log.v("Test", "信用卡");
					// 储蓄账户加载
					adapter2 = new ArrayAdapter<String>(AccountQuery.this,
							android.R.layout.simple_spinner_item);
					adapter2
							.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
					adapter2.add("611111111111");
					adapter2.add("222222222222222");
					adapter2.add("333333333");
					adapter2.add("444444444");
					spinner2 = (Spinner) findViewById(R.id.spinnerAcc);
					spinner2.setAdapter(adapter2);
					spinner2.setOnItemSelectedListener(new OnItemSelectedListener() {

								public void onItemSelected(
										AdapterView<?> parent, View view,
										int position, long id) {
									Spinner spinner = (Spinner) parent;
									Log.v("Test", "id = "+ id+ "("+ spinner.getSelectedItem().toString() + ")");
								}

								public void onNothingSelected(
										AdapterView<?> arg0) {
								}
							});
					// 结束信用卡用户绑定
				}
			}

			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});
		// 跳转到前一个界面
		intent = new Intent();
		tvClassFirst = (TextView) this.findViewById(R.id.class_first);
		tvClassFirst.setText("账户查询");
		tvClassFirst.setVisibility(View.VISIBLE);

		// 设置最下面的首页
		ImageView iv_now = (ImageView) this.findViewById(R.id.btnCoustom);
		iv_now.setVisibility(View.VISIBLE);
		// 返回键设定
		btnReturn = (ImageView) this.findViewById(R.id.returnToPre);
		btnReturn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				onBackPressed();
				finish();
			}
		});

		// 继续按钮跳转
		btnContinue = (Button) findViewById(R.id.btnContinue);
		// System.out.println("跳转");
		btnContinue.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(AccountQuery.this, AccountQueryType.class);
				AccountQuery.this.startActivity(intent);
			}
		});
	}

}

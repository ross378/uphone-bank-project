package com.ultrawise.android.bank.view.account_management;

import com.ultrawise.android.bank.view.ABankMain;
import com.ultrawise.android.bank.view.FinancialConsultation;
import com.ultrawise.android.bank.view.account_management.AccountInfoSelect.SpinnerSelectedListener;
import com.ultrawise.android.bank.view.transfer.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TableLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class ActiveAccountSelect extends Activity {

	private Spinner spnrSelectTpye;
	private ArrayAdapter<String> adapterType;
	private Spinner spnrSelectAcc;
	private ArrayAdapter<String> adapterAcc;
	private Button btnActive;
	protected Intent intent;
	private EditText dtPwd;
	protected boolean flag;
	private GestureDetector mGestureDetector;
	private TextView tvClassFirst;
	private TextView tvClassSecond;
	private TextView tvClassThrid;
	private ImageView btnReturn;
	private ImageView btnMain;
	private ImageView btnHelper;
	protected View view;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);// 去标题栏
		this.setContentView(R.layout.account_active_select);

		/**
		 * 下拉框，账户类型:spnrSelectTpye，账户：spnrSelectAcc
		 */
		spnrSelectTpye = (Spinner) findViewById(R.id.accAct_SpnrSelectType);
		// 将可选内容与ArrayAdapter连接起来
		String[] accTypeArray = this.getResources().getStringArray(
				R.array.accinfo_accType);
		adapterType = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, accTypeArray);
		// 设置下拉列表的风格
		adapterType
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// 将adapter 添加到spinner中
		spnrSelectTpye.setAdapter(adapterType);
		// 添加事件Spinner事件监听
		spnrSelectTpye.setOnItemSelectedListener(new SpinnerSelectedListener());

		spnrSelectAcc = (Spinner) this.findViewById(R.id.accAct_SpnrSelectAcc);
		String[] accArray = this.getResources().getStringArray(
				R.array.accinfo_acc);
		adapterAcc = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, accArray);
		adapterAcc
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnrSelectAcc.setAdapter(adapterAcc);
		spnrSelectAcc.setOnItemSelectedListener(new SpinnerSelectedListener());
		spnrSelectAcc.setClickable(false);

		// 获取输入的密码
		dtPwd = (EditText) this.findViewById(R.id.accAct_dtPwd);
		String password = dtPwd.getText().toString();

		// 按钮 激活
		btnActive = (Button) this.findViewById(R.id.accAct_btnActive);
		btnActive.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				// 从服务器比较密码是否正确

				// 获取选中的账户
				String account = spnrSelectAcc.getSelectedItem().toString();
				// 弹出对话框
				flag=true;
				if (flag == true) {
					AlertDialog myDialog=new AlertDialog.Builder(ActiveAccountSelect.this)
							.setMessage("账户" + account + "已成功绑定")
							.setPositiveButton("确定",
									new DialogInterface.OnClickListener() {

										public void onClick(
												DialogInterface dialog,
												int which) {

											Toast.makeText(
													ActiveAccountSelect.this,
													"绑定成功", Toast.LENGTH_SHORT)
													.show();
											finish();
										}
									}).show();
					
					
//					myDialog.getWindow().setContentView(R.layout.dialog);
				} else {
					new AlertDialog.Builder(ActiveAccountSelect.this)
							.setMessage("密码错误，绑定失败")
							.setPositiveButton("确定",
									new DialogInterface.OnClickListener() {

										public void onClick(
												DialogInterface dialog,
												int which) {

											Toast.makeText(
													ActiveAccountSelect.this,
													"未绑定", Toast.LENGTH_SHORT)
													.show();
											dialog.dismiss();
										}
									}).show();
				}

			}

		});

		// 向右滑动触发后退
		mGestureDetector = new GestureDetector(this,
				new GestureDetector.SimpleOnGestureListener() {
					@Override
					public boolean onScroll(MotionEvent e1, MotionEvent e2,
							float distanceX, float distanceY) {
						// TODO Auto-generated method stub
						if (distanceY == 0 && distanceX < 0)
							onBackPressed();

						return super.onScroll(e1, e2, distanceX, distanceY);
					}
				});

		// 设置层级关系
		tvClassFirst = (TextView) this.findViewById(R.id.class_first);
		tvClassFirst.setText("首页>");
		tvClassFirst.setVisibility(View.VISIBLE);
		tvClassFirst.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				intent = new Intent();
				intent.setClass(ActiveAccountSelect.this, ABankMain.class);
				ActiveAccountSelect.this.startActivity(intent);
			}
		});

		tvClassSecond = (TextView) this.findViewById(R.id.class_second);
		tvClassSecond.setText("账户管理>");
		tvClassSecond.setVisibility(View.VISIBLE);
		tvClassSecond.setClickable(true);
		tvClassSecond.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				intent = new Intent();
				intent.setClass(ActiveAccountSelect.this,
						AccountManagementList.class);
				ActiveAccountSelect.this.startActivity(intent);

			}
		});

		tvClassThrid = (TextView) this.findViewById(R.id.class_third);
		tvClassThrid.setText("账户绑定");
		tvClassThrid.setVisibility(View.VISIBLE);

		// 返回键设定
		btnReturn = (ImageView) this.findViewById(R.id.returnToPre);
		btnReturn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				onBackPressed();
				finish();
			}
		});
		// 底部按钮设置
		btnMain = (ImageView) this.findViewById(R.id.btnMain);
		btnMain.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				intent = new Intent();
				intent.setClass(ActiveAccountSelect.this, ABankMain.class);
				ActiveAccountSelect.this.startActivity(intent);
			}
		});

		btnHelper = (ImageView) this.findViewById(R.id.btnHelper);
		btnHelper.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				intent = new Intent();
				intent.setClass(ActiveAccountSelect.this, FinancialConsultation.class);
				ActiveAccountSelect.this.startActivity(intent);
			}
		});
	}

	// 选择下拉框选项触发
	class SpinnerSelectedListener implements OnItemSelectedListener {

		public void onItemSelected(AdapterView<?> parent, View view,
				int position, long id) {
			// TODO Auto-generated method stub
			switch (parent.getId()) {
			case R.id.accAct_SpnrSelectType:
				spnrSelectTpye.setSelection(position);
				spnrSelectAcc.setClickable(true);
				break;
			case R.id.accAct_SpnrSelectAcc:
				spnrSelectAcc.setSelection(position);
				break;
			}
			switch (view.getId()) {

			}

		}

		public void onNothingSelected(AdapterView<?> parent) {
			// TODO Auto-generated method stub

		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		return mGestureDetector.onTouchEvent(event);

	}

}

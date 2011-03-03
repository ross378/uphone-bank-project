package com.ultrawise.android.bank.view.account_management;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

import com.ultrawise.android.bank.consum_webservices.AccManaConWebservices;
import com.ultrawise.android.bank.view.ABankMain;
import com.ultrawise.android.bank.view.FinancialConsultation;
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
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class AccountAdd extends Activity {

	private Spinner spnrSelectTpye;
	private ArrayAdapter<String> adapterType;
	private Button btnAdd;
	protected EditText dtAcc;
	protected EditText dtNickName;
	protected EditText dtPwd;
	protected boolean flag;
	private GestureDetector mGestureDetector;
	private TextView tvClassFirst;
	protected Intent intent;
	private TextView tvClassSecond;
	private TextView tvClassThrid;
	private TextView tvClassFour;
	private ImageView btnMain;
	private ImageView btnHelper;
	private ImageView btnReturn;
	//和服务器连接有关
	private AccManaConWebservices amConWebservice = new AccManaConWebservices();
	private List<String> lstOut = new ArrayList();
	
	public void getData(){
		// 从服务器获取所需数据
	// 获取账户类型
		
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);// 去标题栏
		this.setContentView(R.layout.account_add);

		

		// 从服务器获取用户号
		String userNo = "303249578";
		((TextView) this.findViewById(R.id.accAdd_tvUser2)).setText(userNo);

		/**
		 * 下拉框，账户类型:spnrSelectTpye，账户：spnrSelectAcc
		 */
		spnrSelectTpye = (Spinner) findViewById(R.id.accAdd_SpnrSelectType);
		// 将可选内容与ArrayAdapter连接起来
//		String[] accTypeArray = this.getResources().getStringArray(
//				R.array.accinfo_accType);
		adapterType = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, amConWebservice.connectHttp("0102", lstOut));
		// 设置下拉列表的风格
		adapterType
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// 将adapter 添加到spinner中
		spnrSelectTpye.setAdapter(adapterType);
		// 添加事件Spinner事件监听
		spnrSelectTpye.setOnItemSelectedListener(new SpinnerSelectedListener());

		// 按钮 添加
		btnAdd = (Button) this.findViewById(R.id.accAdd_btnAdd);
		btnAdd.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// 获取输入的账户类型
				String accTpyeValue = spnrSelectTpye.getSelectedItem()
						.toString();
				// 获取输入的账户
				dtAcc = (EditText) AccountAdd.this
						.findViewById(R.id.accAdd_dtAcc);
				String accountValue = dtAcc.getText().toString();
				// 获取输入的账户别名
				dtNickName = (EditText) AccountAdd.this
						.findViewById(R.id.accAdd_dtNickName);
				String nickName = dtNickName.getText().toString();
				// 获取输入的密码
				dtPwd = (EditText) AccountAdd.this
						.findViewById(R.id.accAdd_dtPwd);
				String pwdValue = dtPwd.getText().toString();
				// 向服务器添加账户

				// 弹出对话框
				flag = true;
				if (flag == true) {
					new AlertDialog.Builder(AccountAdd.this)
							.setTitle("成功提示：")
							.setMessage("账户添加成功")
							.setPositiveButton("确定",
									new DialogInterface.OnClickListener() {

										public void onClick(
												DialogInterface dialog,
												int which) {

											Toast.makeText(AccountAdd.this,
													"操作完成", Toast.LENGTH_SHORT)
													.show();
											dialog.dismiss();
											// 清空数据
											dtAcc.setText("");
											dtNickName.setText("");
											dtPwd.setText("");
										}
									}).show();
				} else {
					new AlertDialog.Builder(AccountAdd.this)
							.setTitle("失败提示：")
							.setMessage("添加账户已存在或输入输入信息有误")
							.setPositiveButton("确定",
									new DialogInterface.OnClickListener() {

										public void onClick(
												DialogInterface dialog,
												int which) {

											Toast.makeText(AccountAdd.this,
													"操作完成", Toast.LENGTH_SHORT)
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
		// 层级关系
		tvClassFirst = (TextView) this.findViewById(R.id.class_first);
		tvClassFirst.setText("首页>");
		tvClassFirst.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				intent = new Intent();
				intent.setClass(AccountAdd.this, ABankMain.class);
				AccountAdd.this.startActivity(intent);
			}
		});
		tvClassSecond = (TextView) this.findViewById(R.id.class_second);
		tvClassSecond.setText("账户管理>");
		tvClassSecond.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				intent = new Intent();
				intent.setClass(AccountAdd.this, AccountManagementList.class);
				AccountAdd.this.startActivity(intent);
			}
		});
		tvClassThrid = (TextView) this.findViewById(R.id.class_third);
		tvClassThrid.setText("添加账户");

		tvClassFirst.setVisibility(View.VISIBLE);
		tvClassSecond.setVisibility(View.VISIBLE);
		tvClassThrid.setVisibility(View.VISIBLE);

		// set bottom button

		btnMain = (ImageView) this.findViewById(R.id.btnMain);
		btnMain.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				intent = new Intent();
				intent.setClass(AccountAdd.this, ABankMain.class);
				AccountAdd.this.startActivity(intent);
			}
		});

		btnHelper = (ImageView) this.findViewById(R.id.btnHelper);
		btnHelper.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				intent = new Intent();
				intent.setClass(AccountAdd.this, FinancialConsultation.class);
				AccountAdd.this.startActivity(intent);
			}
		});

		// 返回键设定
		btnReturn = (ImageView) this.findViewById(R.id.returnToPre);
		btnReturn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				onBackPressed();
				finish();
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

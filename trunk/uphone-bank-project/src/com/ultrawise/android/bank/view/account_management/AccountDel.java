package com.ultrawise.android.bank.view.account_management;

import com.ultrawise.android.bank.view.ABankMain;
import com.ultrawise.android.bank.view.FinancialConsultation;
import com.ultrawise.android.bank.view.account_management.AccountInfoSelect.SpinnerSelectedListener;
import com.ultrawise.android.bank.view.transfer.R;

import android.app.Activity;
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
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;

public class AccountDel extends Activity {

	/**
	 * 静态变量，用于指示作用
	 */
	private final static String TAG = "AccountInfoSelect";
	public final static String ACCOUNT_TYPE="accountType";
	public final static String ACCOUNT="account";
	private Spinner spnrSelectTpye;
	private ArrayAdapter<String> adapterType;
	private Spinner spnrSelectAcc;
	private ArrayAdapter<String> adapterAcc;
	private Button btnGoOn;
	protected Intent intent;
	private GestureDetector mGestureDetector;
	private TextView tvClassFirst;
	private TextView tvClassSecond;
	private TextView tvClassThrid;
	private ImageView btnReturn;
	private ImageView btnMain;
	private ImageView btnHelper;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);// 去标题栏
		this.setContentView(R.layout.account_info_select);

		/**
		 * 下拉框，账户类型:spnrSelectTpye，账户：spnrSelectAcc
		 */
		spnrSelectTpye = (Spinner) findViewById(R.id.accinfo_SpnrSelectType);
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

		spnrSelectAcc = (Spinner) this.findViewById(R.id.accinfo_SpnrSelectAcc);
		String[] accArray = this.getResources().getStringArray(
				R.array.accinfo_acc);
		adapterAcc = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, accArray);
		adapterAcc
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnrSelectAcc.setAdapter(adapterAcc);
		spnrSelectAcc.setOnItemSelectedListener(new SpinnerSelectedListener());
		spnrSelectAcc.setClickable(false);

		// 按钮 继续
		btnGoOn = (Button) this.findViewById(R.id.accinfo_btnGoOn);
		btnGoOn.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				// TODO Auto-generated method stub
				intent=new Intent();
				intent.putExtra(AccountInfoSelect.ACCOUNT_TYPE, spnrSelectTpye.getSelectedItem().toString());
				intent.putExtra(AccountInfoSelect.ACCOUNT, spnrSelectAcc.getSelectedItem().toString());
				intent.setClass(AccountDel.this, AccountDelInfo.class);
				AccountDel.this.startActivity(intent);
			}
			
		});
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
		
		// 设置层级关系
		tvClassFirst = (TextView) this.findViewById(R.id.class_first);
		tvClassFirst.setText("首页>");
		tvClassFirst.setVisibility(View.VISIBLE);
		tvClassFirst.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				intent=new Intent();
				intent.setClass(AccountDel.this, ABankMain.class);
				AccountDel.this.startActivity(intent);
			}
		});

		tvClassSecond = (TextView) this.findViewById(R.id.class_second);
		tvClassSecond.setText("账户管理>");
		tvClassSecond.setVisibility(View.VISIBLE);
		tvClassSecond.setClickable(true);
		tvClassSecond.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				intent=new Intent();
				intent.setClass(AccountDel.this, AccountManagementList.class);
				AccountDel.this.startActivity(intent);

			}
		});

		tvClassThrid = (TextView) this.findViewById(R.id.class_third);
		tvClassThrid.setText("删除账户");
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
				intent = AccountDel.this.getIntent();
				intent.setClass(AccountDel.this, ABankMain.class);
				AccountDel.this.startActivity(intent);
			}
		});

		btnHelper = (ImageView) this.findViewById(R.id.btnHelper);
		btnHelper.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				intent = AccountDel.this.getIntent();
				intent.setClass(AccountDel.this, FinancialConsultation.class);
				AccountDel.this.startActivity(intent);
			}
		});
	}
	//触摸触发
	@Override
	public boolean onTouchEvent(MotionEvent event) {

		return mGestureDetector.onTouchEvent(event);

	}

	//选择下拉框选项触发
	class SpinnerSelectedListener implements OnItemSelectedListener {

		public void onItemSelected(AdapterView<?> parent, View view,
				int position, long id) {
			// TODO Auto-generated method stub
			switch (parent.getId()) {
			case R.id.accinfo_SpnrSelectType:
				spnrSelectTpye.setSelection(position);
				spnrSelectAcc.setClickable(true);
				break;
			case R.id.accinfo_SpnrSelectAcc:
				spnrSelectAcc.setSelection(position);
				break;
			}
			switch(view.getId()){
				
			}

		}

		public void onNothingSelected(AdapterView<?> parent) {
			// TODO Auto-generated method stub

		}
	}

}

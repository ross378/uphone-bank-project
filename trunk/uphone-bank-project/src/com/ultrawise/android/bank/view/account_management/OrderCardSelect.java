package com.ultrawise.android.bank.view.account_management;

import java.util.ArrayList;
import java.util.List;

import com.ultrawise.android.bank.consum_webservices.AccManaConWebservices;
import com.ultrawise.android.bank.view.ABankMain;
import com.ultrawise.android.bank.view.FinancialConsultation;
import com.ultrawise.android.bank.view.account_management.ActiveAccountSelect.SpinnerSelectedListener;
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
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class OrderCardSelect extends Activity {

	private Spinner spnrSelectTpye;
	private ArrayAdapter<String> adapterType;
	private Spinner spnrSelectAcc;
	private ArrayAdapter<String> adapterAcc;
	private View btnNext;
	protected boolean flag;
	protected Intent intent;
	private String accountTypeValue;
	private String accountValue;
	private TextView tvClassFirst;
	private TextView tvClassSecond;
	private TextView tvClassThrid;
	private ImageView btnReturn;
	private GestureDetector mGestureDetector;
	private ImageView btnMain;
	private ImageView btnHelper;

	/**
	 * 静态变量，用于intent传输中方便使用
	 */
	public final static String ACCOUNT_TYPE = "accountType";
	public final static String ACCOUNT = "account";
	public final static String CHANGE_REASON = "changeReason";
	public final static String NET = "net";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题栏
		setContentView(R.layout.account_order_card_select);

		/**
		 * 下拉框，账户类型:spnrSelectTpye，账户：spnrSelectAcc
		 */
		spnrSelectTpye = (Spinner) findViewById(R.id.accOrder_SpnrSelectType);
		// 将可选内容与ArrayAdapter连接起来
		adapterType = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item,
				AccManaConWebservices.connectHttp(this, "0101", null));
		// 设置下拉列表的风格
		adapterType
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// 将adapter 添加到spinner中
		spnrSelectTpye.setAdapter(adapterType);
		// 添加事件Spinner事件监听
		spnrSelectTpye.setOnItemSelectedListener(new SpinnerSelectedListener());

		spnrSelectAcc = (Spinner) this
				.findViewById(R.id.accOrder_SpnrSelectAcc);
		adapterAcc = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item);
		adapterAcc
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnrSelectAcc.setAdapter(adapterAcc);
		spnrSelectAcc.setOnItemSelectedListener(new SpinnerSelectedListener());
		spnrSelectAcc.setClickable(false);

		// 按钮 激活
		btnNext = (Button) this.findViewById(R.id.accOrder_btnNextTo2);
		btnNext.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// 获取账户类型和账户号
				accountTypeValue = spnrSelectTpye.getSelectedItem().toString();
				accountValue = spnrSelectAcc.getSelectedItem().toString();
				// TODO 跳转至选择换卡原因和换卡网点的页面
				intent = new Intent();
				intent.putExtra(OrderCardSelect.ACCOUNT_TYPE, accountTypeValue);
				intent.putExtra(OrderCardSelect.ACCOUNT, accountValue);
				intent.setClass(OrderCardSelect.this, OrderCardSelect2.class);
				OrderCardSelect.this.startActivity(intent);

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
				intent.setClass(OrderCardSelect.this, ABankMain.class);
				OrderCardSelect.this.startActivity(intent);
			}
		});

		tvClassSecond = (TextView) this.findViewById(R.id.class_second);
		tvClassSecond.setText("账户管理>");
		tvClassSecond.setVisibility(View.VISIBLE);
		tvClassSecond.setClickable(true);
		tvClassSecond.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				intent = new Intent();
				intent.setClass(OrderCardSelect.this,
						AccountManagementList.class);
				OrderCardSelect.this.startActivity(intent);

			}
		});

		tvClassThrid = (TextView) this.findViewById(R.id.class_third);
		tvClassThrid.setText("预约换卡");
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
				intent.setClass(OrderCardSelect.this, ABankMain.class);
				OrderCardSelect.this.startActivity(intent);
			}
		});

		btnHelper = (ImageView) this.findViewById(R.id.btnHelper);
		btnHelper.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				intent = new Intent();
				intent.setClass(OrderCardSelect.this,
						FinancialConsultation.class);
				OrderCardSelect.this.startActivity(intent);
			}
		});
	}

	// 选择下拉框选项触发
	class SpinnerSelectedListener implements OnItemSelectedListener {

		public void onItemSelected(AdapterView<?> parent, View view,
				int position, long id) {
			// TODO Auto-generated method stub
			switch (parent.getId()) {
			case R.id.accOrder_SpnrSelectType:
				String accTypeName = spnrSelectTpye.getSelectedItem()
						.toString();
				List<String> lstOut = new ArrayList<String>();
				lstOut.clear();
				// lstOut.add(UserLogin.userNO);// 用户号
				lstOut.add("Sun01");
				lstOut.add(accTypeName);
				List<String> lstAcc = AccManaConWebservices.connectHttp(
						OrderCardSelect.this, "0113", lstOut);// 从服务器获取账户
				adapterAcc.clear();
				if (lstAcc.size() != 0) {
					for (String s : lstAcc) {
						adapterAcc.add(s);
					}
					spnrSelectAcc.setClickable(true);
				} else {
					spnrSelectAcc.setClickable(false);
				}
				spnrSelectAcc.setAdapter(adapterAcc);
				break;
			case R.id.accOrder_SpnrSelectAcc:

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

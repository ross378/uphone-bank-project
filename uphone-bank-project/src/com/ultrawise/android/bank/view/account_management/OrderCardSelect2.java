package com.ultrawise.android.bank.view.account_management;

import com.ultrawise.android.bank.view.ABankMain;
import com.ultrawise.android.bank.view.FinancialConsultation;
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

public class OrderCardSelect2 extends Activity {

	private Intent intent;
	private String strAccountTypeValue;
	private String strAccountValue;
	private Spinner spnrChangeReason;
	private ArrayAdapter<String> adapterType;
	private Spinner spnrSelectNet;
	private ArrayAdapter<String> adapterAcc;
	private Button btnNext;
	protected String strChangeReason;
	protected String strNet;
	private TextView tvClassFirst;
	private TextView tvClassSecond;
	private TextView tvClassThrid;
	private ImageView btnReturn;
	private GestureDetector mGestureDetector;
	private ImageView btnMain;
	private ImageView btnHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题栏
		setContentView(R.layout.account_order_card_select2);
		intent = OrderCardSelect2.this.getIntent();
		if (intent != null) {
			// 从预约换卡页面传来的数据中获取账户类型和账户号
			strAccountTypeValue = intent
					.getStringExtra(OrderCardSelect.ACCOUNT_TYPE);
			strAccountValue = intent.getStringExtra(OrderCardSelect.ACCOUNT);
			if (strAccountTypeValue != null && strAccountValue != null) {

			} else {
				// 进错页面了吧你
			}
		} else {
			// 错误的进入此界面
		}

		/**
		 * 下拉框，账户类型:spnrSelectTpye，账户：spnrSelectAcc
		 */
		spnrChangeReason = (Spinner) findViewById(R.id.accOrder_SpnrChangeReason);
		// 将可选内容与ArrayAdapter连接起来
		String[] reasonArray = this.getResources().getStringArray(
				R.array.accOrder_reasonArray);
		adapterType = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, reasonArray);
		// 设置下拉列表的风格
		adapterType
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// 将adapter 添加到spinner中
		spnrChangeReason.setAdapter(adapterType);
		// 添加事件Spinner事件监听
		spnrChangeReason
				.setOnItemSelectedListener(new SpinnerSelectedListener());

		spnrSelectNet = (Spinner) this
				.findViewById(R.id.accOrder_SpnrSelectNet);
		String[] netArray = this.getResources().getStringArray(
				R.array.list_bank_net);
		adapterAcc = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, netArray);
		adapterAcc
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnrSelectNet.setAdapter(adapterAcc);
		spnrSelectNet.setOnItemSelectedListener(new SpinnerSelectedListener());
		spnrSelectNet.setClickable(false);

		
		// 按钮 激活
		btnNext = (Button) this.findViewById(R.id.accOrder_btnNextTo3);
		btnNext.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO 跳转到显示预约还卡信息的页面
				// 获取换卡原因和换卡网点
				strChangeReason = spnrChangeReason.getSelectedItem().toString();
				strNet = spnrSelectNet.getSelectedItem().toString();
				intent = new Intent();
				intent.putExtra(OrderCardSelect.ACCOUNT_TYPE,
						strAccountTypeValue);
				intent.putExtra(OrderCardSelect.ACCOUNT, strAccountValue);
				intent.putExtra(OrderCardSelect.CHANGE_REASON, strChangeReason);
				intent.putExtra(OrderCardSelect.NET, strNet);
				intent.setClass(OrderCardSelect2.this, OrderCardShowInfo.class);
				OrderCardSelect2.this.startActivity(intent);
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
				intent.setClass(OrderCardSelect2.this, ABankMain.class);
				OrderCardSelect2.this.startActivity(intent);
			}
		});

		tvClassSecond = (TextView) this.findViewById(R.id.class_second);
		tvClassSecond.setText("账户管理>");
		tvClassSecond.setVisibility(View.VISIBLE);
		tvClassSecond.setClickable(true);
		tvClassSecond.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				intent = new Intent();
				intent.setClass(OrderCardSelect2.this, AccountManagementList.class);
				OrderCardSelect2.this.startActivity(intent);
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
				intent = OrderCardSelect2.this.getIntent();
				intent.setClass(OrderCardSelect2.this, ABankMain.class);
				OrderCardSelect2.this.startActivity(intent);
			}
		});

		btnHelper = (ImageView) this.findViewById(R.id.btnHelper);
		btnHelper.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				intent = OrderCardSelect2.this.getIntent();
				intent.setClass(OrderCardSelect2.this, FinancialConsultation.class);
				OrderCardSelect2.this.startActivity(intent);
			}
		});
	}

	// 选择下拉框选项触发
	class SpinnerSelectedListener implements OnItemSelectedListener {

		public void onItemSelected(AdapterView<?> parent, View view,
				int position, long id) {
			// TODO Auto-generated method stub
			switch (parent.getId()) {
			case R.id.accOrder_SpnrChangeReason:
				spnrChangeReason.setSelection(position);
				spnrSelectNet.setClickable(true);
				break;
			case R.id.accOrder_SpnrSelectNet:
				spnrSelectNet.setSelection(position);
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

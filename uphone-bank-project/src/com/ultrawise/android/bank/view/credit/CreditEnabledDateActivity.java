package com.ultrawise.android.bank.view.credit;

import com.ultrawise.android.bank.view.transfer.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
/**
 * 
 * @信用卡有效期选择对话框Activity
 *
 */
public class CreditEnabledDateActivity extends Activity {
	//客户姓名
	private String userName=null;
	//信用卡号
	private String creditNo=null;
	//证件类型选项号
	private int pakitPostion=0;
	//证件号
	private String pakitNo=null;
	//手机号码
	private String mobileNo=null;
	//固定电话
	private String phone=null;
	//信用卡有效期值
	private String dateEnable=null;
	//日期选择控件
	private DatePicker enabledDatePicker=null;
	//有效日期按钮
	private Button enabledDateButton=null;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.creditenableddate);
        //获得日期选择控件对象
        enabledDatePicker=(DatePicker)findViewById(R.id.enabledDatePicker);
        
        
        //获得有效期确定按钮对象,并设置其鼠标单击事件监听
        enabledDateButton=(Button)findViewById(R.id.enabledDateButton);
        enabledDateButton.setOnClickListener(new EnabledDateButtonListener());
        Intent intent=this.getIntent();
        userName=intent.getStringExtra("userName");
        creditNo=intent.getStringExtra("creditNo");
       // pakitPostion=Integer.parseInt(intent.getStringExtra("pakitPostion"));
    	pakitNo=intent.getStringExtra("pakitNo");
    	mobileNo=intent.getStringExtra("mobileNo");
    	phone=intent.getStringExtra("phone");
    	
        
	}
	class EnabledDateButtonListener implements OnClickListener{

		public void onClick(View arg0) {
			int year=enabledDatePicker.getYear();
			int month=enabledDatePicker.getMonth()+1;
			int dayOfMonth=enabledDatePicker.getDayOfMonth();
			System.out.println(year);
			System.out.println(month);
			System.out.println(dayOfMonth);
			
			dateEnable=year+"-"+month+"-"+dayOfMonth;
			Intent intent=new Intent();
			intent.putExtra("userName",userName);
			intent.putExtra("creditNo",creditNo);
			//intent.putExtra("pakitPostion",pakitPostion+"");
			intent.putExtra("pakitNo",pakitNo);
			intent.putExtra("mobileNo",mobileNo);
			intent.putExtra("phone",phone);
			intent.putExtra("dateEnable",dateEnable);
			
			intent.setClass(CreditEnabledDateActivity.this,ActivateCard.class);
			CreditEnabledDateActivity.this.startActivity(intent);
			
			
		}
		
	}
}

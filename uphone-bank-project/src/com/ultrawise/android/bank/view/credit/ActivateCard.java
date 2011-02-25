package com.ultrawise.android.bank.view.credit;

import com.ultrawise.android.bank.view.ABankMain;
import com.ultrawise.android.bank.view.transfer.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
/**
 * 
 * @author weijuan
 * @date 2011-1-18
 * 开卡布局Activity-ActivateCard
 *
 */
public class ActivateCard extends Activity {
	//开卡按钮
	private Button activateCardButton=null;
	//客户姓名输入框
	private EditText userNameEditText=null;
	//信用卡号输入框
	private EditText activateCardNumberEditText=null;
	//证件类型下拉框
	private Spinner pakitSpinner=null;
	//卡片有效期输入框
	private EditText creditEnabledDateText=null;
	//证件号输入框
	private EditText pakitNoEditText=null;
	//手机号码输入框
	private EditText mobileNoEdit=null;
	//固定电话输入框
	private EditText phoneNoEdit=null;
	//帐户密码输入框
	private EditText passwordEdit=null;
	
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
	//帐户密码
	private String accountPassword=null;
	//信用卡有效期值
	private String dateEnable=null;
	Intent intent;
	ImageView btnCoustom;
	ImageView btnMain;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activatecard);
        
        ImageView iv_now = (ImageView)this.findViewById(R.id.btnCoustom);
        iv_now.setVisibility(View.VISIBLE);
    	intent = new Intent();
        TextView tvCredit= (TextView)this.findViewById(R.id.class_first);
        tvCredit.setText("首页>信用卡>开卡");
        tvCredit.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				 intent.setClass(ActivateCard.this, CreditView.class);
				 ActivateCard.this.startActivity(intent);
			}
		});
        tvCredit.setVisibility(View.VISIBLE);
        
      //设置底部按钮
		btnCoustom = (ImageView) this.findViewById(R.id.btnCoustom);
		btnCoustom.setImageResource(R.drawable.cardbg_sy_b);
		btnCoustom.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				intent.setClass(ActivateCard.this, ABankMain.class);
				ActivateCard.this.startActivity(intent);
			}
		});
		btnCoustom.setVisibility(View.VISIBLE);
		
		btnMain = (ImageView) this.findViewById(R.id.btnMain);
		btnMain.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				intent.setClass(ActivateCard.this, ABankMain.class);
				ActivateCard.this.startActivity(intent);
			}
		});
		
        //获得开卡按钮对象，并设置其鼠标单击事件监听
        activateCardButton=(Button)findViewById(R.id.activateCard1);
        activateCardButton.setOnClickListener(new ActivateCardButtonListener());
        
        //获得信用卡号输入框对象
        activateCardNumberEditText=(EditText)findViewById(R.id.creditNoEdit);
        //获得客户姓名输入框对象
        userNameEditText=(EditText)findViewById(R.id.userNameEdit);
        
        //获得卡片有效期至输入框对象，并设置其鼠标单击事件监听
        creditEnabledDateText=(EditText)findViewById(R.id.creditEnabledDateEdit);
        creditEnabledDateText.setOnClickListener(new CreditEnabledDateTextListener());
        
        //获得手机号码输入框对象
        mobileNoEdit=(EditText)findViewById(R.id.mobileNoEdit);
        //获得固定电话输入框对象
        phoneNoEdit=(EditText)findViewById(R.id.phoneNoEdit);
        //获得帐户密码输入框对象
        passwordEdit=(EditText)findViewById(R.id.creditpasswordEdit);
        //获得证件号输入框对象
        pakitNoEditText=(EditText)findViewById(R.id.pakitNoEditText);
        
        
        Intent intent=this.getIntent();
        if(intent!=null){
        	userName=intent.getStringExtra("userName");
        	creditNo=intent.getStringExtra("creditNo");
        	//pakitPostion=Integer.parseInt(intent.getStringExtra("pakitPostion"));
        	pakitNo=intent.getStringExtra("pakitNo");
        	mobileNo=intent.getStringExtra("mobileNo");
        	phone=intent.getStringExtra("phone");
        	accountPassword=intent.getStringExtra("accountPassword");
        	dateEnable=intent.getStringExtra("dateEnable");
        	userNameEditText.setText(userName);
        	activateCardNumberEditText.setText(creditNo);
        	creditEnabledDateText.setText(dateEnable);
        	pakitNoEditText.setText(pakitNo);
        	mobileNoEdit.setText(mobileNo);
        	phoneNoEdit.setText(phone);
        	//pakitSpinner.setSelection(pakitPostion,true);
        	
        }
    	
        //初始化证件类型控件值
       // final String[] arrs=new String[]{"身份证","学生证","工作证","军人证"};
        pakitSpinner= (Spinner)findViewById(R.id.pakitSpinner); 
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,R.array.pakitvalue);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);   

        pakitSpinner.setAdapter(adapter);  

        pakitSpinner.setSelection(1,true);

         pakitSpinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){

                public void onItemSelected(AdapterView<?> parent, View arg1, int position, long arg3){


                	//pakitPostion=parent.getSelectedItemPosition();
                	parent.setVisibility(View.VISIBLE);
                	


                }
                public void onNothingSelected(AdapterView<?> parent){
                	
                	
                }

            });

    }

        
	
	class ActivateCardButtonListener implements OnClickListener{

		public void onClick(View arg0) {
			userName=userNameEditText.getText().toString();
	        creditNo=activateCardNumberEditText.getText().toString();
	        
	    	dateEnable=creditEnabledDateText.getText().toString();
	    	String info=null;
	    	String flag=null;
	    	int activateFlag=0;
	    	if(userName==null ||userName.trim().length()==0||
	    			creditNo==null || creditNo.trim().length()==0||
	    			dateEnable==null || dateEnable.trim().length()==0){
	    			activateFlag=1;
	    			flag="对不起,开卡失败";
	    			info="用户名,信用卡号,信用卡有效期不能为空";
	    	}else{
	    		activateFlag=2;
	    		flag="恭喜您"+userName;
	    		info="您的卡号为"+creditNo+"的信用卡已经开通服务！\n";
	    	}
			Intent intent=new Intent();
			intent.putExtra("flag",flag);
			intent.putExtra("info",info);
			intent.putExtra("activateFlag",activateFlag+"");
			intent.setClass(ActivateCard.this,ActivateCardDialog.class);
			ActivateCard.this.startActivity(intent);
			
		}
		
	}
	/**
	 * 
	 * 信用卡有效期输入框单击响应
	 * 跳转到有效期选择对话框Activity-CreditEnabledDateActivity
	 *
	 */
	class CreditEnabledDateTextListener implements OnClickListener{

		public void onClick(View arg0) {
			Intent intent=new Intent();
			userName=userNameEditText.getText().toString();
	        creditNo=activateCardNumberEditText.getText().toString();
	    	pakitNo=pakitNoEditText.getText().toString();
	    	mobileNo=mobileNoEdit.getText().toString();
	    	phone=phoneNoEdit.getText().toString();
	        
	    	intent.putExtra("userName", userName);
	    	intent.putExtra("creditNo", creditNo);
	    	//intent.putExtra("pakitPostion", pakitPostion+"");
	    	
	    	intent.putExtra("pakitNo", pakitNo);
	    	intent.putExtra("mobileNo", mobileNo);
	    	intent.putExtra("phone", phone);
			intent.setClass(ActivateCard.this,CreditEnabledDateActivity.class);
			ActivateCard.this.startActivity(intent);
			
		}
		
	}
}

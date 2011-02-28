package com.ultrawise.android.bank.view;
import com.ultrawise.android.bank.view.ABankMain.CreditButtonListener;
import com.ultrawise.android.bank.view.DepositeRates.BackImageViewListener;
import com.ultrawise.android.bank.view.DepositeRates.PhoneBankImageViewListener;

import com.ultrawise.android.bank.view.credit.CreditView;
import com.ultrawise.android.bank.view.transfer.R;
import com.ultrawise.android.bank.view.transfer.TransResult;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
/**
 * 
 * @author weijuan
 * @Date 2011-1-18
 * 手机用户使用手机银行需要的登陆Activity-UserLogin
 *
 */
public class UserLogin extends Activity {
	//登陆按钮
	private Button userLoginButton=null;
	//用户名输入框
	private EditText nameEditText=null;
	//密码输入框
	private EditText passwdEditText=null;
	//附加码输入框
	private EditText pyramidEditText=null;
	//用户名
	private String userName=null;
	//密码
	private String passwd=null;
	//附加码
	private String pyramid=null;
	
	 private ImageView phoneBank = null;
	 private ImageView helper = null;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.userlogin);
	
        //获得用户名输入框，并获得输入框中的值
        nameEditText=(EditText)findViewById(R.id.nameEdit);
        
        
        //获得登陆按钮，并设置登陆按钮的鼠标响应事件
        userLoginButton=(Button)findViewById(R.id.userLogin);
       // userLoginButton.setVisibility(View.VISIBLE);
        userLoginButton.setOnClickListener(new UserLoginButtonListener());
        //获得用户名输入框，并获得输入框中输入的值
        passwdEditText=(EditText)findViewById(R.id.passwdEdit);
        
        
        //获得密码输入框，并获得输入框中输入的值
        pyramidEditText=(EditText)findViewById(R.id.pyramidEdit);
        
        phoneBank = (ImageView)findViewById(R.id.btnMain);
        phoneBank.setOnClickListener(new PhoneBankImageViewListener());
        helper = (ImageView)findViewById(R.id.btnHelper);
        helper.setOnClickListener(new HelperImageViewListener());
       
	}
	/**
	 * 登陆按钮响应
	 * @author Administrator
	 *跳转到登陆成功对话框Activity-UserLoginDialog
	 */
	class UserLoginButtonListener implements OnClickListener{

		public void onClick(View arg0) {
			String flag="手机银行";
			String info=null;
			int loginFlag=0;
			userName=nameEditText.getText().toString();
			passwd=passwdEditText.getText().toString();
			pyramid=pyramidEditText.getText().toString();
			
			//判断用户名，密码，附加码为空或输入空白
			if(userName==null || userName.trim().length()==0||
			   passwd==null || passwd.trim().length()==0||
			   pyramid==null || pyramid.trim().length()==0){
				info="用户名，密码，附加码不能为空!";
				loginFlag=1;
			//判断正确的用户名，密码
			}else if(userName.equals("zhangsan") && passwd.equals("123")){
				info="尊敬的客户"+userName+"\n您上次登陆的时间为\n"+"2010-1-13 16:20:20\n这是你第20次登录系统！\n";
				loginFlag=2;
			//判断错误的用户名，密码，附加码
			}else{
				info="用户名，密码，附加码错误!";
				loginFlag=3;
			}
			Intent intent=new Intent();
			if(loginFlag == 2)
			{
				intent.setClass(UserLogin.this,ABankMain.class);
				UserLogin.this.startActivity(intent);
			}else
			{
				intent.putExtra("flag", flag);
				intent.putExtra("info", info);
				intent.putExtra("loginFlag",loginFlag+"");
				intent.setClass(UserLogin.this,UserLoginDialog.class);
				UserLogin.this.startActivity(intent);
			}
			
			
		}
		
	}
	
 class PhoneBankImageViewListener implements OnClickListener{
		 
		 public void onClick(View args0){
			 UserLogin.this.finish();
			 Intent intent = new Intent();
			 intent.setClass(UserLogin.this, ABankMain.class);
			 UserLogin.this.startActivity(intent);
		 }
	 }
	 
	 class HelperImageViewListener implements OnClickListener{
		 
		 public void onClick(View args0){
			 UserLogin.this.finish();
			 Intent intent = new Intent();
			 intent.setClass(UserLogin.this, FinancialConsultation.class);
			 UserLogin.this.startActivity(intent);
		 }
	 }

}

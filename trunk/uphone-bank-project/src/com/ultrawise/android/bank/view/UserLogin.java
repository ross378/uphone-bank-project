package com.ultrawise.android.bank.view;

import com.ultrawise.android.bank.view.transfer.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

public class UserLogin extends Activity {
	private Button userLoginButton=null;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userlogin);
        userLoginButton=(Button)findViewById(R.id.userLogin);
        
	}
}

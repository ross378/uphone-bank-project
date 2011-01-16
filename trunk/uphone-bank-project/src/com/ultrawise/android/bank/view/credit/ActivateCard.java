package com.ultrawise.android.bank.view.credit;

import com.ultrawise.android.bank.view.transfer.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActivateCard extends Activity {
	private Button activateCardButton=null;
	private EditText activateCardNumberEditText=null;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activatecard);
        activateCardButton=(Button)findViewById(R.id.activateCard);
        activateCardButton.setOnClickListener(new ActivateCardButtonListener());
        activateCardNumberEditText=(EditText)findViewById(R.id.activateCardNumber);
	}
	class ActivateCardButtonListener implements OnClickListener{

		public void onClick(View arg0) {
			String creditNo=activateCardNumberEditText.getText().toString();
			Toast.makeText(ActivateCard.this, "恭喜你，你已经通过手机成功办理了卡号为"+creditNo+"信用卡的手机银行业务开通服务，此卡从现在起将不能再使用！", Toast.LENGTH_SHORT).show();
			
		}
		
	}
}

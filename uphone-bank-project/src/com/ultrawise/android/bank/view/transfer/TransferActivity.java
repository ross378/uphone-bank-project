package com.ultrawise.android.bank.view.transfer;

import com.ultrawise.android.bank.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

public class TransferActivity extends Activity {
	Button btn_ok = null;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transfer);
    }
}
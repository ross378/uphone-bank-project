package com.ultrawise.android.bank.view.transfer;

import com.ultrawise.android.bank.view.transfer.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

public class TransInfo extends Activity {
    /** Called when the activity is first created. */
	Button btn = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trans_ph_confirm);
    }
}
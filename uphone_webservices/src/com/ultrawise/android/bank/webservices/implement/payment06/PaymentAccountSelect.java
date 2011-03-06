package com.ultrawise.android.bank.webservices.implement.payment06;

import org.w3c.dom.Document;

import com.ultrawise.android.bank.webservices.base.payment06.IPamymentAccountSelect;

public class PaymentAccountSelect implements IPamymentAccountSelect{

	FileOperation file;
	public String getAccountTypeAndNum() {
		file=new FileOperation();
		file.getFileDocument("paypal");
	
		return null;
	}

}

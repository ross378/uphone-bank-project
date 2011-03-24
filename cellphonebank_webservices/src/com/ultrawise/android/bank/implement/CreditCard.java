package com.ultrawise.android.bank.implement;

import java.util.HashMap;

import com.ultrawise.android.bank.base.ITrans;

public class CreditCard extends Account implements ITrans {

	public HashMap<String, String> getPaymentHisInfo(String id,String paymentNam) {
		// TODO Auto-generated method stub
		return null;
	}

	public HashMap<String, String> getPaymentInfo(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public HashMap<String, String> getRechargeInfo(String paymentName,
			String paymentActNo, double paymentAmt, String paymentPhoneNum) {
		// TODO Auto-generated method stub
		return null;
	}

	public HashMap<String, String> getTargetPhoneInfo(String account,
			String password, String amtph, double amtnum) {
		// TODO Auto-generated method stub
		return null;
	}

	public HashMap<String,String> recharge(String paymentName,
			double paymentAmt, String paymentActNo, String paymentActPasswd,String paymentNum) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean setDetail(String serNo, String detail) {
		// TODO Auto-generated method stub
		return false;
	}

	public HashMap<String, String> transfeAct(String account, String password,
			String amtph, double amtnum) {
		// TODO Auto-generated method stub
		return null;
	}

}

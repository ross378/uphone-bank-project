package com.ultrawise.android.bank.implement;

import java.util.HashMap;

import com.ultrawise.android.bank.base.ITrans;

public class CurrentDeposit extends Account implements ITrans {

	public HashMap<String, String> getPaymentHisInfo(String id,String paymentNam) {
		HashMap<String,String>  paymentHisInfo = new HashMap<String, String>();
		if("1".equals(id) && "水费".equals(paymentNam)){
			paymentHisInfo.put("paymentNam", "水费");
			paymentHisInfo.put("paymentAmt", "300");
			paymentHisInfo.put("paymentTim", "2011-03-15");
			paymentHisInfo.put("paymentSer", "123565876");
			paymentHisInfo.put("paymentAct", "2222222222222");
		}
		return paymentHisInfo;
	}

	public HashMap<String, String> getPaymentInfo(String id) {
		HashMap<String,String> paymentInfo = new HashMap<String, String>();
		if("1".equals(id)){
			paymentInfo.put("paymentNam", "三月份水费");
			paymentInfo.put("paymentAmt", "300");
			paymentInfo.put("charge", "无锡自来水公司");
			paymentInfo.put("concordat", "3274198256");
			paymentInfo.put("deadline", "2010-12-31");
		}
		return paymentInfo;
	}

	public HashMap<String, String> getRechargeInfo(String paymentName,
			String paymentActNo, double paymentAmt, String paymentPhoneNum) {
		HashMap<String,String> recharfeInfo = new HashMap<String, String>();
		
		return recharfeInfo;
	}

	public HashMap<String, String> getTargetPhoneInfo(String account,
			String password, String amtph, double amtnum) {
		
		return null;
	}

	public HashMap<String, String> recharge(String paymentName,
			double paymentAmt, String paymentActNo, String paymentActPasswd,String paymentNum) {
		HashMap<String,String> recharge = new HashMap<String, String>();
		if("手机充值".equals(paymentName)){
			
		}
		return recharge;
	}

	public boolean setDetail(String serNo, String detail) {
		if("1".equals(serNo)){
			return true;
		}
		return false;
	}

	public HashMap<String, String> transfeAct(String account, String password,
			String amtph, double amtnum) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public HashMap<String, String> getComeQueryInfo(String id) {
		// TODO Auto-generated method stub
		return super.getComeQueryInfo(id);
	}
	
	@Override
	public HashMap<String, String> getListQueryInfo(String id) {
		// TODO Auto-generated method stub
		return super.getListQueryInfo(id);
	}
}

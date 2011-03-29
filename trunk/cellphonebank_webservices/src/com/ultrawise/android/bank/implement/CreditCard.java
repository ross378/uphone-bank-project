package com.ultrawise.android.bank.implement;

import java.util.HashMap;

import com.ultrawise.android.bank.base.ICreditCard;
import com.ultrawise.android.bank.base.IQuery;
import com.ultrawise.android.bank.base.ITrans;
import com.ultrawise.android.bank.base.IUpdate;
import com.ultrawise.bank.implement.dao.DataAccessModel;

public class CreditCard extends Account implements ITrans, IUpdate, ICreditCard {
	// -------转账---------
	public HashMap<String, String> getPaymentHisInfo(String id,
			String paymentNam) {
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

	public HashMap<String, String> recharge(String paymentName,
			double paymentAmt, String paymentActNo, String paymentActPasswd,
			String paymentNum) {
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
	
	public HashMap<String, String> getComeQueryInfo(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public HashMap<String, String> getListQueryInfo(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	// -------转账---------

	// -------更新---------
	public boolean deleAcc(String accNo) {
		// TODO Auto-generated method stub
		return DataAccessModel.newInstances().createUpdataTools().updata(
				"accout", "orderid", accNo, "bind", "否");

	}

	public boolean lossRegister(String accNo) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean setActActive(String accNo, String accPwd) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean setBind(String accNo) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean setNickName(String accNo, String name) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean setOrderCard(String accNo) {
		// TODO Auto-generated method stub
		return false;
	}

	// -------更新---------

	// -------信用卡专用---------
	public boolean creditRepayment(String account, String password,
			double payamt) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean destroyCard(HashMap<String, String> userInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	public HashMap<String, String> getCreditRepaymentInfor(String account) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean openCard(HashMap<String, String> userInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	// -------信用卡专用---------

	// -------查询---------
	@Override
	public boolean acctIsActive(String paymentActNo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public HashMap<String, String> getAccInfo(String acc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getNickName(String acc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<String, String> getOrderInfo(String acc) {
		// TODO Auto-generated method stub
		return null;
	}
	// -------查询---------
}

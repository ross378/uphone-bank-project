package com.ultrawise.android.bank.implement;

import java.util.HashMap;

import com.ultrawise.android.bank.base.ITrans;
import com.ultrawise.android.bank.base.IUpdate;

public class TimeDeposits extends Account implements IUpdate {

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

	// ----更新-----
	public boolean deleAcc(String accNo) {
		// TODO Auto-generated method stub
		return false;
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
	// ----更新-----
}

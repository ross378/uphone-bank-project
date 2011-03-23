package com.ultrawise.android.bank.implement;

import java.util.HashMap;
import java.util.Map;

import com.ultrawise.android.bank.base.IQuery;

public abstract class Account implements IQuery {

	public boolean acctIsActive(String paymentActNo) {
		// TODO Auto-generated method stub
		return false;
	}

	public HashMap<String, String> getAccInfo(String acc) {
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

	public String getNickName(String acc) {
		// TODO Auto-generated method stub
		return null;
	}

	public HashMap<String, String> getOrderInfo(String acc) {
		// TODO Auto-generated method stub
		return null;
	}
}

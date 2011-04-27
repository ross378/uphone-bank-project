package com.ultrawise.android.bank.implement;

import java.util.HashMap;
import java.util.Map;

import com.ultrawise.android.bank.base.IQuery;

public abstract class Account implements IQuery {
	protected static long transferId = 5;
	protected static long rechargeId = 4;
	protected static long paymentId = 2;
	// 这些方法应该是抽象的

	public abstract boolean acctIsActive(String paymentActNo);

	public abstract  HashMap<String, String> getAccInfo(String acc);

	public abstract String getNickName(String acc);

	public abstract HashMap<String, String> getOrderInfo(String acc);
}

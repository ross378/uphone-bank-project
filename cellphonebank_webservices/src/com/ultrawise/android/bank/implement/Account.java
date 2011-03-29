package com.ultrawise.android.bank.implement;

import java.util.HashMap;
import java.util.Map;

import com.ultrawise.android.bank.base.IQuery;

public abstract class Account implements IQuery {

	public abstract boolean acctIsActive(String paymentActNo);

	public abstract  HashMap<String, String> getAccInfo(String acc);

	public abstract String getNickName(String acc);

	public abstract HashMap<String, String> getOrderInfo(String acc);
}

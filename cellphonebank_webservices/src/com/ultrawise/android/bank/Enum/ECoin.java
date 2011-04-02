package com.ultrawise.android.bank.Enum;

import com.ultrawise.log.Log;

public enum ECoin {
	DOLLAR("美元"), RMB("人民币"), Euro("欧元"), ye("日元");

	private final String mName;

	private ECoin(String name) {
		mName = name;
	}

	public static ECoin getCoin(String name) {

		for (ECoin a : ECoin.values()) {
			if (a.mName.equals(name)) {
				return a;
			}
		}
		Log.getInstance().getLogger().error(
				"Enum Coin isn't find,pelse check the param from client");
		throw new NullPointerException("I can't find Enum Coin,so it is null");
	}

	public static String getCoinName(ECoin a) {
		return a.mName;
	}
}

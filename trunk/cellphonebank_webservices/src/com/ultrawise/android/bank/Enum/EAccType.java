package com.ultrawise.android.bank.Enum;

import com.ultrawise.log.Log;

public enum EAccType {
	CREDIT_CARD("信用卡"), CURRENT_DEPOSIT("活期储蓄卡"), TIME_DEPOSITS("定期储蓄卡");

	private final String mName;

	private EAccType(String name) {
		mName = name;
	}

	public static EAccType getEAccType(String accType) {
		for (EAccType a : EAccType.values()) {
			if (a.mName.equals(accType)) {
				return a;
			}
		}
		Log.getInstance().getLogger().error(
				"Enum AccType isn't find,pelse check the param from client");
		throw new NullPointerException(
				"I can't find Enum AccType,so it is null");
	}
}

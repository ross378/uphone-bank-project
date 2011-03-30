package com.ultrawise.android.bank.Enum;

import com.ultrawise.log.Log;

public enum TableName {
	WATERCOSTINFO("水费"),POWERCOSTINFO("电费"),GASCOSTINFO("煤气费"),
	HOUSERENDCOSEINFO("房租费"),QQCHARGEINFO("Q币充值"),PHONECHARGEINFO("手机充值"),
	WYCARDCHARGEINFO("网易充值");
	private final String mName;

	private TableName(String name) {
		mName = name;
	}

	public static TableName getTableName(String name) {

		for (TableName a : TableName.values()) {
			if (a.mName.equals(name)) {
				return a;
			}
		}
		Log.getInstance().getLogger().error(
				"Enum TableName isn't find,pelse check the param from client");
		throw new NullPointerException(
				"I can't find Enum TableName,so it is null");
	}

	public static String getTableName(TableName a) {
		return a.mName;
	}
}

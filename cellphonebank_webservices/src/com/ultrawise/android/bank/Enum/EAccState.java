package com.ultrawise.android.bank.Enum;

import com.ultrawise.log.Log;

public enum EAccState {

	BIND("bind"), UNBIND("unbind"), ACTIVE("active"), UNACTIVE("unactive"), LOSS(
			"loss"), UNLOSS("unloss"), ORDER("order"), UNORDER("unorder");

	private final String mName;

	private EAccState(String name) {
		mName = name;
	}

	public static EAccState getAccState(String name) {

		for (EAccState a : EAccState.values()) {
			if (a.mName.equals(name)) {
				return a;
			}
		}
		Log.getInstance().getLogger().error(
				"Enum AccState isn't find,pelse check the param from client");
		throw new NullPointerException(
				"I can't find Enum AccState,so it is null");
	}

	public static String getStateName(EAccState a) {
		return a.mName;
	}
}

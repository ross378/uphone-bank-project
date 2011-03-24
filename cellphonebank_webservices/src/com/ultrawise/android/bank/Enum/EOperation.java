package com.ultrawise.android.bank.Enum;

import com.ultrawise.log.Log;

public enum EOperation {
	/* 为每个功能设定功能号 */
	GET_ACC_TYPE("0101"), GET_ACC_INFO("0102"), GET_ACC_WITH_NICKNAME("0103");

	private final String mNum;

	private EOperation(String num) {
		mNum = num;
	}

	public static EOperation getEoperation(String num) {

		for (EOperation e : EOperation.values()) {
			if (e.mNum.equals(num)) {
				return e;
			}
		}
		Log.getInstance().getLogger().error(
				"Enum EOperation isn't find,pelse check the param from client");
		throw new NullPointerException(
				"I can't find Enum EOperation,so it is null");
	}

	public static String getOperNum(EOperation e) {
		return e.mNum;
	}
}

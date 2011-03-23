package com.ultrawise.android.bank.Enum;

public enum EOperation {
	/* 为每个功能设定功能号 */
	GET_ACC_TYPE("0101"), GET_ACC_INFO("0102");

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
		return null;
	}

	public static String getOperNum(EOperation e) {
		return e.mNum;
	}
}

package com.ultrawise.android.bank.webservices;

enum Eoperation {
	/* 为每个功能设定功能号 */
	GET_ACC_TYPE("0101"), GET_ACC_INFO("0102");

	private final String mNum;

	private Eoperation(String num) {
		mNum = num;
	}

	public static Eoperation getEoperation(String num) {

		for (Eoperation e : Eoperation.values()) {
			if (e.mNum.equals(num)) {
				return e;
			}
		}
		return null;
	}

	public static String getOperNum(Eoperation e) {
		return e.mNum;
	}
}

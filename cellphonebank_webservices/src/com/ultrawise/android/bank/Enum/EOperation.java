package com.ultrawise.android.bank.Enum;

import com.ultrawise.log.Log;

public enum EOperation {
	/* 储蓄卡 */
	GET_ACC_INFO("0601"),
	GET_TRARGETPHONEINFO("0301"),GET_TRANSFEACT("0302"),GET_LISTQUERYINFO("0303"),
	GET_COMEQUERYINFO("0304"),GET_SET_DETAIL("0305"),GET_PAYMENTHISINFO("0306"),
	GET_RECHARGE("0307"),GET_PAYMENTINFO("0308");
	/* 储蓄卡 */
	
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

package com.ultrawise.android.bank.Enum;

import java.util.ArrayList;
import java.util.List;

import com.ultrawise.log.Log;

public enum EOperation {
	/* 系统 */
	GET_ACC_TYPE("0101"), GET_ACC_WITH_NICKNAME("0103"), ADD_ACC("0104"), GET_ACC(
			"0105"), GET_ACC_TYPE_ALL("0106"), GET_ACC_TYPE_ON_CREDITCARD(
			"0107"), GET_COME_HISTORY("0108"), GET_COST("0109"), GET_CREDIT_CARD(
			"0110"), GET_EXTRA_CODE("0111"), GET_LIST_HISTORY("0112"), GET_NET(
			"0113"), GET_NET_ADDRESS("0114"), GET_OPERATOR("0115"), GET_PAYMENT_HISTORY(
			"0116"), GET_PAYMENT_INFO("0117"), GET_PAYMENT_NAME("0118"), GET_PAYMENT_NAME_ON_MANA(
			"0119"), GET_PRE_ACC("0120"), GET_SEL_SERVICE_NAME("0121"), GET_SEL_SERVICE_NAME_BY_USER_ID(
			"0122"), GET_USED_CREDIT_CARD("0123"), LOGIN("0124"), SET_PRE_ACC(
			"0125"), UPDATE_PAYMENT_STATE("0126"),
	/* 系统 */

	/* 储蓄卡 */
	GET_ACC_INFO("0601"), GET_TRARGETPHONEINFO("0301"), GET_TRANSFEACT("0302"), GET_LISTQUERYINFO(
			"0303"), GET_COMEQUERYINFO("0304"), GET_SET_DETAIL("0305"), GET_PAYMENTHISINFO(
			"0306"), GET_RECHARGE("0307"), GET_PAYMENTINFO("0308");
	/* 储蓄卡 */

	private final String mNum;

	private EOperation(String num) {
		mNum = num;
	}

	public static EOperation getEoperation(String num) {
		List<EOperation> list = new ArrayList<EOperation>();

		for (EOperation e : EOperation.values()) {
			if (e.mNum.equals(num)) {
				list.add(e);
			}

		}
		/* 检测是否为空 */
		if (list.size() == 0) {
			Log
					.getInstance()
					.getLogger()
					.error(
							"Enum EOperation isn't find,pelse check the param from client");
			throw new NullPointerException(
					"I can't find Enum EOperation,so it is null");
		}

		/* 如果有多个相同操作号，就报错。PS: 没有找到更合适的控制不能重复添加，只能延迟到代码执行的时候才知道 */
		if (list.size() > 1) {
			throw new IllegalArgumentException(
					"It's not client's fault.Operation has been here! don't input again");
		}
		return list.get(0);
	}

	public static String getOperNum(EOperation e) {
		return e.mNum;
	}
}

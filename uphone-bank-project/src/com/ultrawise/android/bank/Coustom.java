package com.ultrawise.android.bank;

import java.util.List;

public class Coustom {
	static String CoustomName = "何龙威";
	private static List<Account> accActivation;
	private static List<Account> accUnActivation;

	public static List<Account> getAccActivation() {
		if (accActivation == null) {
			// Activation
			Account acc1 = new Account("4403011988102821", "我的储蓄卡", "活期卡",
					true, "建设银行深圳市梅林支行", "2006/07/09");
			Account acc2 = new Account("5560654220320266", "我的信用卡", "活期卡",
					true, "建设银行深圳市梅林支行", "2007/08/08");
			Account acc3 = new Account("1122065468210030", "我的龙卡", "活期卡", true,
					"建设银行深圳市梅林支行", "2008/09/07");
			accActivation.add(acc1);
			accActivation.add(acc2);
			accActivation.add(acc3);
		}
		return accActivation;
	}

	public static List<Account> getAccUnActivation() {
		// UnActivation
		if (accUnActivation == null) {
			Account acc4 = new Account("3322019830320266", "我的第二信用卡", "活期卡",
					false, "建设银行深圳市梅林支行", "2009/10/06");
			Account acc5 = new Account("9574548189112338", "我的金卡", "活期卡",
					false, "建设银行深圳市梅林支行", "2010/11/05");
			accUnActivation.add(acc4);
			accUnActivation.add(acc5);
		}
		return accUnActivation;
	}
}

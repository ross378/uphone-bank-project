package com.ultrawise.android.bank;

public class Account {
	String accNum;
	String accNickName;
	String accType;// 账户类型
	boolean accActivation;// 账户是否激活
	String accOpenStation;// 开户行
	String accOpenDate;// 开户日

	public Account(String accNum, String accNickName, String accType,
			boolean accActivation, String accOpenStation, String accOpenDate) {
		this.accNum = accNum;
		this.accNickName = accNickName;
		this.accType = accType;
		this.accActivation = accActivation;
		this.accOpenStation = accOpenStation;
		this.accOpenDate = accOpenDate;
	}
}

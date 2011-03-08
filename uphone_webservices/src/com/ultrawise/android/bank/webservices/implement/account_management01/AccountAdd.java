package com.ultrawise.android.bank.webservices.implement.account_management01;

import org.w3c.dom.Document;

import com.ultrawise.android.bank.webservices.base.account_management01.IAccountAdd;

public class AccountAdd implements IAccountAdd {

	public String addAccount(String userNo, String accId, String accountType,
			String account, String accountNickName, String password) {
		// TODO Auto-generated method stub
		Document doc = All.readTxt("accout.txt");

		return "";
	}

}

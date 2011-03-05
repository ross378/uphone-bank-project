package com.ultrawise.android.bank.webservices.base.account_management01;

import java.util.List;

public interface INickName {

	/**
	 * 设置账户别名
	 * 
	 * @author hosolo
	 * @param account
	 * @param nickName
	 * @return true/flase
	 */
	public List<String> setNickName(String account, String nickName);

}

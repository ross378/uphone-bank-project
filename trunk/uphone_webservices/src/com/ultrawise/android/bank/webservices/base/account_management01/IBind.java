package com.ultrawise.android.bank.webservices.base.account_management01;

import java.util.List;

public interface IBind {

	/**
	 * 账户绑定
	 * 
	 * @author hosolo
	 * @param unbindAccount
	 * @param password
	 * @return true/false
	 */
	public boolean setBind(String unbindAccount, String password);

	/**
	 * 获取未绑定账户
	 * 
	 * @author hosolo
	 * @param UserNo
	 * @param accTypeName
	 * @return 一堆未绑定的账户
	 */
	public List<String> getUnBindAcc(String userNo, String accTypeName);
}

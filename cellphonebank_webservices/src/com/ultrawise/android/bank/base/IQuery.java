package com.ultrawise.android.bank.base;

import java.util.List;
import java.util.Map;

public interface IQuery {
	/**
	 * 查询所有账户类型
	 * 
	 * @return 账户类型列表
	 */
	public List<String> getAccType();

	/**
	 * 账户管理，通过账户号查找账户的信息
	 * 
	 * @param account
	 * @return 账户信息键值对
	 */
	public Map<String, String> getAccInfoOnAccMana(String account);
}

package com.ultrawise.android.bank.base;

import java.util.HashMap;

public interface ICreditCard {
	/**
	 * 信用卡开卡，需要用户名，信用卡号，有效期，证件号，手机号，固定电话，密码
	 * 
	 * @param userInfo
	 * @return 开卡是否成功
	 */
	public boolean openCard(String userName, String creditCardNo,
			String availbDate, String idNo, String cellPhone, String tel,
			String pwd);

	/**
	 * 信用卡销卡 需要用户名，信用卡号，证件号，手机号，密码
	 * 
	 * @param userInfo
	 * @return 销卡是否成功
	 */
	public boolean destroyCard(String userName, String creditCardNo,
			String idNo, String cellPhone, String pwd);

	/**
	 * 查询信用卡的还款信息
	 * 
	 * @param account
	 * @return 信用卡的还款信息
	 */
	public HashMap<String, String> getCreditRepaymentInfor(String account);

	/**
	 * 信用卡还款
	 * 
	 * @param account
	 * @param password
	 * @param payamt
	 * @return 是否还款成功
	 */
	public boolean creditRepayment(String account, String password,
			double payamt);
}

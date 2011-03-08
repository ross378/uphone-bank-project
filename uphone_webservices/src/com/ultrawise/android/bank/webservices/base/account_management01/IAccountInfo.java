package com.ultrawise.android.bank.webservices.base.account_management01;

import java.util.List;

import org.codehaus.jettison.json.JSONObject;

public interface IAccountInfo {
	/**
	 * 获取账户信息（不同的账户类型，返回不同的数据）
	 * 
	 * @author hosolo
	 * @param account
	 * @return account infomation
	 *         （包括账户类型，币种，（余额，储种，账户状态，是否激活，开户行，开户日）（存期，起息日，利率）（
	 *         信用额度，可用额度，预借现金可用额度，每月账单日，本期应还金额，本期最低还款，本期到期还款日）
	 */
	public List<String> getAccInfo(String account);
}

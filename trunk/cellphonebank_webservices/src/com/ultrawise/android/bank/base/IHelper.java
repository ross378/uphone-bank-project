package com.ultrawise.android.bank.base;

import java.util.HashMap;
import java.util.List;

public interface IHelper {
	/**
	 * 查询币种的类型
	 * @return    所有的币种类型
	 */
	public List<String> getMoneyType();
	
	/**
	 * 查询利率
	 * @param type     
	 * @return		        利率的详细信息
	 */
	public HashMap<String,String> getRate(String type);
	
	/**
	 * 货币的兑换
	 * @param currencyDenomination 		
	 * @param sourceCurrencyType		
	 * @param destinationCurrencyType	
	 * @return							兑换后的金额
	 */
	public double getExchangeResult(double currencyDenomination,String sourceCurrencyType,String destinationCurrencyType);
	
}

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
	
	/**
	 * 从服务器获取和模块有关的网点
	 * @param operNo		
	 * @return				所有的网点
	 */
	public List<String> getNet(String operNo);
	
	/**
	 * 查询某个网点的地址
	 * @param net	
	 * @return		该网点的地址
	 */
	public String getNetAddress(String net);
	
	/**
	 * 获取操作的工本费
	 * @param operNo   
	 * @return		        该操作的工本费
	 */
	public double getCast(String operNo);
}

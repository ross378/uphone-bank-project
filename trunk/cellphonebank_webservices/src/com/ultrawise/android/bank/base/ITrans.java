package com.ultrawise.android.bank.base;

import java.util.Date;
import java.util.HashMap;

public interface ITrans {
	/**
	 * 手机到手机的转账
	 * @param account	
	 * @param password	
	 * @param amtph		
	 * @param amtnum	
	 * @return			转账的详细信息	
	 */
	public HashMap<String,String> getTargetPhoneInfo(String account,String password,String amtph,double amtnum);
	
	/**
	 * 确认转账
	 * @param account	
	 * @param password	
	 * @param amtph		
	 * @param amtnum	
	 * @return			转账返回的详细信息
	 */
	public HashMap<String,String> transfeAct(String account,String password,String amtph,double amtnum);
	
	/**
	 * 来账查询
	 * @param userid			
	 * @param transStartDate	
	 * @param transEndDate		
	 * @return					在查询时间段内的所有记录
	 */
	public HashMap<String,String> getComeQuery(String userid,Date transStartDate,Date transEndDate);
	
	public HashMap<String,String> getListQuery(String userid,Date transStartDate,Date transEndDate);
	
	/**
	 * 某条明细的查询
	 * @param id	
	 * @return		某条明细的详细信息
	 */
	public HashMap<String,String> getListQueryInfo(String id);
	
	/**
	 * 某条来账的查询
	 * @param id	
	 * @return		某条来账的详细信息
	 */
	public HashMap<String,String> getComeQueryInfo(String id);
	
	/**
	 * 设置记录的描述信息
	 * @param serNo		
	 * @param detail	
	 * @return	                      是否设置成功
	 */
	public boolean setDetail(String serNo,String detail);
	
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

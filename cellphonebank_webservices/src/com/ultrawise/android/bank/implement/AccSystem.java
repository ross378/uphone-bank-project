package com.ultrawise.android.bank.implement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ultrawise.android.bank.Enum.EAccState;
import com.ultrawise.android.bank.base.IAccSystem;
import com.ultrawise.android.bank.base.IQuery;
import com.ultrawise.bank.base.dao.IDataInsertTools;
import com.ultrawise.bank.base.dao.IDataQueryTools;
import com.ultrawise.bank.base.dao.IDataUpdataTools;
import com.ultrawise.bank.implement.dao.DataAccessModel;
import com.ultrawise.bank.implement.dao.XMLAccessModel;

public class AccSystem implements IAccSystem {

	private DataAccessModel xmlAccM = null;
	private IDataInsertTools insertTools = null;
	private IDataQueryTools queryTools = null;
	private IDataUpdataTools updataTools = null;
	
	public AccSystem() {

		this.xmlAccM = DataAccessModel.newInstances();
		insertTools = this.xmlAccM.createInsertTools();
		queryTools = this.xmlAccM.createQueryTools();
		updataTools = this.xmlAccM.createUpdataTools();
		
	}

	public boolean addAcc(String userId, String accNo, String accType,
			String accNickName, String accPwd) {
		// TODO Auto-generated method stub

		boolean result = insertTools.insertThree("assign", userId, accNo,
				accType, accNickName, accPwd);

		if (result) {
			return true;
		} else
			return false;

	}

	public List<String> getAcc(String userId, String accType, EAccState accState) {
		// TODO Auto-generated method stub
		System.out.println(userId + accType + accState);
		List<String> list = new ArrayList<String>();
		list.add("440301198810282153");
		list.add("398450928901759384");
		return list;
	}

	public Map<String, String> getAccWithNickName(String userId,
			String accType, EAccState accState) {
		// TODO Auto-generated method stub
		System.out.println(userId + accType + accState);
		Map<String, String> map = new HashMap<String, String>();
		map.put("93405793424572", "我的储蓄卡");
		map.put("70839475042387", "信用卡");
		return map;
	}

	public List<String> getAccType() {
		// TODO Auto-generated method stub
		List<String> list = new ArrayList<String>();
	
		HashMap<String, String> record = this.queryTools.query("paypal", "id", "paypal01");
		list.add(record.get("tyname"));
		
		return list;
	}

	public List<String> getAccTypeAll() {
		// TODO Auto-generated method stub

		List<String> list = new ArrayList<String>();
		List<HashMap<String, String>> records = this.queryTools.query("paypal");
		int i = 0;
		while (i < records.size()) {
			list.add((String) records.get(i).get("tyname"));
			i++;
		}

		System.out.println("--***---" + records.get(1).get("id"));
		return list;
	}

	public List<String> getAccTypeOnCreditCard() {
		List<String> list = new ArrayList<String>();
		// TODO Auto-generated method stub
		list.add("活期");
		return null;
	}

	/**
	 * gsm 2011.04.06
	 * 功能号 0108
	 * 需访问表 accout transfers
	 */
	public Map<String, String> getComeHistory(String userId, String startDate,
			String endDate) {
		/**
		 * 先查transfers表得到<inant>112</inant>//转进账号
		 */
		String inantString=DataAccessModel.newInstances().createQueryTools().queryByTime("transfers", "userid",
													userId,startDate,endDate,"outdata").get("inant");
		/**
		 * 再查accout表得到改账号的所有信息
		 */
		HashMap<String, String> hmap=DataAccessModel.newInstances().createQueryTools().query("accout", "orderid",
				inantString);
		return hmap;
	}

	/**
	 * gsm 2011.04.06
	 * 功能号 0109
	 * 需访问表 cost
	 * 0表示挂失
	 * 1表示预约
	 */
	public String getCost(String costType) {
		HashMap<String, String>hMap=DataAccessModel.newInstances().createQueryTools().query("cost", "type",costType);
		String moneyString=hMap.get("money");
		return moneyString;
	}

	public List<String> getBindCreditCard(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<String> getCreditCard(String userId) {
		List<String> list = new ArrayList<String>();
		// TODO Auto-generated method stub
		return null;
	}

	public String getExtraCode() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<String> getIdType() {
		List<String> list = new ArrayList<String>();
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * gsm
	 * 2011.04.01
	 * 功能号 0112
	 * 需访问 transfers,remit 2个表
	 * 先在transfers表中查到流水账号如<sequence>tf00001</sequence>//流水号
	 * 然后利用流水账号和'汇款'(或者'收入')查到交易时间
	 * 例如当点击 汇款时在查询remit 表中的交易金额，地址，汇款人姓名电话等。。
	 * 				      在paymentform 中查询出付费项目等
	 * 				     在rechargeform 中查询出付费单位等
	 */
	public Map<String, String> getListHistory(String userId, String startDate,
			String endDate) {
		HashMap<String,String> hMap=new HashMap<String, String>();
		String sequenceStr=DataAccessModel.newInstances().createQueryTools().queryByTime("transfers", "userid",
										userId,startDate,endDate,"outdata").get("sequence");
		if(!sequenceStr.equals("")){
		//汇款时间
		String remtTime=DataAccessModel.newInstances().createQueryTools().query("remit", "sequence",
				sequenceStr,"name","汇款").get("time");
		//收入时间
		String IncomeTime=DataAccessModel.newInstances().createQueryTools().query("remit", "sequence",
				sequenceStr,"name","收入").get("time");
		
		//包装成HashMap<String,String>
		hMap.put("typeRemt", "汇款");
		hMap.put("remtTime", remtTime);
		hMap.put("typeIncome", "收入");
		hMap.put("IncomeTime", IncomeTime);
		}else {
			hMap=null;
		}
		return hMap;
	}
/**
 * gsm 2010.04.01
 * 功能号 0113
 * 需访问表net
 */
	public List<String> getNet() {
		List<String> list = new ArrayList<String>();
		List<HashMap<String, String>> hMap=DataAccessModel.newInstances().createQueryTools().query("net");
		for(HashMap<String, String> h:hMap){
			list.add(h.get("name"));
		}
		return list;
	}

	/**
	 * gsm 2010.04.01
	 * 功能号 0114
	 * 需访问表net 
	 */
	public String getNetAddress(String net) {
		HashMap<String, String> hMap=DataAccessModel.newInstances().createQueryTools().query("net","name",net);
		String adresString=hMap.get("dress");
		return adresString;
	}
	/**
	 * gsm
	 * 2011.3.31
	 * 功能号：0115
	 */
	public List<String> getOperator(String paymentId) {
		List<String> list = new ArrayList<String>();

		HashMap<String, String>	hm=DataAccessModel.newInstances().createQueryTools().query("patype", "id",paymentId);
		String operator1=hm.get("operator1");
		String operator2=hm.get("operator2");
		String operator3=hm.get("operator3");
		list.add(operator1);
		list.add(operator2);
		list.add(operator3);
		return list;
	}

	/**
	 * gsm 2011.04.01
	 * 功能号 0116
	 * 需查pendingform表
	 * 表中时间为2011-7-12
	 */
	public Map<String, String> getPaymentHistory(String userId,
			String startDate, String endDate) {
		HashMap<String,String> hM=null;
		HashMap<String,String> hMap=DataAccessModel.newInstances().createQueryTools().
							queryByTime("pendingform", "id",userId,startDate,endDate,"dulimit");
		System.out.println(hMap.get("state"));
		if(hMap.get("state").equals("1")){//1表示缴费
			hM=hMap;
		}
		return hM;
	}

	public Map<String, String> getPaymentInfo(String userId) {
		// TODO Auto-generated method stub
		// 不实现
		return null;
	}
	
	/**
	 * gsm
	 * 2011.3.31
	 */
	public List<String> getPaymentName(String userId) {
		List<String> list = new ArrayList<String>();
		// TODO Auto-generated method stub
		HashMap<String, String>	hm=DataAccessModel.newInstances().createQueryTools().query("pendingform", "userid",userId,
				"state","1");//表示未缴费的
		String name=hm.get("name");
		list.add(name);
		return list;
	}

	/**
	 * gsm
	 * 2011.3.31
	 */
	public List<String> getPaymentNameOnMana() {
		List<String> list = new ArrayList<String>();
		List<HashMap<String, String>> listHashMaps=DataAccessModel.newInstances().createQueryTools().query("openServiceInfo");
		for (HashMap<String, String> hm:listHashMaps) {
			list.add(hm.get("prname"));
		}
		return list;
	}

	public String getPreAcc(String userId) {
		// TODO Auto-generated method stub

		HashMap<String, String> record = queryTools.query("accout", "userid",
				userId);
		String state = record.get("orderid");

		return state;
	}

	/**
	 * gsm
	 * 2011.3.31
	 */
	public List<String> getSelServiceName() {
		List<String> list = new ArrayList<String>();

		List<HashMap<String, String>> list2=DataAccessModel.newInstances().createQueryTools().query("patype");
		for(HashMap<String, String> hm: list2){
			list.add(hm.get("name"));
		}
		return list;
	}

	//暂时不实现
	public List<String> getSelServiceNameByUserId(String userId) {
		List<String> list = new ArrayList<String>();
		// TODO Auto-generated method stub
		return null;
	}

	public List<String> getUsedCreditCard(String userId) {
		List<String> list = new ArrayList<String>();
		// TODO Auto-generated method stub
		return null;
	}

	public boolean login(String userId, String userPwd, String exCode) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean setPreAcc(String userId, String accNo) {
		// TODO Auto-generated method stub
		
		boolean result = this.updataTools.updata("accout", "userid", userId, "orderid", accNo);
		return result;
	}

	/**
	 * gsm 2011.04.01
	 * 功能号：0126
	 * 需访问 userInfo
	 */
	public boolean updatePaymentState(String payName, String state) {
		boolean a=DataAccessModel.newInstances().createUpdataTools().updata("userInfo", "userName",payName,"state",state);
		
		return a;
	}

}

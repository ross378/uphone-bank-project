package com.ultrawise.android.bank.implement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ultrawise.android.bank.Enum.EAccState;
import com.ultrawise.android.bank.base.IAccSystem;
import com.ultrawise.bank.base.dao.IDataInsertTools;
import com.ultrawise.bank.base.dao.IDataQueryTools;
import com.ultrawise.bank.base.dao.IDataUpdataTools;
import com.ultrawise.bank.implement.dao.DataAccessModel;

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

	/**
	 * 钟小会
	 */
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

	/**
	 * 钟小会
	 */
	public List<String> getAcc(String userId, String accType, EAccState accState) {
		List<String> result = new ArrayList<String>();
		String tableName = "";
		HashMap<String, String> records = new HashMap<String, String>();
		//根据不同类型的卡 访问不同的表
		if("信用卡".equals(accType)){
			tableName = "creditCard";
		}else {
			tableName = "accout";
		}
		//根据客户端传过来的账户类型  找到账户类型对应的id
		String accTypeId = this.queryTools.query("paypal","tyname",accType).get("id"); 
		//根据要查询的不同账户状态来选择账户
		switch (accState) {
		case BIND: {
			records = this.queryTools.query(tableName, "userid", userId,
					"actype", accTypeId, "bind", "1");
			if(records.get("orderid")!=null)
				result.add(records.get("orderid"));
			break;
		}
		case UNBIND: {
			records = this.queryTools.query(tableName, "userid", userId,
					"actype", accTypeId, "bind", "0");
			if(records.get("orderid")!=null)
				result.add(records.get("orderid"));
			break;
		}
		case LOSS: {
			records = this.queryTools.query(tableName, "userid", userId,
					"actype", accTypeId, "loss", "1");
			if(records.get("orderid")!=null)
				result.add(records.get("orderid"));
			break;
		}
		case UNLOSS: {
			records = this.queryTools.query(tableName, "userid", userId,
					"actype", accTypeId, "loss", "0");
			if(records.get("orderid")!=null)
				result.add(records.get("orderid"));
			break;
		}
		case ACTIVE: {
			records = this.queryTools.query(tableName, "userid", userId,
					"actype", accTypeId, "activation", "1");
			if(records.get("orderid")!=null)	
				result.add(records.get("orderid"));
			break;
		}
		case UNACTIVE: {
			records = this.queryTools.query(tableName, "userid", userId,
					"actype", accTypeId, "activation", "0");
			if(records.get("orderid")!=null)
				result.add(records.get("orderid"));
			break;
		}
		case ORDER: {
			records = this.queryTools.query(tableName, "userid", userId,
					"actype", accTypeId, "orderstate", "1");
			if(records.get("orderid")!=null)
				result.add(records.get("orderid"));
		}
		case UNORDER: {
			records = this.queryTools.query(tableName, "userid", userId,
					"actype", accTypeId, "orderstate", "0");
			if(records.get("orderid")!=null)
				result.add(records.get("orderid"));
			break;
		}
		}

		return result;
	}

	/**
	 * 钟小会 此方法没做
	 */
	public Map<String, String> getAccWithNickName(String userId,
			String accType, EAccState accState) {
		// TODO Auto-generated method stub
		System.out.println(userId + accType + accState);
		Map<String, String> map = new HashMap<String, String>();
		map.put("93405793424572", "我的储蓄卡");
		map.put("70839475042387", "信用卡");
		return map;
	}

	/**
	 * 钟小会
	 */
	public List<String> getAccType() {
		// TODO Auto-generated method stub
		List<String> list = new ArrayList<String>();

		HashMap<String, String> record = this.queryTools.query("paypal", "id",
				"paypal01");
		list.add(record.get("tyname"));

		return list;
	}

	/**
	 * 钟小会
	 */
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

	/**
	 * 钟小会
	 */
	public List<String> getAccTypeOnCreditCard() {
		List<String> list = new ArrayList<String>();
		// TODO 只有活期储蓄卡!!!!!
		HashMap<String, String> records = this.queryTools.query("paypal", "id",
				"3");
		list.add(records.get("tyname"));
		return list;
	}

	/**
	 * gsm 2011.04.06 功能号 0108 需访问表 accout transfers
	 */
	public Map<String, String> getComeHistory(String userId, String startDate,
			String endDate) {
		/**
		 * 先查transfers表得到<inant>112</inant>//转进账号
		 */
		String inantString = DataAccessModel.newInstances().createQueryTools()
				.queryByTime("transfers", "userid", userId, startDate, endDate,
						"outdata").get("inant");
		/**
		 * 再查accout表得到转账号的所有信息
		 */
		HashMap<String, String> hmap = DataAccessModel.newInstances()
				.createQueryTools().query("accout", "orderid", inantString);
		return hmap;
	}

	/**
	 * gsm 2011.04.06 功能号 0109 需访问表 cost 0表示挂失 1表示预约
	 */
	public String getCost(String costType) {
		HashMap<String, String> hMap = DataAccessModel.newInstances()
				.createQueryTools().query("cost", "type", costType);
		String moneyString = hMap.get("money");
		return moneyString;
	}

	public List<String> getBindCreditCard(String userId) {
		// TODO Auto-generated method stub
		List<String> lstStr = new ArrayList<String>();
		lstStr.add("123");
		lstStr.add("321");

		return lstStr;
	}

	/**
	 * 钟小会
	 */
	public List<String> getCreditCard(String userId) {
		List<String> result = new ArrayList<String>();
		// TODO Auto-generated method stub
		List<HashMap<String, String>> records = this.queryTools
				.query("creditCard");
		if (records == null) {
			return null;
		}
		for (int i = 0; i < records.size(); i++) {
			if (records.get(i).get("userid").equals(userId)) {
				result.add(records.get(i).get("orderid"));
			}
		}
		return result;
	}

	/**
	 * 钟小会 此方法没做
	 */
	public String getExtraCode() {
		// TODO Auto-generated method stub
		return "1234";
	}

	/**
	 * 钟小会
	 */
	public List<String> getIdType() {
		List<String> list = new ArrayList<String>();
		// TODO Auto-generated method stub

		List<HashMap<String, String>> records = this.queryTools
				.query("credentialsInfo");
		if (records == null) {
			return null;
		}
		for (int i = 0; i < records.size(); i++) {
			list.add(records.get(i).get("name"));
		}

		return list;
	}

	/**
	 * gsm 2011.04.01 功能号 0112 需访问 transfers,remit 2个表
	 * 先在transfers表中查到流水账号如<sequence>tf00001</sequence>//流水号
	 * 然后利用流水账号和'汇款'(或者'收入')查到交易时间 例如当点击 汇款时在查询remit 表中的交易金额，地址，汇款人姓名电话等。。
	 * 在paymentform 中查询出付费项目等 在rechargeform 中查询出付费单位等
	 */
	public Map<String, String> getListHistory(String userId, String startDate,
			String endDate) {
		HashMap<String, String> hMap = new HashMap<String, String>();
		String sequenceStr = DataAccessModel.newInstances().createQueryTools()
				.queryByTime("transfers", "userid", userId, startDate, endDate,
						"outdata").get("sequence");
		if (!sequenceStr.equals("")) {
			// 汇款时间
			String remtTime = DataAccessModel.newInstances().createQueryTools()
					.query("remit", "sequence", sequenceStr, "name", "汇款").get(
							"time");
			// 收入时间
			String IncomeTime = DataAccessModel.newInstances()
					.createQueryTools().query("remit", "sequence", sequenceStr,
							"name", "收入").get("time");

			// 包装成HashMap<String,String>
			hMap.put("id", "汇款");
			hMap.put("typeRemt", "汇款");
			hMap.put("remtTime", remtTime);
			hMap.put("typeIncome", "收入");
			hMap.put("IncomeTime", IncomeTime);
		} else {
			hMap = null;
		}
		return hMap;
	}

	/**
	 * gsm 2010.04.01 功能号 0113 需访问表net
	 */
	public List<String> getNet() {
		List<String> list = new ArrayList<String>();
		List<HashMap<String, String>> hMap = DataAccessModel.newInstances()
				.createQueryTools().query("net");
		for (HashMap<String, String> h : hMap) {
			list.add(h.get("name"));
		}
		return list;
	}

	/**
	 * gsm 2010.04.01 功能号 0114 需访问表net
	 */
	public String getNetAddress(String net) {
		HashMap<String, String> hMap = DataAccessModel.newInstances()
				.createQueryTools().query("net", "name", net);
		String adresString = hMap.get("dress");
		return adresString;
	}

	/**
	 * gsm 2011.3.31 功能号：0115
	 */
	public List<String> getOperator(String paymentId) {
		List<String> list = new ArrayList<String>();

		HashMap<String, String> hm = DataAccessModel.newInstances()
				.createQueryTools().query("patype", "id", paymentId);
		String operator1 = hm.get("operator1");
		String operator2 = hm.get("operator2");
		String operator3 = hm.get("operator3");
		list.add(operator1);
		list.add(operator2);
		list.add(operator3);
		return list;
	}

	/**
	 * gsm 2011.04.01 功能号 0116 需查pendingform表 表中时间为2011-7-12
	 */
	public Map<String, String> getPaymentHistory(String userId,
			String startDate, String endDate) {
		HashMap<String, String> hM = null;
		HashMap<String, String> hMap = DataAccessModel.newInstances()
				.createQueryTools().queryByTime("pendingform", "id", userId,
						startDate, endDate, "dulimit");
		System.out.println(hMap.get("state"));
		if (hMap.get("state").equals("1")) {// 1表示缴费
			hM = hMap;
		}
		return hM;
	}

	public Map<String, String> getPaymentInfo(String userId) {
		// TODO Auto-generated method stub
		// 不实现
		return null;
	}

	/**
	 * gsm 2011.3.31
	 * 功能号 0118 
	 * 需访问表pendingform
	 * 1.首先遍历整张表找到所有记录
	 * 2.将所有结果的userId和传进来进行比较,查到相等的返回
	 */
	public Map<String, String> getPaymentName(String userId) {

		Map<String, String> map = new HashMap<String, String>();
		// 查到整张表的记录
		List<HashMap<String, String>> list2 = DataAccessModel.newInstances()
				.createQueryTools().query("pendingform");
		//遍历list2中的每一项
		for(int i=0;i<list2.size();i++){
			HashMap<String, String> hm=list2.get(i);
			//判断userid 和缴费状态state 0表示未缴费
			if(hm.get("userid").equals(userId)&&hm.get("state").equals("0")){
				map.put(hm.get("name"), hm.get("damout"));
			}
		}
		return map;
	}

	/**
	 * gsm 2011.3.31
	 */
	public List<String> getPaymentNameOnMana() {
		List<String> list = new ArrayList<String>();
		List<HashMap<String, String>> listHashMaps = DataAccessModel
				.newInstances().createQueryTools().query("openServiceInfo");
		for (HashMap<String, String> hm : listHashMaps) {
			list.add(hm.get("prname"));
		}
		return list;
	}

	/**
	 * 钟小会
	 * gsm 2011.04.15中午修改过返回值新加了金额 balance
	 */
	public String getPreAcc(String userId) {
		// TODO Auto-generated method stub

		HashMap<String, String> record = queryTools.query("accout", "userid",
				userId);
		String state = record.get("orderid")+"#"+record.get("balance");

		return state;
	}

	/**
	 * gsm 2011.3.31
	 */
	public List<String> getSelServiceName() {
		List<String> list = new ArrayList<String>();

		List<HashMap<String, String>> list2 = DataAccessModel.newInstances()
				.createQueryTools().query("patype");
		for (HashMap<String, String> hm : list2) {
			list.add(hm.get("name"));
		}
		return list;
	}

	/**
	 * gsm 暂时不实现
	 */
	public List<String> getSelServiceNameByUserId(String userId) {
		List<String> list = new ArrayList<String>();
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 钟小会 此方法没做
	 */
	public List<String> getUsedCreditCard(String userId) {
		List<String> list = new ArrayList<String>();
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 钟小会
	 */
	public boolean login(String userId, String userPwd, String exCode) {
		HashMap<String, String> record = this.queryTools.query("userInfo",
				"userid", userId);
		if (record == null) {
			return false;
		}
		if (userPwd.equals(record.get("password")) && exCode.equals("***")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 钟小会
	 */
	public boolean setPreAcc(String userId, String accNo) {
		// TODO Auto-generated method stub

		boolean result = this.updataTools.updata("accout", "userid", userId,
				"orderid", accNo);
		return result;
	}

	/**
	 * gsm 2011.04.01 功能号：0126 需访问 userInfo
	 */
	public boolean updatePaymentState(String payName, String state) {
		boolean a = DataAccessModel.newInstances().createUpdataTools().updata(
				"userInfo", "userName", payName, "state", state);

		return a;
	}

}

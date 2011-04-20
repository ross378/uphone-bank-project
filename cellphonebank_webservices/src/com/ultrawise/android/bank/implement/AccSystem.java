package com.ultrawise.android.bank.implement;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sun.xml.bind.v2.util.QNameMap.Entry;
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
		String tableName = "";
		if ("信用卡".equals(accType)) {
			tableName = "creditCard";
		} else {
			tableName = "accout";
		}

		HashMap<String, String> accInfo = this.queryTools.query(tableName,
				"userid", userId);
		String type = this.queryTools.query("paypal", "id",
				accInfo.get("actype")).get("tyname");
		System.out.println(userId + "," + accNo + "," + accType + ","
				+ accNickName + "," + accPwd);
		if (accNo.equals(accInfo.get("orderid"))
				&& accPwd.equals(accInfo.get("actpwd")) && type.equals(accType)
				&& "0".equals(accInfo.get("isadd"))) {
			return this.updataTools.updata(tableName, "userid", userId,
					"aliss", accNickName, "isadd", "1");
		} else {
			return false;
		}

	}

	/**
	 * 钟小会
	 */
	public List<String> getAcc(String userId, String accType, EAccState accState) {
		List<String> result = new ArrayList<String>();
		String tableName = "";
		HashMap<String, String> records = new HashMap<String, String>();
		// 根据不同类型的卡 访问不同的表
		if ("信用卡".equals(accType)) {
			tableName = "creditCard";
		} else {
			tableName = "accout";
		}
		// 根据客户端传过来的账户类型 找到账户类型对应的id
		String accTypeId = this.queryTools.query("paypal", "tyname", accType)
				.get("id");
		// 根据要查询的不同账户状态来选择账户
		switch (accState) {
		case BIND: {
			records = this.queryTools.query(tableName, "userid", userId,
					"actype", accTypeId, "bind", "1");
			if (records.get("orderid") != null)
				result.add(records.get("orderid"));
			break;
		}
		case UNBIND: {
			records = this.queryTools.query(tableName, "userid", userId,
					"actype", accTypeId, "bind", "0");
			if (records.get("orderid") != null)
				result.add(records.get("orderid"));
			break;
		}
		case LOSS: {
			records = this.queryTools.query(tableName, "userid", userId,
					"actype", accTypeId, "loss", "1");
			if (records.get("orderid") != null)
				result.add(records.get("orderid"));
			break;
		}
		case UNLOSS: {
			records = this.queryTools.query(tableName, "userid", userId,
					"actype", accTypeId, "loss", "0");
			if (records.get("orderid") != null)
				result.add(records.get("orderid"));
			break;
		}
		case ACTIVE: {
			records = this.queryTools.query(tableName, "userid", userId,
					"actype", accTypeId, "activation", "1");
			if (records.get("orderid") != null)
				result.add(records.get("orderid"));
			break;
		}
		case UNACTIVE: {
			records = this.queryTools.query(tableName, "userid", userId,
					"actype", accTypeId, "activation", "0");
			if (records.get("orderid") != null)
				result.add(records.get("orderid"));
			break;
		}
		case ORDER: {
			records = this.queryTools.query(tableName, "userid", userId,
					"actype", accTypeId, "orderstate", "1");
			if (records.get("orderid") != null)
				result.add(records.get("orderid"));
		}
		case UNORDER: {
			records = this.queryTools.query(tableName, "userid", userId,
					"actype", accTypeId, "orderstate", "0");
			if (records.get("orderid") != null)
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
				"1");
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
				"1");
		list.add(records.get("tyname"));
		return list;
	}

	/**
	 * gsm 2011.04.06 功能号 0108 需访问表 accout transfers
	 */
	public Map<String, String> getComeHistory(String userId, String startDate,
			String endDate) {
		StringBuffer resultBuffer = new StringBuffer();
		HashMap<String, String> comeHistory = new HashMap<String, String>();
		List<HashMap<String, String>> transfer = this.queryTools.query("transfers");
		Date start = Date.valueOf(startDate);
		Date end = Date.valueOf(endDate);
		for(HashMap<String, String> temp : transfer){
			if(userId.equals(temp.get("userid"))){
				Date date1 = Date.valueOf(temp.get("date"));
				if(date1.after(start) && date1.before(end)){
					resultBuffer.append(temp.get("id")).append("#").append(temp.get("date")).append("#").append("转账");
					resultBuffer.append(",");
				}
			}
		}
		List<HashMap<String, String>> remit = this.queryTools.query("remit");
		for(HashMap<String, String> temp : remit){
			if(userId.equals(temp.get("userid"))){
				Date date1 = Date.valueOf(temp.get("date"));
				if(date1.after(start) && date1.before(end)){
					resultBuffer.append(temp.get("id")).append("#").append(temp.get("date")).append("#").append("汇款");
					resultBuffer.append(",");
				}
			}
		}
		
		comeHistory.put("info", resultBuffer.toString());

		return comeHistory;
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
		// TODO 获取绑定的信用卡
		List<String> lstStr = new ArrayList<String>();
		List<HashMap<String, String>> lstMap = DataAccessModel.newInstances()
				.createQueryTools().query("creditCard");
		for (HashMap<String, String> hashMap : lstMap) {
			if (hashMap.get("bind").equals("1")) {
				lstStr.add(hashMap.get("orderid"));
			}
		}
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
		String random=String.valueOf(Math.random()).substring(2, 6);//个4位随机数	
		return random;
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
		StringBuffer resultBuffer = new StringBuffer();
		List<HashMap<String, String>> transfer = this.queryTools.query("transfers");
		Date start = Date.valueOf(startDate);
		Date end = Date.valueOf(endDate);
		for(HashMap<String, String> temp : transfer){
			if(userId.equals(temp.get("userid"))){
				Date date1 = Date.valueOf(temp.get("date"));
				if(date1.after(start) && date1.before(end)){
					resultBuffer.append(temp.get("id")).append("#").append(temp.get("date")).append("#").append("支出");
					resultBuffer.append(",");
				}
			}
		}
		List<HashMap<String, String>> remit = this.queryTools.query("remit");
		for(HashMap<String, String> temp : remit){
			if(userId.equals(temp.get("userid"))){
				Date date1 = Date.valueOf(temp.get("date"));
				if(date1.after(start) && date1.before(end)){
					resultBuffer.append(temp.get("id")).append("#").append(temp.get("date")).append("#").append("收入");
					resultBuffer.append(",");
				}
			}
		}
		hMap.put("info", resultBuffer.toString());
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
	 *获得运营商名称 gsm 2011.3.31 功能号：0115 查表patype
	 * 
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
	 * gsm 2011.04.01 功能号 0116 需查pendingform表 表中时间为2011-7-12 "pendingform",
	 * "id","1","2011-7-4","2011-7-14","dulimit"
	 */
	public Map<String, String> getPaymentHistory(String userId,
			String startDate, String endDate) {

		// System.out.println("userId=" + userId + "startDate=" + startDate
		// + "endDate=" + endDate);
		StringBuilder stringBuilder = null;
		HashMap<String, String> map = null;
		if (startDate.equals("") || endDate.equals("")) {// 查询最近一个月记录
			stringBuilder = new StringBuilder();
			map = new HashMap<String, String>();
			List<HashMap<String, String>> list = DataAccessModel.newInstances()
					.createQueryTools().query("paymentform");
			for (int i = 0; i < list.size(); i++) {
				stringBuilder.append(list.get(i).get("date") + "#"
						+ list.get(i).get("name") + "#");
				map.put("Info", stringBuilder.toString());
			}
		} else {// 根据时间段查询记录
			List<HashMap<String, String>> list = DataAccessModel.newInstances()
					.createQueryTools().query("paymentform");
			stringBuilder = new StringBuilder();
			map = new HashMap<String, String>();
			for (int i = 0; i < list.size(); i++) {
				Date date3 = Date.valueOf(list.get(i).get("date"));// 查到数据库中的时间
				Date date1 = Date.valueOf(startDate);// 转换传过来的时间
				Date date2 = Date.valueOf(endDate);
				if (userId.equals(list.get(i).get("userid"))
						&& date3.before(date2) && date3.after(date1)) {// 找到了符合条件的
					stringBuilder.append(list.get(i).get("date") + "#"
							+ list.get(i).get("name") + "#");
					map.put("Info", stringBuilder.toString());
				}
			}
		}
		// System.out.println(map.toString());
		return map;
	}

	/**
	 * 功能号0209 查表paymentform
	 * 
	 * @author gsm 2011-4-18
	 * @param userId
	 * @return
	 */
	// public Map<String, String> getPaymentInfo(String userId) {
	// StringBuilder stringBuilder=new StringBuilder();
	// HashMap<String, String> map=new HashMap<String, String>();
	// List<HashMap<String, String>> list = DataAccessModel.newInstances()
	// .createQueryTools().query("paymentform");
	// for(int i=0;i<list.size();i++){
	// stringBuilder.append(list.get(i).get("date")+"#"+list.get(i).get("name"));
	// map.put("Info", stringBuilder.toString());
	// }
	// return map;
	// }

	/**
	 * gsm 2011.3.31 功能号 0118 需访问表pendingform 1.首先遍历整张表找到所有记录
	 * 2.将所有结果的userId和传进来进行比较,查到相等的返回
	 */
	public Map<String, String> getPaymentName(String userId) {

		Map<String, String> map = new HashMap<String, String>();
		// 查到整张表的记录
		List<HashMap<String, String>> list2 = DataAccessModel.newInstances()
				.createQueryTools().query("pendingform");
		// 遍历list2中的每一项
		for (int i = 0; i < list2.size(); i++) {
			HashMap<String, String> hm = list2.get(i);
			// 判断userid 和缴费状态state 0表示未缴费
			if (hm.get("userid").equals(userId) && hm.get("state").equals("0")) {
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
		HashMap<String, String> Maps = DataAccessModel.newInstances()
				.createQueryTools().query("openItem", "1");
		list.add(Maps.get("state0") + "#" + Maps.get("state1") + "#"
				+ Maps.get("state2") + "#" + Maps.get("state3") + "#"
				+ Maps.get("state4") + "#" + Maps.get("state5"));
		return list;
	}

	/**
	 * 钟小会 gsm 2011.04.15中午修改过返回值新加了金额 balance
	 */
	public String getPreAcc(String userId) {
		// TODO Auto-generated method stub
		HashMap<String, String> record = queryTools.query("userInfo", "userid",
				userId);
		String orderid = record.get("preant");// 取得账号
		/**
		 * 利用账号 在account中查到余额和类型 <userid>5</userid> <orderid>114</orderid>//账户号
		 * <actype>1</actype> <balance>80000</balance>
		 */
		HashMap<String, String> hm = queryTools.query("accout", "orderid",
				orderid);
		String balance = hm.get("balance");// 取得余额
		String acctype = hm.get("actype");// 取得类型

		/**
		 * 通过类型在paypal表中查到该类型的名称 <paypal> <id>1</id> <tyname>活期储蓄卡</tyname>
		 * </paypal>
		 */
		HashMap<String, String> hm2 = queryTools.query("paypal", "id", acctype);
		String tyname = hm2.get("tyname");// 取得名称
		return acctype + "#" + orderid + "#" + balance + "#" + tyname;
		// return orderid;
	}

	/**
	 * gsm 2011.3.31 功能号"0121" 需访问表patype
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
		HashMap<String, String> record = DataAccessModel.newInstances().createQueryTools().query("userInfo",
				"userid", userId);
		
		if (record == null) {
			return false;
		}
		if (userPwd.equals(record.get("password"))) {
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

		boolean result = this.updataTools.updata("userInfo", "userid", userId,
				"preant", accNo);
		return result;
	}

	/**
	 * gsm 2011.04.01 功能号：0126 需访问 userInfo
	 */
	public boolean updatePaymentState(String payName, String state) {
		boolean a = DataAccessModel.newInstances().createUpdataTools().updata(
				"openItem", "userid", "1", payName, state);

		return a;
	}
	
	public HashMap<String,String> getUserInfo (String userId){
		HashMap<String, String> userInfo= new HashMap<String,String>();
		List<HashMap<String, String>> userInfoLst = DataAccessModel.newInstances().createQueryTools().query("userInfo");
		for(HashMap<String,String> map:userInfoLst){
			String userid = map.get("userid");
			if(userId.equals(userid)){
				return map;
			}
		}
		return userInfo;
	}
	

}

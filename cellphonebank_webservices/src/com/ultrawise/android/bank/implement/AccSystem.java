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

	public Map<String, String> getComeHistory(String userId, String startDate,
			String endDate) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getCost(String costType) {
		// TODO Auto-generated method stub
		return null;
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

	public Map<String, String> getListHistory(String userId, String startDate,
			String endDate) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<String> getNet() {
		List<String> list = new ArrayList<String>();
		// TODO Auto-generated method stub
		return null;
	}

	public String getNetAddress(String net) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * gsm
	 * 2011.3.31
	 */
	public List<String> getOperator(String paymentId) {
		List<String> list = new ArrayList<String>();
		// TODO Auto-generated method stub
		return null;
	}

	public Map<String, String> getPaymentHistory(String userId,
			String startDate, String endDate) {
		// TODO Auto-generated method stub
		return null;
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

	public boolean updatePaymentState(String payName, String state) {
		// TODO Auto-generated method stub
		return false;
	}

}

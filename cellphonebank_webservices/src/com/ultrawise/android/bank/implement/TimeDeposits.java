package com.ultrawise.android.bank.implement;

import java.util.HashMap;

import com.ultrawise.android.bank.base.ITrans;
import com.ultrawise.android.bank.base.IUpdate;
import com.ultrawise.bank.implement.dao.DataAccessModel;

public class TimeDeposits extends Account implements IUpdate {
	//----查询----
	@Override
	public boolean acctIsActive(String paymentActNo) {
		HashMap<String,String> accInfo = DataAccessModel.newInstances().createQueryTools().query("accout", "orderid",paymentActNo);
		if("1".equals(accInfo.get("activation"))){
			return true;
		}
		return false;
	}

	@Override
	public HashMap<String, String> getAccInfo(String acc) {
		HashMap<String, String> accInfo = new HashMap<String, String>();
		HashMap<String, String> temp = DataAccessModel.newInstances()
				.createQueryTools().query("accout", "orderid", acc);
		HashMap<String, String> accTypeHashMap = DataAccessModel.newInstances().createQueryTools().query("paypal", "id",temp.get("actype"));
		if (temp != null) {
			accInfo.put("账户", temp.get("orderid"));
			accInfo.put("账户类型", accTypeHashMap.get("tyname"));
			accInfo.put("币种", temp.get("montype"));
			accInfo.put("余额", temp.get("balance"));
			accInfo.put("存期", temp.get("period"));
			accInfo.put("起息月", temp.get("months"));
			accInfo.put("利率", temp.get("rate"));
		}
		return accInfo;
	}

	@Override
	public String getNickName(String acc) {
		HashMap<String, String> accInfo = DataAccessModel.newInstances().createQueryTools().query("accout","orderid",acc);
		return accInfo.get("aliss");
	}

	@Override
	public HashMap<String, String> getOrderInfo(String acc) {
		HashMap<String, String> orderInfo = new HashMap<String, String>();
		HashMap<String, String> temp = DataAccessModel.newInstances().createQueryTools().query("appointmentform", "orderid",acc);
		if(temp != null){
			orderInfo.put("account", temp.get("orderid"));
			orderInfo.put("aliss", temp.get("aliss"));
			orderInfo.put("reason", temp.get("reason"));
			orderInfo.put("net", temp.get("net"));
			orderInfo.put("netaddress", temp.get("netaddress"));
			orderInfo.put("cost", temp.get("cost"));
		}else{
			orderInfo.put("not find", "该账号没有预约信息");
		}
		return orderInfo;
	}
	
	public boolean verifyPassword(String account,String password) {
		HashMap<String, String> accHashMap = DataAccessModel.newInstances().createQueryTools().query("accout", "orderid",account);
		if (password.equals(accHashMap.get("actpwd"))) {
			return true;
		}
		
		return false;
	}
	//----查询----
	
	// ----更新-----
	public boolean deleAcc(String accNo) {
		// TODO Auto-generated method stub
		return DataAccessModel.newInstances().createUpdataTools().updata(
				"accout", "orderid", accNo, "delete", "1");
	}

	public boolean lossRegister(String accNo) {
		// TODO Auto-generated method stub
		return DataAccessModel.newInstances().createUpdataTools().updata(
				"accout", "orderid", accNo, "loss", "1");
	}

	public boolean setActActive(String accNo, String accPwd) {
		// TODO Auto-generated method stub
		HashMap<String, String> accInfo = DataAccessModel.newInstances()
				.createQueryTools().query("accout", "orderid", accNo);
		if (accPwd.equals(accInfo.get("pwd"))) {
			return DataAccessModel.newInstances().createUpdataTools().updata(
					"accout", "orderid", accNo, "activation", "1");
		} else {
			return false;
		}
	}

	public boolean setBind(String accNo) {
		// TODO Auto-generated method stub
		return DataAccessModel.newInstances().createUpdataTools().updata(
				"accout", "orderid", accNo, "bind", "1");
	}

	public boolean setNickName(String accNo, String name) {
		// TODO Auto-generated method stub
		return DataAccessModel.newInstances().createUpdataTools().updata(
				"accout", "orderid", accNo, "aliss", name);
	}

	public boolean setOrderCard(String accNo, String aliss, String reason,
			String net, String netaddress, double cost) {
		// TODO Auto-generated method stub
		DataAccessModel.newInstances().createInsertTools().insertThree(
				"appointmentform", "orderid:" + accNo, "aliss:" + aliss,
				"reason:" + reason, "net:" + net, "netaddress:" + netaddress,
				"cost:" + String.valueOf(cost));
		return DataAccessModel.newInstances().createUpdataTools().updata(
				"accout", "orderid", accNo, "orderstate", "1");
	}
	// ----更新-----

	
}

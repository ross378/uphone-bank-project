package com.ultrawise.android.bank.implement;

import java.util.HashMap;

import com.ultrawise.android.bank.Enum.EOperation;
import com.ultrawise.android.bank.Enum.TableName;
import com.ultrawise.android.bank.base.ITrans;
import com.ultrawise.android.bank.file.implement.DataAccessModel;

public class CurrentDeposit extends Account implements ITrans {

	public HashMap<String, String> getPaymentHisInfo(String paymentNam,String id) {
		System.out.println(paymentNam);
		HashMap<String,String>  paymentHisInfo = new HashMap<String, String>();
		TableName tn = TableName.getTableName(paymentNam);
		String fileName = "";
		switch (tn){
		case WATERCOSTINFO:
			fileName = "waterCostInfo";
			break;
		case POWERCOSTINFO:
			fileName = "powerCostInfo";
			break;
		case GASCOSTINFO:
			fileName = "gasCoseInfo";
			break;
		case HOUSERENDCOSEINFO:
			fileName = "houseRendCoseInfo";
			break;
		default :
			paymentHisInfo.put("error", "查询的表不存在！");
			return paymentHisInfo;
		}
		HashMap<String,String> temp = DataAccessModel.newInstances().createQueryTools().query(fileName, "id",id);
		if(temp != null){
			paymentHisInfo.put("paymentNam", paymentNam);
			paymentHisInfo.put("paymentAmt", temp.get("damout"));
			paymentHisInfo.put("paymentTim", temp.get("date"));
			paymentHisInfo.put("paymentSer", temp.get("dunum"));
			paymentHisInfo.put("paymentAct", temp.get("account"));
		}
		return paymentHisInfo;
	}

	public HashMap<String, String> getPaymentInfo(String id) {
		HashMap<String,String> paymentInfo = new HashMap<String, String>();
		
		HashMap<String,String> temp = DataAccessModel.newInstances().createQueryTools().query("undecided", "id",id);
		if(temp != null){
			System.out.println(temp.get("paymentNam"));
			paymentInfo.put("paymentNam", temp.get("paymentNam"));
			System.out.println(temp.get("paymentAmt"));
			paymentInfo.put("paymentAmt", temp.get("paymentAmt"));
			System.out.println(temp.get("charge"));
			paymentInfo.put("charge", temp.get("charge"));
			System.out.println(temp.get("concordat"));
			paymentInfo.put("concordat", temp.get("concordat"));
			System.out.println(temp.get("deadline"));
			paymentInfo.put("deadline", temp.get("deadline"));
		}
		return paymentInfo;
	}

	public HashMap<String, String> getRechargeInfo(String paymentName,
			String paymentActNo, double paymentAmt, String paymentPhoneNum) {
		HashMap<String,String> recharfeInfo = new HashMap<String, String>();
		
		return recharfeInfo;
	}

	public HashMap<String, String> getTargetPhoneInfo(String account,
			String password, String amtph, double amtnum) {
		
		return null;
	}

	public HashMap<String, String> recharge(String paymentName,
			double paymentAmt, String paymentActNo, String paymentActPasswd,String paymentNum) {
		HashMap<String,String> recharge = new HashMap<String, String>();
		if("手机充值".equals(paymentName)){
			
		}
		return recharge;
	}

	public boolean setDetail(String serNo, String detail) {
		if("1".equals(serNo)){
			return true;
		}
		return false;
	}

	public HashMap<String, String> transfeAct(String account, String password,
			String amtph, double amtnum) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public HashMap<String, String> getComeQueryInfo(String id) {
		HashMap<String, String> comeQueryInfo = new HashMap<String, String>();
		HashMap<String,String> temp = DataAccessModel.newInstances().createQueryTools().query("comeAccount","id",id);
		return comeQueryInfo;
	}
	
	@Override
	public HashMap<String, String> getListQueryInfo(String id) {
		HashMap<String, String> listQueryInfo = new HashMap<String, String>();
		HashMap<String,String> temp = DataAccessModel.newInstances().createQueryTools().query("outAccount","id",id);
		if(temp != null){
			
		}
		return listQueryInfo;
	}
	
	@Override
	public HashMap<String, String> getAccInfo(String acc) {
		HashMap<String, String> accInfo = new HashMap<String, String>();
		HashMap<String,String> temp = DataAccessModel.newInstances().createQueryTools().query("accout","orderid",acc);
		if (temp != null) {
			accInfo.put("balance", temp.get("balance"));
		}
		return accInfo;
	}
}

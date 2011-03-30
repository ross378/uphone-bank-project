package com.ultrawise.android.bank.implement;

import java.util.Date;
import java.util.HashMap;

import com.ultrawise.android.bank.Enum.EOperation;
import com.ultrawise.android.bank.Enum.TableName;
import com.ultrawise.android.bank.Helper.Helper;
import com.ultrawise.android.bank.base.ITrans;
import com.ultrawise.android.bank.base.IUpdate;
import com.ultrawise.bank.implement.dao.DataAccessModel;

public class CurrentDeposit extends Account implements ITrans, IUpdate {

	public HashMap<String, String> getPaymentHisInfo(String paymentNam,
			String id) {
		System.out.println(paymentNam);
		HashMap<String, String> paymentHisInfo = new HashMap<String, String>();
		TableName tn = TableName.getTableName(paymentNam);
		String fileName = "";
		switch (tn) {
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
		default:
			paymentHisInfo.put("error", "查询的表不存在！");
			return paymentHisInfo;
		}
		HashMap<String, String> temp = DataAccessModel.newInstances()
				.createQueryTools().query(fileName, "id", id);
		if (temp != null) {
			paymentHisInfo.put("paymentNam", paymentNam);
			paymentHisInfo.put("paymentAmt", temp.get("damout"));
			paymentHisInfo.put("paymentTim", temp.get("date"));
			paymentHisInfo.put("paymentSer", temp.get("dunum"));
			paymentHisInfo.put("paymentAct", temp.get("account"));
		}
		return paymentHisInfo;
	}

	public HashMap<String, String> getPaymentInfo(String id) {
		HashMap<String, String> paymentInfo = new HashMap<String, String>();

		HashMap<String, String> temp = DataAccessModel.newInstances()
				.createQueryTools().query("undecided", "id", id);
		if (temp != null) {
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
		HashMap<String, String> recharfeInfo = new HashMap<String, String>();

		return recharfeInfo;
	}

	public HashMap<String, String> getTargetPhoneInfo(String account,
			String password, String amtph, double amtnum) {

		return null;
	}

	public HashMap<String, String> recharge(String paymentName,
			double paymentAmt, String paymentActNo, String paymentActPasswd,String paymentNum,String operator) {
		HashMap<String,String> recharge = new HashMap<String, String>();
		System.out.println(paymentName);
		TableName tn = TableName.getTableName(paymentName);
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
		case QQCHARGEINFO:
			fileName = "qqChargeInfo";
			break;
		case PHONECHARGEINFO:
			fileName = "phoneChargeInfo";
			break;
		case WYCARDCHARGEINFO:
			fileName = "WYCardChargeInfo";
			break;
		default :
			recharge.put("error", "缴费的表不存在！");
			return recharge;
		}
		boolean isActive = acctIsActive(paymentActNo);
		if (!isActive) {
			DataAccessModel.newInstances().createUpdataTools().updata("accout", "orderid:"+paymentActNo, "activation","1");
		}
		HashMap<String,String> temp = DataAccessModel.newInstances().createQueryTools().query("accout", "orderid",paymentActNo);
		if(paymentActPasswd.equals(temp.get("actpwd"))){
			double balance = Double.parseDouble(temp.get("balance"));
			balance -= paymentAmt;
			DataAccessModel.newInstances().createUpdataTools().updata("accout", "orderid",paymentActNo, "balance",String.valueOf(balance));
			HashMap<String,String> temp1 = DataAccessModel.newInstances().createQueryTools().query("selservice", "name",paymentName);
			String serNo = temp1.get("serNo");
			DataAccessModel.newInstances().createInsertTools().insertThree(fileName,"id:2","userid:"+temp.get("userid"),
					"crepnum:"+paymentNum,"credata:"+Helper.getCurrentTime(),"serNo:"+serNo,"operator:"+operator,"account:"+paymentActNo);
			recharge.put("serNo", serNo);
			recharge.put("result", "true");
		}
		return recharge;
	}
	
	public boolean setDetail(String serNo, String detail) {
		if ("1".equals(serNo)) {
			return true;
		}
		return false;
	}

	public HashMap<String, String> transfeAct(String account, String password,
			String amtph, double amtnum) {
		HashMap<String, String> transInfo = new HashMap<String, String>();
		HashMap<String, String> accInfo = DataAccessModel.newInstances()
				.createQueryTools().query("accout", "orderid", account);
		if (password.equals(accInfo.get("actpwd"))) {
			double balance = Double.parseDouble(accInfo.get("balance"));
			balance -= amtnum;
			boolean result = DataAccessModel.newInstances().createUpdataTools()
					.updata("accout", "orderid:" + account, "balance",
							String.valueOf(balance));
			if (result) {
				HashMap<String, String> userInfo = DataAccessModel
						.newInstances().createQueryTools().query("userInfo",
								"phnum", amtph);
				String userid = userInfo.get("userid");
				HashMap<String, String> accInfo1 = DataAccessModel
						.newInstances().createQueryTools().query("accout",
								"userid", userid);
				double balance1 = Double.parseDouble(accInfo1.get("balance"));
				balance1 += amtnum;
				boolean result1 = DataAccessModel.newInstances()
						.createUpdataTools().updata("accout",
								"userid:" + userid, "balance",
								String.valueOf(balance1));
				if (result1) {
					transInfo.put("result", "转账成功");
					transInfo.put("balance", String.valueOf(balance));
				} else {
					balance += amtnum;
					DataAccessModel.newInstances().createUpdataTools().updata(
							"accout", "orderid:" + account, "balance",
							String.valueOf(balance));
					transInfo.put("result", "转账失败");
				}
			} else {
				transInfo.put("result", "转账失败");
			}
		} else {
			transInfo.put("result", "密码错误");
		}

		return transInfo;
	}

	public HashMap<String, String> getComeQueryInfo(String id) {
		HashMap<String, String> comeQueryInfo = new HashMap<String, String>();
		HashMap<String, String> temp = DataAccessModel.newInstances()
				.createQueryTools().query("comeAccount", "id", id);
		return comeQueryInfo;
	}

	public HashMap<String, String> getListQueryInfo(String id) {
		HashMap<String, String> listQueryInfo = new HashMap<String, String>();
		HashMap<String, String> temp = DataAccessModel.newInstances()
				.createQueryTools().query("outAccount", "id", id);
		if (temp != null) {

		}
		return listQueryInfo;
	}

	@Override
	public HashMap<String, String> getAccInfo(String acc) {
		HashMap<String, String> accInfo = new HashMap<String, String>();
		HashMap<String, String> temp = DataAccessModel.newInstances()
				.createQueryTools().query("accout", "orderid", acc);
		if (temp != null) {
			accInfo.put("balance", temp.get("balance"));
		}
		return accInfo;
	}

	@Override
	public boolean acctIsActive(String paymentActNo) {
		HashMap<String,String> accInfo = DataAccessModel.newInstances().createQueryTools().query("accout", "orderid",paymentActNo);
		if("1".equals(accInfo.get("activation"))){
			return true;
		}
		return false;
	}

	@Override
	public String getNickName(String acc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<String, String> getOrderInfo(String acc) {
		// TODO Auto-generated method stub
		return null;
	}

	public HashMap<String, String> recharge(String paymentName,
			double paymentAmt, String paymentActNo, String paymentActPasswd,
			String paymentNum) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean deleAcc(String accNo) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean lossRegister(String accNo) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean setActActive(String accNo, String accPwd) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean setBind(String accNo) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean setNickName(String accNo, String name) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean setOrderCard(String accNo) {
		// TODO Auto-generated method stub
		return false;
	}
}

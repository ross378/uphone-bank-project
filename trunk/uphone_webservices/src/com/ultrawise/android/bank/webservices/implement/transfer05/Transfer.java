package com.ultrawise.android.bank.webservices.implement.transfer05;

import java.util.ArrayList;
import java.util.List;

import com.ultrawise.android.bank.webservices.base.transfer05.ITransfer;

public class Transfer implements ITransfer {

	public List<String> getfiracc(String username) {
		// TODO Auto-generated method stub
		List<String> lstuserinfo = new ArrayList<String>();
		if (username.equals("zhangsan"))
			{
				String useracc = "123456789";
				String accinfo = "活期储蓄账户[]";
				lstuserinfo.add(accinfo);
				lstuserinfo.add(useracc);
				return lstuserinfo;
			}
		else 
			{
				String useracc = "error";
				String accinfo = "errorinfo";
				lstuserinfo.add(accinfo);
				lstuserinfo.add(useracc);
				return lstuserinfo;
			}
	}

	public List<String> getcomacctype(String userinfo) {
		// TODO Auto-generated method stub
		List<String> lstacctype = new ArrayList<String>();
		if(userinfo.equals("zhangsan")){
			
			//String flag = "succeed";
			String acctype1 = "活期储蓄账户";
			String acctype2 = "定期储蓄账户";
			//lstacctype.add(flag);
			lstacctype.add(acctype1);
			lstacctype.add(acctype2);
			return lstacctype;
		
		}else{
			
			String flag = "failed";
			lstacctype.add(flag);
			return lstacctype;
			
		}
	}
	
	public List<String> getcomacc(String accinfo) {
		// TODO Auto-generated method stub
		List<String> lstcomacc = new ArrayList<String>();
		if(accinfo.equals("活期储蓄账户")){
			lstcomacc.add("123456789");
			lstcomacc.add("234567891");
			lstcomacc.add("345678912");
			lstcomacc.add("456789123");
			return lstcomacc;
		}else if(accinfo.equals("定期储蓄账户")){
			lstcomacc.add("023456789");
			lstcomacc.add("234567890");
			lstcomacc.add("345678902");
			lstcomacc.add("456789023");
			return lstcomacc;
		}else{
			lstcomacc.add("error");
			return lstcomacc;
		}
	}
	
	public List<String> getuserpsd(String useracc,String userpasd) {
		// TODO Auto-generated method stub
		List<String> lstuserinfo = new ArrayList<String>();
		if(useracc.equals("123456789") && userpasd.equals("123456"))
		{
			String flag = "true";
			String balance = "30000";
			lstuserinfo.add(flag);
			lstuserinfo.add(balance);
			return lstuserinfo;
		}
		else
		{
			String flag = "false";
			String balance = "00000";
			lstuserinfo.add(flag);
			lstuserinfo.add(balance);
			return lstuserinfo;
		}
	}

	public List<String> contransfer(String account, String amtnum, String amtpsd, String amtph) {
		// TODO Auto-generated method stub
		List<String> flag = new ArrayList<String>();
		if(account.equals("123456789")&& amtpsd.equals("123456")){
			flag.add("true");
		}
		
		return null;
	}

	public List<String> transfer(String account, String amtnum, String amtph) {
		// TODO Auto-generated method stub
		return null;
	}

}

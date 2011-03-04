package com.ultrawise.android.bank.webservices.implement.transfer05;

import com.ultrawise.android.bank.webservices.base.transfer05.ITransfer;

public class Transfer implements ITransfer {

	public String getuseracc(String username) {
		// TODO Auto-generated method stub
		if (username == "zhangsan")
			{return "123456789012";}
		else 
			{return null;}
	}

	public String getuserpsd(String usracc) {
		// TODO Auto-generated method stub
		if(usracc == "123456789012")
		{
			return "111111";
		}
		else
		{
			return null;
		}
	}
}

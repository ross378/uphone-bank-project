package com.ultrawise.android.bank.webservices.base.transfer05;

import java.util.List;



public interface ITransfer {
	 List<String> getfiracc(String userinfo);
	 List<String> getcomacctype(String userinfo);
	 List<String> getcomacc(String acctype);
	 List<String> getuserpsd(String useracc,String userpasd);
	 List<String> contransfer(String account, String amtnum, String amtpsd, String amtph);
	 List<String> transfer(String account, String amtnum, String amtph);
}

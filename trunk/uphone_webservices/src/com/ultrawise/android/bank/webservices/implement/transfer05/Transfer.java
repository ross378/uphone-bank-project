package com.ultrawise.android.bank.webservices.implement.transfer05;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.ultrawise.android.bank.webservices.base.transfer05.ITransfer;

public class Transfer implements ITransfer {

	public List<String> getfiracc(String username) {
		// TODO Auto-generated method stub
		List<String> lstuserinfo = new ArrayList<String>();
		
		 String firacc = null;
		 String acctype = null;
		 String acctypename = null;
		
		OperateXmlFile oxf = new OperateXmlFile();
		lstuserinfo.clear();
		//查询用户表
		Document doc = oxf.getFileDocument("Users");
		NodeList nl = doc.getElementsByTagName("user");
		
		for(int i=0; i < nl.getLength(); i++){
		Node my_node = nl.item(i);
		NamedNodeMap nnm = my_node.getAttributes();
		String userid = nnm.getNamedItem("userid").getNodeValue();
		if(userid.equals("Sun01")){
			
			//System.out.print("Sun01"+"\n");
			NodeList nl2 = doc.getElementsByTagName("preant");
			Node preant = nl2.item(0);
			firacc = preant.getFirstChild().getNodeValue();
			//System.out.print(firacc+"\n");
		}
		//System.out.print(userid+"\n");
		}
		
		//查询账户表
		Document doc2 = oxf.getFileDocument("accout");
		NodeList nl3 = doc2.getElementsByTagName("accout");
		for(int j=0; j<nl3.getLength(); j++){
			Node mynode2 = nl3.item(j);
			NamedNodeMap nnm2 = mynode2.getAttributes();
			String allacc = nnm2.getNamedItem("id").getNodeValue();
			//System.out.print(allacc+ "\n");
			if(allacc.equals(firacc)){
				NodeList nl4 = doc2.getElementsByTagName("actype");
				Node paypal = nl4.item(0);
				acctype = paypal.getFirstChild().getNodeValue();
				//System.out.print(acctype + "\n");
			}
		}
		
		//查询账户类型表
		Document doc3 = oxf.getFileDocument("paypal");
		NodeList nl5 = doc3.getElementsByTagName("paypal");
		for(int k=0; k<nl5.getLength(); k++){
			Node mynode3 = nl5.item(k);
			NamedNodeMap nnm3 = mynode3.getAttributes();
			String paypal2 = nnm3.getNamedItem("id").getNodeValue();
			//System.out.print(paypal2 + "\n");
			if(paypal2.equals(acctype)){
				NodeList nl6 = doc3.getElementsByTagName("tyname");
				Node tyname = nl6.item(0);
				acctypename = tyname.getFirstChild().getNodeValue();
				//System.out.println(acctypename +"\n");
			}
		}
		
		lstuserinfo.add(acctypename);
		lstuserinfo.add(firacc);
		return lstuserinfo;
		
	}

	public List<String> getcomacctype(String userinfo) {
		// TODO Auto-generated method stub
		List<String> lstacctype = new ArrayList<String>();
		
		String acctype = null;
		String acctypename = null;
		OperateXmlFile oxf = new OperateXmlFile();
		lstacctype.clear();
		
		//查询账户表
		Document doc2 = oxf.getFileDocument("accout");
		Document doc3 = oxf.getFileDocument("paypal");
		NodeList nl3 = doc2.getElementsByTagName("accout");
		for(int j=0; j<nl3.getLength(); j++){
			Node mynode2 = nl3.item(j);
			NamedNodeMap nnm2 = mynode2.getAttributes();
			String allacc = nnm2.getNamedItem("userid").getNodeValue();
			//System.out.print(allacc+ "\n");
			if(allacc.equals(userinfo)){
				NodeList nl4 = doc2.getElementsByTagName("actype");
					Node paypal = nl4.item(j);
					acctype = paypal.getFirstChild().getNodeValue();
					
					//查询账户类型表
					
					NodeList nl5 = doc3.getElementsByTagName("paypal");
					for(int k=0; k<nl5.getLength(); k++){
						Node mynode3 = nl5.item(k);
						NamedNodeMap nnm3 = mynode3.getAttributes();
						String paypal2 = nnm3.getNamedItem("id").getNodeValue();
						//System.out.print(paypal2 + "\n");
						if(paypal2.equals(acctype)){
							NodeList nl6 = doc3.getElementsByTagName("tyname");
							Node tyname = nl6.item(k);
							acctypename = tyname.getFirstChild().getNodeValue();
							lstacctype.add(acctypename);
							//System.out.println(acctypename +"\n");
						}
					}
				//System.out.print(acctype + "\n");
			}
		}
		
		return lstacctype;	
		
	}
	
	public List<String> getcomacc(String username, String accinfo) {
		// TODO Auto-generated method stub
		List<String> lstcomacc = new ArrayList<String>();
		
		String account = null;
		String acctype = null;
		String acctypename = null;
		String acctypeid = null;
		
		if(accinfo.equals("活期储蓄卡")){
			acctypeid = "paypal01";
		}else if(accinfo.equals("定期储蓄卡")){
			acctypeid = "paypal02";
		}else if(accinfo.equals("活期储蓄")){
			acctypeid = "paypal03";
		}else{
			acctypeid = "paypal01";
		}
		
		OperateXmlFile oxf = new OperateXmlFile();
		lstcomacc.clear();
		//查询账户表
		Document doc2 = oxf.getFileDocument("accout");
		NodeList nl3 = doc2.getElementsByTagName("accout");
		for(int j=0; j<nl3.getLength(); j++){
			Node mynode2 = nl3.item(j);
			NamedNodeMap nnm2 = mynode2.getAttributes();
			String alluser = nnm2.getNamedItem("userid").getNodeValue();
			//System.out.print(allacc+ "\n");
			if(alluser.equals(username)){
				NodeList nl4 = doc2.getElementsByTagName("actype");
				Node paypal = nl4.item(j);
				acctype = paypal.getFirstChild().getNodeValue();
				if(acctype.equals(acctypeid)){
					account = nnm2.getNamedItem("id").getNodeValue();
					lstcomacc.add(account);
				}
				//System.out.print(acctype + "\n");
			}
		}
		return lstcomacc;
		
	}
	
	public List<String> getuserpsd(String useracc,String userpasd) {
		// TODO Auto-generated method stub
		List<String> lstuserinfo = new ArrayList<String>();
		
		String account = null;
		String accpwd = null;
		String balance = null;
		String flag = null;
		
		OperateXmlFile oxf = new OperateXmlFile();
		lstuserinfo.clear();
		//查询账户表
		Document doc2 = oxf.getFileDocument("accout");
		NodeList nl3 = doc2.getElementsByTagName("accout");
		for(int j=0; j<nl3.getLength(); j++){
			Node mynode2 = nl3.item(j);
			NamedNodeMap nnm2 = mynode2.getAttributes();
			String allacc = nnm2.getNamedItem("id").getNodeValue();
			//System.out.print(allacc+ "\n");
			if(allacc.equals(useracc)){
				NodeList nl4 = doc2.getElementsByTagName("actpwd");
				Node paypal = nl4.item(j);
				accpwd = paypal.getFirstChild().getNodeValue();
				if(accpwd.equals(userpasd)){
					NodeList nl5 = doc2.getElementsByTagName("balance");
					Node bal = nl5.item(j);
					balance = bal.getFirstChild().getNodeValue();
					
					flag = "true";
					lstuserinfo.add(flag);
					lstuserinfo.add(balance);
					
				}else{
					flag = "false";
					balance = "0000,00";
					lstuserinfo.add(flag);
					lstuserinfo.add(balance);					
				}
			}
		}
		return lstuserinfo;

		
	}

	public List<String> contransfer(String useracc, String amtnum, String userpasd, String amtph) {
		// TODO Auto-generated method stub
		List<String> flag = new ArrayList<String>();
		
		String userph = null;
		String accpwd = null;
		String transamt = null;
		String fee = "000";
		String receiver = "error";
		Boolean flagph = false;
		Boolean flagpwd = false;
		
		Double dbamt = (Double.parseDouble(amtnum)*0.002);
		fee = dbamt.toString();
		
		OperateXmlFile oxf = new OperateXmlFile();
		flag.clear();
		
		//查询用户表
		Document doc = oxf.getFileDocument("Users");
		NodeList nl = doc.getElementsByTagName("phnum");
		for(int i=0; i < nl.getLength(); i++){
			Node my_node = nl.item(i);
			userph = my_node.getFirstChild().getNodeValue();
			if(userph.equals(amtph)){
				NodeList nl2 = doc.getElementsByTagName("name");
				Node user_name = nl2.item(i);
				receiver = user_name.getFirstChild().getNodeValue();
				
				flagph = true;
			}
		}
		
		//查询账户表
		Document doc2 = oxf.getFileDocument("accout");
		NodeList nl3 = doc2.getElementsByTagName("accout");
		for(int j=0; j<nl3.getLength(); j++){
			Node mynode2 = nl3.item(j);
			NamedNodeMap nnm2 = mynode2.getAttributes();
			String allacc = nnm2.getNamedItem("id").getNodeValue();
			//System.out.print(allacc+ "\n");
			if(allacc.equals(useracc)){
				NodeList nl4 = doc2.getElementsByTagName("actpwd");
				Node paypal = nl4.item(j);
				accpwd = paypal.getFirstChild().getNodeValue();
				if(accpwd.equals(userpasd)){flagpwd = true;}
			}
		}
		if(flagph==true){flag.add("trueph");}else{flag.add("falseph");}
		if(flagpwd==true){flag.add("truepwd");}else{flag.add("falsepwd");}
		flag.add(fee);
		flag.add(receiver);
		return flag;
		
	}

	public List<String> transfer(String account, String amtnum, String amtph) {
		// TODO Auto-generated method stub
		
		List<String> flag = new ArrayList<String>();
		
		String balance = null;
		String balance2 = null;
		String newbal2 = null;
		Double dbamtfee = null;
		Double transamt = null;
		Double newbal = null;
		Boolean savexml = false;
		Boolean savexml2 = false;
		
		String userph = null;
		String recpreant = null;
		String recbalance = null;
		String recbalance2 = null;
		String recnewbal2 = null;
		Double recnewbal = null;
		
		String flag1 = "failed";
		
		dbamtfee = (Double.parseDouble(amtnum)*0.002);
		transamt = Double.parseDouble(amtnum);
		OperateXmlFile oxf = new OperateXmlFile();
		flag.clear();
		
		//查询账户表
		Document doc2 = oxf.getFileDocument("accout");
		NodeList nl3 = doc2.getElementsByTagName("accout");
		for(int j=0; j<nl3.getLength(); j++){
			Node mynode2 = nl3.item(j);
			NamedNodeMap nnm2 = mynode2.getAttributes();
			String allacc = nnm2.getNamedItem("id").getNodeValue();
			//System.out.print(allacc+ "\n");
			if(allacc.equals(account)){
				NodeList nl4 = doc2.getElementsByTagName("balance");
				Node nodebal = nl4.item(j);
				balance = nodebal.getFirstChild().getNodeValue();
				//System.out.print(acctype + "\n");
				
				balance2 = balance.replaceAll(",", "");
				Double dbbal = Double.parseDouble(balance2);
				newbal = dbbal - transamt - dbamtfee;
				newbal2 = newbal.toString();
				
				Node nodebal2 = nl4.item(j);
				nodebal2.getFirstChild().setNodeValue(newbal2);
				savexml = oxf.saveDocument(doc2, "accout");
				
				break;
			}
		}
		
		//查询用户表
		Document doc = oxf.getFileDocument("Users");
		NodeList nl = doc.getElementsByTagName("phnum");
		for(int i=0; i < nl.getLength(); i++){
			Node my_node = nl.item(i);
			userph = my_node.getFirstChild().getNodeValue();
			if(userph.equals(amtph)){
				NodeList nl2 = doc.getElementsByTagName("preant");
				Node user_preant = nl2.item(i);
				recpreant = user_preant.getFirstChild().getNodeValue();
				
				//查询账户表
				Document doc3 = oxf.getFileDocument("accout");
				NodeList nl5 = doc2.getElementsByTagName("accout");
				for(int j=0; j<nl3.getLength(); j++){
					Node mynode2 = nl3.item(j);
					NamedNodeMap nnm2 = mynode2.getAttributes();
					String allacc = nnm2.getNamedItem("id").getNodeValue();
					//System.out.print(allacc+ "\n");
					if(allacc.equals(recpreant)){
						NodeList nl4 = doc3.getElementsByTagName("balance");
						Node recbal = nl4.item(j);
						recbalance = recbal.getFirstChild().getNodeValue();
						
						recbalance2 = recbalance.replaceAll(",", "");
						Double dbrecbal = Double.parseDouble(recbalance2);
						recnewbal = dbrecbal + transamt;
						recnewbal2 = recnewbal.toString();
						
						Node recbal2 = nl4.item(j);
						recbal2.getFirstChild().setNodeValue(recnewbal2);
						savexml2 = oxf.saveDocument(doc3, "accout");
						
						break;
					}
				}
			}
		}
		
		if(savexml == true && savexml2 == true){flag1 = "succeed";}
		flag.add(flag1);
		flag.add(newbal2);
		return flag;
	}

	public List<String> contransfer2(String account, String amtnum,	String amtpsd, String amtph) {
		// TODO Auto-generated method stub
		
		List<String> flag = new ArrayList<String>();
		
		String receiid = null;
		String accpwd = null;
		String transamt = null;
		String receid = null;
		String fee = "000";
		String receiver = "error";
		Boolean flagph = false;
		Boolean flagpwd = false;
		
		Double dbamt = (Double.parseDouble(amtnum)*0.002);
		fee = dbamt.toString();
		
		OperateXmlFile oxf = new OperateXmlFile();
		flag.clear();

		//查询账户表
		Document doc2 = oxf.getFileDocument("accout");
		NodeList nl3 = doc2.getElementsByTagName("accout");
		for(int j=0; j<nl3.getLength(); j++){
			Node mynode2 = nl3.item(j);
			NamedNodeMap nnm2 = mynode2.getAttributes();
			String allacc = nnm2.getNamedItem("id").getNodeValue();
			//System.out.print(allacc+ "\n");
			if(allacc.equals(account)){
				NodeList nl4 = doc2.getElementsByTagName("actpwd");
				Node paypal = nl4.item(j);
				accpwd = paypal.getFirstChild().getNodeValue();
				if(accpwd.equals(amtpsd)){flagpwd = true;}
			}
		}
		//查询账户表2
		Document doc3 = oxf.getFileDocument("accout");
		NodeList nl5 = doc2.getElementsByTagName("accout");
		for(int j=0; j<nl5.getLength(); j++){
			Node mynode2 = nl5.item(j);
			NamedNodeMap nnm2 = mynode2.getAttributes();
			String allacc = nnm2.getNamedItem("id").getNodeValue();
			//System.out.print(allacc+ "\n");
			if(allacc.equals(amtph)){
				flagph = true;
				String recid = nnm2.getNamedItem("userid").getNodeValue();
				//查询用户表
				Document doc = oxf.getFileDocument("Users");
				NodeList nl = doc.getElementsByTagName("user");
				for(int i=0; i < nl.getLength(); i++){
					Node mynode3 = nl.item(i);
					NamedNodeMap nnm3 = mynode3.getAttributes();
					String alluserid = nnm3.getNamedItem("userid").getNodeValue();
					if(alluserid.equals(recid)){
						NodeList nl2 = doc.getElementsByTagName("name");
						Node user_name = nl2.item(i);
						receiver = user_name.getFirstChild().getNodeValue();
					}
				}
			}
		}
		
		
		if(flagph==true){flag.add("trueph");}else{flag.add("falseph");}
		if(flagpwd==true){flag.add("truepwd");}else{flag.add("falsepwd");}
		flag.add(fee);
		flag.add(receiver);
		return flag;
		
	}

	public List<String> transfer2(String account, String amtnum, String amtph) {
		// TODO Auto-generated method stub
		
		List<String> flag = new ArrayList<String>();
		
		String balance = null;
		String balance2 = null;
		String newbal2 = null;
		Double dbamtfee = null;
		Double transamt = null;
		Double newbal = null;
		Boolean savexml = false;
		Boolean savexml2 = false;
		
		String userph = null;
		String recpreant = null;
		String recbalance = null;
		String recbalance2 = null;
		String recnewbal2 = null;
		Double recnewbal = null;
		
		String flag1 = "failed";
		
		dbamtfee = (Double.parseDouble(amtnum)*0.002);
		transamt = Double.parseDouble(amtnum);
		OperateXmlFile oxf = new OperateXmlFile();
		flag.clear();
		
		//查询账户表
		Document doc2 = oxf.getFileDocument("accout");
		NodeList nl3 = doc2.getElementsByTagName("accout");
		for(int j=0; j<nl3.getLength(); j++){
			Node mynode2 = nl3.item(j);
			NamedNodeMap nnm2 = mynode2.getAttributes();
			String allacc = nnm2.getNamedItem("id").getNodeValue();
			//System.out.print(allacc+ "\n");
			if(allacc.equals(account)){
				NodeList nl4 = doc2.getElementsByTagName("balance");
				Node nodebal = nl4.item(j);
				balance = nodebal.getFirstChild().getNodeValue();
				//System.out.print(acctype + "\n");
				
				balance2 = balance.replaceAll(",", "");
				Double dbbal = Double.parseDouble(balance2);
				newbal = dbbal - transamt - dbamtfee;
				newbal2 = newbal.toString();
				
				Node nodebal2 = nl4.item(j);
				nodebal2.getFirstChild().setNodeValue(newbal2);
				savexml = oxf.saveDocument(doc2, "accout");
				
				break;
			}
		}
				
				//查询账户表
				Document doc3 = oxf.getFileDocument("accout");
				NodeList nl5 = doc2.getElementsByTagName("accout");
				for(int j=0; j<nl3.getLength(); j++){
					Node mynode2 = nl3.item(j);
					NamedNodeMap nnm2 = mynode2.getAttributes();
					String allacc = nnm2.getNamedItem("id").getNodeValue();
					//System.out.print(allacc+ "\n");
					if(allacc.equals(amtph)){
						NodeList nl4 = doc3.getElementsByTagName("balance");
						Node recbal = nl4.item(j);
						recbalance = recbal.getFirstChild().getNodeValue();
						
						recbalance2 = recbalance.replaceAll(",", "");
						Double dbrecbal = Double.parseDouble(recbalance2);
						recnewbal = dbrecbal + transamt;
						recnewbal2 = recnewbal.toString();
						
						Node recbal2 = nl4.item(j);
						recbal2.getFirstChild().setNodeValue(recnewbal2);
						savexml2 = oxf.saveDocument(doc3, "accout");
						
						break;
					}
				}
		if(savexml == true && savexml2 == true){flag1 = "succeed";}
		flag.add(flag1);
		flag.add(newbal2);
		return flag;
		
	}

}

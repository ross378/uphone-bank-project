package com.ultrawise.android.bank.webservices.implement.payment06;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.ultrawise.android.bank.webservices.base.payment06.IPaymentPend;

public class PaymentPend implements IPaymentPend {

	FileOperation file;

	// 修改账户余额
	public void updateAccountBalance(String userNo,String paymentActNo,String paymentAmt) {
		// 修改账户的余额
		// TODO Auto-generated method stub
		String num;
		Document doc = file.getFileDocument("accout");
		NodeList n = doc.getElementsByTagName("accout");
		for (int j = 0; j < n.getLength(); j++) {
			Node myNode = n.item(j);
			NamedNodeMap nnm = myNode.getAttributes();
			// nnm.item(0).getNodeValue().equals(usrNo)
			if (userNo.equals("zhangsan")) {
				if (nnm.item(0).getNodeValue().equals(paymentActNo)) {
					num = nnm.item(0).getNodeValue();// 取出帐号
					// 获的他的子节点
					NodeList n2 = myNode.getChildNodes();
					// 取出帐号的余额和密码
					for (int i = 1; i < n2.getLength(); i += 2) {
						if (n2.item(i).getNodeName().equals("balance")) {
							n2.item(i).getFirstChild().setNodeValue(paymentAmt+",00");
						}
					}
				}
			}
		}
		file.saveDocument(doc, "accout");
	}

	//
	public String updateAccountStatue(String userNo, String paymentActNo) {
		// TODO Auto-generated method stub
		return null;
	}

	//
	public String verifyAccount(String usrNo, String paymentActNo,
			String paymentActPasswd) {
		// TODO Auto-generated method stub
		return null;
	}

	// 获取账户余额
	public String getAccountBalance(String userNo, String paymentActNo) {
		// 取缴费账户的余额
		// TODO Auto-generated method stub
		String num = null;
		String balance = null;
		String pwd = null;
		StringBuffer numAndBlance = new StringBuffer();
		Document doc = file.getFileDocument("accout");
		NodeList n = doc.getElementsByTagName("accout");
		for (int j = 0; j < n.getLength(); j++) {
			Node myNode = n.item(j);
			NamedNodeMap nnm = myNode.getAttributes();
			if (nnm.item(0).getNodeValue().equals(paymentActNo)) {
				num = nnm.item(0).getNodeValue();// 取出帐号
				// 获的他的子节点
				NodeList n2 = myNode.getChildNodes();
				// 取出帐号的余额和密码
				for (int i = 1; i < n2.getLength(); i += 2) {
					if (n2.item(i).getNodeName().equals("balance")) {
						balance = n2.item(i).getFirstChild().getNodeValue();
					}
					if (n2.item(i).getNodeName().equals("actpwd")) {
						pwd = n2.item(i).getFirstChild().getNodeValue();
					}

				}
			}
		}
		System.out.println(num);
		numAndBlance.append(num + ":" + balance + ":" + pwd);
		return numAndBlance.toString();
	}

	// 添加缴费日期和 添加便捷服务的记录
	public void addPaymentDate(String userNo, String paymentName,String paymentActNo) {
		// TODO Auto-generated method stub
		String pay_Name=paymentName.split("#")[0];
		System.out.println(pay_Name+"/////////////");
		if(pay_Name.equals("三月份水费"))
		{
			addDate("wtdemand",userNo,paymentName,paymentActNo);
			
		}
		if(pay_Name.equals("三月份电费"))
		{
			addDate("powdemand",userNo,paymentName,paymentActNo);
			
		}
		if(pay_Name.equals("三月份煤气费"))
		{
			addDate("gasdemand",userNo,paymentName,paymentActNo);
			
		}
		if(pay_Name.equals("三月份房租费"))
		{
			addDate("rendemand",userNo,paymentName,paymentActNo);
			
		}
		if(pay_Name.equals("手机")){
			
			Document doc1=file.insertNode(file.getFileDocument("preplen"),"Prep"+"#"+"preplen"+"#"+paymentName);
			file.saveDocument(doc1, "preplen");
			
		}
	    if(pay_Name.equals("QQ")){
	    	Document doc1=file.insertNode(file.getFileDocument("qreplen"),"Qrep"+"#"+"qreplen"+"#"+paymentName);
			file.saveDocument(doc1, "qreplen");
			
		}
	   if(pay_Name.equals("网易")){
		
		   Document doc1=file.insertNode(file.getFileDocument("nesfil"),"Nes"+"#"+"nesfil"+"#"+paymentName);
			file.saveDocument(doc1, "nesfil");
	  }

	}

	private void addDate(String fileName, String userNo, String paymentName,String paymentActNo) {
		Document doc=file.getFileDocument(fileName);
		NodeList userNode = doc.getElementsByTagName("user");

		Calendar cal=new GregorianCalendar();//时间有关的对象
		// 读取所有的用户节点
		for (int i = 0; i <= userNode.getLength(); i = i + 2) {
			Node n = userNode.item(i);// 遍历所有的用户节点
			NamedNodeMap nodeMap = n.getAttributes();// 取出节点内部所有的属性\
			if (nodeMap.getNamedItem("name").getNodeValue().equals(userNo)) {
				// 判断此节点name属性和用户名是否相同
				// 跳到他的子节点
				System.out.println("----------------------------");
				NodeList n2 = n.getChildNodes();
				for (int j = 1; j < n2.getLength(); j += 2) {
					Node my_node1 = n2.item(j);
					NamedNodeMap nodeMap1 = my_node1.getAttributes();
					// 月份
					String month = nodeMap1.getNamedItem("month")
							.getNodeValue();
					if (month.equals(paymentName)) {
						// 遍历水费的子节点
						NodeList n3 = my_node1.getChildNodes();
						for (int q = 1; q < n3.getLength(); q += 2) {
							Node my_node2 = n3.item(q);
							if (my_node2.getNodeName().equals("date")) {// 金额
								my_node2.getFirstChild().setNodeValue(
										cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH)+1)
												+ "-" + cal.get(Calendar.DAY_OF_MONTH));

							}
							if (my_node2.getNodeName().equals("account")) {// 金额
								my_node2.getFirstChild().setNodeValue(paymentActNo);

							}
						}
					}
				}
			}
		}
		file.saveDocument(doc, fileName);
	
	}
}

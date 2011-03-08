package com.ultrawise.android.bank.webservices.implement.account_management01;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

import com.ultrawise.android.bank.webservices.base.account_management01.IOrderCard;

public class OrderCard implements IOrderCard {

	// 功能0104
	public List<String> getOrderInfo(String account) {
		// TODO Auto-generated method stub
		ArrayList<String> lstStr = new ArrayList<String>();

		NodeList nl = All.readTxt("order.txt").getElementsByTagName("act");// 获取所有的账户种类节点
		for (int i = 0; i < nl.getLength(); i++) {
			String value = nl.item(i).getAttributes().getNamedItem("id")
					.getNodeValue();
			if (account.equals(value)) {
				// 如果账号相同
				NodeList n = nl.item(i).getChildNodes();
				for (int y = 1; y < n.getLength(); y += 2) {
					String v = n.item(y).getFirstChild().getNodeValue();
					lstStr.add(v);
				}
			}
		}
		return lstStr;
	}

	// 功能0112
	public boolean setOrderCard(String account) {
		// TODO Auto-generated method stub
		Document doc = All.readTxt("accout.txt");
		NodeList nl = doc.getElementsByTagName("accout");
		for (int i = 0; i < nl.getLength(); i++) {
			NamedNodeMap att = nl.item(i).getAttributes();
			String value = att.getNamedItem("id").getNodeValue();
			if (value.equals(account)) {
				// 如果账号相同
				nl = nl.item(i).getChildNodes();
				for (int y = 0; y < nl.getLength(); y++) {
					String nName = nl.item(y).getNodeName();
					if (nName.equals("orderstate")) {
						// 如果是预约状态
						nl.item(y).getFirstChild().setNodeValue("预约换卡");
						All.writeTxt(doc, "accout.txt");
						return true;
					}
				}
			}
		}
		return false;
	}

	// 功能0110
	public List<String> getNet() {
		// TODO Auto-generated method stub
		List<String> lstNet = new ArrayList<String>();
		Document doc = All.readTxt("net.txt");
		NodeList nl = doc.getElementsByTagName("net");
		for (int i = 0; i < nl.getLength(); i++) {
			NamedNodeMap att = nl.item(i).getAttributes();
			String net = att.getNamedItem("name").getNodeValue();
			lstNet.add(net);
		}
		return lstNet;
	}

	// 功能111
	public String getAddress(String net) {
		// TODO Auto-generated method stub

		Document doc = All.readTxt("net.txt");
		NodeList nl = doc.getElementsByTagName("net");
		for (int i = 0; i < nl.getLength(); i++) {
			NamedNodeMap att = nl.item(i).getAttributes();
			String netValue = att.getNamedItem("name").getNodeValue();
			if (netValue.equals(net)) {
				return nl.item(i).getFirstChild().getNodeValue();
			}
		}
		return "";
	}

	// 功能0113
	public List<String> getUnorderAcc(String userNo, String accTypeName) {
		ArrayList<String> lstStr = new ArrayList<String>();
		boolean isSameType = false;
		boolean isNotOrder = false;

		String accTypeId = All.getAccTypeId(accTypeName);// 获取账户类型id
		NodeList nl = All.readTxt("accout.txt").getElementsByTagName("accout");
		for (int i = 0; i < nl.getLength(); i++) {
			if (nl.item(i).hasAttributes()) {
				NamedNodeMap att = nl.item(i).getAttributes();
				String idValue = att.getNamedItem("userid").getNodeValue();
				if (idValue.equals(userNo)) {
					// 如果和用户号相等
					NodeList nl2 = nl.item(i).getChildNodes();
					for (int y = 1; y < nl2.getLength(); y += 2) {
						String nName = nl2.item(y).getNodeName();
						if (nName.equals("actype")) {
							String nValue = nl2.item(y).getFirstChild()
									.getNodeValue();
							if (nValue.equals(accTypeId)) {
								// 如果账户类型id相等
								isSameType = true;
							}
						}
						if (nName.equals("orderstate")) {
							String nValue = nl2.item(y).getFirstChild()
									.getNodeValue();
							if (!nValue.equals("预约换卡")) {
								// 如果不是预约的
								isNotOrder = true;
							}
						}
						if (isSameType && isNotOrder) {
							String accId = att.getNamedItem("id")
									.getNodeValue();
							lstStr.add(accId);
							isSameType = false;
							isNotOrder = false;
						}
					}
				}
			}
			isSameType = false;
			isNotOrder = false;
		}
		return lstStr;
	}

}

package com.ultrawise.android.bank.webservices.implement.account_management01;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

import com.sun.research.ws.wadl.Doc;
import com.ultrawise.android.bank.webservices.base.account_management01.IAccountPre;

public class AccountPre implements IAccountPre {

	public String getPreAccount(String userNo) {
		// TODO Auto-generated method stub
		Document doc = All.readTxt("Users.txt");
		NodeList nl = doc.getElementsByTagName("user");
		for (int i = 0; i < nl.getLength(); i++) {
			String userid = nl.item(i).getAttributes().getNamedItem("userid")
					.getNodeValue();
			if (userid.equals(userNo)) {
				nl = nl.item(i).getChildNodes();
				for (int x = 1; x < nl.getLength(); x += 2) {
					String nName = nl.item(x).getNodeName();
					if (nName.equals("preant")) {
						return nl.item(x).getFirstChild().getNodeValue();
					}
				}
			}
		}
		return "";
	}

	public List<String> getUnpreAccount(String userNo) {
		// 需要绑定的和添加过的，但不是挂失的
		ArrayList<String> lstStr = new ArrayList<String>();
		boolean isNotLoss = false;
		boolean isBind = false;
		boolean isAdd = false;

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
						if (nName.equals("loss")) {
							String nValue = nl2.item(y).getFirstChild()
									.getNodeValue();

							if (!nValue.equals("挂失")) {
								// 如果账户类型id相等
								isNotLoss = true;
							}
						}
						if (nName.equals("bind")) {

							String nValue = nl2.item(y).getFirstChild()
									.getNodeValue();

							if (nValue.equals("是")) {
								// 如果是绑定的
								isBind = true;
							}
						}
						if (nName.equals("isadd")) {
							String isadd = nl2.item(y).getFirstChild()
									.getNodeValue();
							if (isadd.equals("是")) {
								isAdd = true;
							}
						}
						if (isNotLoss && isBind && isAdd) {
							String accId = att.getNamedItem("id")
									.getNodeValue();
							lstStr.add(accId);
							isNotLoss = false;
							isBind = false;
							isAdd = false;
						}
					}
				}
			}
			isNotLoss = false;
			isBind = false;
			isAdd = false;
		}
		return lstStr;
	}

	public boolean setPreAccount(String userNo, String account) {
		// TODO Auto-generated method stub
		Document doc = All.readTxt("Users.txt");
		NodeList nl = doc.getElementsByTagName("user");
		for (int i = 0; i < nl.getLength(); i++) {
			String userid = nl.item(i).getAttributes().getNamedItem("userid")
					.getNodeValue();
			if (userid.equals(userNo)) {
				nl = nl.item(i).getChildNodes();
				for (int x = 1; x < nl.getLength(); x += 2) {
					String nName = nl.item(x).getNodeName();
					if (nName.equals("preant")) {
						nl.item(x).getFirstChild().setNodeValue(account);
						All.writeTxt(doc, "Users.txt");
						return true;
					}
				}
			}
		}
		return false;
	}

}

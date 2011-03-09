package com.ultrawise.android.bank.webservices.implement.account_management01;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

import com.ultrawise.android.bank.webservices.base.account_management01.IAccountAdd;

public class AccountAdd implements IAccountAdd {

	public boolean addAccount(String userNo, String accTypeName,
			String account, String accountNickName, String password) {
		// TODO Auto-generated method stub
		boolean isSamePwd = false;
		boolean isSameType = false;
		Document doc = All.readTxt("accout.txt");
		String accTypeId = All.getAccTypeId(accTypeName);
		doc.normalize();
		NodeList nl = doc.getElementsByTagName("accout");
		for (int i = 0; i < nl.getLength(); i++) {
			if (nl.item(i).hasAttributes()) {
				NamedNodeMap att = nl.item(i).getAttributes();
				String userId = att.getNamedItem("userid").getNodeValue();
				String accId = att.getNamedItem("id").getNodeValue();
				if (userId.equals(userNo) && accId.equals(account)) {
					nl = nl.item(i).getChildNodes();
					Node node = null;
					Node nodeAdd = null;
					for (int x = 0; x < nl.getLength(); x++) {
						String nName = nl.item(x).getNodeName();
						if (nName.equals("actpwd")) {
							String pwd = nl.item(x).getFirstChild()
									.getNodeValue();
							if (pwd.equals(password)) {
								isSamePwd = true;
							}
						}
						if (nName.equals("actype")) {
							String typeId = nl.item(x).getFirstChild()
									.getNodeValue();
							if (typeId.equals(accTypeId)) {
								isSameType = true;
							}
						}
						if (nName.equals("aliss")) {
							node = nl.item(x);
						}
						if (nName.equals("isadd")) {
							nodeAdd = nl.item(x);
						}
						if (node != null && isSamePwd && nodeAdd != null
								&& isSameType) {
							node.getFirstChild().setNodeValue(accountNickName);
							nodeAdd.getFirstChild().setNodeValue("是");
							All.writeTxt(doc, "accout.txt");
							isSamePwd = false;
							isSameType = false;
							return true;
						}
					}
				}
				isSamePwd = false;
				isSameType = false;
			}

		}
		return false;
	}

}

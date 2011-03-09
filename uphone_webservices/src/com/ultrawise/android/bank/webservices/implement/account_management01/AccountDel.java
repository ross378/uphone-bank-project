package com.ultrawise.android.bank.webservices.implement.account_management01;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.ultrawise.android.bank.webservices.base.account_management01.IAccountDel;

public class AccountDel implements IAccountDel {

	public boolean deleteAccount(String bindAccount) {
		// TODO Auto-generated method stub
		Document doc = All.readTxt("accout.txt");
		NodeList nl = doc.getElementsByTagName("accout");
		for (int i = 0; i < nl.getLength(); i++) {
			if (nl.item(i).hasAttributes()) {
				NamedNodeMap att = nl.item(i).getAttributes();
				String idValue = att.getNamedItem("id").getNodeValue();
				if (idValue.equals(bindAccount)) {
					// 如果和未绑定账号的相等
					nl = nl.item(i).getChildNodes();
					for (int y = 1; y < nl.getLength(); y += 2) {
						String nodeName = nl.item(y).getNodeName();
						if (nodeName.equals("bind")) {
							// 如果是绑定的属性
							nl.item(y).getFirstChild().setNodeValue("x");
							All.writeTxt(doc, "accout");
							return true;
						}

					}
				}
			}

		}
		return false;
	}

}

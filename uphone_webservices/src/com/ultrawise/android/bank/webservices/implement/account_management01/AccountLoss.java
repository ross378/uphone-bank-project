package com.ultrawise.android.bank.webservices.implement.account_management01;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

import com.ultrawise.android.bank.webservices.base.account_management01.IAccountLoss;

public class AccountLoss implements IAccountLoss {

	// 这个写的还满快的，难道是今天有的洗澡，所以加快速度？
	// 我极其邪恶的在accout文件里面添加了3个标签，估计其他人读取都会出问题，哈哈，谁让你们都按位置来读取
	public boolean setLoss(String unlossAccount) {
		// TODO Auto-generated method stub
		Document doc = All.readTxt("accout.txt");
		NodeList nl = doc.getElementsByTagName("accout");
		for (int i = 0; i < nl.getLength(); i++) {
			if (nl.item(i).hasAttributes()) {
				NamedNodeMap att = nl.item(i).getAttributes();
				String idValue = att.getNamedItem("id").getNodeValue();
				if (idValue.equals(unlossAccount)) {
					// 如果和账号相同
					nl = nl.item(i).getChildNodes();
					for (int y = 1; y < nl.getLength(); y += 2) {
						String nodeName = nl.item(y).getNodeName();
						if (nodeName.equals("loss")) {
							// 如果是绑定的属性
							nl.item(y).getFirstChild().setNodeValue("挂失");
							All.writeTxt(doc, "accout.txt");
							return true;
						}
					}
				}
			}
		}
		return false;
	}

}

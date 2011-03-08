package com.ultrawise.android.bank.webservices.implement.account_management01;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;

import org.w3c.dom.NodeList;

import com.ultrawise.android.bank.webservices.base.account_management01.INickName;

/**
 * 路径要注意
 * 
 * @author hosolo
 * 
 */
public class NickName implements INickName {

	/**
	 * 功能号0103
	 */
	public void setNickName(String account, String nickName) {
		List<String> lstStr = new ArrayList<String>();
		// TODO 设置别名
		Document doc = All.readTxt("accout.txt");
		doc.normalize();

		NodeList nl = doc.getElementsByTagName("accout");// 获取节点
		for (int i = 0; i < nl.getLength(); i++) {
			if (nl.item(i).hasAttributes()) {// 如果有属性
				NamedNodeMap nnm = nl.item(i).getAttributes();// 获取节点的属性

				String attValue = nnm.getNamedItem("id").getNodeValue();
				if (attValue.equals(account)) {
					// 如果值和参数相同
					nl = nl.item(i).getChildNodes();
					for (int z = 0; z < nl.getLength(); z++) {
						String nodeName = nl.item(z).getNodeName();
						if (nodeName.equals("aliss")) {
							nl.item(z).getFirstChild().setNodeValue(nickName);// 设置节点的值
							// 写入文件
							All.writeTxt(doc, "accout.txt");
						}
					}
				}

			}
		}
	}
}

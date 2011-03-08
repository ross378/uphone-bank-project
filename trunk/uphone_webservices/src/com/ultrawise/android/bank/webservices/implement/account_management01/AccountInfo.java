package com.ultrawise.android.bank.webservices.implement.account_management01;

import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

import com.ultrawise.android.bank.webservices.base.account_management01.IAccountInfo;

/**
 * for循环非常的恶心，我基本浪费了一天的时间在for循环嵌套上，什么时候能用上java7啊，快点给我来linq和lambda撒
 * 
 * @author hosolo
 * 
 */
public class AccountInfo implements IAccountInfo {

	/**
	 * 功能0103
	 */
	public List<String> getAccInfo(String account) {
		// TODO 查询账户详情
		ArrayList<String> lstStr = new ArrayList<String>();
		Document doc = All.readTxt("accout.txt");
		doc.normalize();
		NodeList nl = doc.getElementsByTagName("accout");// 获取所有账户字节点
		for (int i = 0; i < nl.getLength(); i++) {
			if (nl.item(i).hasAttributes()) {
				NamedNodeMap nnm = nl.item(i).getAttributes();

				String attValue = nnm.getNamedItem("id").getNodeValue();
				if (attValue.equals(account)) {
					// 如果账号相等
					nl = nl.item(i).getChildNodes();
					for (int y = 1; y < nl.getLength(); y += 2) {
						// System.out.println(nl.item(y).getNodeName());
						// 把账户下所有的节点的值都保存起来，直接传到客户端
						String value = nl.item(y).getFirstChild()
								.getNodeValue();
						lstStr.add(value);
					}
				}
			}
		}
		return lstStr;
	}
}

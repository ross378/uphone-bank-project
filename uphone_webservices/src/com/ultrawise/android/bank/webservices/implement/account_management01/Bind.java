package com.ultrawise.android.bank.webservices.implement.account_management01;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.ultrawise.android.bank.webservices.base.account_management01.IBind;

/**
 * 这个算法写的真是邪恶，难看死了
 * 
 * @author hosolo
 * 
 */
public class Bind implements IBind {

	/**
	 * 测试的时候注意点文件存放的路径，不是在工程目录下
	 */
	public boolean setBind(String unbindAccount, String password) {
		// TODO 核对密码是否相等
		boolean isSamePwd = false;

		Document doc = All.readTxt("accout.txt");
		NodeList nl = doc.getElementsByTagName("accout");
		for (int i = 0; i < nl.getLength(); i++) {
			if (nl.item(i).hasAttributes()) {
				NamedNodeMap att = nl.item(i).getAttributes();
				String idValue = att.getNamedItem("id").getNodeValue();
				if (idValue.equals(unbindAccount)) {
					// 如果和未绑定账号的相等
					nl = nl.item(i).getChildNodes();
					Node node = null;
					for (int y = 1; y < nl.getLength(); y += 2) {
						String nodeName = nl.item(y).getNodeName();
						if (nodeName.equals("bind")) {
							// 如果是绑定的属性
							node = nl.item(y);
						}
						if (nodeName.equals("actpwd")) {
							String pwd = nl.item(y).getFirstChild()
									.getNodeValue();
							if (password.equals(pwd)) {
								// 如果密码相同
								isSamePwd = true;
							}
						}

						if (node != null && isSamePwd == true) {
							node.getFirstChild().setNodeValue("是");
							All.writeTxt(doc, "accout.txt");
							isSamePwd = false;

							return true;
						}
					}
				}
			}
			isSamePwd = false;

		}
		return false;
	}

	public List<String> getUnBindAcc(String userNo, String accTypeName) {
		ArrayList<String> lstStr = new ArrayList<String>();

		boolean isSameType = false;
		boolean isNotBind = false;
		boolean isNotLoss = false;
		boolean isAdd = false;

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
						if (nName.equals("bind")) {
							String nValue = nl2.item(y).getFirstChild()
									.getNodeValue();
							if (nValue.equals("否")) {
								// 如果是未绑定的
								isNotBind = true;
							}
						}
						if (nName.equals("loss")) {
							String loss = nl2.item(y).getFirstChild()
									.getNodeValue();
							if (!loss.equals("挂失")) {
								isNotLoss = true;
							}
						}
						if (nName.equals("isadd")) {
							String isadd = nl2.item(y).getFirstChild()
									.getNodeValue();
							if (isadd.equals("是")) {
								isAdd = true;
							}
						}
						if (isSameType && isNotBind && isNotLoss && isAdd) {
							String accId = att.getNamedItem("id")
									.getNodeValue();
							lstStr.add(accId);
							isSameType = false;
							isNotBind = false;
							isNotLoss = false;
							isAdd = false;
						}
					}
				}
			}
			isSameType = false;
			isNotBind = false;
			isNotLoss = false;
			isAdd = false;
		}
		return lstStr;
	}
}

package com.ultrawise.android.bank.webservices.implement.account_management01;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.sun.research.ws.wadl.Doc;
import com.ultrawise.android.bank.webservices.base.account_management01.IAll;

/**
 * 从txt文件里面读取数据，返回数据
 * 
 * @author hosolo
 * 
 */
public class All implements IAll {
	/**
	 * 公共方法写入txt
	 * 
	 * @author hosolo
	 * @param doc
	 * @param fileName
	 */
	public static void writeTxt(Document doc, String fileName) {
		String path = Thread.currentThread().getContextClassLoader()
				.getResource("").getPath();
		TransformerFactory tFactory = TransformerFactory.newInstance();
		Transformer transformer;
		try {
			transformer = tFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new java.io.File(path
					+ fileName));
			transformer.transform(source, result);
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 公共方法读取txt
	 * 
	 * @author hosolo
	 * @param fileName
	 * @return doc
	 */
	public static Document readTxt(String fileName) {
		String path = Thread.currentThread().getContextClassLoader()
				.getResource("").getPath();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					new FileInputStream(path + fileName), "utf-8"));
			String line;
			StringBuffer data = new StringBuffer();
			while ((line = br.readLine()) != null) {
				data.append(line + "\n");
			}
			ByteArrayInputStream stream = new ByteArrayInputStream(data
					.toString().getBytes("utf-8"));
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(stream);
			return doc;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static boolean comparePwdFormAcc(String account, String password) {
		// 核对密码
		Document doc = readTxt("accout.txt");
		NodeList nl = doc.getElementsByTagName("accout");
		for (int i = 0; i < nl.getLength(); i++) {
			if (nl.item(i).hasAttributes()) {
				NamedNodeMap att = nl.item(i).getAttributes();
				for (int x = 0; x < att.getLength(); x++) {
					String idValue = att.getNamedItem("id").getNodeValue();
					if (idValue.equals(account)) {
						// 如果和账号的相等
						nl = nl.item(i).getChildNodes();
						for (int y = 1; y < nl.getLength(); y += 2) {
							String nodeName = nl.item(y).getNodeName();
							if (nodeName.equals("actpwd")) {
								String pwd = nl.item(y).getNodeValue();
								if (pwd.equals(password)) {
									// 如果密码
									return true;
								}

							}
						}
					}
				}
			}
		}
		return false;
	}

	public static boolean isBind(String account) {
		// 是否为绑定账户
		Document doc = All.readTxt("accout.txt");
		NodeList nl = doc.getElementsByTagName("accout");
		for (int i = 0; i < nl.getLength(); i++) {
			if (nl.item(i).hasAttributes()) {
				NamedNodeMap att = nl.item(i).getAttributes();
				for (int x = 0; x < att.getLength(); x++) {
					String idValue = att.getNamedItem("id").getNodeValue();
					if (idValue.equals(account)) {
						// 如果和未绑定账号的相等
						nl = nl.item(i).getChildNodes();
						for (int y = 1; y < nl.getLength(); y += 2) {
							String nodeName = nl.item(y).getNodeName();
							if (nodeName.equals("bind")) {
								String bValue = nl.item(y).getNodeValue();
								if (bValue.equals("是")) {
									return true;
								}
							}
						}
					}
				}
			}
		}
		return false;
	}

	/**
	 * 
	 * @param accTypeName
	 * @return 账户类型id
	 */
	public static String getAccTypeId(String accTypeName) {
		NodeList nl = readTxt("paypal.txt").getElementsByTagName("tyname");
		for (int i = 0; i < nl.getLength(); i++) {
			String value = nl.item(i).getFirstChild().getNodeValue();
			if (accTypeName.equals(value)) {
				// 如果账户类型名称相等
				Node pn = nl.item(i).getParentNode();
				NamedNodeMap att = pn.getAttributes();

				String idValue = att.getNamedItem("id").getNodeValue();
				return idValue;

			}
		}
		return "";
	}

	/**
	 * 功能号 0101
	 */
	public List<String> getAccType() {
		// TODO 获取账户类型
		ArrayList<String> lstStr = new ArrayList<String>();

		NodeList nl = readTxt("paypal.txt").getElementsByTagName("tyname");// 获取所有的账户种类节点
		for (int i = 0; i < nl.getLength(); i++) {
			String value = nl.item(i).getFirstChild().getNodeValue();// 获取节点的值
			lstStr.add(value);
		}

		return lstStr;
	}

	/**
	 * 功能0102
	 */
	public List<String> getBindAcc(String UserNo, String accTypeName) {
		// TODO Auto-generated method stub
		ArrayList<String> lstStr = new ArrayList<String>();
		boolean isSameType = false;
		boolean isBind = false;
		boolean isAdd = false;
		String accTypeId = getAccTypeId(accTypeName);// 获取账户类型id
		NodeList nl = readTxt("accout.txt").getElementsByTagName("accout");
		for (int i = 0; i < nl.getLength(); i++) {
			if (nl.item(i).hasAttributes()) {
				NamedNodeMap att = nl.item(i).getAttributes();
				String idValue = att.getNamedItem("userid").getNodeValue();
				if (idValue.equals(UserNo)) {
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
						if (isSameType && isBind && isAdd) {
							String accId = att.getNamedItem("id")
									.getNodeValue();
							lstStr.add(accId);
							isSameType = false;
							isBind = false;
							isAdd = false;
						}
					}
				}
			}
			isSameType = false;
			isBind = false;
			isAdd = false;
		}
		return lstStr;
	}

	/**
	 * 获取别名,功能0108
	 */
	public String getNickName(String account) {
		// TODO Auto-generated method stub
		Document doc = All.readTxt("accout.txt");
		NodeList nl = doc.getElementsByTagName("accout");
		for (int i = 0; i < nl.getLength(); i++) {
			String id = nl.item(i).getAttributes().getNamedItem("id")
					.getNodeValue();
			if (id.equals(account)) {
				nl = nl.item(i).getChildNodes();
				for (int y = 1; y < nl.getLength(); y += 2) {
					String nName = nl.item(y).getNodeName();
					if (nName.equals("aliss")) {
						return nl.item(y).getFirstChild().getNodeValue();
					}
				}
			}
		}
		return "";
	}

	/**
	 * 获取工本费
	 */
	public String getLossCost() {
		// TODO Auto-generated method stub
		Document doc = All.readTxt("cost.txt");
		NodeList nl = doc.getElementsByTagName("losscost");
		return nl.item(0).getFirstChild().getNodeValue();
	}

	public List<String> getUserNo() {
		// 获取用户号
		return null;
	}

	public String getOrderCost() {
		// TODO Auto-generated method stub
		return null;
	}

}

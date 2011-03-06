package com.ultrawise.android.bank.webservices.implement.payment06;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

import com.ultrawise.android.bank.webservices.base.payment06.IPaymentDetail;

public class PaymentDetail implements IPaymentDetail {

	public String getPaymentDetail(String userNo, String paymentName,
			String monthName) {

		String WtdStr = null;
		// 获取某一张表
		// TODO Auto-generated method stub
		if (paymentName.equals("水费")) {
			String path = "D:\\wtdemand.txt";
			WtdStr = parseXml(FileToStream(path), userNo, monthName);
		}
		if (paymentName.equals("电费")) {

			String path = "D:\\powdemand.txt";
			WtdStr = parseXml(FileToStream(path), userNo, monthName);
		}
		if (paymentName.equals("煤气费")) {

			String path = "D:\\gasdemand.txt";
			WtdStr = parseXml(FileToStream(path), userNo, monthName);
		}
		if (paymentName.equals("房租费")) {

			String path = "D:\\rendemand.txt";
			WtdStr = parseXml(FileToStream(path), userNo, monthName);
		}
		return WtdStr;
	}

	// 将文件读取转化成流
	private InputStream FileToStream(String path) {
		InputStream stream = null;
		StringBuffer data = new StringBuffer();
		String line = null;
		String result2 = null;

		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					new FileInputStream(path), "UTF-8"));

			try {
				while ((line = br.readLine()) != null) {
					data.append(line + "\n");
					stream = new ByteArrayInputStream(data.toString().getBytes(
							"UTF-8"));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stream;
	}

	// 将流以xml方式读取后转化成string
	private String parseXml(InputStream is, String userNo, String monthName) {
		StringBuffer sb = new StringBuffer();
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(is);
			NodeList userNode = doc.getElementsByTagName("user");
			// 读取所有的用户节点
			for (int i = 0; i <= userNode.getLength(); i = i + 2) {
				Node n = userNode.item(i);// 遍历所有的用户节点
				NamedNodeMap nodeMap = n.getAttributes();// 取出节点内部所有的属性\
				if (nodeMap.getNamedItem("name").getNodeValue().equals(userNo)) {
					// 判断此节点name属性和用户名是否相同
					// 跳到他的子节点
					System.out.println("----------------------------");
					NodeList n2 = n.getChildNodes();// wtdemands
					for (int j = 1; j < n2.getLength(); j += 2) {
						Node my_node1 = n2.item(j);
						NamedNodeMap nodeMap1 = my_node1.getAttributes();
						// 月份
						String month = nodeMap1.getNamedItem("month")
								.getNodeValue();
						if (month.equals(monthName)) {
							// 遍历水费的子节点
							NodeList n3 = my_node1.getChildNodes();
							String damout = null, charger = null, dunum = null, dulimit = null, date = null;
							for (int q = 1; q < n3.getLength(); q += 2) {
								Node my_node2 = n3.item(q);
								if (my_node2.getNodeName().equals("damout")) {// 金额
									damout = my_node2.getFirstChild()
											.getNodeValue();
									System.out.println(damout);
								}
								if (my_node2.getNodeName().equals("charger")) {// 收费方
									charger = my_node2.getFirstChild()
											.getNodeValue();
									System.out.println(charger);
								}
								if (my_node2.getNodeName().equals("dunum")) {// 合同号
									dunum = my_node2.getFirstChild()
											.getNodeValue();
									System.out.println(dunum);
								}
								if (my_node2.getNodeName().equals("dulimit")) {// 期限
									dulimit = my_node2.getFirstChild()
											.getNodeValue();
									System.out.println(dulimit);
								}
								// if (my_node2.getNodeName().equals("date"))
								// {// 缴费日期
								// date =
								// my_node2.getFirstChild().getNodeValue();
								// System.out.println(date);
								// }

							}
							sb.append("项目名称" + ":" + month + ":" + "缴费金额" + ":"
									+ damout + "元" + ":" + "收费方" + ":"
									+ charger + ":" + "缴费合同号" + ":" + dunum
									+ ":" + "缴费期限" + ":" + dulimit);
						}
					}
				}

			}

		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			System.out.println("1111111111111");
			e.printStackTrace();
		} catch (SAXException e) {
			System.out.println("2222222222");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("3333333331");
			e.printStackTrace();
		}
		return sb.toString();
	}

	private String parseAllser(InputStream is, String userNo) {
		StringBuffer sb = new StringBuffer();
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(is);

			NodeList userNode = doc.getElementsByTagName("user");
			// 读取所有的用户节点
			for (int i = 0; i <= userNode.getLength(); i = i + 2) {
				Node n = userNode.item(i);// 遍历所有的用户节点
				NamedNodeMap nodeMap = n.getAttributes();// 取出节点内部所有的属性\
				if (nodeMap.getNamedItem("name").getNodeValue().equals(userNo)) {
					// 判断此节点name属性和用户名是否相同
					// 跳到他的子节点
					NodeList n2 = n.getChildNodes();// wtdemands
					for (int j = 1; j < n2.getLength(); j += 2) {
						Node my_node1 = n2.item(j);
						NamedNodeMap nodeMap1 = my_node1.getAttributes();
						// 月份
						String month = nodeMap1.getNamedItem("month")
								.getNodeValue();
						// 遍历水费的子节点
						NodeList n3 = my_node1.getChildNodes();
						String damout = null;
						for (int q = 1; q < n3.getLength(); q += 2) {
							Node my_node2 = n3.item(q);
							if (my_node2.getNodeName().equals("damout")) {// 金额
								damout = my_node2.getFirstChild()
										.getNodeValue();
							}
						}
						sb.append(month + ":" + damout);
					
					}
					
				}
			}
		} catch (ParserConfigurationException e) {
			System.out.println("1111111111111");
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sb.toString();
	}

	public String getPaymentNameAndMoney(String userNo, String paymentName) {
		// 读取所有的水费表，电费表，房租费表，
		// TODO Auto-generated method stub
		StringBuffer allStr = new StringBuffer();		
		String ren =null,gas =null,pow=null,wtd=null;
		if (paymentName.equals("待缴费项目")) {
			String path1 = "D:\\wtdemand.txt";
			String path2 = "D:\\powdemand.txt";
			String path3 = "D:\\gasdemand.txt";
			String path4 = "D:\\rendemand.txt";
			wtd = parseAllser(FileToStream(path1), userNo);
			pow = parseAllser(FileToStream(path2), userNo);
			gas = parseAllser(FileToStream(path3), userNo);
			ren = parseAllser(FileToStream(path4), userNo);
			allStr.append(wtd+":"+pow+":"+gas+":"+ren);
			System.out.println(allStr.toString());
		}
		return allStr.toString();
	}




}

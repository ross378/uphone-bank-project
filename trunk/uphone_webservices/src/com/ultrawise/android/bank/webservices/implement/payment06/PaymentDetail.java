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

import javax.ws.rs.core.NewCookie;
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
	FileOperation file=new FileOperation();

	public String getPaymentDetail(String userNo, String paymentName,
			String monthName) {

		String WtdStr = null;
		// 获取某一张表
		// TODO Auto-generated method stub
		if (paymentName.equals("水费")) {

			WtdStr = parseXml(file.getFileDocument("wtdemand"), userNo, monthName);
		}
		if (paymentName.equals("电费")) {


			WtdStr = parseXml(file.getFileDocument("powdemand"), userNo, monthName);
		}
		if (paymentName.equals("煤气费")) {


			WtdStr = parseXml(file.getFileDocument("gasdemand"), userNo, monthName);
		}
		if (paymentName.equals("房租费")) {
			WtdStr = parseXml(file.getFileDocument("rendemand"), userNo, monthName);
		}
		return WtdStr;
	}
	// 将流以xml方式读取后转化成string
	private String parseXml(Document doc, String userNo, String monthName) {
		StringBuffer sb = new StringBuffer();
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
									damout = my_node2.getFirstChild().getNodeValue();
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

		return sb.toString();
	}

	private String parseAllser(Document doc, String userNo) {
		StringBuffer sb = new StringBuffer();
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
		return sb.toString();
	}

	public String getPaymentNameAndMoney(String userNo, String paymentName) {
		// 读取所有的水费表，电费表，房租费表，
		// TODO Auto-generated method stub
		StringBuffer allStr = new StringBuffer();		
		String ren =null,gas =null,pow=null,wtd=null;
		if (paymentName.equals("待缴费项目")) {
			wtd = parseAllser(file.getFileDocument("wtdemand"), userNo);
			pow = parseAllser(file.getFileDocument("powdemand"), userNo);
			gas = parseAllser(file.getFileDocument("gasdemand"), userNo);
			ren = parseAllser(file.getFileDocument("rendemand"), userNo);
			allStr.append(wtd+":"+pow+":"+gas+":"+ren);
			System.out.println(allStr.toString());
		}
		return allStr.toString();
	}




}

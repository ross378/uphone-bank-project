package com.ultrawise.android.bank.webservices.implement.payment06;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

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

	public String getPaymentDetail(String userNo, String paymentName) {

		String WtdStr = null;
		// 获取某一张表
		// TODO Auto-generated method stub
		if (paymentName.equals("水费")) {
			String path = "C:\\Users\\Administrator\\Desktop \\wtdemand.txt";
			WtdStr = parseXml(FileToStream(path), userNo);
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
					new FileInputStream(path)));
			try {
				while ((line = br.readLine()) != null) {
					data.append(line + "\n");
					stream = new ByteArrayInputStream(data.toString()
							.getBytes());
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stream;
	}

	// 将流以xml方式读取后转化成string
	private String parseXml(InputStream is, String userNo) {
		StringBuffer sb = new StringBuffer();
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(is);

			NodeList userNode = doc.getElementsByTagName("user");

			// 读取所有的用户节点
			for (int i = 0; i < userNode.getLength(); i++) {
				Node n = userNode.item(i);// 遍历所有的用户节点
				NamedNodeMap nodeMap = n.getAttributes();// 取出节点内部所有的属性
				if (nodeMap.getNamedItem("name").getNodeName() == userNo) {
					// 判断此节点name属性和用户名是否相同
					// 跳到他的子节点
					NodeList n2 = n.getChildNodes();// wtdemands
					for (int j = 0; j < n2.getLength(); j++) {
						Node my_node1 = n2.item(j);
						NamedNodeMap nodeMap1 = my_node1.getAttributes();
						// 月份
						String month = nodeMap1.getNamedItem("month")
								.getNodeName();
						// 遍历水费的子节点
						NodeList n3 = my_node1.getChildNodes();
						String damout=null, charger=null, dunum=null, dulimit=null, date=null;
						for (int q = 0; q < n3.getLength(); q++) {
							Node my_node2 = n3.item(q);
							if (my_node2.getNodeName().equals("damout")) {// 金额
								damout = my_node2.getFirstChild().getNodeValue();
							} else 
								if (my_node2.getNodeName().equals("charger")) {// 收费方
								charger = my_node2.getFirstChild().getNodeValue();
							} else 
								if (my_node2.getNodeName().equals("dunum")) {// 合同号
								dunum = my_node2.getFirstChild().getNodeValue();
							} else 
								if (my_node2.getNodeName().equals("dulimit")) {// 期限
								dulimit = my_node2.getFirstChild().getNodeValue();
							} else 
								if (my_node2.getNodeName().equals("date")) {// 缴费日期
								date = my_node2.getFirstChild().getNodeValue();
							}

						}
						sb.append(month + ":" + damout + ":" + charger + ":"+ dunum + ":" + dulimit + ":" + date);
					}
				}

			}

			// //合同号
			// n2 = doc.getElementsByTagName("dunum");
			// Node getNode=n2.item(0);
			// String dunum=getNode.getFirstChild().getNodeValue();
			//					
			// //缴费金额
			// n2 = doc.getElementsByTagName("damout");
			// getNode=n2.item(0);
			// String damout=getNode.getFirstChild().getNodeValue();
			//					
			// //日期
			// n2 = doc.getElementsByTagName("date");
			// getNode=n2.item(0);
			// String date=getNode.getFirstChild().getNodeValue();
			//					
			// //收费方
			// n2 = doc.getElementsByTagName("charger");
			// getNode=n2.item(0);
			// String charger=getNode.getFirstChild().getNodeValue();
			// //缴费期限
			// n2 = doc.getElementsByTagName("dulimit");
			// getNode=n2.item(0);
			// String dulimit=getNode.getFirstChild().getNodeValue();
			// sb.append(month+":"+dunum+":"+damout+":"+date+":"+charger+":"+dulimit);

		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
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

	public String getPaymentNameAndMoney(String userNo) {
		// 读取所有的水费表，电费表，房租费表，
		// TODO Auto-generated method stub
		return null;
	}

}

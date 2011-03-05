package com.ultrawise.android.bank.webservices.implement.account_management01;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.ultrawise.android.bank.webservices.base.account_management01.INickName;

/**
 * 路径要注意
 * 
 * @author hosolo
 * 
 */
public class NickName implements INickName {

	private String path;
	private BufferedReader br;
	private ByteArrayInputStream stream;
	private String line;
	private StringBuffer data = new StringBuffer();
	private DocumentBuilderFactory dbf;
	private StringBuffer sb;
	private DocumentBuilder db;

	public List<String> setNickName(String account, String nickName) {
		// TODO 设置别名
		path = Thread.currentThread().getContextClassLoader().getResource("")
				.getPath();
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(
					path + "/account.txt"),"utf-8"));
			while ((line = br.readLine()) != null) {
				data.append(line + "\n");
			}
			stream = new ByteArrayInputStream(data.toString().getBytes("utf-8"));

			sb = new StringBuffer();
			dbf = DocumentBuilderFactory.newInstance();

			db = dbf.newDocumentBuilder();
			Document doc = db.parse(stream);
			NodeList nl = doc.getElementsByTagName("id");
			for (int i = 0; i < nl.getLength(); i++) {
				String value = nl.item(i).getFirstChild().getNodeValue();//获取节点的值
				if(value.equals(account)){//如果值和参数相同
					
					
				}
			}
			Node my_node = nl.item(0);
			NamedNodeMap nnm = my_node.getAttributes();
			String userValue = nnm.getNamedItem("user").getNodeValue();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}

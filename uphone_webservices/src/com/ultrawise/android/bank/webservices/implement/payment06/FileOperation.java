package com.ultrawise.android.bank.webservices.implement.payment06;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

public class FileOperation {
		
	//读取的文件后返回一个Document对象
	public static Document getFileDocument(String fileName){
		StringBuffer data = new StringBuffer();
		String line = "";
		ByteArrayInputStream stream = null;
		Document doc = null;
		try {
			String path=Thread.currentThread().getContextClassLoader().getResource("").getPath(); 
			BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(path+"\\"+fileName+".txt"),"utf-8"));
			while((line=br.readLine())!=null){
				data.append(line+"\n");
			}
			stream=new ByteArrayInputStream(data.toString().getBytes("utf-8"));
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder db=dbf.newDocumentBuilder();
			doc=db.parse(stream);
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
		
		return doc;
	}
	
	
	//将Document对象保存到文件中
	public static boolean saveDocument(Document doc,String fileName){
		TransformerFactory tFactory =TransformerFactory.newInstance();
		Transformer transformer=null;
		try {
			transformer = tFactory.newTransformer();
		} catch (TransformerConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}
		
		DOMSource source = new DOMSource(doc);
		String path=Thread.currentThread().getContextClassLoader().getResource("").getPath();
		StreamResult result = new StreamResult(new java.io.File(path+"\\"+fileName+".txt"));
		
		try {
			transformer.transform(source, result);
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	//向xml文件中插入结点
	public static Document insertNode(Document doc,String value){
		System.out.println(value+"_______________________________");
		String[] values = value.split("#");
		Calendar cal=new GregorianCalendar();
		System.out.println(values.length);
		NodeList n1 = doc.getElementsByTagName(values[0]);
		Node root = n1.item(0);
		Element node = doc.createElement(values[1]);
		
		Element node1 = doc.createElement("id");
		Text text1 = doc.createTextNode("1");
		node1.appendChild(text1);
		node.appendChild(node1);
		
		Element node2 = doc.createElement("userid");
		Text text2 = doc.createTextNode("zhangsan");
		node2.appendChild(text2);
		node.appendChild(node2);
		//金额
		Element node3 = doc.createElement("credit");
		Text text3 = doc.createTextNode(values[6]+".00");
		node3.appendChild(text3);
		node.appendChild(node3);
		//号码
		Element node4 = doc.createElement("creqqnum");
		Text text4 = doc.createTextNode(values[3]);
		node4.appendChild(text4);
		node.appendChild(node4);
		//日期
		Element node5 = doc.createElement("credata");
		Text text5 = doc.createTextNode(cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH)+1)
				+ "-" + cal.get(Calendar.DAY_OF_MONTH));
		node5.appendChild(text5);
		node.appendChild(node5);
		
		Element node6 = doc.createElement("creno");
		Text text6 = doc.createTextNode(values[5]);
		node6.appendChild(text6);
		node.appendChild(node6);
		
		Element node7 = doc.createElement("operator");
		Text text7 = doc.createTextNode(values[4]);
		node7.appendChild(text7);
		node.appendChild(node7);
		
		Element node8 = doc.createElement("account");
		Text text8 = doc.createTextNode(values[7]);
		node8.appendChild(text8);
		node.appendChild(node8);
		
		root.appendChild(node);
		
		return doc;
	}
	
}

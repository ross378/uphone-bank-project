package com.ultrawise.android.bank.webservices.implement.account_query02;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.ultrawise.android.bank.webservices.base.account_Query02.IAccountQueryInfo;


public class AccountDAOQuery implements IAccountQueryInfo{
	
	//按账户号查找
	public String getAccountQueryById(int id) {
		// TODO Auto-generated method stub
		return "Id还没做";
	}
	
	//按账户类型查找
	public String getAccountQueryByType(String type) {
		// TODO Auto-generated method stub
		return getByType(type);
	 }
	
	private String getByType(String type)
	{
		StringBuffer data=new StringBuffer();
		String line=null;
		String result2=null;
		String path=null;
		
		try {
			path=Thread.currentThread().getContextClassLoader().getResource("").getPath();
			System.out.println("file path="+path);
			BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(path+"\\act.txt"),"utf-8"));
			while((line=br.readLine())!=null){
				data.append(line+"\n");
			}
			ByteArrayInputStream stream=new ByteArrayInputStream(data.toString().getBytes("utf-8"));
			
			String result=parseXml(stream);//查询值
			String[] results=result.split(":");
			
			//Integer.toString(id)
			if(type.equals(results[0])){
				result2=results[1]+":"+results[2];
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result2;
	}

/*
 
 public class UserDao implements IUserDao {

	public String getActInfo(String name) {
		StringBuffer data=new StringBuffer();
		String line=null;
		String result2=null;
		String path=null;
		try {
//			Properties property = System.getProperties();
//
//			String str = property.getProperty("user.dir");
//
//			System.out.println(str);

			path=Thread.currentThread().getContextClassLoader().getResource("").getPath(); 

			BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(path+"\\act.txt")));
			
			while((line=br.readLine())!=null){
				data.append(line+"\n");
			}
			ByteArrayInputStream stream=new ByteArrayInputStream(data.toString().getBytes());
			String result=parseXml(stream);
			
			String[] results=result.split(":");
			
			
			if(name.equals(results[0])){
				result2=results[1]+":"+results[2];
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result2;
	}
	
	// Write to Xml
	public void setActInfo(String actNo,String actPwd){
		StringBuffer data=new StringBuffer();
		String line=null;
		String result2=null;
		String path=null;
		try {
//			Properties property = System.getProperties();
//
//			String str = property.getProperty("user.dir");
//
//			System.out.println(str);

			path=Thread.currentThread().getContextClassLoader().getResource("").getPath(); 

			BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(path+"\\act.txt")));
			
			while((line=br.readLine())!=null){
				data.append(line+"\n");
			}
			ByteArrayInputStream stream=new ByteArrayInputStream(data.toString().getBytes());
			parseWriteXml(stream,actNo,actPwd);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} */	
 
	private String parseXml2(InputStream is){
		StringBuffer sb=new StringBuffer();
		DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder db=dbf.newDocumentBuilder();
			Document doc=db.parse(is);
			NodeList nl = doc.getElementsByTagName("Type");
			Node my_node = nl.item(0);
			NamedNodeMap nnm=my_node.getAttributes();
			String userValue=nnm.getNamedItem("name").getNodeValue();
			System.out.println("stream="+is.toString());
			System.out.println("userValue="+userValue);
			sb.append(userValue+":");
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
	
	// get from XML
	private String parseXml(InputStream is){
		StringBuffer sb=new StringBuffer();
		DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder db=dbf.newDocumentBuilder();
			Document doc=db.parse(is);
			NodeList nl = doc.getElementsByTagName("Act");
			Node my_node = nl.item(0);
			NamedNodeMap nnm=my_node.getAttributes();
			String userValue=nnm.getNamedItem("user").getNodeValue();
			
			nl = doc.getElementsByTagName("ActNo");
			Node my_node1 = nl.item(0);
			String actNo=my_node1.getFirstChild().getNodeValue();
			
			nl = doc.getElementsByTagName("ActPwd");
			Node my_node2 = nl.item(0);
			String actPwd=my_node2.getFirstChild().getNodeValue();
			
			sb.append(userValue+":"+actNo+":"+actPwd);
			
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
}
	
	/*//write to XML
	private void parseWriteXml(InputStream is,String actNo,String actPwd){
		StringBuffer sb=new StringBuffer();
		DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder db=dbf.newDocumentBuilder();
			Document doc=db.parse(is);
			doc.normalize();
			
			Text textseg;
			Element actElement=doc.createElement("act");
			actElement.setAttribute("user", "002");
			
			Element actNoElement=doc.createElement("ActNo");
			textseg=doc.createTextNode(actNo);
			actNoElement.appendChild(textseg);
			actElement.appendChild(actNoElement);
			 
			Element actPwdElement=doc.createElement("ActPwc");
			textseg=doc.createTextNode(actPwd);
			actPwdElement.appendChild(textseg);
			actElement.appendChild(actPwdElement);

			doc.getDocumentElement().appendChild(actElement);
			
			TransformerFactory tFactory =TransformerFactory.newInstance();
			Transformer transformer=null;
			try {
				transformer = tFactory.newTransformer();
			} catch (TransformerConfigurationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			DOMSource source = new DOMSource(doc);
			String path=Thread.currentThread().getContextClassLoader().getResource("").getPath(); 
			StreamResult result = new StreamResult(new java.io.File(path+"\\act.txt"));
			try {
				transformer.transform(source, result);
			} catch (TransformerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
		
	}
} 
  
*/
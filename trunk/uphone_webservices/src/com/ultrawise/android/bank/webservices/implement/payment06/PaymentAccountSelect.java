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
import java.util.ArrayList;
import java.util.List;

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
import com.ultrawise.android.bank.webservices.base.payment06.IPamymentAccountSelect;

public class PaymentAccountSelect implements IPamymentAccountSelect{

	FileOperation file;
	String[] type;
	public String getFirstAccNum(String userNo)//首选账户
	{
		String num=null;
		String balance=null;
		String pwd=null;
		System.out.println("/////////////////");
		StringBuffer numAndBlance=new StringBuffer();
		Document doc=file.getFileDocument("accout");
		NodeList n=doc.getElementsByTagName("accout");
		Node myNode=n.item(0);
		NamedNodeMap nnm=myNode.getAttributes();
		num=nnm.item(0).getNodeValue();//取出第一个帐号
		//取出第一个帐号的余额
		//获的他的子节点
		NodeList n2=myNode.getChildNodes();
		for(int i=1;i<n2.getLength();i+=2){	
			if(n2.item(i).getNodeName().equals("balance")){
				balance=n2.item(i).getFirstChild().getNodeValue();
			}
			if(n2.item(i).getNodeName().equals("actpwd")){
				pwd=n2.item(i).getFirstChild().getNodeValue();
			}
			
		}
		
//		String num=myNode.item(0).getFirstChild().getNodeValue();
		System.out.println(num);
		numAndBlance.append(num+":"+balance+":"+pwd);
	return numAndBlance.toString();	
	}
	public String getAccountTypeAndNum() {//其他账户
	String typeAndNum=null;
	typeAndNum=getAccoutNum(preaseType());
	System.out.println(typeAndNum);
		return typeAndNum;
	}
	private String[] preaseType(){//取出属性名和属性ID
		file=new FileOperation();
		String idVal=null;
		String typeName=null;
		Document doc= file.getFileDocument("paypal");
		
		NodeList idNode = doc.getElementsByTagName("paypal");
		type=new String[2*idNode.getLength()];
//		System.out.println(idNode.getLength());
		for(int i=0;i<idNode.getLength();i++){
			//在paypal中循环，找到每个节点的ID和其子属性的值
			Node n=idNode.item(i);
			NamedNodeMap myNode1=n.getAttributes();
			//取出ID的值
			 idVal=myNode1.getNamedItem("id").getNodeValue();
			//取子节点的值
			NodeList n2=n.getChildNodes();
			for(int j=1;j<n2.getLength();j+=2){
				Node myNode2=n2.item(j);
				typeName=myNode2.getFirstChild().getNodeValue();	
			}
			System.out.println(typeName+idVal);
			type[2*i]=typeName;
			type[2*i+1]=idVal;
		}	
		return type; 	
	}
	
	//得到类型中文名字和此类型下面的帐号
	//类型：帐号  类型 帐号
	private String getAccoutNum(String[] type){
		StringBuffer typeAndNum=new StringBuffer();
		String typeName=null;
		String num=null;
		System.out.println(type.length);
		for(int i=0;i<type.length;i=i+2){
		
				//取出的就是具体的类型的中文
				typeName=type[i];							
				//取出的就是ID
				//根据ID取出相应的帐号
				num=getAllNum(type[i+1]);				
	
			if(i!=type.length-2){
			typeAndNum.append(typeName+":"+num+":");
			}else{
				typeAndNum.append(typeName+":"+num);				
			}
		}
		
		
		return typeAndNum.toString();
	}
	
	
	//得到类型中文名字和此类型下面的帐号
	private String getAllNum(String id){//根据ID找到所有帐号
		List<String> lstStr = new ArrayList<String>();
	Document doc1=file.getFileDocument("accout");
	NodeList list=doc1.getElementsByTagName("accout");// 获取所有的账户种类节点
	
	for(int i = 0; i < list.getLength();i++ ){
		Node node = list.item(i);
		NamedNodeMap nnm=node.getAttributes();
		String attrValue=nnm.item(0).getNodeValue();//获得属性值
			NodeList n2 = node.getChildNodes();
			for(int j=1;j<n2.getLength();j=j+2){
				if(n2.item(j).getFirstChild().getNodeValue().equals(id.trim()))
				{
					lstStr.add(attrValue);
				}
		}
	}
	StringBuffer cc=new StringBuffer();
	for(int x=0;x<=lstStr.size()-1;x++){
		if(x!=lstStr.size()-1){
		cc.append(lstStr.get(x)+",");
		}else{		
			cc.append(lstStr.get(x));
		}	
	}
	return cc.toString();	
	}

}

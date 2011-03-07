package com.ultrawise.android.bank.webservices.implement.credit04;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.ultrawise.android.bank.webservices.base.credit04.ICardDetail;

public class CardDetail implements ICardDetail{
	private String path = Thread.currentThread().getContextClassLoader()
	.getResource("").getPath();
    private BufferedReader br;
    private String line;
    private StringBuffer data = new StringBuffer(); 
   ArrayList<String> list=new ArrayList<String>();
   String str="";
	public String SearchCardDetail(String cardNo) {
		// TODO Auto-generated method stub
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(
					path + "/accout.txt"),"utf-8"));
			while ((line = br.readLine()) != null) {
				data.append(line + "\n");
				//System.out.println(line);
			}
			
			ByteArrayInputStream stream = new ByteArrayInputStream(data
					.toString().getBytes("utf-8"));
			
			//StringBuffer sb = new StringBuffer();
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			
			DocumentBuilder db=dbf.newDocumentBuilder();//获得解析器
			Document doc=db.parse(stream);//获得根结点
			NodeList n1 = doc.getElementsByTagName("accout");
			
			for(int i = 0; i < n1.getLength();i++ ){
				Node my_node = n1.item(i);
				NamedNodeMap norderid=my_node.getAttributes();
				if( norderid.getNamedItem("id").getNodeValue().equals(cardNo))
				{
					list.clear();
					list.add(cardNo);
					
					list.add(doc.getElementsByTagName("montype").item(i).getFirstChild().getNodeValue());
					
					list.add(doc.getElementsByTagName("balance").item(i).getFirstChild().getNodeValue());
					
					list.add(doc.getElementsByTagName("period").item(i).getFirstChild().getNodeValue());
					
					list.add(doc.getElementsByTagName("months").item(i).getFirstChild().getNodeValue());
					
					list.add(doc.getElementsByTagName("rate").item(i).getFirstChild().getNodeValue());
					for(String s :list)
					{
						str+=s+":";
					}
					System.out.println(str);
					return str;
				}	
			}	
    	}catch(Exception e)
    	{
    		System.out.println("dfd出错啦");
    	}
    	
    	return null;
		
	}

}

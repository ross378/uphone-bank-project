package com.ultrawise.android.bank.webservices.implement.credit04;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

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
		data=new StringBuffer();
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
	/**
	 * 获得两个时间段下的全部信息
	 * 功能号420
	 * 参数 开始时间 start 结束时间end
	 */
	public String getByTime(String no,String start, String end) {
		List<String> lstStr = new ArrayList<String>();
			Date date1 = Date.valueOf(start);
			Date date2 = Date.valueOf(end);
			data=new StringBuffer();
			String ss="";
			try {
				br = new BufferedReader(new InputStreamReader(new FileInputStream(
						path + "/Transfers.txt"),"utf-8"));
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
				NodeList n1 = doc.getElementsByTagName("trans");
				System.out.println("aa"+no);
				for(int i = 0; i < n1.getLength();i++ ){
					Node my_node = n1.item(i);
					NamedNodeMap norderid=my_node.getAttributes();
					System.out.println(norderid.getNamedItem("id").getNodeValue());
					if( norderid.getNamedItem("id").getNodeValue().equals(no))
					{
						String attrValue2=norderid.getNamedItem("zhantype").getNodeValue();//获得交易类型
						NodeList n2 = my_node.getChildNodes();
						String time=doc.getElementsByTagName("date").item(i).getFirstChild().getNodeValue();
						Date date3 = Date.valueOf(time);
                       
						if(date3.before(date2)&&date3.after(date1)){
							 String name=doc.getElementsByTagName("name").item(i).getFirstChild().getNodeValue();
							 String amount=doc.getElementsByTagName("amount").item(i).getFirstChild().getNodeValue();
						ss+=time+"#"+attrValue2+"#"+amount+"#"+name+":";
					}
				}
			}

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
		System.out.println("---"+ss);
		return ss;
	}

}

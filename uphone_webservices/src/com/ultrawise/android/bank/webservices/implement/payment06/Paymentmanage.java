package com.ultrawise.android.bank.webservices.implement.payment06;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.ultrawise.android.bank.webservices.base.payment06.IPaymentmanage;

public class Paymentmanage implements IPaymentmanage {

	public String getPaymentmanageName() {
		Document doc = FileOperation.getFileDocument("serable");
		List<Serable> serablelist = selectSerable(doc);
		StringBuffer sb = new StringBuffer();
		Serable serable = null;
		for(int i=0;i<serablelist.size();i++){
			serable = serablelist.get(i);
			if(i==serablelist.size()-1){
				sb.append(serable.getPrname()).append(":").append(serable.getOpen());
			}else{
				sb.append(serable.getPrname()).append(":").append(serable.getOpen()).append(":");
			}
		}
		return sb.toString();
	}

	public String updatePayment(String paymentName,String value) {
		Document doc = FileOperation.getFileDocument("serable");
		if(updataSelect(doc, paymentName, value)){
			return "true";
		}
		return "false";
	}
	
	//查询serable表
	public List<Serable> selectSerable(Document doc){
		List<Serable> serableList = new ArrayList<Serable>();
		Serable serable = null;
		NodeList n1 = doc.getElementsByTagName("serable");
		for(int i = 0; i < n1.getLength();i++ ){
			
			serable = new Serable();
			Node my_node = n1.item(i);
			
			NodeList n2 = my_node.getChildNodes();
			serable.setId(n2.item(1).getFirstChild().getNodeValue());
			serable.setPrname(n2.item(3).getFirstChild().getNodeValue());
			serable.setOpen(n2.item(5).getFirstChild().getNodeValue());
			
			serableList.add(serable);
			
		}
		return serableList;
	}
	
	//修改serable表中某个结点的值
	public boolean updataSelect(Document doc,String nodeName,String value){
		NodeList n1 = doc.getElementsByTagName("serable");
		for(int i = 0; i < n1.getLength();i++ ){
			Node my_node = n1.item(i);
			NodeList n2 = my_node.getChildNodes();
			if(n2.item(3).getFirstChild().getNodeValue().equals(nodeName)){
				n2.item(5).getFirstChild().setNodeValue(value);
			}
		}
		return FileOperation.saveDocument(doc, "serable");
	}
	
}

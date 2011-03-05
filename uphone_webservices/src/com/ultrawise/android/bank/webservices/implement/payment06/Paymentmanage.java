package com.ultrawise.android.bank.webservices.implement.payment06;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.ultrawise.android.bank.webservices.base.payment06.IPaymentmanage;

public class Paymentmanage implements IPaymentmanage {

	public String getPaymentmanageName() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getUserOpenPayment(String userNo) {
		// TODO Auto-generated method stub
		return null;
	}

	public String updatePayment(String userNo, String paymentName) {
		// TODO Auto-generated method stub
		return null;
	}
	
	//查询serable表
	public List<Serable> selectSerable(Document doc){
		List<Serable> serableList = new ArrayList<Serable>();
		Serable serable = null;
		NodeList n1 = doc.getElementsByTagName("Serable");
		for(int i = 0; i < n1.getLength();i++ ){
			
			serable = new Serable();
			Node my_node = n1.item(i);
			
			NodeList n2 = my_node.getChildNodes();
			
			serable.setId(n2.item(0).getFirstChild().getNodeValue());
			serable.setPrname(n2.item(1).getFirstChild().getNodeValue());
			serable.setOpen(n2.item(2).getFirstChild().getNodeValue());
			
			serableList.add(serable);
			
		}
		return serableList;
	}
	
	//修改serable表中某个结点的值
	public boolean updataSelect(Document doc,String nodeName,String value){
		
		return false;
	}
	
}

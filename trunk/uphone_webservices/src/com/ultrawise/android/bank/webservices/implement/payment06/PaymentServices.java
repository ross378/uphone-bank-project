package com.ultrawise.android.bank.webservices.implement.payment06;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.ultrawise.android.bank.webservices.base.payment06.IPaymentServices;

public class PaymentServices implements IPaymentServices {

	public String getServicesName() {
		StringBuffer sb = new StringBuffer();
		Document doc = FileOperation.getFileDocument("selfservice");
		NodeList n1 = doc.getElementsByTagName("service");
		for(int i = 0; i < n1.getLength();i++ ){
			NodeList n2 = n1.item(i).getChildNodes();
			for(int j = 0;j < n2.getLength();j++){
				Node node = n2.item(j);
				if(node.getNodeType() == Node.ELEMENT_NODE){
					if(node.getNodeName().equals("payname")){
						if(i != n1.getLength()-1){
							sb.append(n2.item(3).getFirstChild().getNodeValue()).append(":");
						}else{
							sb.append(n2.item(3).getFirstChild().getNodeValue());
						}	
					}
				}
			}
		}
		return sb.toString();
	}

	public String getUserAccount(String userNo) {
		// TODO Auto-generated method stub
		return null;
	}

	public String phonePayment(String paymenName, double paymentAmt,
			String paymentActNo, String paymentActPasswd, String paymentPhoneNum) {
		// TODO Auto-generated method stub
		return null;
	}

	public String phonePaymentDetail(String paymentName, String paymentActNo,
			double paymentAmt, String paymentPhoneNum) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getServicesOperator(String id) {
		StringBuffer sb = new StringBuffer();
		Document doc = FileOperation.getFileDocument("selfservice");
		NodeList n1 = doc.getElementsByTagName("service");
		boolean flag = false;
		for(int i = 0; i < n1.getLength();i++ ){
			flag = false;
			NodeList n2 = n1.item(i).getChildNodes();
			for(int j = 0;j < n2.getLength();j++){
				Node node = n2.item(j);
				if(node.getNodeType() == Node.ELEMENT_NODE){
					if(node.getNodeName().equals("id")){
						String msg = node.getFirstChild().getNodeValue();
						if(id.equals(msg))
							flag = true;
					}
					if(node.getNodeName().equals("operator")&&flag){
						NodeList n3 = node.getChildNodes();
						for(int x = 0;x < n3.getLength();x++){
							Node node1 = n3.item(x);
							if(node1.getNodeType() == Node.ELEMENT_NODE){
								if(x!=n3.getLength() - 2){
									sb.append(node1.getFirstChild().getNodeValue()).append(",");
								}else{
									sb.append(node1.getFirstChild().getNodeValue()).append(":");
								}
							}
						}
					}
					if(node.getNodeName().equals("serviceno")&& flag){
						sb.append(n2.item(7).getFirstChild().getNodeValue());
					}
				}
			}
			
		}
		return sb.toString();
	}

}

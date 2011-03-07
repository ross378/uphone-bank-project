package com.ultrawise.android.bank.webservices.implement.payment06;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import com.ultrawise.android.bank.webservices.base.payment06.IPaymentServices;

public class PaymentServices implements IPaymentServices {

	public String getServicesName() {
		StringBuffer sb = new StringBuffer();
		Document doc = FileOperation.getFileDocument("selfservice");
		NodeList n1 = doc.getElementsByTagName("service");
		for(int i = 0; i < n1.getLength();i++ ){
			NodeList n2 = n1.item(i).getChildNodes();
			if(i != n1.getLength()-1){
				sb.append(n2.item(3).getFirstChild().getNodeValue()).append(":");
			}else{
				sb.append(n2.item(3).getFirstChild().getNodeValue());
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
		for(int i = 0; i < n1.getLength();i++ ){
			NodeList n2 = n1.item(i).getChildNodes();
			if(n2.item(1).getFirstChild().getNodeValue().equals(id)){
				NodeList n3 = n2.item(5).getChildNodes();
				for(int j = 1;j < n3.getLength() - 1;j+=2){
					if(j!=n3.getLength() - 2){
						sb.append(n3.item(j).getFirstChild().getNodeValue()).append(",");
					}else{
						sb.append(n3.item(j).getFirstChild().getNodeValue()).append(":");
					}
				}
				sb.append(n2.item(7).getFirstChild().getNodeValue());
			}
		}
		return sb.toString();
	}

}

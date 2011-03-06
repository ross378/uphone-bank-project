package com.ultrawise.android.bank.webservices.implement.payment06;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.ultrawise.android.bank.webservices.base.payment06.IPaymentLastMonth;

public class PaymentLastMonth implements IPaymentLastMonth {

	public String getLastPaymentDetail(String userNo,
			Date paymentDateNow, Date paymentDateBeforeAMonth,int fileName,int flag) {
		String name = getPaymentName(fileName);
		Document doc = FileOperation.getFileDocument(name.split(":")[0]);
		String str = "";
		if(flag == 1){
			List<History1> historylist = selectHistory1(doc, userNo, "user");
			str = ListToString(historylist, 1, paymentDateNow, paymentDateBeforeAMonth,1)+":"+name.split(":")[1];
		}else{
			List<History2> historylist = selectHistory2(doc, userNo, name.split(":")[0]);
			str = ListToString(historylist, 2, paymentDateNow, paymentDateBeforeAMonth,1)+":"+name.split(":")[1];
		}
		return str;
	}

	public String getLastPaymentName(String userNo,
			Date paymentDateNow, Date paymentDateBeforeAMonth) {
		StringBuffer sb = new StringBuffer();
		String str = "";
		Document wtdoc = FileOperation.getFileDocument("wtdemand");
		List<History1> wtlist = selectHistory1(wtdoc,userNo,"user");
		str = ListToString(wtlist,1,paymentDateNow,paymentDateBeforeAMonth,0);
		if(str != null && !str.equals("")){
			sb.append(str).append(":").append("水费").append(":");
		}
		
		Document powdoc = FileOperation.getFileDocument("powdemand");
		List<History1> powlist = selectHistory1(powdoc,userNo,"user");
		str = ListToString(powlist,1,paymentDateNow, paymentDateBeforeAMonth,0);
		if(str != null && !str.equals("")){
			sb.append(str).append(":").append("电费").append(":");
		}
		
		Document gasdoc = FileOperation.getFileDocument("gasdemand");
		List<History1> gaslist = selectHistory1(gasdoc,userNo,"user");
		str = ListToString(gaslist,1,paymentDateNow, paymentDateBeforeAMonth,0);
		if(str != null && !str.equals("")){
			sb.append(str).append(":").append("天然气").append(":");
		}
		
		Document rendoc = FileOperation.getFileDocument("rendemand");
		List<History1> renlist = selectHistory1(rendoc,userNo,"user");
		str = ListToString(renlist,1,paymentDateNow,paymentDateBeforeAMonth,0);
		if(str != null && !str.equals("")){
			sb.append(str).append(":").append("房租").append(":");
		}
		
		Document predoc = FileOperation.getFileDocument("preplen");
		List<History2> prelist = selectHistory2(predoc,userNo,"preplen");
		str = ListToString(prelist,2,paymentDateNow,paymentDateBeforeAMonth,0);
		if(str != null && !str.equals("")){
			sb.append(str).append(":").append("手机充值").append(":");
		}
		
		Document qredoc = FileOperation.getFileDocument("qreplen");
		List<History2> qrelist = selectHistory2(qredoc,userNo,"qreplen");
		str = ListToString(qrelist,2,paymentDateNow,paymentDateBeforeAMonth,0);
		if(str != null && !str.equals("")){
			sb.append(str).append(":").append("Q币充值").append(":");
		}
		
		Document nesdoc = FileOperation.getFileDocument("nesfil");
		List<History2> neslist = selectHistory2(nesdoc,userNo,"nesfil");
		str = ListToString(neslist,2,paymentDateNow,paymentDateBeforeAMonth,0);
		if(str != null && !str.equals("")){
			sb.append(str).append(":").append("网易充值");
		}
		
		
		return sb.toString();
	}
	
	public List<History1> selectHistory1(Document doc,String userNo,String node){
		List<History1> historyList = new ArrayList<History1>();
		History1 history = null;
		NodeList n1 = doc.getElementsByTagName(node);
		for(int i = 0; i < n1.getLength();i++ ){
			Node my_node = n1.item(i);
			if(my_node.hasAttributes()){
				NamedNodeMap nnm=my_node.getAttributes();
				if(userNo.equals(nnm.getNamedItem("name").getNodeValue())){
					NodeList n2 = my_node.getChildNodes();
					for(int j = 0 ;j < n2.getLength();j++){
						Node node1 = n2.item(j);
						NodeList n3 = node1.getChildNodes();
						if(n3.getLength()>0){
							history = new History1();
							
							history.setDunum(n3.item(1).getFirstChild().getNodeValue());
							history.setDamout(n3.item(3).getFirstChild().getNodeValue());
							history.setDate(n3.item(5).getFirstChild().getNodeValue());
							history.setCharger(n3.item(7).getFirstChild().getNodeValue());
							history.setDulimit(n3.item(9).getFirstChild().getNodeValue());
							history.setAccount(n3.item(11).getFirstChild().getNodeValue());
							
							historyList.add(history);
						}
					}
				}
				
			}
		}
		return historyList;
	}
	
	public List<History2> selectHistory2(Document doc,String userNo,String node){
		List<History2> historyList = new ArrayList<History2>();
		History2 history = null;
		NodeList n1 = doc.getElementsByTagName(node);
		for(int i = 0; i < n1.getLength();i++ ){
			Node my_node = n1.item(i);
			NodeList n2 = my_node.getChildNodes();
			String user = n2.item(3).getFirstChild().getNodeValue();
			if(userNo.equals(user)){
				history = new History2();
				history.setId(n2.item(1).getFirstChild().getNodeValue());
				history.setId(user);
				history.setCredit(n2.item(5).getFirstChild().getNodeValue());
				history.setCrepnum(n2.item(7).getFirstChild().getNodeValue());
				history.setCredate(n2.item(9).getFirstChild().getNodeValue());
				history.setCreno(n2.item(11).getFirstChild().getNodeValue());
				history.setOperator(n2.item(13).getFirstChild().getNodeValue());
				history.setAccount(n2.item(15).getFirstChild().getNodeValue());
				
				historyList.add(history);
			}
		}
		return historyList;
	}
	
	private String ListToString(List list,int flag,Date paymentDateNow, Date paymentDateBeforeAMonth,int all){
		Date date = null;
		History1 history1 = null;
		History2 history2 = null;
		StringBuffer sb = new StringBuffer();
		for (int i = 0;i<list.size();i++){
			if(flag == 1){
				history1 = (History1)list.get(i);
				date = StringToDate(history1.getDate());
				if(date.before(paymentDateNow)&&date.after(paymentDateBeforeAMonth)){
					if(all == 1){
						sb.append(history1.getDate()).append(":").append(history1.getAccount()).append(":")
						.append(history1.getDamout()).append(":").append(history1.getDunum());
					}else{
						sb.append(history1.getDate());
					}
				}
			}else{
				history2 = (History2)list.get(i);
				date = StringToDate(history2.getCredate());
				if(date.before(paymentDateNow)&&date.after(paymentDateBeforeAMonth)){
					if(all == 1){
					sb.append(history2.getCredate()).append(":").append(history2.getAccount()).append(":")
					.append(history2.getCredit()).append(":").append(history2.getCreno());
					}else{
						sb.append(history2.getCredate());
					}
				}
			}
		}
		return sb.toString();
	}
	
	public Date StringToDate(String value){
		Date date = null;
		String formatPattern="yyyy-MM-dd";
		SimpleDateFormat sdf=new SimpleDateFormat(formatPattern);
		try {
			date=sdf.parse(value);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}                        
		return date;
	}

	public String DateToString(Date date){
		String result = "";
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		result = df.format(date);
		return result;
	}
	
	private String getPaymentName(int file){
		String name = "";
		switch(file){
		case 0: 
			name = "wtdemand"+":"+"水费";
			break;
		case 1:
			name = "powdemand"+":"+"电费";
			break;
		case 2:
			name = "gasdemand"+":"+"天然气";
			break;
		case 3:
			name = "rendemand"+":"+"房租";
			break;
		case 4:
			name = "preplen"+":"+"手机充值";
			break;
		case 5:
			name = "qreplen"+":"+"Q币充值";
			break;
		case 6:
			name = "nesfil"+":"+"网易充值";
			break;
		}
		return name;
	}

}

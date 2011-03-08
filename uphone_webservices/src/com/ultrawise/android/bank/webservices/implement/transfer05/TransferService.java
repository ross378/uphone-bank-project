package com.ultrawise.android.bank.webservices.implement.transfer05;

import java.util.ArrayList;
import java.util.List;

import it.sauronsoftware.base64.Base64;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;


import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

@Path("transws")

public class TransferService {

	
	
	private final static int GET_FIR_ACC = 501;
	private final static int GET_COM_ACCTPYE = 502;
	private final static int GET_COM_ACC = 503;
	private final static int GET_USER_ACC_ACTIVE = 504;
	private final static int CON_TRANSFER = 505;
	private final static int TRANSFER = 506;
	private final static int CON_TRANSFER2 = 507;
	private final static int TRANSFER2 = 508;
	
	@Consumes("application/x-www-form-urlencoded")
	@Path("do")
	@Produces("application/json")
	@POST
	public JSONObject doPsot(@FormParam("value") String anything){
		
		Transfer transfer = new Transfer();
		String[] mValue = doDecode(anything).split(":");
		int action = Integer.parseInt(mValue[0]);
		
		if(action == GET_FIR_ACC){
			
			String username = mValue[1];
			
			List<String> useracc = transfer.getfiracc(username);
			
			return wrapUp(doEncode(useracc));
			
		}else if(action == GET_COM_ACCTPYE){
			
			String username = mValue[1];
			
			List<String> acctype = transfer.getcomacctype(username);
			
			return wrapUp(doEncode(acctype));
			
		}else if(action == GET_COM_ACC){
			
			String username = mValue[1];
			String acctype = mValue[2];
			List<String> comacc = transfer.getcomacc(username,acctype);
			
			return wrapUp(doEncode(comacc));
			
		}else if(action == GET_USER_ACC_ACTIVE){
			
			String useracc = mValue[1];
			String userpasd = mValue[2];
			List<String> flag = transfer.getuserpsd(useracc,userpasd);
			
			return wrapUp(doEncode(flag));
			
		}else if(action == CON_TRANSFER){
			String useracc = mValue[1];
			String amtpsd = mValue[2];
			String amtph = mValue[3];
			String amtnum = mValue[4];
			List<String> flag = transfer.contransfer(useracc,amtnum,amtpsd,amtph);
			
			return wrapUp(doEncode(flag));
		}else if(action == TRANSFER){
			String useracc = mValue[1];
			String amtnum = mValue[2];
			String amtph = mValue[3];
			
			List<String> flag = transfer.transfer(useracc,amtnum,amtph);
			
			return wrapUp(doEncode(flag));
		}else if(action == CON_TRANSFER2){
			String useracc = mValue[1];
			String amtpsd = mValue[2];
			String amtph = mValue[3];
			String amtnum = mValue[4];
			List<String> flag = transfer.contransfer2(useracc,amtnum,amtpsd,amtph);
			
			return wrapUp(doEncode(flag));
		}
		return null;
	}
	
	private String doDecode(String strMiWen){
		return Base64.decode(strMiWen, "utf-8");
	}
	
	private List<String> doEncode(List<String> lstMingWen) {

		List<String> lstMiWen = new ArrayList<String>();
		if (lstMingWen.size() != 0) {
			for (String value : lstMingWen) {
				lstMiWen.add(Base64.encode(value, "utf-8"));
			}
		}
		return lstMiWen;

	}
	
	private JSONObject wrapUp(List<String> lstMiWen) {
		JSONObject wrapJsonObj = new JSONObject();
		try {
			for (int i = 0; i < lstMiWen.size(); i++) {
				wrapJsonObj.put("miwen" + i, lstMiWen.get(i));
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return wrapJsonObj;
	}
}

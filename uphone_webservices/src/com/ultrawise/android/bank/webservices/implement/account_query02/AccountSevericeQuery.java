package com.ultrawise.android.bank.webservices.implement.account_query02;

import it.sauronsoftware.base64.Base64;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

@Path("/query")
public class AccountSevericeQuery {
	private JSONObject mJsonObj = new JSONObject();
	private int mAction;
	private String[] mValue;
	private String mReturnString;
	// 静态的功能标识
	private final static int GET_ACC_TYPE = 21;
	private final static int GET_PAYPAL_ID = 22;
	private final static int GET_XINGXI_ID = 23;
	private final static int GET_TIME = 24;//时间段查询
	private final static int GET_TIME_QNE = 25;//例如转账
	

	/**
	 * 接收请求 1.解析json 2.解密 3.解析字符串格式 4.调用功能 5.加密 6.包装成json 7.返回json
	 * 
	 * @author hosolo
	 * @param anything
	 * @return
	 */
	@Consumes("application/x-www-form-urlencoded")
	@Path("do/")
	@Produces("application/json")
	@POST
	public JSONObject doGet(@FormParam("value") String anything) {
		
		System.out.println("前台传入的anything=" + anything);
		String[] mValue = doDecode(anything).split(":");
		int mAction = Integer.parseInt(mValue[0]);
		System.out.println("mValue[0]="+mValue[0]);
		

//		 调用功能
		switch (mAction) {
		case GET_ACC_TYPE:
			
			return wrapUp(doEncode(AccountQueryManager.getInstance().getAccType()));

		case GET_PAYPAL_ID:
			
			return wrapUp(doEncode(AccountQueryManager.getInstance().getAccountQueryByType(mValue[1])));
			
		case GET_XINGXI_ID:
			
			return wrapUp(doEncode(AccountQueryManager.getInstance().getAccountQueryById((mValue[1]))));
			
		case GET_TIME:
			System.out.println(mValue[1]);
			String[] str=mValue[1].split("#");
			System.out.println("str[0]="+str[0]);
			System.out.println("str[1]="+str[1]);
			System.out.println("str[2]="+str[2]);
			
			
			List<String> list=AccountQueryManager.getInstance().getByTime("622202114", str[1],str[2]);
			for(String g: list )
			{
				System.out.println("023==="+g.toString());
			}
			return wrapUp(doEncode(list));
			
		case GET_TIME_QNE://例如转账
			
			System.out.println(mValue[1]);
			String[] str2=mValue[1].split("#");
			System.out.println("str[0]="+str2[0]);
			System.out.println("str[1]="+str2[1]);
			
			
			List<String> list2=AccountQueryManager.getInstance().getByTimeAcct(str2[0], str2[1]);
			for(String g: list2 )
			{
				System.out.println("023==="+g.toString());
			}
			return wrapUp(doEncode(list2));
			
		default:
			List<String> lstStr = new ArrayList<String>();
			lstStr.add("sorry");
			return wrapUp(doEncode(lstStr));
		}
	}

	/**
	 * 解密
	 * 
	 * @param strMiWen
	 * @return
	 */
	private String doDecode(String strMiWen) {
		return Base64.decode(strMiWen, "utf-8");
	}
	
	
	
//	/**
//	 *加密
//	 * 
//	 * @param strMingWen
//	 * @return
//	 */
//
//	private String doEncode(List<String> lstMingWen) {
//
//		String listMiWe="";
//			for (String value : lstMingWen) {
//				listMiWe+=value+":";
//			}
//		
//		return Base64.encode(listMiWe,"UTF-8");
//	}
//
//	/**
//	 * 将每个密文包装成JSON
//	 * 
//	 * @param lstValue
//	 * @return
//	 */
//	
//	private JSONObject wrapUp(String lstMiWen) {
//		
//		JSONObject wrapJsonObj = new JSONObject();
//		try {
//				wrapJsonObj.put("miwen", lstMiWen);
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return wrapJsonObj;
//	}

	
	private List<String> doEncode(List<String> lstMingWen) {

		List<String> lstMiWen = new ArrayList<String>();
		if (lstMingWen.size() != 0) {
			for (String value : lstMingWen) {
				lstMiWen.add(Base64.encode(value, "utf-8"));
			}
		}
		return lstMiWen;

	}

	/**
	 * 将每个密文包装成JSON
	 * 
	 * @param lstValue
	 * @return
	 */
	
	private JSONObject wrapUp(List<String> lstMiWen) {
		
		JSONObject wrapJsonObj = new JSONObject();
		try {
			for (int i = 0; i < lstMiWen.size(); i++) {
				wrapJsonObj.put("miwen"+i , lstMiWen.get(i));
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return wrapJsonObj;
	}
}

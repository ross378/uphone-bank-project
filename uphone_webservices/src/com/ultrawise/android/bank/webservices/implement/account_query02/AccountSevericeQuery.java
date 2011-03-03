package com.ultrawise.android.bank.webservices.implement.account_query02;

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

@Path("/accquery")
public class AccountSevericeQuery {
	private JSONObject mJsonObj = new JSONObject();
	private int mAction;
	private String[] mValue;
	private String mReturnString;
	// 静态的功能标识
	private final static int GET_USER_NO = 101;

	/**
	 * 接收请求 1.解析json 2.解密 3.解析字符串格式 4.调用功能 5.加密 6.包装成json 7.返回json
	 * 
	 * @author hosolo
	 * @param anything
	 * @return
	 */
	@Consumes("application/x-www-form-urlencoded")
	@Path("accquery/")
	@Produces("application/json")
	@POST
	public JSONObject doGet(@FormParam("value") String anything) {
		// 解析json
		// 解密
		// 解析格式
		// String anything = "0101";
		mValue = anything.split(":");
		mAction = Integer.parseInt(mValue[0]);
		System.out.println("前台传入的anything="+anything);
		mJsonObj = doWrapUp(AccountQueryManager.getInstance().getAccountQueryByName("888"));
		// 调用功能
//		switch (mAction) {
//		case GET_USER_NO:
//			mReturnString = action.performGetUserNo();
//			mJsonObj = doWrapUp(mReturnString);
//			break;
//		default:
//			break;
//		}
//		// 加密
//		// 包装成json
		return mJsonObj;
	}

	private JSONObject doWrapUp(String value) {
		JSONObject wrapJsonObj = new JSONObject();
		try {
			wrapJsonObj.put("value", value);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return wrapJsonObj;
	}
}

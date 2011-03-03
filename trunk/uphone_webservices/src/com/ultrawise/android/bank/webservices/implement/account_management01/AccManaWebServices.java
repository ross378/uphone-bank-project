package com.ultrawise.android.bank.webservices.implement.account_management01;

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

@Path("/")
public class AccManaWebServices {
	private Action mAction = new Action(new All(), null, null, null, null,
			null, null, null, null);

	// 静态的功能标识
	private final static int GET_USER_NO = 101;
	private final static int GET_ACC_TYPE = 102;

	/**
	 * 接收请求 1.解析json 2.解密 3.解析字符串格式 4.调用功能 5.加密 6.包装成json 7.返回json
	 * 
	 * @author hosolo
	 * @param anything
	 * @return
	 */
	@Consumes("application/x-www-form-urlencoded")
	@Path("amws/")
	@Produces("application/json")
	@POST
	public JSONObject doGet(@FormParam("value") String anything) {
		// 解析json
		// 解密
		// 解析格式
		// 加密
		// 包装成json
		// return mJsonObj;
		String[] mValue = anything.split(":");
		int action = Integer.parseInt(mValue[0]);

		// 调用功能
		switch (action) {
		case GET_USER_NO:
			return wrapUp(doEncode(mAction.performGetUserNo()));

		case GET_ACC_TYPE:
			return wrapUp(doEncode(mAction.performGetAccType()));

		default:
			List<String> lstStr = new ArrayList<String>();
			lstStr.add("sorry");
			return wrapUp(lstStr);
		}
	}

	/**
	 * 将每个字符串分别加密，相当与重新把数据包装成list
	 * 
	 * @param msg
	 * @return 每个加密的字符串
	 */
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
				wrapJsonObj.put("miwen" + i, lstMiWen.get(i));
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return wrapJsonObj;
	}
}

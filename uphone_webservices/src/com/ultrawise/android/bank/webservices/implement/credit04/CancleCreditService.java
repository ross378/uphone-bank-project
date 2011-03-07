package com.ultrawise.android.bank.webservices.implement.credit04;

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

@Path("/credit")
public class CancleCreditService {
/*	private Action mAction = new Action(new All(), null, null, null, null,
			null, null, null, null);*/

	// 静态的功能标识
	private final static int Active_Credit =441;//开卡
	private final static int Cancle_Credit = 451;//销卡
	private final static int SearchPyte=410;//获取帐号类型
	private final static int SearchNo=411;//获取帐号
	private final static int CardDetail=412;//获取帐号信息

	/**
	 * 接收请求  1.解密 2.解析字符串格式 3.调用功能 4.加密 5.包装成json 6.返回json
	 * 
	 * @author hosolo
	 * @param anything
	 * @return
	 */
	@Consumes("application/x-www-form-urlencoded")
	@Produces("application/json")
	@POST
	public JSONObject doPost(@FormParam("value") String anything) {
		System.out.println("sdsdfsf");
         CancleCard cancle=new CancleCard();
		String[] mValue = doDecode(anything).split(":");
		int action = Integer.parseInt(mValue[0]);

		// 调用功能
		switch (action) {
		case Active_Credit:
			return wrapUp(Base64.encode(cancle.ActiveCardByOrderid(mValue), "utf-8"));
		case Cancle_Credit:
			return wrapUp(Base64.encode(cancle.CancleCardByOrderid(mValue), "utf-8"));
            //信用卡号，开户名，证件类型，证件号，手机号码,密码
		case SearchPyte:
			SearchPyte search=new SearchPyte();
			return wrapUp(Base64.encode(search.Search(),"utf-8"));
		case SearchNo:
			SearchCardNo searNo=new SearchCardNo();
			return wrapUp(Base64.encode(searNo.SearchNoByCardPyte(mValue[1]),"utf-8"));
		case CardDetail:
			CardDetail card=new CardDetail();
			return wrapUp(Base64.encode(card.SearchCardDetail(mValue[1]),"utf-8"));
		default:
		
			String lstStr="sorry";
			return wrapUp(lstStr);
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

	/**
	 * 加密，相当与重新把数据包装成list
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
	private JSONObject wrapUp(String lstMiWen) {
		JSONObject wrapJsonObj = new JSONObject();
		try {
				wrapJsonObj.put("miwen", lstMiWen);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return wrapJsonObj;
	}
}

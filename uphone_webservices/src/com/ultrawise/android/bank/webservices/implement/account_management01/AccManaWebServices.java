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

@Path("amws")
public class AccManaWebServices {
	private Action mAction = new Action(new All(), null, null,
			new AccountInfo(), new AccountLoss(), new AccountPre(), new Bind(),
			new NickName(), new OrderCard());

	// 静态的功能标识
	private final static int GET_ACC_TYPE = 101;
	private final static int GET_BIND_ACC = 102;
	private final static int GET_ACC_INFO = 103;
	private final static int GET_ORDER_INFO = 104;
	private final static int GET_UNBIND_ACC = 105;
	private final static int SET_BIND = 106;
	private final static int SET_LOSS = 107;
	private final static int GET_NICK_NAME = 108;
	private final static int GET_LOSS_COST = 109;
	private final static int GET_NET = 110;
	private final static int GET_NET_ADDRESS = 111;
	private final static int SET_ORDER = 112;
	private final static int GET_UNORDER_ACC = 113;
	private final static int GET_PRE_ACC = 114;
	private final static int SET_PRE_ACC = 115;
	private final static int GET_UNPRE_ACC = 116;
	private final static int ADD_ACC = 117;

	/**
	 * 接收请求 1.解密 2.解析字符串格式 3.调用功能 4.加密 5.包装成json 6.返回json
	 * 
	 * @author hosolo
	 * @param anything
	 * @return
	 */
	@Consumes("application/x-www-form-urlencoded")
	@Path("do")
	@Produces("application/json")
	@POST
	public JSONObject doPost(@FormParam("value") String anything) {

		String[] value = doDecode(anything).split(":");
		int action = Integer.parseInt(value[0]);
		String value02 = "";
		String value03 = "";
		if (value.length > 1) {
			value02 = value[1];
			if (value.length > 2) {
				value03 = value[2];
			}
		}
		// 调用功能
		switch (action) {

		case GET_ACC_TYPE:
			return wrapUp(doEncode(mAction.performGetAccType()));
		case GET_BIND_ACC:
			// params:userNo,accType
			return wrapUp(doEncode(mAction.performGetBindAcc(value02, value03)));
		case GET_ACC_INFO:
			// params:acc
			return wrapUp(doEncode(mAction.performGetAccInfo(value02)));
		case GET_ORDER_INFO:
			// params:acc
			return wrapUp(doEncode(mAction.performGetOrderInfo(value02)));
		case GET_UNBIND_ACC:
			// params:userNo,accType
			return wrapUp(doEncode(mAction
					.performGetUnbindAcc(value02, value03)));
		case SET_BIND:
			// params:unbindAcc,pwd
			return wrapUp(doEncode(mAction.performSetBind(value02, value03)));
		case SET_LOSS:
			// params:unlossAcc
			return wrapUp(doEncode(mAction.performSetLoss(value02)));
		case GET_NICK_NAME:
			// params:acc,nickName
			return wrapUp(doEncode(mAction.performGetNickName(value02)));
		case GET_LOSS_COST:
			return wrapUp(doEncode(mAction.performGetlossCost()));
		case GET_NET:
			return wrapUp(doEncode(mAction.performGetNet()));
		case GET_NET_ADDRESS:
			// params:net
			return wrapUp(doEncode(mAction.performGetNetAddress(value02)));
		case SET_ORDER:
			// params:acc
			return wrapUp(doEncode(mAction.performSetOrderCard(value02)));
		case GET_UNORDER_ACC:
			// params:userNo,accType
			return wrapUp(doEncode(mAction.performGetUnorderAcc(value02,
					value03)));
		case GET_PRE_ACC:
			// params:userNo
			return wrapUp(doEncode(mAction.performGetPreAcc(value02)));
		case SET_PRE_ACC:
			// params:userNo,acc
			return wrapUp(doEncode(mAction.performSetPreAcc(value02, value03)));
		case GET_UNPRE_ACC:
			// params:userNo
			return wrapUp(doEncode(mAction.performGetUnpreAcc(value02)));
		case ADD_ACC:
			// params:accountType,account,accountNickName,password

		default:
			return null;
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
	private JSONObject wrapUp(List<String> lstMiWen) {
		JSONObject wrapJsonObj = new JSONObject();
		if (lstMiWen.size() != 0) {
			try {
				for (int i = 0; i < lstMiWen.size(); i++) {
					wrapJsonObj.put("miwen" + i, lstMiWen.get(i));
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return wrapJsonObj;
	}
}

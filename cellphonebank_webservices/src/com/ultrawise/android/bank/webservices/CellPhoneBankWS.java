package com.ultrawise.android.bank.webservices;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.ultrawise.android.bank.Enum.EAccState;
import com.ultrawise.android.bank.Enum.EAccType;
import com.ultrawise.android.bank.Enum.EOperation;
import com.ultrawise.android.bank.implement.CreditCard;
import com.ultrawise.android.bank.implement.CurrentDeposit;
import com.ultrawise.android.bank.implement.TimeDeposits;
import com.ultrawise.log.Log;

@Path("cellphone")
public class CellPhoneBankWS {
	Logger log = Log.getInstance().getLogger();// 日志对象

	@Produces("application/json")
	@Consumes("application/x-www-form-urlencoded")
	@POST
	@Path("do")
	public JSONObject doPost(@FormParam("params") String params,
			@FormParam("accType") String accType) {
		String[] arrayParams = params.split(":");

		/* 确定账户类型，根据账户类型然后给Action赋值 */
		EAccType ea = EAccType.getEAccType(accType);
		Action action = null;
		switch (ea) {
		case CREDIT_CARD:
			// 信用卡
			CreditCard cc = new CreditCard();
			action = Action.getAction(cc, cc, cc, cc);
			break;
		case CURRENT_DEPOSIT:
			// 活期储蓄卡
			CurrentDeposit cd = new CurrentDeposit();
			action = Action.getAction(cd, cd, cd, null);
			break;
		case TIME_DEPOSITS:
			// 定期储蓄卡
			TimeDeposits td = new TimeDeposits();
			action = Action.getAction(td, td, null, null);
			break;
		}

		/* 获取枚举功能号 */
		EOperation eo = EOperation.getEoperation(arrayParams[0]);
		/* 根据第一个参数获得得功能号进行功能选择 */
		switch (eo) {
		/**
		 * 系统功能
		 */
		case GET_ACC_TYPE:
		case GET_ACC_WITH_NICKNAME:
		case ADD_ACC:
		case GET_ACC:
		case GET_ACC_TYPE_ALL:
		case GET_ACC_TYPE_ON_CREDITCARD:
		case GET_COME_HISTORY:
		case GET_COST:
		case GET_CREDIT_CARD:
		case GET_EXTRA_CODE:
		case GET_LIST_HISTORY:
		case GET_NET:
		case GET_NET_ADDRESS:
		case GET_OPERATOR:
		case GET_PAYMENT_HISTORY:
		case GET_PAYMENT_NAME:
		case GET_PAYMENT_NAME_ON_MANA:
		case GET_PRE_ACC:
		case GET_SEL_SERVICE_NAME:
		case GET_SEL_SERVICE_NAME_BY_USER_ID:
		case GET_USED_CREDIT_CARD:
		case LOGIN:
		case SET_PRE_ACC:
		case UPDATE_PAYMENT_STATE:

			/**
			 * 查询功能
			 */
		case GET_ACC_INFO:
			// 查询账号信息
			return action.performGetAccInfo(arrayParams[1]);
		case GET_NICK_NAME:
		case GET_ORDER_INFO:
		case ACC_IS_ACTIVE:

			/**
			 * 修改功能
			 */
		case SET_NICK_NAME:
		case SET_ORDER_CARD:
		case SET_BIND:
		case LOSS_REGISTER:
		case DELE_ACC:
		case SET_ACC_ACTIVE:

			/**
			 * 转账部分
			 */
		case GET_TARGET_PHONE_INFO:
			// 取得对方手机信息
			return action.performGetTargetPhoneInfo(arrayParams[1],
					arrayParams[2], arrayParams[3], Double
							.parseDouble(arrayParams[4]));
		case TRANSFE_ACC:
			// 确认转账
			return action.performTransfeAct(arrayParams[1], arrayParams[2],
					arrayParams[3], Double.parseDouble(arrayParams[4]));
		case GET_LISTQUERY_INFO:
			// 明细信息
			return action.performGetListQueryInfo(arrayParams[1]);
		case GET_COMEQUERY_INFO:
			// 来账信息
			return action.performGetComeQueryInfo(arrayParams[1]);
		case SET_DETAIL:
			// 设置描述信息
			return action.performSetDetail(arrayParams[1], arrayParams[2]);
		case GET_PAYMENT_HIS_INFO:
			// 查看某条历史缴费记录的详细信息
			return action.performGetPaymentHisInfo(arrayParams[1],
					arrayParams[2]);
		case GET_RECHARGE_INFO:

		case RECHARGE:
			// 确认缴费
			return action.performRecharge(arrayParams[1], Double
					.parseDouble(arrayParams[2]), arrayParams[3],
					arrayParams[4], arrayParams[5],arrayParams[6]);
		case GET_PAYMENT_INFO:
			// 查看某条待缴费的详细信息
			return action.performGetPaymentInfo(arrayParams[1]);

			/**
			 * 信用卡部分
			 */
		case OPEN_CARD:
		case DESTROY_CARD:
		case GET_CREDIT_REPAYMENT_INFO:
		case CREDIT_REPAYMENT:

			/**
			 * 助手部分
			 */
		case GET_MONEY_TYPE:
		case GET_RATE:
		case GET_EXCHANGE_RESULT:

		default:
			JSONObject jsonObj = null;
			try {
				jsonObj = new JSONObject().put("404", 404);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return jsonObj;
		}
	}
}

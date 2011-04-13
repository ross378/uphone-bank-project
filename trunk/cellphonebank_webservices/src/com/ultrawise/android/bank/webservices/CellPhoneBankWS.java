package com.ultrawise.android.bank.webservices;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.ultrawise.android.bank.Enum.EAccState;
import com.ultrawise.android.bank.Enum.EAccType;
import com.ultrawise.android.bank.Enum.ECoin;
import com.ultrawise.android.bank.Enum.EOperation;
import com.ultrawise.android.bank.Enum.ERateType;
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
	public JSONObject doPost(@FormParam("params") String params) {
		/* 获取参数格式，账户类型(可null):功能号:参数1,参数2 */
		String[] arrayParams = params.split(":");
		String accTypeId = "";
		String operationNo = "";
		String firstValue = "";
		String secondValue = "";
		String thirdParam = "";
		String fourParam = "";
		String fiveParam = "";
		String sixParam = "";
		String sevenParam = "";
		int len = arrayParams.length;
		if (len > 8)
			sevenParam = arrayParams[8];
		if (len > 7)
			sixParam = arrayParams[7];
		if (len > 6)
			fiveParam = arrayParams[6];
		if (len > 5)
			fourParam = arrayParams[5];
		if (len > 4)
			thirdParam = arrayParams[4];
		if (len > 3)
			secondValue = arrayParams[3];
		if (len > 2)
			firstValue = arrayParams[2];
		if (len > 1) {
			operationNo = arrayParams[1];
			accTypeId = arrayParams[0];
		}

		// CurrentDeposit cd = new CurrentDeposit();
		// Action action = Action.getAction(cd, cd, cd, null);

		Action action = null;
		/* 确定账户类型，根据账户类型然后给Action赋值 */
		EAccType ea = EAccType.getEAccTypeById(accTypeId);
		if (ea == EAccType.NULL) {
			// 如果账户类型为默认值0，即操作与实体信用卡和储蓄卡没有关系，将调用无参数构造的action
			action = Action.getNewAction();
		} else {
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
		}

		/* 获取枚举功能号 */
		EOperation eo = EOperation.getEoperation(operationNo);
		/* 根据第一个参数获得得功能号进行功能选择 */
		switch (eo) {
		/**
		 * 系统功能
		 */
		case GET_ACC_TYPE:
			// 获取账户类型
			return action.performGetAccType();
		case GET_ACC_WITH_NICKNAME:
			// 获取账户带有别名的
			return action.performGetAccWithNickName(firstValue, secondValue,
					EAccState.getAccState(thirdParam));
		case ADD_ACC:
			// 添加账户
			return action.performAddAcc(firstValue, secondValue, thirdParam,
					fourParam, fiveParam);
		case GET_ACC:
			// 获取账户，除定期
			return action.performGetAcc(firstValue, secondValue, EAccState
					.getAccState(thirdParam));
		case GET_ACC_TYPE_ALL:
			// 获取所有账户类型
			return action.performGetAccTypeAll();
		case GET_ACC_TYPE_ON_CREDITCARD:
			// 获取账户类型，除定期，信用卡
			return action.performGetAccTypeOnCreditCard();
		case GET_COME_HISTORY:
			// 获取来账历史列表
			return action.performGetComeHistory(firstValue, secondValue,
					thirdParam);
		case GET_COST:
			// 获取工本费
			return action.performGetCost(firstValue);
		case GET_CREDIT_CARD:
			// 取得已注册的信用卡
			return action.performGetCreditCard(firstValue);
		case GET_EXTRA_CODE:
			// 获取附加码
			return action.performGetExtraCode();
		case GET_LIST_HISTORY:
			// 获取明细历史列表
			return action.performGetListHistory(firstValue, secondValue,
					thirdParam);
		case GET_NET:
			// 获取网点
			return action.performGetNet();
		case GET_NET_ADDRESS:
			// 获取网点地址
			return action.performGetNetAddress(firstValue);
		case GET_OPERATOR:
			// 获取运营商
			return action.performGetOperator(firstValue);
		case GET_PAYMENT_HISTORY:
			// 获取缴费历史记录列表
			return action.performGetPaymentHistory(firstValue, secondValue,
					thirdParam);
		case GET_BIND_CREDIT_CARD:
			// 获取已绑定的信用卡
			return action.performGetBindCreditCard(firstValue);
		case GET_PAYMENT_NAME:
			// 获取待缴费的名称列表
			return action.performGetPaymentName(firstValue);
		case GET_PAYMENT_NAME_ON_MANA:
			// 获取缴费名称列表
			return action.performGetPaymentNameOnMana();
		case GET_PRE_ACC:
			// 获取首选账户
			return action.performGetPreAcc(firstValue);
		case GET_SEL_SERVICE_NAME:
			// 获取便捷服务的名称列表
			return action.performGetSelServiceName();
		case GET_SEL_SERVICE_NAME_BY_USER_ID:
			// 通过用户id获取便捷服务的名称列表
			return action.performGetSelServiceNameByUserId(firstValue);
		case GET_USED_CREDIT_CARD:
			// 获取已经用过的信用卡号
			return action.performGetUsedCreditCard(firstValue);
		case LOGIN:
			// 登录
			return action.performLogin(firstValue, secondValue, thirdParam);
		case SET_PRE_ACC:
			// 设置首选账户
			return action.performSetPreAcc(firstValue, secondValue);
		case UPDATE_PAYMENT_STATE:
			// 更改服务的状态
			return action.performUpdatePaymentState(firstValue, secondValue);

			/**
			 * 查询功能
			 */
		case GET_ACC_INFO:
			// 查询账号信息
			return action.performGetAccInfo(firstValue);
		case GET_NICK_NAME:
			// 获取账户别名
			return action.performGetNickName(firstValue);
		case GET_ORDER_INFO:
			// 获取预约信息
			return action.performGetOrderInfo(firstValue);
		case ACC_IS_ACTIVE:
			// 是否激活
			return action.performAcctIsActive(firstValue);

			/**
			 * 修改功能
			 */
		case SET_NICK_NAME:
			// 设置账户别名
			return action.performSetNickName(firstValue, secondValue);
		case SET_ORDER_CARD:
			// 预约换卡
			return action.performSetOrderCard(firstValue, secondValue,
					thirdParam, fourParam, fiveParam, Double
							.parseDouble(sixParam));
		case SET_BIND:
			// 绑定账户
			return action.performSetBind(firstValue);
		case LOSS_REGISTER:
			// 挂失账户
			return action.performLossRegister(firstValue);
		case DELE_ACC:
			// 删除账户
			return action.performDeleAcc(firstValue);
		case SET_ACC_ACTIVE:
			// 激活账户
			return action.performSetActActive(firstValue, secondValue);

			/**
			 * 转账部分
			 */
		case GET_TARGET_PHONE_INFO:
			// 取得对方手机信息
			return action.performGetTargetPhoneInfo(firstValue, secondValue,
					thirdParam, Double.parseDouble(fourParam));
		case TRANSFE_ACC:
			// 确认转账
			return action.performTransfeAct(firstValue, secondValue,
					thirdParam, Double.parseDouble(fourParam));
		case GET_LISTQUERY_INFO:
			// 明细信息
			return action.performGetListQueryInfo(firstValue,secondValue);
		case GET_COMEQUERY_INFO:
			// 来账信息
			return action.performGetComeQueryInfo(firstValue, secondValue);
		case SET_DETAIL:
			// 设置描述信息
			return action.performSetDetail(firstValue, secondValue);
		case GET_PAYMENT_HIS_INFO:
			// 查看某条历史缴费记录的详细信息
			return action.performGetPaymentHisInfo(firstValue);
		case GET_RECHARGE_INFO:
			// 获取缴费信息
		case RECHARGE:
			// 确认充值
			return action.performRecharge(firstValue, Double
					.parseDouble(secondValue), thirdParam, fourParam,
					fiveParam, sixParam);
		case PAYMENT:
			// 确认缴费
			return action
					.performPayment(firstValue,
							Double.parseDouble(secondValue), thirdParam,
							fourParam, fiveParam);
		case GET_PAYMENT_INFO:
			// 查看某条待缴费的详细信息
			return action.performGetPaymentInfo(firstValue, secondValue);

			/**
			 * 信用卡部分
			 */
		case OPEN_CARD:
			// 开卡
			return action.performOpenCard(firstValue, secondValue, thirdParam,
					fourParam, fiveParam, sixParam, sevenParam);
		case DESTROY_CARD:
			// 销卡
			return action.performDestroyCard(firstValue, secondValue,
					thirdParam, fourParam, firstValue);
		case GET_CREDIT_REPAYMENT_INFO:
			// 获取信用卡还款信息
			return action.performGetCreditRepaymentInfor(firstValue);
		case CREDIT_REPAYMENT:
			// 信用卡还款
			return action.performCreditRepayment(firstValue, secondValue,
					thirdParam, Double.parseDouble(fourParam));

			/**
			 * 助手部分
			 */
		case GET_MONEY_TYPE:
			// 获取币种
			return action.performGetMoneyType();
		case GET_RATE:
			// 获取利率
			return action.performGetRate(ERateType.getRateType(firstValue));
		case GET_EXCHANGE_RESULT:
			// 获取汇率转换的结果
			return action.performGetExchangeResult(Double
					.parseDouble(firstValue), ECoin.getCoin(secondValue), ECoin
					.getCoin(thirdParam));
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

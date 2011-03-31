package com.ultrawise.android.bank.implement;

import java.util.List;
import java.util.Map;

import com.ultrawise.android.bank.Enum.ERateType;
import com.ultrawise.android.bank.base.IHelper;

public class FinaHelper implements IHelper {

	public double getExchangeResult(double currencyDenomination,
			String sourceCurrencyType, String destinationCurrencyType) {
		// TODO Auto-generated method stub
		return 0;
	}

	public Map<String, String> getRate(ERateType type) {
		// TODO Auto-generated method stub
		switch (type) {
		case LENDING_RATE:
			// 贷款利率
			break;

		case DEPOSIT_RATE:
			// 存款利率
			break;
		}
		return null;
	}

	public List<String> getMoneyType() {
		// TODO Auto-generated method stub
		return null;
	}

}

package com.ultrawise.android.bank.implement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.ultrawise.android.bank.Enum.ECoin;
import com.ultrawise.android.bank.Enum.ERateType;
import com.ultrawise.android.bank.base.IHelper;
import com.ultrawise.bank.implement.dao.DataAccessModel;

public class FinaHelper implements IHelper {

	public double getExchangeResult(double currencyDenomination,
			ECoin sourceCurrencyType, ECoin destinationCurrencyType) {
		// TODO Auto-generated method stub
		double dExchange = 0;

		// boolean isDollar = false;
		boolean isSource = false;
		boolean isDestin = false;
		String dollarRate = "1.0";
		String SourceRate = "";
		String DestinRate = "";
		List<HashMap<String, String>> rate = DataAccessModel.newInstances()
				.createQueryTools().query("exchangeRateInfo");
		for (HashMap<String, String> hashMap : rate) {
			for (Entry<String, String> e : hashMap.entrySet()) {
				// 如果是源，保存
				if (e.getKey().equals("coin")) {
					if (e.getValue().equals(
							ECoin.getCoinName(sourceCurrencyType))) {
						isSource = true;
					}
				}
				if (e.getKey().equals("rate")) {
					SourceRate = e.getValue();
				}
				if (isSource && !SourceRate.equals("")) {
					break;
				}
			}
		}
		for (HashMap<String, String> hashMap : rate) {
			for (Entry<String, String> e : hashMap.entrySet()) {
				// 如果是目标，保存
				if (e.getKey().equals("coin")) {
					if (e.getValue().equals(
							ECoin.getCoinName(destinationCurrencyType))) {
						isDestin = true;
						break;
					}
				}
				if (e.getKey().equals("rate")) {
					DestinRate = e.getValue();
				}
				if (isDestin && !DestinRate.equals("")) {
					break;
				}
			}
		}
		if (isSource && isDestin) {
			// 进行计算
			double dDollar = Double.parseDouble(dollarRate);
			double dSource = Double.parseDouble(SourceRate);
			double dDestin = Double.parseDouble(DestinRate);
			System.out.println(dDollar);
			System.out.println(dSource);
			System.out.println(dDestin);
			
			double tempEx = (dDollar / dSource) * dDestin;
			dExchange=currencyDenomination*tempEx;
			return dExchange;
		}
		return dExchange;
	}

	public Map<String, String> getRate(ERateType type) {
		// TODO Auto-generated method stub
		Map<String, String> map = new HashMap<String, String>();
		List<HashMap<String, String>> rate = new ArrayList<HashMap<String, String>>();
		switch (type) {
		case LENDING_RATE:
			// 贷款利率
			rate = DataAccessModel.newInstances().createQueryTools().query(
					"loanRateInfo");
			for (HashMap<String, String> hashMap : rate) {
				String id = "";
				for (Entry<String, String> e : hashMap.entrySet()) {
					if (e.getKey().equals("id")) {
						id = e.getValue();
					}

					if (e.getKey().equals("rate")) {
						// 所有的汇率全部都放在一起
						map.put(id, e.getValue());
					}
				}
			}
			return map;

		case DEPOSIT_RATE:
			// 存款利率
			rate = DataAccessModel.newInstances().createQueryTools().query(
					"depositeRateInfo");
			for (HashMap<String, String> hashMap : rate) {
				String id = "";
				for (Entry<String, String> e : hashMap.entrySet()) {
					if (e.getKey().equals("id")) {
						id = e.getValue();
					}
					if (e.getKey().equals("rate")) {
						// 所有的汇率全部都放在一起
						map.put(id, e.getValue());
					}
				}
			}
			return map;
		}
		return map;
	}

	public List<String> getMoneyType() {
		// TODO Auto-generated method stub
		List<String> lstStr = new ArrayList<String>();
		List<HashMap<String, String>> moneyType = DataAccessModel
				.newInstances().createQueryTools().query("exchangeRateInfo");
		for (HashMap<String, String> hashMap : moneyType) {
			for (Entry<String, String> e : hashMap.entrySet()) {
				if (e.getKey().equals("coin")) {
					// 把所有的币种都添加到list里面
					lstStr.add(e.getValue());
				}
			}
		}
		return lstStr;
	}

}

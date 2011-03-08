package com.ultrawise.android.bank.webservices.base.credit04;

public interface ICardDetail {
  public String SearchCardDetail(String cardNo);
  public String getByTime(String cardNo,String start,String end);//按时间查询交易信息
}

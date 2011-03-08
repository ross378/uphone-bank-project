package com.ultrawise.android.bank.webservices.implement.account_management01;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class AmTest {

	@Test
	@Ignore
	public void testGetAccInfo() {

		List<String> lstStr = new ArrayList<String>();
		lstStr.add("110");
		lstStr.add("paypal01");
		lstStr.add("No");
		lstStr.add("我的定期存蓄卡");
		lstStr.add("1000,00");
		lstStr.add("人民币");
		lstStr.add("三个月");
		lstStr.add("201011");
		lstStr.add("1.5%");
		lstStr.add("五年");
		lstStr.add("是");
		lstStr.add("x");
		lstStr.add("x");
		lstStr.add("x");
		lstStr.add("x");

		AccountInfo ai = new AccountInfo();
		Assert.assertEquals(lstStr, ai.getAccInfo("622202111"));
		// fail("Not yet implemented");
	}

	@Test
	@Ignore
	public void testGetAccType() {
		All a = new All();
		List<String> lstStr = new ArrayList<String>();
		lstStr.add("活期储蓄卡");
		lstStr.add("定期储蓄卡");
		lstStr.add("信用卡");
		Assert.assertEquals(lstStr, a.getAccType());

	}

	@Test
	@Ignore
	public void testSetBind() {
		Bind b = new Bind();
		Assert.assertEquals(true, b.setBind("622202111", "123456"));
	}

	@Test
	@Ignore
	public void testGetBindAcc() {
		All a = new All();

		List<String> lstStr = new ArrayList<String>();
		lstStr.add("622202111");
		// lstStr.add("622202112");
		Assert.assertEquals(lstStr, a.getBindAcc("Sun01", "活期储蓄卡"));
	}

	@Test
	@Ignore
	public void testGetUnbindAcc() {
		Bind a = new Bind();

		List<String> lstStr = new ArrayList<String>();
		lstStr.add("622202113");
		Assert.assertEquals(lstStr, a.getUnBindAcc("Sun01", "信用卡"));
	}

	@Test
	@Ignore
	public void testSetLoss() {
		AccountLoss a = new AccountLoss();
		Assert.assertEquals(true, a.setLoss("622202111"));

	}

	@Test
	@Ignore
	public void testGetNickName() {
		All a = new All();
		assertEquals("我的存蓄卡", a.getNickName("622202111"));
	}

	@Test
	@Ignore
	public void testGetCost() {
		All a = new All();
		assertEquals("10元", a.getLossCost());
	}

	@Test
	@Ignore
	public void testGetNet() {
		OrderCard o = new OrderCard();
		List<String> lstStr = new ArrayList<String>();
		lstStr.add("小北储蓄所");
		lstStr.add("省行营业室");
		assertEquals(lstStr, o.getNet());
	}

	@Test
	@Ignore
	public void testGetAddress() {
		OrderCard o = new OrderCard();
		Assert.assertEquals("天堂区小北路08号", o.getAddress("小北储蓄所"));
	}

	@Test
//	@Ignore
	public void testSetOrderCard() {
		OrderCard o = new OrderCard();
		Assert.assertEquals(true, o.setOrderCard("622202111"));
	}
}

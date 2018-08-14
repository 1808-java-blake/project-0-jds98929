package com.revature.Bank;

import org.junit.Assert;
import org.junit.Test;

import com.revature.beans.User;
import com.revature.screens.DepositScreen;
import com.revature.screens.WithdrawalScreen;

public class BankTest {
	private User u = new User();
	private static final DepositScreen ds = new DepositScreen();
	private static final WithdrawalScreen ws = new WithdrawalScreen();
	
	@Test
	public void checkingDepositTest() {
		u.setCheckingAccountBalance("0.00");
		double result = ds.getCheckingBalance(u, 50.75);
		Assert.assertEquals(50.75, result, 0);
	}
	
	@Test
	public void savingsDepositTest() {
		u.setSavingsAccountBalance("0.00");
		double result = ds.getSavingsBalance(u, 50.75);
		Assert.assertEquals(50.75, result, 0);
	}
	
	@Test
	public void checkingWithdrawTest() {
		u.setCheckingAccountBalance("60.00");
		double result = ws.getCheckingBalance(u, 50.75);
		Assert.assertEquals(9.25, result, 0);
	}
	
	@Test
	public void savingsWithdrawTest() {
		u.setSavingsAccountBalance("60.00");
		double result = ws.getSavingsBalance(u, 50.75);
		Assert.assertEquals(9.25, result, 0);
	}


}

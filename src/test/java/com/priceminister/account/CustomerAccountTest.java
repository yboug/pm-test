package com.priceminister.account;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import com.priceminister.account.implementation.CustomerAccount;
import com.priceminister.account.implementation.rules.CustomerAccountRule;

/**
 * Please create the business code, starting from the unit tests below.
 * Implement the first test, the develop the code that makes it pass. Then focus
 * on the second test, and so on.
 * <p>
 * We want to see how you "think code", and how you organize and structure a
 * simple application.
 * <p>
 * When you are done, please zip the whole project (incl. source-code) and send
 * it to recrutement-dev@priceminister.com
 */
@RunWith(Parameterized.class)
public class CustomerAccountTest {
	Double amount;
	Double expectedAmount;
	Account customerAccount;
	AccountRule rule;

	public CustomerAccountTest(Double amount, Double expectedAmount) {
		this.amount = amount;
		this.expectedAmount = expectedAmount;
	}

	@Parameterized.Parameters
	public static Collection rangeNumber() {
		return Arrays.asList(new Object[][]{{2d, 2d}, {6d, 8d}, {19d, 27d}, {22d, 49d}, {23d, 72d}});
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		rule = new CustomerAccountRule();
		customerAccount = CustomerAccountSingleton.getCustomerAccount();
	}

	/**
	 * Tests that an empty account always has a balance of 0.0, not a NULL.
	 */
	@Test
	public void testAccountWithoutMoneyHasZeroBalance() {
		assertEquals(0.0, new CustomerAccount().getBalance(), 0.0);
	}

	/**
	 * Adds money to the account and checks that the new balance is as expected.
	 */
	@Test
	public void testAddPositiveAmount() {
		customerAccount.add(amount);
		assertEquals(customerAccount.getBalance(), expectedAmount, 0.0);
	}

	// Also implement missing unit tests for the above functionalities.

	/**
	 * Tests that an illegal withdrawal throws the expected exception. Use the logic
	 * contained in CustomerAccountRule; feel free to refactor the existing code.
	 */
	@Test(expected = IllegalBalanceException.class)
	public void testWithdrawAndReportBalanceIllegalBalance() throws IllegalBalanceException {
		customerAccount.withdrawAndReportBalance(73d, rule);
	}

}

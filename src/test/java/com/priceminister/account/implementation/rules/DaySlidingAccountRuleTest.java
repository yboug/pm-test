package com.priceminister.account.implementation.rules;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import com.priceminister.account.Account;
import com.priceminister.account.AccountRule;
import com.priceminister.account.AccountWithdrawalsHistory;
import com.priceminister.account.implementation.CustomerAccount;
import com.priceminister.account.implementation.CustomerAccountWithdrawalsHistory;

public class DaySlidingAccountRuleTest {

	AccountWithdrawalsHistory withdrawalsHistory;
	Account account;
	AccountRule accountRule;

	/**
	 * Account balance = 200 euro withdrawal 100 euro today - 1 withdrawal 50 euro
	 * today - 2 withdrawal 0 euro today limit sliding three days 300 euro
	 */

	@Before
	public void setUp() {
		account = new CustomerAccount();
		account.add(200d);
		withdrawalsHistory = new CustomerAccountWithdrawalsHistory();
		withdrawalsHistory.add(LocalDate.now(), 0d);
		withdrawalsHistory.add(LocalDate.now().minusDays(1), 100d);
		withdrawalsHistory.add(LocalDate.now().minusDays(2), 50d);
		accountRule = new DaySlidingAccountRule(withdrawalsHistory, 300d, 3, account);
	}

	@Test
	public void given_account_balance_and_three_days_sliding_should_withdraw() {
		assertTrue(accountRule.withdrawPermitted(51d));
		assertTrue(accountRule.withdrawPermitted(50d));
		assertFalse(accountRule.withdrawPermitted(49d));
	}
}

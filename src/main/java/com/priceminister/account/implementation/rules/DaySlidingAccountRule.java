package com.priceminister.account.implementation.rules;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.priceminister.account.Account;
import com.priceminister.account.AccountRule;
import com.priceminister.account.AccountWithdrawalsHistory;

public class DaySlidingAccountRule implements AccountRule {

	private final AccountWithdrawalsHistory accountWithDrawHistory;

	private final Double limit;

	private final int previousDay;

	private final Account account;

	public DaySlidingAccountRule(AccountWithdrawalsHistory accountWithDrawHistory, Double limit, int previousDay,
			Account account) {
		this.accountWithDrawHistory = accountWithDrawHistory;
		this.limit = limit;
		this.previousDay = previousDay;
		this.account = account;
	}

	@Override
	public boolean withdrawPermitted(Double resultingAccountBalance) {
		double withdrawAmount = account.getBalance() - resultingAccountBalance;
		return Double.compare(
				withdrawAmount + buildDays().stream().filter(date -> accountWithDrawHistory.history().get(date) != null)
						.mapToDouble(date -> accountWithDrawHistory.history().get(date)).sum(),
				limit) <= 0;
	}

	private List<LocalDate> buildDays() {
		List<LocalDate> dates = new ArrayList<>(previousDay);
		for (int i = 0; i < previousDay; i++) {
			dates.add(LocalDate.now().minusDays(i));
		}
		return dates;
	}
}

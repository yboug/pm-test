package com.priceminister.account.implementation;

import com.priceminister.account.Account;
import com.priceminister.account.AccountRule;
import com.priceminister.account.IllegalBalanceException;

public class CustomerAccount implements Account {

	private Double balance;

	public CustomerAccount() {
		this.balance = 0.0;
	}

	public void add(Double addedAmount) {
		balance = Double.sum(balance, addedAmount);
	}

	public Double getBalance() {
		return balance;
	}

	public Double withdrawAndReportBalance(Double withdrawnAmount, AccountRule rule) throws IllegalBalanceException {
		Double result = balance - withdrawnAmount;
		if (rule.withdrawPermitted(result)) {
			balance = result;
			return balance;
		}
		throw new IllegalBalanceException(result);
	}

}

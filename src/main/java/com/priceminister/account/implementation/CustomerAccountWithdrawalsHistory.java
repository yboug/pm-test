package com.priceminister.account.implementation;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import com.priceminister.account.AccountWithdrawalsHistory;

public class CustomerAccountWithdrawalsHistory implements AccountWithdrawalsHistory {

	private Map<LocalDate, Double> withdrawalsMap = new HashMap<>();

	@Override
	public Map<LocalDate, Double> history() {
		return withdrawalsMap;
	}

	@Override
	public void add(LocalDate date, Double withdrawalAmount) {
		withdrawalsMap.merge(date, withdrawalAmount, Double::sum);
	}
}

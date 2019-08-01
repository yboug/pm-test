package com.priceminister.account;

import java.time.LocalDate;
import java.util.Map;

public interface AccountWithdrawalsHistory {

	/**
	 * @return map contains history of withdrawals range of dates including today
	 */
	Map<LocalDate, Double> history();

	/**
	 * add a new withdrawal to existing or newer transaction in a specific date if
	 * the date exist : the new amount will be : (existing withdrawals amount) +
	 * (the new withdrawal amount)
	 *
	 * @param date
	 *            day of withdrawal
	 * @param withdrawalAmount
	 *            withdrawal amount
	 */
	void add(LocalDate date, Double withdrawalAmount);
}

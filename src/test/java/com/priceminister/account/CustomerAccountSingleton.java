package com.priceminister.account;

import com.priceminister.account.implementation.CustomerAccount;

public class CustomerAccountSingleton {

	private static Account customerAccount;

	public static Account getCustomerAccount() {
		if (customerAccount == null) {
			customerAccount = new CustomerAccount();
		}
		return customerAccount;
	}
}

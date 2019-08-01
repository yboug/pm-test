package com.priceminister.account.implementation.rules;

import com.priceminister.account.AccountRule;

public class ClassicAccountRule implements AccountRule {
	@Override
	public boolean withdrawPermitted(Double resultingAccountBalance) {
		return false;
	}
}

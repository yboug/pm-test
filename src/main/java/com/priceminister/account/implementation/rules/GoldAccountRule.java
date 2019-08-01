package com.priceminister.account.implementation.rules;

import com.priceminister.account.AccountRule;

public class GoldAccountRule implements AccountRule {

	private Double overDraft;

	public GoldAccountRule(Double overDraft) {
		this.overDraft = overDraft;
	}

	@Override
	public boolean withdrawPermitted(Double resultingAccountBalance) {
		return overDraft + resultingAccountBalance > 0;
	}
}

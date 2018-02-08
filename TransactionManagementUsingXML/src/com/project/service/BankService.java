package com.project.service;

import com.project.model.Account;

public interface BankService {

	public void transferFund(Account fromAccount, Account toAccount, Double amount);
}

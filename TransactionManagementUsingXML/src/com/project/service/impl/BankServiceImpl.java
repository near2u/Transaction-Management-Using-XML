package com.project.service.impl;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.project.dao.BankDao;
import com.project.model.Account;
import com.project.service.BankService;

public class BankServiceImpl implements BankService {
	
	private BankDao bankDao;
	
	public void setBankDao(BankDao bankDao) {
		this.bankDao = bankDao;
	}

	
	@Override
	public void transferFund(Account fromAccount, Account toAccount,
			Double amount) {
	
		bankDao.withdraw(fromAccount, toAccount, amount);
		bankDao.deposit(fromAccount, toAccount, amount);
		
	}

}

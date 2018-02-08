package com.project.dao.impl;

import javax.sql.rowset.JdbcRowSet;

import org.springframework.jdbc.core.JdbcTemplate;

import com.project.dao.BankDao;
import com.project.dao.mapper.BankRowMapper;
import com.project.model.Account;

public class BankDaoImpl implements BankDao{
	
	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	

	@Override
	public void withdraw(Account fromAccount, Account toAccount, Double amount) {
		
		Account account = getAccountBalance(fromAccount);
		Double balance = account.getAccountBalance() - amount;
		
		String sql  = "update icici_bank set account_balance=? where account_number=?";
		int update = jdbcTemplate.update(sql, balance, account.getAccountNumber());
		if(update>0) {
			System.out.println("Amount "+amount+" withdrawed from account: "+account.getAccountNumber());
		}
		
		
	}


	private Account getAccountBalance(Account fromAccount) {
		String sql = "select * from icici_bank where account_number=?";
		Account account  = jdbcTemplate.queryForObject(sql, new BankRowMapper(), fromAccount.getAccountNumber());
		return account;
	}

	@Override
	public void deposit(Account fromAccount, Account toAccount, Double amount) {
		
		Account account = getAccountBalance(toAccount);
		Double balance = account.getAccountBalance() + amount;
		
		String sql  = "update icici_bank set account_balance=? where account_number=?";
		int update = jdbcTemplate.update(sql, balance, account.getAccountNumber());
		if(update>0) {
			System.out.println("Amount "+amount+" withdrawed from account: "+account.getAccountNumber());
		}
		throw new RuntimeException();
	}

}

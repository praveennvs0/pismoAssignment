package com.paysafe.transactionRoutine.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paysafe.transactionRoutine.repository.Account;
import com.paysafe.transactionRoutine.repository.AccountRepository;
import com.paysafe.transactionRoutine.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepository;
	
	@Override
	public Account saveAccount(Account account) {
		Account accountResource = accountRepository.save(account);
		return accountResource;
	}

	@Override
	public Account getAccountById(int accountId) {
		Account account = accountRepository.findByAccountId(accountId);
		return account;
	}
	

}

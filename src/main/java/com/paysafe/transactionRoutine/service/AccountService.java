package com.paysafe.transactionRoutine.service;

import com.paysafe.transactionRoutine.repository.Account;

public interface AccountService {

	Account saveAccount(Account account);
	
	Account getAccountById(int accountId);
}

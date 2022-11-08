package com.paysafe.transactionRoutine.tests;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.paysafe.transactionRoutine.exception.TransactionRoutineException;
import com.paysafe.transactionRoutine.repository.Account;
import com.paysafe.transactionRoutine.repository.AccountRepository;
import com.paysafe.transactionRoutine.repository.Transaction;
import com.paysafe.transactionRoutine.service.impl.AccountServiceImpl;
import com.paysafe.transactionRoutine.validator.TransactionValidator;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceImplTest {

	@Mock
	private AccountRepository accountRepository;
	
	@InjectMocks
	private AccountServiceImpl accountServiceImpl;
	
	@Test
	public void saveAccount() throws TransactionRoutineException {
		Account account = Account.builder().accountId(1).documentNumber("1223").build();
		Account accountInResponse = Account.builder().accountId(10).documentNumber("423").build();
		Mockito.when(accountRepository.save(account)).thenReturn(account);
		accountServiceImpl.saveAccount(account);
		Mockito.verify(accountRepository).save(account);
		Assert.assertEquals(accountInResponse.getAccountId(),10);
		
		
	}
	
	@Test
	public void getAccount() throws TransactionRoutineException {
		int accountId = 123;
		Account account = Account.builder().accountId(accountId).documentNumber("1223").build();
		Account accountInResponse = Account.builder().accountId(10).documentNumber("423").build();
		Mockito.when(accountRepository.findByAccountId(accountId)).thenReturn(account);
		accountServiceImpl.getAccountById(accountId);
		Mockito.verify(accountRepository).findByAccountId(accountId);
		Assert.assertEquals(accountInResponse.getAccountId(),10);
		
		
	}
}

package com.paysafe.transactionRoutine.tests;

import java.math.BigDecimal;

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
import com.paysafe.transactionRoutine.validator.TransactionValidator;

@RunWith(MockitoJUnitRunner.class)
public class TransactionValidatorTest {

	@Mock
	private AccountRepository accountRepository;
	
	@InjectMocks
	private TransactionValidator transactionValidator;
	
	@Test
	public void validate() throws TransactionRoutineException {
		Transaction transaction = Transaction.builder().accountId(1).amount(new BigDecimal(-123)).operationTypeId(1).build();
		Account account = Account.builder().accountId(1).documentNumber("1223").build();
		Mockito.when(accountRepository.findByAccountId(1)).thenReturn(account);
		transactionValidator.validate(transaction);
		Mockito.verify(accountRepository).findByAccountId(1);
		
	}
	
	@Test(expected=Exception.class)
	public void validateCreditVoucherNegativeAmountThrowsException() throws TransactionRoutineException {
		Transaction transaction = Transaction.builder().accountId(4).amount(new BigDecimal(-123)).operationTypeId(1).build();
		transactionValidator.validate(transaction);
		
	}
	
	@Test(expected=Exception.class)
	public void validateNormalPurchasePositiveAmountThrowsException() throws TransactionRoutineException {
		Transaction transaction = Transaction.builder().accountId(1).amount(new BigDecimal(123)).operationTypeId(1).build();
		transactionValidator.validate(transaction);
		
	}
}

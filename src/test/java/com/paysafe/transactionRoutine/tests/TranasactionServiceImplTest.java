package com.paysafe.transactionRoutine.tests;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.paysafe.transactionRoutine.exception.TransactionRoutineException;
import com.paysafe.transactionRoutine.repository.Transaction;
import com.paysafe.transactionRoutine.repository.TransactionRepository;
import com.paysafe.transactionRoutine.service.impl.TransactionServiceImpl;
import com.paysafe.transactionRoutine.validator.TransactionValidator;

@RunWith(MockitoJUnitRunner.class)
public class TranasactionServiceImplTest {
	@Mock
	private TransactionRepository transactionRepository;

	@Mock
	private TransactionValidator transactionValidator;
	
	@InjectMocks
	private TransactionServiceImpl transactionServiceImpl;
	
	@Test
	public void saveTransaction() throws TransactionRoutineException {
		Transaction transaction = Transaction.builder().accountId(1).amount(new BigDecimal(-50)).operationTypeId(1).build();
		Transaction transactionInResponse = Transaction.builder().transactionId(10).build();
		Mockito.when(transactionRepository.save(transaction)).thenReturn(transactionInResponse);
		transactionServiceImpl.saveTransaction(transaction);
		Mockito.verify(transactionRepository).save(transaction);
		Assert.assertEquals(transactionInResponse.getTransactionId(),10);
		
		
	}
	

	@Test
	public void fetchRemainingAmount() throws TransactionRoutineException {
		Transaction transaction1 = Transaction.builder().accountId(1).amount(new BigDecimal(-50)).balance(new BigDecimal(-50)).operationTypeId(1).build();
		Transaction transaction2 = Transaction.builder().accountId(1).amount(new BigDecimal(-23.5)).balance(new BigDecimal(-23.5)).operationTypeId(1).build();
		Transaction transaction3 = Transaction.builder().accountId(1).amount(new BigDecimal(-18.7)).balance(new BigDecimal(-18.7)).operationTypeId(1).build();
		List<Transaction> input = new ArrayList<>();
		input.add(transaction1);
		input.add(transaction2);
		input.add(transaction3);
		
		
		Transaction transactionInResponse = Transaction.builder().transactionId(10).build();
		
		BigDecimal remainingAmount = transactionServiceImpl.clearTransaction(new BigDecimal(60), input);
		Assert.assertEquals(transaction1.getBalance(),BigDecimal.ZERO);
		Assert.assertEquals(transaction2.getBalance(),new BigDecimal(-13.5));
		Assert.assertEquals(transaction3.getBalance(),new BigDecimal(-18.7));
		
		
		Assert.assertEquals(remainingAmount,BigDecimal.ZERO);
		
		System.out.println("remaining amount is : " + remainingAmount);
		
		
	}

}

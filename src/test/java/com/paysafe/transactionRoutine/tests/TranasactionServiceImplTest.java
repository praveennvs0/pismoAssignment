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
		Transaction transaction = Transaction.builder().accountId(1).amount(new BigDecimal(-123)).operationTypeId(1).build();
		Transaction transactionInResponse = Transaction.builder().transactionId(10).build();
		Mockito.when(transactionRepository.save(transaction)).thenReturn(transactionInResponse);
		transactionServiceImpl.saveTransaction(transaction);
		Mockito.verify(transactionRepository).save(transaction);
		Assert.assertEquals(transactionInResponse.getTransactionId(),10);
		
		
	}
	

}

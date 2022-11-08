package com.paysafe.transactionRoutine.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.paysafe.transactionRoutine.exception.TransactionRoutineException;
import com.paysafe.transactionRoutine.repository.Transaction;
import com.paysafe.transactionRoutine.repository.TransactionRepository;
import com.paysafe.transactionRoutine.service.TransactionService;
import com.paysafe.transactionRoutine.validator.TransactionValidator;

@Component
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private TransactionRepository transactionRepository;
	

	@Autowired
	private TransactionValidator transactionValidator;
	
	@Override
	public Transaction saveTransaction(Transaction transaction) throws TransactionRoutineException {
		transactionValidator.validate(transaction);
		transaction.setEventDate(new Date());
		Transaction transactionResource = transactionRepository.save(transaction);
		return transactionResource ;
		
	}
	
	

}

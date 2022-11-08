package com.paysafe.transactionRoutine.service;

import com.paysafe.transactionRoutine.exception.TransactionRoutineException;
import com.paysafe.transactionRoutine.repository.Transaction;

public interface TransactionService {
	
	public Transaction saveTransaction(Transaction transaction) throws TransactionRoutineException;
	
}

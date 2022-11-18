package com.paysafe.transactionRoutine.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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
	private  TransactionRepository transactionRepository;
	

	@Autowired
	private TransactionValidator transactionValidator;
	
	@Override
	public Transaction saveTransaction(Transaction transaction) throws TransactionRoutineException {
		transactionValidator.validate(transaction);
		transaction.setEventDate(new Date());
		Transaction transactionResource = transactionRepository.save(transaction);
		return transactionResource ;
		
	}
	
	
	public Transaction save(Transaction transaction) {
		Transaction result = new Transaction();
		BigDecimal remainingAmount = transaction.getAmount();
		if(remainingAmount.compareTo(BigDecimal.ZERO) < 0) {
			result = transactionRepository.save(transaction);
		}
		else {
			int accountId = transaction.getAccountId();
			List<Transaction> transactions =   transactionRepository.fetchByAccountId(accountId);
			BigDecimal remainingBalance = clearTransaction(remainingAmount, transactions);
			transaction.setBalance(remainingBalance);
			transactionRepository.save(transaction);
		
	}
		return result;
	}


	private BigDecimal clearTransaction(BigDecimal remainingAmount, List<Transaction> transactions) {
		for(Transaction txn : transactions ) {
			BigDecimal currBalance = txn.getBalance();
			if(currBalance.compareTo(BigDecimal.ZERO) > 0 || remainingAmount.compareTo(BigDecimal.ZERO) <= 0) {
				continue ;
			}

			if (remainingAmount.compareTo(currBalance.abs()) > 0) {
				remainingAmount = remainingAmount.subtract(currBalance.abs());
				currBalance = BigDecimal.ZERO;
				txn.setBalance(BigDecimal.ZERO);
				
			} else {
				// partial settlement
				currBalance = currBalance.abs().subtract(remainingAmount.abs());
				remainingAmount = BigDecimal.ZERO;
				txn.setBalance(currBalance.negate());
			}
			
			
			transactionRepository.save(txn);

}
		
		return remainingAmount ;
	}
	

}

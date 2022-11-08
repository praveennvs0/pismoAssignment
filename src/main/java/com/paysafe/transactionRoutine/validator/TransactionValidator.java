package com.paysafe.transactionRoutine.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.paysafe.transactionRoutine.enums.OperationType;
import com.paysafe.transactionRoutine.exception.TransactionRoutineException;
import com.paysafe.transactionRoutine.repository.Account;
import com.paysafe.transactionRoutine.repository.AccountRepository;
import com.paysafe.transactionRoutine.repository.Transaction;

@Component
public class TransactionValidator {
	
	@Autowired
	private AccountRepository accountRepository;

	public void validate(Transaction transaction) throws TransactionRoutineException {
		int accountId = transaction.getAccountId();
		Account account = accountRepository.findByAccountId(accountId);
		if(account == null) {
			throw new TransactionRoutineException("accountId","AccountId does not exist",HttpStatus.BAD_REQUEST);
		}
		
		int operationTypeId = transaction.getOperationTypeId();
		OperationType operationType = OperationType.findOperationType(operationTypeId);
		if (OperationType.CREDIT_VOUCHER.equals(operationType) && transaction.getAmount().intValue() < 0 ) {
			throw new TransactionRoutineException("amount","Negative amount not supported for this operation type",HttpStatus.BAD_REQUEST);
		}
		
		//all other operation types
		if(transaction.getAmount().intValue() > 0 ) {
			throw new TransactionRoutineException("amount","Positive amount not supported for this operation type",HttpStatus.BAD_REQUEST);
		}
		
	}
	
}

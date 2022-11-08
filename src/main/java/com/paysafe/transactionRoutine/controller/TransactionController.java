package com.paysafe.transactionRoutine.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paysafe.transactionRoutine.exception.ErrorResult;
import com.paysafe.transactionRoutine.exception.TransactionRoutineException;
import com.paysafe.transactionRoutine.repository.Transaction;
import com.paysafe.transactionRoutine.service.TransactionService;

@RestController
@RequestMapping("transactionRoutine/v1")
public class TransactionController {
	
	@Autowired
	private TransactionService transactionService;
	
	private static final Logger logger = LoggerFactory.getLogger(TransactionController.class) ;
	
	@PostMapping("/transactions")
	public ResponseEntity<Transaction> saveTransaction( @RequestBody Transaction transaction) throws TransactionRoutineException {
		Transaction transactionResource = transactionService.saveTransaction(transaction);
		logger.info("Transaction saved");
		return new ResponseEntity<>(transactionResource,HttpStatus.CREATED);
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorResult> handleException(TransactionRoutineException exception) {
		ErrorResult result = ErrorResult.createResult(exception.getField(),exception.getMessage());
		return new ResponseEntity<>(result,exception.getStatus());
	}
	
}

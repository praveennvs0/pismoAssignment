package com.paysafe.transactionRoutine.exception;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class TransactionRoutineException extends Exception {
	
	private String message;
	private String field ;
	private int returnCode;
	private HttpStatus status;
	
	public TransactionRoutineException() { 
		super("Invalid amount");
	}
	
	public TransactionRoutineException(String field,String message,HttpStatus status) { 
	this.message = message;
	this.field = field;
	this.status = status;
	}
}

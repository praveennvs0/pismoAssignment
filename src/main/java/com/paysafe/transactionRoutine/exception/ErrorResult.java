package com.paysafe.transactionRoutine.exception;

import lombok.Data;

@Data
public class ErrorResult {
	
	private String field;
	private String message;
	
	public static ErrorResult createResult(String field,String message) {
		ErrorResult errorResult = new ErrorResult();
		errorResult.setField(field);
		errorResult.setMessage(message);
		return errorResult;
		
	}

}

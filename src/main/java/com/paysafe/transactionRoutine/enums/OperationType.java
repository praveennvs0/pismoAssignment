package com.paysafe.transactionRoutine.enums;

public enum OperationType {
	NORMAL_PURCHASE (1),
	INSTALLMENT_PURCHASE (2),
	WITHDRAWAL(3),
	CREDIT_VOUCHER(4);

	int operationTypeCode ;
	
	OperationType(int operationTypeCode) {
		this.operationTypeCode = operationTypeCode;
	}
	
	public static OperationType findOperationType(int code) {
		for(OperationType operationType : OperationType.values()) {
			if(operationType.operationTypeCode == code)
				return operationType;
		}
		return null;
	}

}

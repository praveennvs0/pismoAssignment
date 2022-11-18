package com.paysafe.transactionRoutine.repository;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transaction {
	
	@Id
	@GeneratedValue
	@JsonProperty("transaction_id")
	private int transactionId;
	@JsonProperty("account_id")
	private int accountId;
	@JsonProperty("operation_type_id")
	private int operationTypeId;
	private BigDecimal amount;
	private BigDecimal balance;
	private Date eventDate;
	

}

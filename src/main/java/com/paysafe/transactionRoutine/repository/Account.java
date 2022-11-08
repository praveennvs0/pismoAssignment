package com.paysafe.transactionRoutine.repository;

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
public class Account {
	
	@Id
	@GeneratedValue
	@JsonProperty("account_id")
	private int accountId;
	@JsonProperty("document_number")
	private String documentNumber;
	
	

}

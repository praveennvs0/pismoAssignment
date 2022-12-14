package com.paysafe.transactionRoutine.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

	List<Transaction> findByTransactionId(String transactionId);

	List<Transaction> fetchByAccountId(int accountId);
	

}

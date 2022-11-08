package com.paysafe.transactionRoutine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

	Account findByAccountId(int accountId);
	
	

}

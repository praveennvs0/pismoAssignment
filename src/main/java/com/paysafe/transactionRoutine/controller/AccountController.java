package com.paysafe.transactionRoutine.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paysafe.transactionRoutine.repository.Account;
import com.paysafe.transactionRoutine.service.AccountService;

@RestController
@RequestMapping("transactionRoutine/v1")
public class AccountController {
	
	@Autowired
	private AccountService accountService;

	private static final Logger logger = LoggerFactory.getLogger(AccountController.class) ;
	
	@PostMapping("/accounts")
	public ResponseEntity<Account> saveAccount( @RequestBody Account account) {
		Account accountResource = accountService.saveAccount(account);
		logger.info("Account saved");
		return new ResponseEntity<>(accountResource,HttpStatus.CREATED);
	}

	@GetMapping("/accounts/{accountId}")
	public ResponseEntity<Account> getAccountById(@PathVariable int accountId) {
		Account account = accountService.getAccountById(accountId);
		if(account == null) {
			logger.info("Account does not exist");
			return new ResponseEntity<>(account,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(account,HttpStatus.OK);
	}
	
}

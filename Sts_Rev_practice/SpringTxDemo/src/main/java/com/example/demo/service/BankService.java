package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Account;
import com.example.demo.repo.AccountRepository;

import jakarta.transaction.Transactional;

@Service
public class BankService {
private final AccountRepository accountRepository;
private final AuditService auditService;

public BankService(AccountRepository accountRepository,AuditService auditService) {
	this.accountRepository=accountRepository;
	this.auditService=auditService;
}

//REQUIRED is default: joins existing tx or creates new if none
@Transactional
public void transfer(Long fromId,Long toId,double amount,boolean failAfterDebit) {
	auditService.log("Transfer START: from=" + fromId+" to = "+toId+" amount= "+amount);
	
	Account from=accountRepository.findById(fromId)
			.orElseThrow(()->new RuntimeException("From account not found"));
	
	Account to=accountRepository.findById(toId)
			.orElseThrow(()-> new RuntimeException("To account not found"));
	
	//Debit
	if(from.getBalance()<amount) {
		auditService.log("Transfer FAILED: insufficient balance");
		throw new RuntimeException("Insufficient balance");
	}
	from.setBalance(from.getBalance()-amount);
	accountRepository.save(from);
	
	//Simulate failure after debit(to prove rollback)
	if(failAfterDebit) {
		auditService.log("Transfer FAILED: simulated failure after debit");
		throw new RuntimeException("simulated failure after debit");
	}
	
	//Credit
	 to.setBalance(to.getBalance()+amount);
	 accountRepository.save(to);
	 
	 auditService.log("Transfer SUCCESS");
}

}

package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.entity.Account;
import com.example.demo.repo.AccountRepository;
import com.example.demo.service.BankService;

@SpringBootApplication
public class SpringTxDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringTxDemoApplication.class, args);
	}
	
	@Bean
	CommandLineRunner run(AccountRepository accountRepository,BankService bankService) {
		return args->{
			//create two accounts
			Account a1=accountRepository.save(new Account("Srivani",5000));
			Account a2=accountRepository.save(new Account("vani",1000));
			
			System.out.println("Before Transfer:");
			printBalances(accountRepository);
			
			
			//1-  SUCCESS scanerio(commits)
			try {
				bankService.transfer(a1.getId(), a2.getId(), 500, false);
			}catch(Exception ex) {
				System.out.println("SUCCESS scenario exception: "+ex.getMessage());
			}
			
			System.out.println("\n After SUCCESS Transfer:");
			printBalances(accountRepository);
			
			//2- FAIL scenario(rollback main transfer)
			try {
				bankService.transfer(a1.getId(), a2.getId(), 700, true);
			}catch(Exception ex) {
				System.out.println("\nFAIL scenario exception: "+ex.getMessage());
			}
			
			System.out.println("\nAfter FAIL Transfer(should rollback):");
			printBalances(accountRepository);
			
			System.out.println("\nNow open H2 console and check audit_logs table.");
		};
	}
	
	private void printBalances(AccountRepository repo) {
		repo.findAll().forEach(a-> System.out.println(a.getName()+"=>"+a.getBalance())
		);
	}

}

package com.example.main.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.main.model.Account;
import com.example.main.repository.AccountRepository;


@Service
public class AccountService {

	@Autowired
	private AccountRepository accountRepo;

	public List<Account> getAllTransactions(Long accountNumber) {
		return accountRepo.findAllTransactions(accountNumber);
	}
	
	public void saveTransaction(Account transaction) {
         accountRepo.save(transaction);
    }
	
	
	public void insert(Account account,BigDecimal lrb) {
		int amount;
		amount = account.getCreditAmount().compareTo(BigDecimal.ZERO);
        if(amount == 0)
        	account.setRunningBalance(lrb.subtract(account.getDebitAmount()));
        else
        	account.setRunningBalance(lrb.add(account.getCreditAmount()));
        saveTransaction(account);
	}
	public void update(Account account,BigDecimal lrb) {
		int amount;
		amount = account.getCreditAmount().compareTo(BigDecimal.ZERO);
        if(amount == 0)
        	account.setRunningBalance(lrb.subtract(account.getDebitAmount()));
        else
        	account.setRunningBalance(lrb.add(account.getCreditAmount()));
        accountRepo.save(account);
	}
}

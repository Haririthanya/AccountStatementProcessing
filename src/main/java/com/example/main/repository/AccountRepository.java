package com.example.main.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.main.model.Account;
import com.example.main.model.AccountPK;

public interface AccountRepository extends JpaRepository<Account, AccountPK> {

	@Query("SELECT a FROM Account a WHERE a.accountPK.accountNumber = :accountNumber")
    public List<Account> findAllTransactions(@Param("accountNumber")Long accountNumber);
	
	@Query(value="Select running_balance from account where account_number= ?1 and transactional_date_time < ?2 order by transactional_date_time desc limit 1",nativeQuery=true)
	public BigDecimal getPreviousRunningBalance(Long accountNumber, java.sql.Timestamp date);
	
	@Query("Select a from Account a WHERE a.accountPK.transactionDateTime > ?1")
	public List<Account> findAllNextTransaction(java.sql.Timestamp dateTime);
}

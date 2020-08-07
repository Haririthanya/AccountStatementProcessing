package com.example.main.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.opencsv.bean.CsvBindByName;

@Entity
@Table(name="account")
public class Account implements Comparable<Account>{ 
	
	@EmbeddedId
	AccountPK accountPK;
	
	@CsvBindByName(column="Credit")
	private BigDecimal creditAmount;
	
	@CsvBindByName(column="Withdrawals")
	private BigDecimal debitAmount;
	
	@CsvBindByName(column = "Running Balance")
	private BigDecimal runningBalance;
	
	public Account() {
		super();
	}
	
	public Account(AccountPK accountPK, BigDecimal creditAmount, BigDecimal debitAmount, BigDecimal runningBalance) {
		super();
		this.accountPK = accountPK;
		this.creditAmount = creditAmount;
		this.debitAmount = debitAmount;
		this.runningBalance = runningBalance;
	}

	public AccountPK getAccountPK() {
		return accountPK;
	}

	public void setAccountPK(AccountPK accountPK) {
		this.accountPK = accountPK;
	}
	
	public BigDecimal getCreditAmount() {
		return creditAmount;
	}

	public void setCreditAmount(BigDecimal creditAmount) {
		this.creditAmount = creditAmount;
	}

	public BigDecimal getDebitAmount() {
		return debitAmount;
	}

	public void setDebitAmount(BigDecimal debitAmount) {
		this.debitAmount = debitAmount;
	}

	public BigDecimal getRunningBalance() {
		return runningBalance;
	}

	public void setRunningBalance(BigDecimal runningBalance) {
		this.runningBalance = runningBalance;
	}

	@Override
	public int compareTo(Account o) {
		// TODO Auto-generated method stub
		if (accountPK.getTransactionDateTime() == null || o.accountPK.getTransactionDateTime() == null) {
		      return 0;
		    }
		    return accountPK.getTransactionDateTime().compareTo(o.accountPK.getTransactionDateTime());
	}

}

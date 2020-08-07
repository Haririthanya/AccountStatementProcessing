package com.example.main.model;

import java.math.BigDecimal;

import javax.persistence.Column;

import com.opencsv.bean.CsvBindByName;

public class CsvFile implements Comparable<CsvFile>{

	
	private Long accountNumber;
	
	private String referenceNumber;
	
	private String transactionalDetails;
	
	private java.sql.Timestamp transactionDateTime;
	
	private BigDecimal creditAmount;
	
	private BigDecimal debitAmount;
	
	private BigDecimal runningBalance;
	
	public BigDecimal getRunningBalance() {
		return runningBalance;
	}

	public void setRunningBalance(BigDecimal runningBalance) {
		this.runningBalance = runningBalance;
	}

	public Long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getReferenceNumber() {
		return referenceNumber;
	}

	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	public String getTransactionalDetails() {
		return transactionalDetails;
	}

	public void setTransactionalDetails(String transactionalDetails) {
		this.transactionalDetails = transactionalDetails;
	}

	public java.sql.Timestamp getTransactionDateTime() {
		return transactionDateTime;
	}

	public void setTransactionDateTime(java.sql.Timestamp transactionDateTime) {
		this.transactionDateTime = transactionDateTime;
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

	@Override
	public int compareTo(CsvFile o) {
		if (getTransactionDateTime() == null || o.getTransactionDateTime() == null) {
		      return 0;
		    }
		    return getTransactionDateTime().compareTo(o.getTransactionDateTime());
	}
	
}









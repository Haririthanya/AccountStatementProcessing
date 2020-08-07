package com.example.main.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;

import com.opencsv.bean.CsvBindByName;

@Embeddable
public class AccountPK implements Serializable{

	public AccountPK() {
		super();
	}

	public AccountPK(Long accountNumber, String referenceNumber, String transactionalDetails,
			Timestamp transactionDateTime) {
		super();
		this.accountNumber = accountNumber;
		this.referenceNumber = referenceNumber;
		this.transactionalDetails = transactionalDetails;
		this.transactionDateTime = transactionDateTime;
	}
	@CsvBindByName(column="Account Number")
	@Column(name="accountNumber")
	private Long accountNumber;
	
	@CsvBindByName(column="Txn Reference Number")
	@Column(name="referenceNumber")
	private String referenceNumber;
	
	@CsvBindByName(column="Details")
	@Column(name="transactionalDetails")
	private String transactionalDetails;
	
	@CsvBindByName(column="Date Time")
	@Column(name="transactionalDateTime")
	private java.sql.Timestamp transactionDateTime;

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
	
}

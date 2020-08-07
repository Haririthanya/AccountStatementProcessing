package com.example.main.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.main.model.Account;
import com.example.main.repository.AccountRepository;
import com.example.main.repository.UserRepository;
import com.example.main.service.AccountService;
import com.example.main.service.UserService;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

@Controller
@SessionAttributes("userId")
public class DownloadController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private AccountRepository accountRepo;

	@Autowired
	private UserRepository userRepo;
	
	@PostMapping("/export-csv")
    public void exportCSV(HttpServletResponse response,HttpSession session,PrintWriter writer) throws Exception {

        //set file name and content type
		response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; file=AllTransactions.csv");
        writer.write("Txn Reference Number, Account Number, Transaction Details,Date Time,Credit Withdrawals,Running Balance"+ "\n");
        List<Account> transactions = accountRepo.findAllTransactions(((Long)session.getAttribute("accountNumber")));
        System.out.println("len :"+transactions.size());
        for (Account record : transactions) {
        	System.out.println(record.getCreditAmount());
            writer.write(record.getAccountPK().getReferenceNumber() + "," + record.getAccountPK().getAccountNumber() +","+record.getAccountPK().getTransactionalDetails()+","+record.getAccountPK().getTransactionDateTime()+","+record.getCreditAmount()+","+record.getDebitAmount()+","+record.getRunningBalance()+ "\n");
        }
                
    }
}

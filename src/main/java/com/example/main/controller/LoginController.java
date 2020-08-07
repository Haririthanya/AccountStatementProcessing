package com.example.main.controller;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.example.main.model.Account;
import com.example.main.model.AccountPK;
import com.example.main.model.User;
import com.example.main.repository.AccountRepository;
import com.example.main.repository.UserRepository;
import com.example.main.service.AccountService;
import com.example.main.service.UserService;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

@Controller
@SessionAttributes("userId")
public class LoginController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private AccountRepository accountRepo;

	@Autowired
	private UserRepository userRepo;
	
	
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping("/home")
	public String home(@RequestParam("userId") Long userId,@RequestParam("password") String password,Model model,HttpServletRequest request) {
		
		
		User user = null;
		try {
			user = userService.getUserById(userId);
		}
		catch(Exception e) {
			System.out.println("User not found");
		}
		if(user!=null && user.getPassword().equals(password)) {
			List<Account> list = accountRepo.findAllTransactions(user.getAccountNumber());
			System.out.println(list.size()+" "+user.getAccountNumber());
			request.setAttribute("userId", userId);
			request.setAttribute("accountNumber", user.getAccountNumber());
			model.addAttribute("userName", user.getName());
			model.addAttribute("allTransactions",list);
			//return accountRepo.findAllTransactions(user.getAccountNumber());
			return "home";
		}
		return "login";
	}
	@GetMapping("/account")
	public Iterable<Account> account(){
		return accountRepo.findAllTransactions((long)1234567890);
	}
	
	@PostMapping("/upload-csv-file")
    public String uploadCSVFile(@RequestParam("file") MultipartFile file, Model model) throws IOException {
		List<Account> CsvFileList = new ArrayList<Account>();

        //dateSplitTimeFormatter dtf = dateSplitTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        Reader reader = new InputStreamReader(file.getInputStream());
        CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();

       

        Iterable<CSVRecord> parser = CSVFormat.EXCEL.withHeader("Txn Reference Number","Account Number","dateSplit Time","Details","Withdrawals","Credit").parse(reader);

        	int index = 0;
            for (CSVRecord record : parser) {
            Account csvFullRecord = new Account();
            AccountPK csvRecord = new AccountPK();
            String referenceNumber = record.get("Txn Reference Number");
            if(referenceNumber.equals("Txn Reference Number"))
            	continue;
            if(referenceNumber.equals(""))
            	break;
            Long accountNumber = Long.parseLong(record.get("Account Number"));
            String details = record.get("Details");
            String dateSplit[] = record.get("dateSplit Time").split("-");
            dateSplit[1].toUpperCase();
            if(dateSplit[1].equalsIgnoreCase("Jan") || dateSplit[1].contains("JAN"))
            	dateSplit[1] = "01";
            if(dateSplit[1].equalsIgnoreCase("Feb") || dateSplit[1].contains("FEB"))
            	dateSplit[1] = "02";
            if(dateSplit[1].equalsIgnoreCase("Mar") || dateSplit[1].contains("MAR"))
            	dateSplit[1] = "03";
            if(dateSplit[1].equalsIgnoreCase("Apr") || dateSplit[1].contains("APR"))
            	dateSplit[1] = "04";
            if(dateSplit[1].equalsIgnoreCase("May") || dateSplit[1].contains("MAY"))
            	dateSplit[1] = "05";
            if(dateSplit[1].equalsIgnoreCase("Jun") || dateSplit[1].contains("JUN"))
            	dateSplit[1] = "06";
            if(dateSplit[1].equalsIgnoreCase("Jul") || dateSplit[1].contains("JUL"))
            	dateSplit[1] = "07";
            if(dateSplit[1].equalsIgnoreCase("AUG") || dateSplit[1].contains("AUG"))
            	dateSplit[1] = "08";
            if(dateSplit[1].equalsIgnoreCase("Sep") || dateSplit[1].contains("SEP"))
            	dateSplit[1] = "09";
            if(dateSplit[1].equalsIgnoreCase("Oct") || dateSplit[1].contains("OCT"))
            	dateSplit[1] = "10";
            if(dateSplit[1].equalsIgnoreCase("Nov") || dateSplit[1].contains("NOV"))
            	dateSplit[1] = "11";
            if(dateSplit[1].equalsIgnoreCase("Dec") || dateSplit[1].contains("DEC"))
            	dateSplit[1] = "12";
            String time = dateSplit[2].split(" ")[1];
            String date = dateSplit[2].split(" ")[0]+"-"+dateSplit[1]+"-"+dateSplit[0]+" "+time+".00005";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
            //System.out.println(index+" "+date+" jkmk");

            Timestamp timestamp = java.sql.Timestamp.valueOf(date);
            BigDecimal debitAmount = new BigDecimal(record.get("Withdrawals"));
            BigDecimal creditAmount = new BigDecimal(record.get("Credit"));
            csvRecord.setAccountNumber(accountNumber);
            csvFullRecord.setCreditAmount(creditAmount);
            csvFullRecord.setDebitAmount(debitAmount);
            csvRecord.setReferenceNumber(referenceNumber);
            csvRecord.setTransactionalDetails(details);
            csvRecord.setTransactionDateTime(timestamp);
            csvFullRecord.setAccountPK(csvRecord);
            CsvFileList.add(csvFullRecord);
            
            //System.out.println(index+" refno: "+referenceNumber+" details: "+details+" acno: "+accountNumber +" acno.length: "+accountNumber+" "+dateSplit[0]+" "+dateSplit[1]+" "+dateSplit[2]);
            index++;
        }
        Collections.sort(CsvFileList);

        for(int i=0;i<CsvFileList.size();i++) {
            BigDecimal lrb = accountRepo.getPreviousRunningBalance(CsvFileList.get(i).getAccountPK().getAccountNumber(),CsvFileList.get(i).getAccountPK().getTransactionDateTime());
        	accountService.insert(CsvFileList.get(i),lrb);
            List<Account> nextTransactions = accountRepo.findAllNextTransaction(CsvFileList.get(i).getAccountPK().getTransactionDateTime());
            for(int j=0;j<nextTransactions.size();j++) {
            	lrb = accountRepo.getPreviousRunningBalance(nextTransactions.get(j).getAccountPK().getAccountNumber(),nextTransactions.get(j).getAccountPK().getTransactionDateTime());
            	accountService.update(nextTransactions.get(j),lrb);
            }
             
        }
       
        //model.addAttribute("csv",nextTransactions);
		return "upload";
    }

}

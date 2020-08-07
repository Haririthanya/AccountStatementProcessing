<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="css/homestyle.css">

</head>
<body>
<nav class="nav navbar navbar-expand-lg navbar-light bg-transparent">
  <a class="navbar-brand text-success" href="#">Navbar</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link text-success" href="#">Home <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link text-success" href="#">Link</a>
      </li>
    </ul>
  </div>
</nav>
<div class="jumbotron">
  <h1 class="display-4">Hello, ${userName}! </h1>
</div>
<div class="content big-banner">
</div>
<div class="table-responsive">
<table class="table table-bordered">
  <thead>
    <tr class="text-success">
      <th scope="col">Txn Reference Number</th>
      <th scope="col">Account Number</th>
      <th scope="col">Transaction Date & Time</th> 
      <th scope="col">Transaction Details</th>
      <th scope="col">Credit Amount</th>
      <th scope="col">Debit Amount</th>
      <th scope="col">Running Balance</th>
    </tr>
  </thead>
  <tbody>
      <c:forEach items="${allTransactions}" var="transaction">
    <tr scope="row">   
        <td>${transaction.accountPK.referenceNumber}</td>
        <td>${transaction.accountPK.accountNumber}</td>
        <td>${transaction.accountPK.transactionDateTime}</td>
        <td>${transaction.accountPK.transactionalDetails}</td>
        <td>${transaction.creditAmount}</td>
        <td>${transaction.debitAmount}</td>
        <td>${transaction.runningBalance}</td>   
    </tr>
</c:forEach>
  </tbody>
</table>
</div>
<form method="POST" action="/upload-csv-file" enctype="multipart/form-data">
    <div class="form-group mt-3">
        <label for="file">Select a CSV file</label>
        <input type="file" name="file" class="form-control-file" id="file" accept=".csv">
    </div>
    <button type="submit" class="btn btn-primary">Import Transaction Data</button>
</form>
<form method="POST" action="/export-csv" >
    <div class="form-group mt-3">
        <input type="submit" name="file"  id="downloadcsv">
    </div>
</form>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
</body>
</html>
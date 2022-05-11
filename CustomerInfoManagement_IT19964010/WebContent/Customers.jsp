<%@page import="com.Customer"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Customer Management</title>
<link rel="stylesheet" href="View/bootstrap.min.css">
<script src="Components/jquery-3.6.0.min.js"></script>
<script src="Components/customers.js"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-6">
				<h1>Customer Management V10.1</h1>
				<form id="formCustomer" name="formCustomer">
					Customer Name: <input id="customerName" name="customerName"
						type="text" class="form-control form-control-sm"> <br>
					Customer Phone No: <input id="customerPhoneNo"
						name="customerPhoneNo" type="text"
						class="form-control form-control-sm"> <br> 
					Email: <input
						id="email" name="email" type="text"
						class="form-control form-control-sm"> <br> 
					House No:
					<input id="houseNo" name="houseNo" type="text"
						class="form-control form-control-sm"> <br> 
					Street: <input
						id="street" name="street" type="text"
						class="form-control form-control-sm"> <br> 
					City: <input
						id="city" name="city" type="text"
						class="form-control form-control-sm"> <br> 
					Zip Code:
					<input id="zipCode" name="zipCode" type="text"
						class="form-control form-control-sm"> <br> 
					Service Start Date: <input id="serviceStartDate" name="serviceStartDate"
						type="text" class="form-control form-control-sm"> <br>
					<input id="btnSave" name="btnSave" type="button" value="Save"
						class="btn btn-primary"> <input type="hidden"
						id="hidCustomerIDSave" name="hidCustomerIDSave" value="">
				</form>
				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>
				<br>
				<div id="divCustomersGrid">
					<%
						Customer customerObj = new Customer();
					out.print(customerObj.readCustomers());
					%>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
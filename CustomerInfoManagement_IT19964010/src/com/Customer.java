package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement; 

public class Customer 
{ //A common method to connect to the DB
		private Connection connect(){ 
			
						Connection con = null; 
						
						try{ 
								Class.forName("com.mysql.jdbc.Driver"); 
 
								//Provide the correct details: DBServer/DBName, username, password 
								con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electrogrid", "root", ""); 
						} 
						catch (Exception e) {
							e.printStackTrace();
							} 
						
						return con; 
			} 
		
		
		public String insertCustomer(String customerName, String customerPhoneNo, String email, String houseNo, String street,
				String city, String zipCode, String serviceStartDate){ 
			
					String output = ""; 
					
					try
					{ 
						Connection con = connect(); 
						
						if (con == null) 
						{
							return "Error while connecting to the database for inserting."; 
							
						} 
						// create a prepared statement
						
						String query = " insert into customer (`customerName`,`customerPhoneNo`,`email`,`houseNo`,`street`,`city`,`zipCode`,`serviceStartDate`)"+" values (?, ?, ?, ?, ?, ?, ?, ?)"; 
						PreparedStatement preparedStmt = con.prepareStatement(query); 
						// binding values
					
						preparedStmt.setString(1, customerName); 
						preparedStmt.setString(2, customerPhoneNo); 
						preparedStmt.setString(3, email); 
						preparedStmt.setString(4, houseNo); 
						preparedStmt.setString(5, street); 
						preparedStmt.setString(6, city); 
						preparedStmt.setString(7, zipCode); 
						preparedStmt.setString(8, serviceStartDate); 
						
						// execute the statement
 
						preparedStmt.execute(); 
						con.close(); 
						
						String newCustomers = readCustomers(); 
						output = "{\"status\":\"success\",\"data\":\""+newCustomers+"\"}"; 
					} 
					
					catch (Exception e) 
					{ 
						output = "{\"status\":\"error\", \"data\":\"Error while inserting the customer.\"}"; 
						System.err.println(e.getMessage()); 
					} 
					return output; 
			} 
		
		
		
		public String readCustomers() 
		{ 
			String output = ""; 
			try
			{ 
				Connection con = connect(); 
		 if (con == null) 
		 { 
		 return "Error while connecting to the database for reading."; 
		 } 
		 // Prepare the html table to be displayed
		 output = "<table border=\"1\" class=\"table\"> <tr>"
				+ "<th>Customer Name</th> <th>Customer Phone No</th>"
		 		+ "<th>Email</th>"
		 		+ "<th>House No</th>"
		 		+ "<th>Street</th>"
		 		+ "<th>City</th>"
		 		+ "<th>Zip Code</th>"
		 		+ "<th>Service Start Date</th>"
		 		+ "<th>Update</th>  <th>Remove</th></tr>";
		   
		
		 String query = "select * from customer"; 
		 Statement stmt = con.createStatement(); 
		 ResultSet rs = stmt.executeQuery(query); 
		 // iterate through the rows in the result set
		 while (rs.next()) 
		 { 
		 String cusId = Integer.toString(rs.getInt("cusId")); 
		 String customerName = rs.getString("customerName"); 
		 String customerPhoneNo = rs.getString("customerPhoneNo"); 
		 String email = rs.getString("email"); 
		 String houseNo = rs.getString("houseNo"); 
		 String street = rs.getString("street"); 
		 String city = rs.getString("city");
		 String zipCode = rs.getString("zipCode"); 
		 String serviceStartDate = rs.getString("serviceStartDate"); 
		 
		 
		 
		 // Add into the html table
		 output += "<tr><td><input id='hidCustomerIDUpdate' name='hidCustomerIDUpdate' type='hidden' value='"+cusId+"'>"+customerName+"</td>"; 
		 output += "<td>" + customerPhoneNo + "</td>"; 
		 output += "<td>" + email + "</td>"; 
		 output += "<td>" + houseNo + "</td>"; 
		 output += "<td>" + street + "</td>"; 
		 output += "<td>" + city + "</td>"; 
		 output += "<td>" + zipCode + "</td>"; 
		 output += "<td>" + serviceStartDate + "</td>"; 
		 // buttons
		 output += "<td><input name='btnUpdate' type='button' value='Update' "
				 + "class='btnUpdate btn btn-secondary' data-customerid='" + cusId + "'></td>"
				 + "<td><input name='btnRemove' type='button' value='Remove' "
				 + "class='btnRemove btn btn-danger' data-customerid='" + cusId + "'></td></tr>"; 
		 
		 } 
		 con.close(); 
		 // Complete the html table
		 output += "</table>"; 
		 } 
		 
		catch (Exception e) 
		 { 
		 output = "Error while reading the customers."; 
		 System.err.println(e.getMessage()); 
		 } 
		return output; 
		}

			
			
			public String updateCustomer(String cusId,String customerName, String customerPhoneNo, String email, String houseNo, String street,
					String city, String zipCode, String serviceStartDate){ 
				
					String output = ""; 
					
					try{ 
							Connection con = connect(); 
							if (con == null){
								return "Error while connecting to the database for updating.";
								} 
							// create a prepared statement
							String query = "UPDATE customer SET customerName=?,customerPhoneNo=?,email=?,houseNo=?,street=?,city=?,zipCode=?,serviceStartDate=? WHERE cusId=?"; 
							PreparedStatement preparedStmt = con.prepareStatement(query); 
							// binding values
							preparedStmt.setString(1, customerName); 
							preparedStmt.setString(2, customerPhoneNo); 
							preparedStmt.setString(3, email); 
							preparedStmt.setString(4, houseNo); 
							preparedStmt.setString(5, street); 
							preparedStmt.setString(6, city); 
							preparedStmt.setString(7, zipCode); 
							preparedStmt.setString(8, serviceStartDate); 
							preparedStmt.setInt(9, Integer.parseInt(cusId)); 
							// execute the statement
							preparedStmt.execute(); 
							con.close(); 
							String newCustomers = readCustomers(); 
							output = "{\"status\":\"success\",\"data\":\""+newCustomers+"\"}"; 

					} 
					
					catch (Exception e){ 
						
						output = "{\"status\":\"error\",\"data\":\"Error while updating the customer.\"}"; 

						System.err.println(e.getMessage()); 
						
					} 
					
					return output; 
			} 
			
			
			public String deleteCustomer(String cusId){ 
				
					String output = ""; 
					
					try{ 
						Connection con = connect(); 
						
						if (con == null){
							return "Error while connecting to the database for deleting."; 
							} 
						// create a prepared statement
						String query = "delete from customer where cusId=?"; 
						PreparedStatement preparedStmt = con.prepareStatement(query); 
						// binding values
						preparedStmt.setInt(1, Integer.parseInt(cusId)); 
						// execute the statement
						preparedStmt.execute(); 
						con.close(); 
						String newCustomers = readCustomers(); 
						 output = "{\"status\":\"success\",\"data\":\""+newCustomers+"\"}"; 

					} 
					
					catch (Exception e){ 
						output = "{\"status\":\"error\",\"data\":\"Error while deleting the customer.\"}";
						System.err.println(e.getMessage()); 
					} 
					return output; 
			} 
			
			
			
			
} 

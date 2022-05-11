$(document).on("click", "#btnSave", function(event)
{ 
// Clear alerts---------------------
 $("#alertSuccess").text(""); 
 $("#alertSuccess").hide(); 
 $("#alertError").text(""); 
 $("#alertError").hide(); 
// Form validation-------------------
var status = validateCustomerForm(); 
if (status != true) 
 { 
 $("#alertError").text(status); 
 $("#alertError").show(); 
 return; 
 } 
// If valid------------------------
var type = ($("#hidCustomerIDSave").val() == "") ? "POST" : "PUT"; 
 $.ajax( 
 { 
 url : "CustomersAPI", 
 type : type, 
 data : $("#formCustomer").serialize(), 
 dataType : "text", 
 complete : function(response, status) 
 { 
 onCustomerSaveComplete(response.responseText, status); 
 } 
 }); 
});

function onCustomerSaveComplete(response, status)
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully saved."); 
 $("#alertSuccess").show(); 
 $("#divCustomersGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error") 
 { 
 $("#alertError").text(resultSet.data); 
 $("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
 $("#alertError").text("Error while saving."); 
 $("#alertError").show(); 
 } else
 { 
 $("#alertError").text("Unknown error while saving.."); 
 $("#alertError").show(); 
 }
$("#hidCustomerIDSave").val(""); 
$("#formCustomer")[0].reset(); 
}


// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
		{ 
		$("#hidCustomerIDSave").val($(this).data("customerid")); 
		 $("#customerName").val($(this).closest("tr").find('td:eq(0)').text()); 
		 $("#customerPhoneNo").val($(this).closest("tr").find('td:eq(1)').text()); 
		 $("#email").val($(this).closest("tr").find('td:eq(2)').text()); 
		 $("#houseNo").val($(this).closest("tr").find('td:eq(3)').text()); 
		 $("#street").val($(this).closest("tr").find('td:eq(4)').text()); 
		 $("#city").val($(this).closest("tr").find('td:eq(5)').text()); 
		 $("#zipCode").val($(this).closest("tr").find('td:eq(6)').text()); 
		 $("#serviceStartDate").val($(this).closest("tr").find('td:eq(7)').text());
		});




$(document).on("click", ".btnRemove", function(event)
		{ 
		 $.ajax( 
		 { 
		 url : "CustomersAPI", 
		 type : "DELETE", 
		 data : "cusId=" + $(this).data("customerid"),
		 dataType : "text", 
		 complete : function(response, status) 
		 { 
		 onCustomerDeleteComplete(response.responseText, status); 
		 } 
		 }); 
		});
		
function onCustomerDeleteComplete(response, status)
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully deleted."); 
 $("#alertSuccess").show(); 
 $("#divCustomersGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error") 
 { 
 $("#alertError").text(resultSet.data); 
 $("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
 $("#alertError").text("Error while deleting."); 
 $("#alertError").show(); 
 } else
 { 
 $("#alertError").text("Unknown error while deleting.."); 
 $("#alertError").show(); 
 } 
}


// CLIENT-MODEL================================================================
function validateCustomerForm()
{
	// customerName
	if ($("#customerName").val().trim() == "")
	{
	return "Insert Customer Name.";
	}
	// customerPhoneNo
	if ($("#customerPhoneNo").val().trim() == "")
	{
	return "Insert Customer Phone No.";
	
    }
	// email
	if ($("#email").val().trim() == "")
	{
	return "Insert Email.";
	}
	// houseNo
	if ($("#houseNo").val().trim() == "")
	{
	return "Insert House No.";
	
    }
	// street
	if ($("#street").val().trim() == "")
	{
	return "Insert Street.";
	}
	// city
	if ($("#city").val().trim() == "")
	{
	return "Insert City.";
	
    }
	// zipCode
	if ($("#zipCode").val().trim() == "")
	{
	return "Insert Zip Code.";
	
    }



// serviceStartDate---------------------
if ($("#serviceStartDate").val().trim() == ""){
	
	return "Insert Service Start Date.";
}
	return true;
}
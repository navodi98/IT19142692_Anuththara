package com;
import java.sql.*;

public class ProjectManagement {

	private Connection connect() 
	 { 
		Connection con = null; 
		 try
		 { 
			 Class.forName("com.mysql.jdbc.Driver"); 
			 con = 
			 DriverManager.getConnection( 
			 "jdbc:mysql://127.0.0.1:3306/test", "root", ""); 
		 } 
		 catch (Exception e) 
		 { 
			 e.printStackTrace(); 
		 } 
		 	return con; 
	 } 
	
	 //Read Method
	 public String readProjects() 
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
			 output = "<table border='1'><tr><th>Project ID</th> <th>Project Name</th><th>Project Type</th>"
			 + "<th>Project Description</th> <th>Update</th><th>Remove</th></tr>"; 
			 String query = "select * from projects"; 
			 Statement stmt = con.createStatement(); 
			 ResultSet rs = stmt.executeQuery(query); 
			 // iterate through the rows in the result set
			 while (rs.next()) 
			 { 
			 String projectID = Integer.toString(rs.getInt("projectID")); 
			 String projectName = rs.getString("projectName");
			 String projectType = Double.toString( 
			 rs.getDouble("projectType")); 
			 String projectDescription = rs.getString("projectDescription"); 
			 // Add into the html table
			 output += "<tr><td><input id='hidprojectIDUpdate' name='hidprojectIDUpdate' type='hidden' value='" + projectID
			 + "'>" + projectID + "</td>"; 
			 output += "<td>" + projectName + "</td>"; 
			 output += "<td>" + projectType + "</td>"; 
			 output += "<td>" + projectDescription + "</td>"; 
			 // buttons
			output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"
			 + "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-projectID='"
			 + projectID + "'>" + "</td></tr>"; 
			 } 
			 con.close(); 
			 // Complete the html table
			 output += "</table>"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "Error while reading the projects."; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
		 } 
	 
	 //Insert Method
	 public String insertProject(String id, String name, 
	 String type, String desc) 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 { 
	 return "Error while connecting to the database for inserting."; 
	 } 
	 // create a prepared statement
	 String query = " insert into projects (`projectID`,`projectName`,`projectType`,`projectDescription`)"
	 + " values (?, ?, ?, ?)";
			 PreparedStatement preparedStmt = con.prepareStatement(query); 
			 // binding values
			 preparedStmt.setInt(1, 0);  
			 preparedStmt.setString(2, name); 
			 preparedStmt.setString(3, name); 
			 preparedStmt.setString(4, desc); 
			 
			 // execute the statement
			 preparedStmt.execute(); 
			 con.close(); 
			 String newItems = readItems(); 
			 output = "{\"status\":\"success\", \"data\": \"" + 
			 newItems + "\"}"; 
			 } 
			 catch (Exception e) 
			 { 
			 output = "{\"status\":\"error\", \"data\": \"Error while inserting the item.\"}"; 
			 System.err.println(e.getMessage()); 
			 } 
			 return output; 
			 } 
			public String updateItem(String ID, String code, String name, 
			 String price, String desc) 
			 { 
			 String output = ""; 
			 try
			 { 
			 Connection con = connect(); 
			 if (con == null) 
			 { 
			 return "Error while connecting to the database for updating."; 
			 } 
			 // create a prepared statement
			 String query = "UPDATE items SET itemCode=?,itemName=?,itemPrice=?,itemDesc=? WHERE itemID=?"; 
			 PreparedStatement preparedStmt = con.prepareStatement(query); 
			 // binding values
			 preparedStmt.setString(1, code); 
			 preparedStmt.setString(2, name); 
			 preparedStmt.setDouble(3, Double.parseDouble(price)); 
			 preparedStmt.setString(4, desc); 
			 preparedStmt.setInt(5, Integer.parseInt(ID)); 
			// execute the statement
			 preparedStmt.execute(); 
			 con.close(); 
			 String newProjects = readProjects(); 
			 output = "{\"status\":\"success\", \"data\": \"" + 
					 newProjects + "\"}"; 
			 } 
			 catch (Exception e) 
			 { 
			 output = "{\"status\":\"error\", \"data\": \"Error while updating the project.\"}"; 
			 System.err.println(e.getMessage()); 
			 } 
			 return output; 
			 } 
			public String deleteProjects(String projectID) 
			 { 
			 String output = ""; 
			 try
			 { 
			 Connection con = connect(); 
			 if (con == null) 
			 { 
			 return "Error while connecting to the database for deleting."; 
			 } 
			 // create a prepared statement
			 String query = "delete from projects where projectID=?"; 
			 PreparedStatement preparedStmt = con.prepareStatement(query); 
			 // binding values
			 preparedStmt.setInt(1, Integer.parseInt(projectID)); 
			 // execute the statement
			 preparedStmt.execute(); 
			 con.close(); 
			 String newProjects = readProjects(); 
			 output = "{\"status\":\"success\", \"data\": \"" + 
					 newProjects + "\"}"; 
			 } 
			 catch (Exception e) 
			 { 
			 output = "{\"status\":\"error\", \"data\": \"Error while deleting the project.\"}"; 
			 System.err.println(e.getMessage()); 
			 } 
			 return output; 
			 } 
	
}

package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Advice{
	
	private Connection connect()
	{
		Connection con = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			//provide the correct details : DBServer/DBName,user name, password
		con = DriverManager.getConnection("jdbc:mysql://localhost/PAF", "root","");
			
		}catch (Exception e)
		{e.printStackTrace();}
		
		return con;
		
	}
	
	public String insertAdvice(String Id, String AdvicerName,String ResearchGroupId,String ResearchType,String AdvicingType, String AdvicerCurrentLevel)
	{
		String output ="";
		
		try {
			
			Connection con = connect();
			
			if(con==null)
			{return "Error while connecting to the database for inserting.";}
			
			// create a prepared statement
			
			String query = " insert into Advice('Id','AdvicerName', 'ResearchGroupId', 'ResearchType', 'AdvicingType', 'AdvicerCurrentLevel)"+" values(?,?,?,?,?,?)";
			
			PreparedStatement pst = con.prepareStatement(query);
			
			pst.setInt(1, 0);
			pst.setString(2, AdvicerName);
			pst.setString(3, ResearchGroupId);
			pst.setString(4, ResearchType);
			pst.setString(5, AdvicingType);
			pst.setString(6, AdvicerCurrentLevel);
			
			pst.execute();
			
			con.close();
			
			output = "Successesfully Added";
	
		} catch(Exception e) {
			
			output = "Error while inserting the item";
			
			System.err.println(e.getMessage());
			
		}
		return output;
	}
	
	public String readAdvice()
	{
		String output = "";
		
		try {
			
			Connection con = connect();
			
			if(con==null) {
				
				return "Error while connecting to the database for reading.";
				
			}
			
			// Prepare the html table to be displayed
			
		output = "<table border ='1'><tr> <th> Id</th> <th> AdvicerName</th>"
				+ "<th> ResearchGroupId </th>" + "<th> ResearchType</th>"
				+"<th> AdvicingType </th>"+ "<th> AdvicerCurrentLevel</th>"
				+"<th>Update</th><th>Remove</th> </tr>";
		
		String query = "select * from Advice";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		
		//iterate through the rows in the result set
		
		while(rs.next()) {
			
			String Id = Integer.toString(rs.getInt("Id"));
			String AdvicerName = rs.getString("AdvicerName");
			String ResearchGroupId = rs.getString("ResearchGroupId");
			String ResearchType = rs.getString("ResearchType");
			String AdvicingType = rs.getString("AdvicingType");
			String AdvicerCurrentLevel = rs.getString("AdvicerCurrentLevel");
			
			//Add into the html table
			
			output += "<tr><td>" + AdvicerName + "</td>";
			output += "<td>" + ResearchGroupId + "</td>";
			output += "<td>" + ResearchType + "</td>";
			output += "<td>" + AdvicingType + "</td>";
			output += "<td>" + AdvicerCurrentLevel + "</td>";
			
			//buttons
			
		output += "<td> <input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'> </td> " + "<td><form method='post' action='Advice.java'>"+"</form> </td> </tr>";
		con.close();
		
		//complete the html table
		
		output += "</table>";
	
		}
		} 
		catch(Exception e)
		
		{
			output ="Error while reading the items.";
			System.err.println(e.getMessage()); 
		}
		
		return output;
			
		}
		
		public String UpdateAdvice(String Id, String AdvicerName, String ResearchGroupId, String ResearchType, String AdvicingType, String AdvicerCurrentLevel)
		{
			
			String output = "";
			
			try {
				Connection con = connect();
				
				if(con==null)
				{
					return "Error while connecting to the database for updating.";
				}
				
				//create a prepared Statement
				
				String query = "UPDATE Advice SET AdvicerName=? , ResearchGroupId=? , ResearchType=? , AdvicingType=? , AdvicerCurrentLevel WHERE Id =?";
				
				    PreparedStatement pst = con.prepareStatement(query);
				    
				    //binding values
				    
				    pst.setString(1, AdvicerName);
				    pst.setString(2, ResearchGroupId);
				    pst.setString(3, ResearchType);
				    pst.setString(4, AdvicingType);
				    pst.setString(5, AdvicerCurrentLevel);
				    pst.setString(1, AdvicerName);
				    pst.setInt(6, Integer.parseInt(Id));
				    
				    //execute the statement
				    
				    pst.execute();
				    con.close();
				    
				    output = "Successfully Updated";
				
			}
			catch(Exception e) {
				
				output = "Error while updating the Advice.";
				System.err.println(e.getMessage());
				
			}
			return output;

		}
		
public String deleteAdvice(String Id)
{
	
	String output = "";
	
	try {
		
		Connection con = connect();
		
		if(con==null)
		{
			return "Error while connecting to the database for deleting.";
		}
		
		// create  a pst statement
		
		String query = "delete from Advice where Id=?";
		
		PreparedStatement pst = con.prepareStatement(query);
		
		//binding values
		
		pst.setInt(1, Integer.parseInt(Id));
		
		// exe statement
		
		pst.execute();
		con.close();
		
		output = "Sucessfully Deleted";
		
	}
	catch(Exception e)
	{
		
		output = "Error while deleteing the Advice.";
		System.err.println(e.getMessage());
		
	}
	return output;
	
}
		
	
	
}

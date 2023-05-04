package com.example.transaction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class transactionSample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Connection conn= null;
		Statement st = null;
		ResultSet set = null;
		
		String url = "jdbc:mysql://localhost:3306/mydb";
		String user = "root";
		String pass = "admin";
		
		try {
			conn = DriverManager.getConnection(url, user, pass);
			
			st = conn.createStatement();
			
			//multiple query
			
	}
		catch(Exception e) {
		System.out.print("Error");
	}
	}

}

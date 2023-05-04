package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JdbcAdd {
	
	public static void main(String[] args ) {
		
		Connection conn= null;
		Statement st = null;
		ResultSet set = null;
		
		String url = "jdbc:mysql://localhost:3306/mydb";
		String user = "root";
		String pass = "admin";
		
		try {
			conn = DriverManager.getConnection(url, user, pass);
			
			st = conn.createStatement();
			
			set = st.executeQuery("select * from edu");
			
			while(set.next()) {
				System.out.println(set.getString("title")+ " "+ set.getString("description"));
			}
		} catch(Exception e) {
			System.out.println("Error");
		}
	}

}

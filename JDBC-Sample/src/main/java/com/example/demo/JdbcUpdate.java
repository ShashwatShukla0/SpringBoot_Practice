package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JdbcUpdate {

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
			
			int row = st.executeUpdate(
					"update into edu" + "set description='ABC'" + "where title='ABC'"
					);
			
			set = st.executeQuery("select * from edu");
			
			displayEdu(conn,"ABC");
			
			while(set.next()) {
				System.out.println(set.getString("title"));
			}
			
	}
		catch(Exception e) {
		System.out.print("Error");
	}
		finally {
			close(conn,st,set);
		}
	}

	private static void close(Connection conn, Statement st, ResultSet set) {
		// TODO Auto-generated method stub
		
	}

	private static void displayEdu(Connection conn, String string) {
		// TODO Auto-generated method stub
		
	}

}

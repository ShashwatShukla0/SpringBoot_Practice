package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JdbcInsert {

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
					"insert into edu" + "(id,title,description,year)" + "values "+ 
					"(5,'MNNM','JDBC','4003')"
					);
			
			set = st.executeQuery("select * from edu");
			
			while(set.next()) {
				System.out.println(set.getString("title"));
			}
			
	}
		catch(Exception e) {
		System.out.print("Error");
	}

}
}

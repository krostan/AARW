package com.database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCDriver {
	private static void loadDriver() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Connection getConn() {
		
		loadDriver();
		 	
		String url ="jdbc:mysql://localhost:8888/pet_directory";
		String user ="";
		String password="";

		Connection conn=null;
		
		try {
			conn = DriverManager.getConnection(url,user,password); 
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
}

package com.eduATM;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ATM_Database_Connection {
	
	private static String un="root";
	private static String pass="Amit@123";
	private static String driver="com.mysql.cj.jdbc.Driver";
	private static String url="jdbc:mysql://localhost:3306/atm_interface";
	
	private static Connection conn=null;
	
	public static Connection getConnection () throws SQLException {
		
		if ( conn==null) 
			conn=DriverManager.getConnection(url,un,pass);
	
		return conn;
	}

}
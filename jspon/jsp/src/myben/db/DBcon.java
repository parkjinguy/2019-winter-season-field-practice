package myben.db;

import java.sql.*;

public class DBcon {
	final static String DB_URL = "jdbc:mysql://localhost:3306/pjg?useSSL=false&serverTimezone=Asia/Seoul";
	final static String DB_USER = "root";
	final static String DB_PASSWORD = "1234";
	
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}
	public static Connection getConnection() throws SQLException{
		return DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
	}
}

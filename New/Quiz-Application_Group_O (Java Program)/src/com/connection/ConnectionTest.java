package com.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/*
 * this class is purelly designed for JDBC connection only, 
 * by using this connection we can perform various operations on DB. 
 */
public class ConnectionTest {
	public static Connection getConnection() throws SQLException {
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			// jdbc:mysql://localhost:3306/Peoples?autoReconnect=true&useSSL=false
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/quiz?autoReconnect=true&useSSL=false", "root", "Yash_Mate@#2487");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;

	}

}

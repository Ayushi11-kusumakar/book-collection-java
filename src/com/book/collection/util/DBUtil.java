package com.book.collection.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBUtil {

	private static final String URL = "jdbc:mysql://localhost/";
	private static final String DB = "Book_collection";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "AmstechInc@26";

	private static final String DRIVER = "com.mysql.jdbc.Driver";

	public static Connection getLocalDBConnection() throws Exception {

		Connection conn = null;

		try {
			// Register JDBC driver
			Class.forName(DRIVER).newInstance();

			// Open a connection
			conn = DriverManager.getConnection(URL + DB, USERNAME, PASSWORD);
			System.out.println("Connected Local Database Successfully.");

		} catch (Exception se) {
			throw new Exception("Failed to create Local Database connection", se);
		}
		return conn;

	}

	public static void close(Connection conn) {
		close(null, null, conn);
	}

	public static void close(Statement stmt, Connection conn) {
		close(null, stmt, conn);
	}

	public static void close(ResultSet rs, Statement stmt, Connection conn) {
		if (rs != null) {
			try {
				rs.close();
			} catch (Exception e) {

			}
		}

		if (stmt != null) {
			try {
				stmt.close();
			} catch (Exception e) {
			}
		}

		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) {

			}
		}

	}
}

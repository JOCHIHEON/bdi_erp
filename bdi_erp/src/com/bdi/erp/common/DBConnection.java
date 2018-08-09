package com.bdi.erp.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private static final String URL = "jdbc:mariadb://localhost:3306/bdi";
	private static final String USER = "root";
	private static final String PASSWORD = "12345678";
	private static final String DRIVER = "org.mariadb.jdbc.Driver";
	private static Connection con = null;
	
	private static void openCon() {
		try {
			Class.forName(DRIVER);
			try {
				con = DriverManager.getConnection(URL, USER, PASSWORD);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static Connection getCon() {
		if(con==null) {
			openCon();
		}
		return con;
	}
	public static void closeCon() {
		if(con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		con = null;
	}
	public static void main(String[] args) {
		Connection con = DBConnection.getCon();
		System.out.println("연결됨");
	}
}

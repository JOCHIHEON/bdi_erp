package com.bdi.erp.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class DBTest {
	public static void main(String[] args) {
//		Driver url ID Pwd
		String driver = "org.mariadb.jdbc.Driver";
		String url = "jdbc:mariadb://localhost:3306/bdi";
		String id = "root";
		String pwd = "12345678";
		Connection con = null;
		int cnt = 0;
		Scanner s = new Scanner(System.in);
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, id, pwd);
			String sql = "select count(*) from user_info";
			sql += " where uiId=? and uiPwd=?";
			PreparedStatement ps = con.prepareStatement(sql);
			while(cnt==0) {
				System.out.println("아이디: ");
				String uiId = s.nextLine();
				System.out.println("비밀번호: ");
				String uiPwd = s.nextLine();
				ps.setString(1, uiId);
				ps.setString(2, uiPwd);
				ResultSet rs = ps.executeQuery();
				rs.next();
				cnt = rs.getInt(1);
				if(cnt==1) {
					System.out.println("로그인성공!");
				}else {
					System.out.println("로그인실패! 아이디,비밀번호 확인");
				}
				System.out.println("검색 명수: " + cnt);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) { 
			e.printStackTrace();
		}
	}
}

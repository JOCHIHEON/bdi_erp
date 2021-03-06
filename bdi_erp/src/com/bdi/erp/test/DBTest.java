package com.bdi.erp.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.bdi.erp.common.DBConnection;

public class DBTest {
	public static void main(String[] args) {
		Connection con = null;
		int cnt = 0;
		Scanner s = new Scanner(System.in);
		List<Map<String,String>> userList = new ArrayList<Map<String,String>>(); 
		try {
			con = DBConnection.getCon();
			String sql = "select * from user_info";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			Map<String,String> userMap;
			while(rs.next()) {
				userMap = new HashMap<String,String>();
				userMap.put("uiNum",rs.getString("uiNum"));
				userMap.put("uiName",rs.getString("uiName"));
				userMap.put("uiId",rs.getString("uiId"));
				userMap.put("uiPwd",rs.getString("uiPwd"));
				userMap.put("uiEmail",rs.getString("uiEmail"));
				userMap.put("uiEtc",rs.getString("uiEtc"));
				userMap.put("credat",rs.getString("credat"));
				userMap.put("cretim",rs.getString("cretim"));
				userMap.put("moddat",rs.getString("moddat"));
				userMap.put("modtim",rs.getString("modtim"));
				userMap.put("modusr",rs.getString("modusr"));
				userMap.put("diNum",rs.getString("diNum"));
				userList.add(userMap);
				System.out.println(userList);
			}
		}catch (SQLException e) { 
			e.printStackTrace();
		}finally {
			if(con!=null) {
				DBConnection.closeCon();
			}
		}
	}
}

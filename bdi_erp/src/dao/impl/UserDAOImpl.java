package dao.impl;

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

import dao.UserDAO;

public class UserDAOImpl implements UserDAO{

	@Override
	public boolean login(String id, String pwd) {

		Connection con = DBConnection.getCon();
		String sql = "select count(*) from user_info";
		sql += " where uiId=? and uiPwd=?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, pwd);
			ResultSet rs = ps.executeQuery();
			rs.next();
			int cnt = rs.getInt(1);
			if(cnt==1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public List<Map<String,String>> getUserList(){
		List<Map<String,String>> userList = new ArrayList<Map<String,String>>(); 
		Connection con = null;
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
		return userList;
	}
}


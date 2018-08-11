package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
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
	public List<Map<String,String>> getUserList(String key, String value){
		List<Map<String,String>> userList = new ArrayList<Map<String,String>>(); 
		Connection con = null;
		try {
			con = DBConnection.getCon();
			String sql = "select * from user_info where 1=1 ";
			if(key!=null && !key.equals("")) {
				sql += " and " + key + " like ?";
			}
			PreparedStatement ps = con.prepareStatement(sql);

			if(key!=null && !key.equals("")) {
				ps.setString(1, "%" + value +"%");
			}
			ResultSet rs = ps.executeQuery();
			ResultSetMetaData rsmt = rs.getMetaData();
			
			Map<String,String> userMap;
			while(rs.next()) {
				userMap = new HashMap<String,String>();
				for(int i=1;i<=rsmt.getColumnCount();i++) {
					String colNm = rsmt.getColumnLabel(i);
					userMap.put(colNm, rs.getString(colNm));
				}
				userList.add(userMap);
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


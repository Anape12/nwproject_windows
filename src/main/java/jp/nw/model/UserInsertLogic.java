package jp.nw.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.nw.parts.DBBase;

public class UserInsertLogic {

	/**
	 * ユーザ―情報一覧表示
	 * */
	public boolean insertProcess(User user){
		List<User> userList = new ArrayList<>();
		Connection conn = null;
		try {
			DBBase dbBase = new DBBase();
			conn = dbBase.getConnection();
			String sql = "INSERT INTO users (name,password,birthday,permission_level,削除フラグ) values (?,?,?,?,0);";
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1,user.getName());
			ps.setString(2,user.getPass());
			ps.setString(3,user.getBirth());
			ps.setInt(4,user.getPermission());

			int num = ps.executeUpdate();
			if(num == 0) {
				return false;
			} else {
				return true;
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * 編集ユーザ－情報取得
	 * */
	public List<User> editUserInfo(String userId){
		List<User> userList = new ArrayList<>();
		Connection conn = null;
		try {
			DBBase dbBase = new DBBase();
		    conn = dbBase.getConnection();
			String sql = "SELECT name,password,permission_level where name =? ORDER BY id";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, userId);

			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				String name = rs.getString("name");
				String pass = rs.getString("password");
				int permission = rs.getInt("permission_level");
				User mutter = new User(name,pass,permission);
				userList.add(mutter);
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		return userList;
	}
}
package jp.nw.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.nw.base.BaseModel;
import jp.nw.parts.DBBase;

public class UserViewLogic extends BaseModel{

	// SQL発行オブジェクト
	private DBBase dbCon = null;

	// 返却情報格納List
	private List<List<String>> retList = null;

	/**
	 * ユーザ―情報一覧表示
	 * */
	public List<User> findAll(){
		List<User> userList = new ArrayList<>();

		BaseModel.logger.writeInfo("UsreViewLogic-findAll");
		
		// Connection取得
		dbCon = new DBBase();

		try(Connection conn = dbCon.getConnection()){

			// テーブル名
			String trgTable = "users_info";
			// カラム情報
			List<String> colList = new ArrayList<String>();
			colList.add("id");
			colList.add("name");
			colList.add("password");
			colList.add("permission");
			// 検索条件
			Map<String, String> whereInfo = new HashMap<String, String>();
			whereInfo.put("delete_flg", "0");
			whereInfo.put("PROCESS_INFO", "ORDER BY id");

			retList = new ArrayList<List<String>>();

			// 要動作チェック(DBでint型のカラムは失敗するはず)
			retList = dbCon.selectSql(trgTable, colList, whereInfo);

			for(List<String> rowInfo : retList) {
				int num = Integer.parseInt(rowInfo.get(0));
				String name = rowInfo.get(1);
				String pass = rowInfo.get(2);
				int permission = Integer.parseInt(rowInfo.get(3));
				User mutter = new User(num, name, pass, permission);
				userList.add(mutter);
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		return userList;
	}
	/**
	 * 編集ユーザ－情報取得
	 * */
	public List<User> editUserInfo(String userId){
		List<User> userList = new ArrayList<>();

		// Connection取得
		dbCon = new DBBase();

		try(Connection conn = dbCon.getConnection()){
			String sql = "SELECT name,password,permission_level FROM users where name =? ORDER BY id";
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
	/**
	 * ユーザー情報変更確定処理
	 * */
	public List<User> confirUserInfo(String nowId, String userId, String userPass, String userPermission) {
		List<User> userList = new ArrayList<>();

		// Connection取得
		dbCon = new DBBase();

		try(Connection conn = dbCon.getConnection()){
			// ユーザー情報編集チェック処理
			if(!userInfoCheck(userId, userPass, userPermission)) {
//				JFrame frame = new JFrame();
//				JOptionPane.showMessageDialog(frame, "値を更新してください");
			} else {
				String sql = "UPDATE users Set name = ?, password = ?, permission_level = ? where name=?";
				PreparedStatement ps = conn.prepareStatement(sql);
				int permission = Integer.parseInt(userPermission) ;
				ps.setString(1, userId);
				ps.setString(2, userPass);
				ps.setInt(3, permission);
				ps.setString(4, nowId);
				// 更新処理
				int num =ps.executeUpdate();
				if(num == 0) {
					userList = findAll();
					return userList;
				} else {
					userList = findAll();
					return userList;
				}
			}
			return userList;
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * ユーザー情報更新チェック処理
	 * @param user
	 * @return boolean
	 */
	public boolean userInfoCheck(String nowId, String nowPass, String permisstion) {
		String name = "";
		String pass = "";
		int permiss = 2;
		// Connection取得
		dbCon = new DBBase();

		try {
			Connection con = dbCon.getConnection();
			String sql = "SELECT name, password, permission_level FROM users WHERE name = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, nowId);

			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				name = rs.getString("name");
				pass = rs.getString("password");
				permiss = rs.getInt("permission_level");
			}
			Integer i = Integer.valueOf(permiss);
			String perm = i.toString();

			if(name.equals(nowId) && pass.equals(nowPass) && permisstion.equals(perm)) {
				return false;
			}
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return true;
	}

	/**
	 *
	 */
	public boolean userInfoUpdate(Map<String, String> postMap) {
		// 編集されたユーザ情報（ID,パスワード,権限レベル）を取得
		String nowUserId = postMap.get("nowId");
		String userId = postMap.get("userId");
		String userPass = postMap.get("userPass");
		String userPermission = postMap.get("userPerm");
		// ユーザー情報編集
		UserViewLogic userview = new UserViewLogic();
		List<User> userList = userview.confirUserInfo(nowUserId,userId,userPass,userPermission);

		if(userList.size() == 0) {
			// エラー
			return false;
		} else {
			return true;
		}


	}
}
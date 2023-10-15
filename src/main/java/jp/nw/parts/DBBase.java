package jp.nw.parts;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBBase {

	private final String URL = "jdbc:mysql://localhost:3306/nwproject_db?serverTimezone=JST";
	private final String USER = "root";
	private final String PASSWORD = "Ae76@231128";

	private Connection con = null;
	//SELECT情報一時退避領域
	private List<String> selectInfo = null;
	//WHERE句情報一時退避領域
	private List<String> whereInfo = null;
	// 発行SQL
	private StringBuilder sb = null;
	// SQL結果返却用Map
	private Map<String, Object> resultMap = null;
	// 複数項目返却Map
	private Map<String,Map<String,Object>> dupliMap = null;
	// 複数項目返却MapKey
	private String deuliKey = null;

	public DBBase() {
		try {
			// Connection生成
			this.con = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		return this.con;
	}

	/**
	 * SELECT SQL発行処理
	 * @param sqlWardInfo カラム名
	 * @param searchInfo 条件句
	 * @param tableName テーブル名
	 * @return Map SQL実行結果
	 * */
	public Map<String, Object> userInfoSql(Map<String, Object> sqlWardInfo, Map<String, Object> searchInfo, String tableName) {

		try {

			// Map初期化
			resultMap = new HashMap<String, Object>();
			dupliMap = new HashMap<String,Map<String,Object>>();

			// 一時退避領域へ各パラメータを格納
			this.selectInfo = (List<String>) sqlWardInfo.get(DaoPart.KOMOKU_INFO.SELECT_INFO);
			this.whereInfo = (List<String>) sqlWardInfo.get(DaoPart.KOMOKU_INFO.WHERE_INFO);

			// 検索情報一括取得
			List<String>  searchInfoList  = new ArrayList<String>();
			for (String key : searchInfo.keySet()) {
				searchInfoList.add((String)searchInfo.get(key));
	        }

			// SQL発行
			sb = new StringBuilder();
			sb.append(DaoPart.SQL.SELECT);
			sb.append(DaoPart.SQL.SPACE);

			for (int i = 0; i < this.selectInfo.size(); i++) {
				sb.append(this.selectInfo.get(i));
				sb.append(",");
			}
			// 末尾のカンマを削除
			sb.setLength(sb.length() - 1);

			sb.append(DaoPart.SQL.SPACE);
			sb.append(DaoPart.SQL.FROM);
			sb.append(DaoPart.SQL.SPACE);
			if (tableName != null || tableName != "") {
				sb.append(tableName);
			}
			sb.append(DaoPart.SQL.SPACE);
			sb.append(DaoPart.SQL.WHEHE);
			sb.append(DaoPart.SQL.SPACE);

			for (int i = 0; i < whereInfo.size(); i++) {
				sb.append(whereInfo.get(i));
				sb.append(DaoPart.SQL.SPACE);
				sb.append(DaoPart.SQL.EQUARL);
				sb.append(DaoPart.SQL.SPACE);
				sb.append("?");
				sb.append(",");
			}
			// 末尾のカンマを削除
			sb.setLength(sb.length() - 1);

			// 結果加工条件
			boolean prcInfo = sqlWardInfo.containsKey(DaoPart.KOMOKU_INFO.PROCESS_INFO);
			// 条件句が存在するかチェック
			if(prcInfo) {
				String prcRs =(String)sqlWardInfo.get(DaoPart.KOMOKU_INFO.PROCESS_INFO);
				String[] info= prcRs.split(";");
				for(int j = 0; j < info.length; j++) {
					sb.append(DaoPart.SQL.SPACE);
					sb.append(info[j]);
				}
			}

			// バインド変数定義
			PreparedStatement ps = con.prepareStatement(sb.toString());

			// バインド変数設定
			int cnt = 1;
			for(int j = 0; j < searchInfo.size(); j++) {
				ps.setString(cnt, searchInfoList.get(j));
			}
			// SQL実行
			ResultSet result = ps.executeQuery();

			while (result.next()) {
				for(int k = 0; k < this.selectInfo.size(); k++) {
					// Key重複チェック
					if(resultMap.containsKey(this.selectInfo.get(k))) {
						Integer i = Integer.valueOf(k);
						String str = i.toString();
						deuliKey = "DKEY".concat(str);
						dupliMap.put(deuliKey, resultMap);
					} else {
						resultMap.put(this.selectInfo.get(k), result.getString(this.selectInfo.get(k)));
					}
				}
			}
		} catch (SQLException e) {
			// Error処理
			e.printStackTrace();
		}

		return resultMap;
	}

	/**
	 * シンプルSelectSQL
	 */
	public List<List<String>> selectSql(String tableName, List<String> columInfo, Map<String, String> whereInfo){

		// 検索条件取得Key
		List<String> keyList = new ArrayList<String>();

		// 検索条件返却リスト
		List<List<String>> retList = new ArrayList<List<String>>();
		try {
			// SQL発行
			sb = new StringBuilder();
			sb.append(DaoPart.SQL.SELECT);
			sb.append(DaoPart.SQL.SPACE);

			for (String columVal : columInfo) {
				sb.append(columVal);
				sb.append(",");
			}
			// 末尾のカンマを削除
			sb.setLength(sb.length() - 1);

			sb.append(DaoPart.SQL.SPACE);
			sb.append(DaoPart.SQL.FROM);
			sb.append(DaoPart.SQL.SPACE);
			if (tableName != null || tableName != "") {
				sb.append(tableName);
			}
			sb.append(DaoPart.SQL.SPACE);
			sb.append(DaoPart.SQL.WHEHE);
			sb.append(DaoPart.SQL.SPACE);

			// 条件句存在チェック
			if(whereInfo.size() != 0) {
				for(String key : whereInfo.keySet()) {
					// 条件句の場合処理をスルー
					if(key.equals(DaoPart.KOMOKU_INFO.PROCESS_INFO)) {
						continue;
					}
					// バインド化用Key
					keyList.add(key);
					sb.append(key);
					sb.append(DaoPart.SQL.SPACE);
					sb.append(DaoPart.SQL.EQUARL);
					sb.append(DaoPart.SQL.SPACE);
					sb.append("?");
					sb.append(",");
				}
				// 末尾のカンマを削除
				sb.setLength(sb.length() - 1);
			}

			// データ操作句が存在する場合
			if(whereInfo.containsKey(DaoPart.KOMOKU_INFO.PROCESS_INFO)) {
				sb.append(DaoPart.SQL.SPACE);
				sb.append(whereInfo.get(DaoPart.KOMOKU_INFO.PROCESS_INFO));
				sb.append(";");
			}

			// バインド変数定義
			PreparedStatement ps = con.prepareStatement(sb.toString());

			// バインド変数設定
			int bindCnt = 1;
			if(keyList.size() != 0) {
				for(int i = 0; i < keyList.size(); i++) {
					ps.setString(bindCnt, whereInfo.get(keyList.get(i)));
					bindCnt++;
				}
			}
			// SQL実行
			ResultSet result = ps.executeQuery();

			while (result.next()) {
				// １カラム返却情報
				List<String> colList =  new ArrayList<String>();
				for(String key : columInfo) {
					colList.add(result.getString(key));
				}
				retList.add(colList);
			}
			// SQL結果返却
			return retList;
		} catch (SQLException e) {
			// Error処理
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Update SQL発行処理
	 * @param tableName テーブル名
	 * @param columnInfo SQLカラム名
	 * @param columnVal SQL設定値
	 * @return 実行結果フラグ
	 * */
	public boolean updateSQL(String tableName, List<String> columnInfo, List<String> columnVal) {
		return true;
	}
}
package jp.nw.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.nw.parts.DBBase;

public class OpenCalenderLogic {

	public Map<String, List<String>> readCalender(String[] param, int[] readDay) {

		Map<String, List<String>> scheduleMap = null;
		List<String> scheduleList = null;

		try {
			// Connection生成
			DBBase dbBase = new DBBase();
			Connection con = dbBase.getConnection();

			// 西暦の取得
			Date date = new Date();
			ZoneId timeZone = ZoneId.systemDefault();
	        LocalDate getLocalDate = date.toInstant().atZone(timeZone).toLocalDate();
	        Integer i = getLocalDate.getYear();
			String year = i.toString();
			// 取得日付を取得
			Integer d = Integer.valueOf(readDay[1]);
			String day = d.toString();

			StringBuilder sb = new StringBuilder();
			sb.append("SELECT DATE, FROMTIME, TOTIME, PLAN FROM ");
			sb.append("a001_schedule_");
			sb.append(param[2]);
			sb.append(" WHERE DATE like ?");
			sb.append(" order by FROMTIME");

			PreparedStatement ps = con.prepareStatement(sb.toString());
			ps.setString(1, year.concat("-").concat(param[0]).concat("-").concat(day).concat("%"));
			// スケジュール格納変数
			scheduleMap = new HashMap<String, List<String>>();
			scheduleList = new ArrayList<String>();

			ResultSet rs = ps.executeQuery();
			Integer cnt = 0;
			while(rs.next()) {
				String key = "KEY";
				// MapのKey生成
				key = key.concat(cnt.toString());
				// Listの初期化、繰り返しの中でインスタンス化はしたくないな
				scheduleList = new ArrayList<String>();

				scheduleList.add(rs.getString("DATE"));
				scheduleList.add(rs.getString("FROMTIME"));
				scheduleList.add(rs.getString("TOTIME"));
				scheduleList.add(rs.getString("PLAN"));
				scheduleMap.put(key, scheduleList);
				++cnt;
			}
			return scheduleMap;
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return null;
	}

	public void getSchedule(String userId, String date) {
		/*↓形式で取得*/
		//SELECT DATE, FROMTIME, TOTIME, PLAN FROM a001_schedule_a0005 WHERE DATE like '2021-8%';
	}
}

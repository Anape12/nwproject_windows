package jp.nw.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

import jp.nw.base.BaseModel;
import jp.nw.parts.DBBase;

public class WriteSheduleLogic extends BaseModel{

	public int writeSchedule(Map<String, String> info) {

		try {
			// Connection生成
			DBBase dbBase = new DBBase();
			Connection con = dbBase.getConnection();

			/* スケジュールパラメータ生成*/
			// 日付
			StringBuilder date  = new StringBuilder();
			date.append(info.get("year"));
			date.append("-");
			date.append(info.get("month"));
			date.append("-");
			date.append(info.get("day"));
			// From～To時刻
			String fromTime = info.get("fromhour").concat(info.get("fromminit"));
			String toTime = info.get("tohour").concat(info.get("tominit"));

			String plan = info.get("plan");
			String memo = info.get("memo");

			// スケジュール設定SQL
			StringBuilder sb = new StringBuilder();
			sb.append("INSERT INTO ");
			sb.append("a001_schedule_");
			sb.append(info.get("query"));
			sb.append(" (DATE,FROMTIME,TOTIME,PLAN,MEMO) values (?,?,?,?,?);");
			PreparedStatement ps = con.prepareStatement(sb.toString());
			ps.setString(1,date.toString());
			ps.setString(2,fromTime);
			ps.setString(3,toTime);
			ps.setString(4,plan);
			ps.setString(5, memo);

			int num = ps.executeUpdate();
			return num;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
}

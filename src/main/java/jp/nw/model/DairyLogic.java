package jp.nw.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jp.nw.parts.DBBase;
import jp.nw.parts.DaoPart;

public class DairyLogic {
	PreparedStatement ps = null;
	Connection con = null;

public void execute(Dairy dairy) {
		// 現在日時を取得
		 LocalDateTime nowDateTime = LocalDateTime.now();
		 DateTimeFormatter java8Format = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		 // 日時情報を指定フォーマットの文字列で取得
		 String java8Disp = nowDateTime.format( java8Format );

		try {
			DBBase dbBase = new DBBase();
			con = dbBase.getConnection();
			 StringBuilder sb = new StringBuilder();
		        sb.append(DaoPart.SQL.INSERT);
		        sb.append(DaoPart.SQL.SPACE);
		        sb.append(DaoPart.SQL.INTO);
		        sb.append(DaoPart.SQL.SPACE);
		        sb.append("a001_daiary");
		        sb.append(DaoPart.SQL.SPACE);
		        sb.append("Values ( ");
		        sb.append("?");
		        sb.append(DaoPart.SQL.CANMA);
		        sb.append(DaoPart.SQL.SPACE);
		        sb.append("?");
		        sb.append(DaoPart.SQL.CANMA);
		        sb.append(DaoPart.SQL.SPACE);
		        sb.append(java8Disp);
		        sb.append(");");

		        ps = con.prepareStatement(sb.toString());

		        ps.setString(1, dairy.getTitle());
				ps.setString(2, dairy.getText());

				con.setAutoCommit(false);
				try {
					int result = ps.executeUpdate();
					System.out.println("結果" + result);
					con.commit();
				} catch (Exception e) {
					 // ロールバック
	                con.rollback();
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			 // 各オブジェクトを解放する
            if(ps != null) {
                try {
                	ps.close();
                }catch (Exception e) {
                    System.out.println(e.getMessage());
              }
            }

            if(con != null) {
                try {
                    con.close();
                }catch (Exception e) {
                    System.out.println(e.getMessage());
              }
		}
	}
	}
}

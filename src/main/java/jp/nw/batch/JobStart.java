package jp.nw.batch;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JobStart {

	public static final String URL = "jdbc:mysql://localhost:3306/nwproject_db?serverTimezone=JST";
    public static final String USERID = "root";
    public static final String PASSWORD = "Ae76@231128";
    public static final String SQL = "PLtest.sql";

    public static Connection con = null;
    public static PreparedStatement ps = null;
    public static ResultSet rs = null;
	
    /**
     * Job実行関数
     * @param jobName 実行ジョブ名
     * @return
     */
	public boolean execute(String jobName) {
		
		// ストアドプロシージャの呼び出し
        try (Connection connection = DriverManager.getConnection(URL, USERID, PASSWORD)) {

        	try (CallableStatement callableStatement = connection.prepareCall(jobName)) {
                // INパラメータの設定
                callableStatement.setString(1, "a0001");

                // ストアドプロシージャの実行
                boolean hasResultSet = callableStatement.execute();
                
                // 結果セットの処理
                if (hasResultSet) {
                    try (ResultSet resultSet = callableStatement.getResultSet()) {
                        while (resultSet.next()) {
                            String password = resultSet.getString("password");
                            
                            // 結果の表示
                            System.out.println("Password: " + password);
                        }
                    }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        } catch (SQLException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		}
		return false;
	}
}

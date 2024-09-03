import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JobStartTest {

		public static final String URL = "jdbc:mysql://localhost:3306/nwproject_db?serverTimezone=JST";
	    public static final String USERID = "root";
	    public static final String PASSWORD = "Ae76@231128";
	    public static final String SQL = "PLtest.sql";

	    public static Connection con = null;
	    public static PreparedStatement ps = null;
	    public static ResultSet rs = null;

        public static void main(String[] args) {
            // SQLファイルのパス
            String sqlFilePath = "src\\PLtest.sql";
            // データベースに接続
//            try (Connection connection = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword)) {
//                // SQLファイルを読み込む
//                StringBuilder sql = new StringBuilder();
//                FileReader reader = new FileReader(sqlFilePath);
//                System.out.println(reader.toString());
//                reader.close();
//                try (BufferedReader br = new BufferedReader(new FileReader(sqlFilePath))) {
//                    String line;
//                    while ((line = br.readLine()) != null) {
//                        sql.append(line).append("\n");
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                    return;
//                }
//
//                // SQL文を実行する
//                try (Statement stmt = connection.createStatement()) {
//                    stmt.execute(sql.toString());
//                    System.out.println("SQLファイルの実行が成功しました。");
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
         // ストアドプロシージャの呼び出し
            try (Connection connection = DriverManager.getConnection(URL, USERID, PASSWORD)) {
                // ストアドプロシージャの呼び出し
                String sql = "{call new_procedure(?)}";
                
                try (CallableStatement callableStatement = connection.prepareCall(sql)) {
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
        }
    }

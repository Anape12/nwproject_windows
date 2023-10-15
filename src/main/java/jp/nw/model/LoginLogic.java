package jp.nw.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.nw.base.BaseModel;
import jp.nw.parts.DBBase;

public class LoginLogic extends BaseModel {

	// ログインパラメータ
	private Map<String, Object> param = null;
	// SELECT情報抽出
	private List<String> selectInfo = null;
	// WHERE情報抽出
	private Map<String, String> whereInfo = null;
	// SQL発行オブジェクト
	private DBBase dbCon = null;
	// SQL結果格納Map
	private Map<String, Object> selectResultMap = null;

	public Map<String, Object> execute(User user, Map<String, Object> sqlinfo, String table) {

		try {

			// 各パラメータ格納オブジェクト初期化
			this.param = new HashMap<>();
			this.selectInfo = new ArrayList<String>();
			this.whereInfo = new HashMap<String, String>();

			// SQL SELECT共通部品実行
			dbCon = new DBBase();
			this.param.put("userid", user.getName());
			this.selectResultMap = dbCon.userInfoSql(sqlinfo, param, table);

			// SQL実行結果を返却Mapへ格納(もっとスマートなやり方に追々修正)
			for(String key : this.selectResultMap.keySet()) {
				param.put(key, this.selectResultMap.get(key));
			}

			// パスワード整合性チェック
			if (user.getPass().equals(selectResultMap.get("password"))) {
				param.put("result", true);
				return param;
			} else {
				param.put("result", false);
				return param;
			}
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return param;
	}

}

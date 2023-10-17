package jp.nw.base;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.nw.logger.ActionLog;

public class BaseModel extends ActionLog{

	// アプリ全体の制御を実施するセッション情報
	public String sessionID = null;
	// ログインID
	public String userId = null;
	
	protected static ActionLog logger = null;

	public BaseModel() {
		System.out.println("new BaseModel");
		if(BaseModel.logger != null) {
			System.out.println("スルー");
		} else {
			BaseModel.logger = new ActionLog();
			BaseModel.logger.getLoggerObj();
		}
	}
	
	public Map<String,Object> getParameter(HttpServletRequest request, HttpServletResponse response) {
		
		Map<String,Object> paramMap = new HashMap<String,Object>();
		// POSTした情報を取得し格納する
		Enumeration<String> enumeration = request.getParameterNames();
		while (enumeration.hasMoreElements()) {
		    String key = (String)enumeration.nextElement();
		    String param = request.getParameter(key);
		    paramMap.put(key, param);
		}
		return paramMap;
	}	
}

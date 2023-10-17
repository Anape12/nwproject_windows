package jp.nw.logger;

import java.io.IOException;
import java.time.LocalDate;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import jp.nw.base.ReadProperties;

public class ActionLog {

    // ログクラス
    protected static Logger logObj = null;
    
    // プロパティ参照クラス
    private ReadProperties read = null;

    // ログファイル名
	private static final String LOGFILE_NAME = "server.log";

    
	public void getLoggerObj() {
		if(ActionLog.logObj != null) {
			System.out.println("スルー");
		} else {
			this.read = new ReadProperties();
			ActionLog.logObj = this.getLogObj();
		}
	}
	
	/**
	 * loggerオブジェクト生成
	 * @return
	 */
	public Logger getLogObj() {
		try {
			ActionLog.logObj = Logger.getLogger("nwproject");
	        Handler handler = new FileHandler(this.read.getLogDir().concat(this.createLogFile()), true);
	        handler.setFormatter(new SimpleFormatter());
	        ActionLog.logObj.addHandler(handler);
	        
	        if(this.read.getLogLv().equals("3")) {
		        ActionLog.logObj.setLevel(Level.ALL);	        
	        } else if (this.read.getLogLv().equals("2")) {
	        	ActionLog.logObj.setLevel(Level.WARNING);	        	        	
	        } else if (this.read.getLogLv().equals("1")) {
	        	ActionLog.logObj.setLevel(Level.SEVERE);	        
	        }
	        return ActionLog.logObj;
		} catch (IOException ie) {
			ie.printStackTrace();
			return null;
		}

	}
	
	/**
	 * SV処理情報出力
	 * @param msg
	 */
	public void writeInfo(String msg) {
		ActionLog.logObj.info(msg);
	}
	
	/**
	 * ログファイル名生成処理
	 * @return
	 */
	private String createLogFile() {
		StringBuilder sb = new StringBuilder();
		LocalDate date = LocalDate.now();
		sb.append(date.toString());
		sb.append("_");
		sb.append(LOGFILE_NAME);
		return sb.toString();
	}
}

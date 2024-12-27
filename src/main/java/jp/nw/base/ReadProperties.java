package jp.nw.base;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class ReadProperties {

	// ログレベルプロパティファイルのパス
    private static final String LOGPROPERTIE_PATH = "LogLevel";
    
    // ログレベル
    private final String LEVEL = "LOG_LEVEL";
    
    // 出力ディレクトリ
    private final String LOGDIR = "TRG_DIR";
    
    // ログレベル
    private String logLv = "0";
    
    // ログ出力ディレクトリ
    private String logDir = "";
    
    private List<String> retVals = null;
    
	public ReadProperties(String path, String[] params) {
		
//		ResourceBundle bundle = ResourceBundle.getBundle(LOGPROPERTIE_PATH, Locale.JAPAN);
		ResourceBundle bundle = ResourceBundle.getBundle(path, Locale.JAPAN);
		
		if(params.length > 0) {
			retVals = new ArrayList<String>();
			for(int cnt = 0; cnt < params.length; cnt++) {
				retVals.add(bundle.getString(params[cnt]));
			}			
		} else {
			// エラー処理
		}
		this.logLv = bundle.getString(LEVEL);
		this.logDir = bundle.getString(LOGDIR);
	}
	
	public String getLogLv() {
		return this.logLv;
	}
	
	public String getLogDir() {
		return this.logDir;
	}
	
	public List<String> getRetVals(){
		return this.retVals;
	}
}

package jp.nw.base;

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
    
	public ReadProperties() {
		
		ResourceBundle bundle = ResourceBundle.getBundle(LOGPROPERTIE_PATH, Locale.JAPAN);
		
		this.logLv = bundle.getString(LEVEL);
		this.logDir = bundle.getString(LOGDIR);
		System.out.println(this.logLv);
		System.out.println(this.logDir);
	}
	
	public String getLogLv() {
		return this.logLv;
	}
	
	public String getLogDir() {
		return this.logDir;
	}
}

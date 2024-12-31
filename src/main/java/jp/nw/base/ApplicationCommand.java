package jp.nw.base;

public class ApplicationCommand {

	protected CommandData output = null;
	
	protected BaseModel logger = null;
	
	public CommandData execute() {
		
		// データ格納
		this.output = new CommandData();
		
		// ログ記述
		this.logger = new BaseModel();
		
		// 各処理結果フラグ
		boolean result = true;
		
		// データ操作処理
		if(result) {
			doCommandData();
		}
		// アプリケーション処理
		if(result) {
			executeCommand();
		}
		// データの格納
		if(result) {
			commandOutput();
		}
		
		return this.output;
			
	}
	
	protected boolean setCommandData() {
		return true;
	}
	
	

	
	/**
	 * データ取得
	 * 詳細実装は各ロジックにて実装
	 * @return
	 */
	protected boolean doCommandData() {
		return true;
	}
	
	/**
	 * 取得データを元に各ロジックを実行
	 * @return
	 */
	protected boolean executeCommand() {
		return true;
	}
	
	/**
	 * 返却データの設定処理
	 * @return
	 */
	protected boolean commandOutput() {
		return true;
	}
	
	public CommandData postExec() {
		
		// データ格納
		this.output = new CommandData();
		
		// ログ記述
		this.logger = new BaseModel();
		
		// 各処理結果フラグ
		boolean result = true;
		
		// データ操作処理
		if(result) {
			postCommandData();
		}
		// アプリケーション処理
		if(result) {
			postExeCommand();
		}
		// データの格納
		if(result) {
			postCommandOutput();
		}
		
		return this.output;		
	}
	
	protected boolean postCommandData() {
		return true;
	}
	
	protected boolean postExeCommand() {
		return true;
	}
	
	protected boolean postCommandOutput() {
		return true;
	}

}

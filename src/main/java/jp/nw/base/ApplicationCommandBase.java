package jp.nw.base;

public class ApplicationCommandBase {

	protected CommandData output = null;
	
	
	public CommandData execute() {
		
		this.output = new CommandData();
		
		boolean result = true;
		
		// データ操作処理
		if(result) {
			doCommandData();
		}
		// アプリケーション処理
		if(result) {
			executeCommand();
		}
		// アプリケーション処理結果データの設定
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
}

package jp.nw.base;

import java.util.HashMap;
import java.util.Map;

public class CommandData {

	Map<String, Object> output = null;
	
	public CommandData() {
		this.output = new HashMap<String, Object> ();
	}
	
	/**
	 * データ設定処理
	 * @param key 値格納Key
	 * @param value 格納値
	 */
	public void setValue(String key, Object value) {
		this.output.put(key, value);
	}
	
	public Object getValue(String key) {
		return this.output.get(key);
	}
	
	public void setMSG_C() {
		
	}
	
	public void setMSG_Inf() {
		
	}
}

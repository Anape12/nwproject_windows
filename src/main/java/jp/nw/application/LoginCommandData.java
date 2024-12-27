package jp.nw.application;

import jp.nw.base.CommandData;

public class LoginCommandData extends CommandData{

	
	private String permission = null;
	
	public void setPerMission(String val) {
		this.permission = val;
	}
	
	public String getPermission() {
		return this.permission;
	}
}

package jp.nw.model;

public class User {

	private int num;
	private String name;
	private String pass;
	private int permission;

	private String oldpass;
	private String newpass;

	private String birth;

	public User() {}
	// LoginCheck用インスタンス
	public User(String name , String pass) {
		this.name = name;
		this.pass = pass;
	}
	// パスワード変更用インスタンス
	public User(String name , String oldpass, String newpass) {
		this.name = name;
		this.oldpass = oldpass;
		this.newpass = newpass;
	}
	// ユーザー一覧用インスタンス
	public User(int num, String name , String pass, int permission) {
		this.permission = permission;
		this.num = num;
		this.name = name;
		this.pass = pass;
	}
	// ユーザー情報変更インスタンス
	public User(String name , String pass, int permission) {
		this.permission = permission;
		this.name = name;
		this.pass = pass;
	}

	// ユーザー登録用インスタンス
	public User(String userId, String userPass, String userBirth, int userPermis) {
		this.name = userId;
		this.pass = userPass;
		this.birth = userBirth;
		this.permission = userPermis;
	}


	public int getNum() {
		return num;
	}
	public String getName() {
		return name;
	}
	public String getPass() {
		return pass;
	}
	public String getoldPass() {
		return oldpass;
	}
	public String getnewPass() {
		return newpass;
	}
	public int getPermission() {
		return permission;
	}
	public String getBirth() {
		return birth;
	}

}

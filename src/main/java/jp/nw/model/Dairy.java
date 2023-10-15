package jp.nw.model;

public class Dairy {

	private String title;
	private String text;

	public Dairy() {};
	public Dairy(String title, String text) {
		this.title = title;
		this.text = text;
	};
	
	public String getTitle() {
		return title;
	}
	public String getText() {
		return text;
	}
}

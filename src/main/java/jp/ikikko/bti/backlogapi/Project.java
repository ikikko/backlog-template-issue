package jp.ikikko.bti.backlogapi;

public class Project {

	private int id;

	private String name;

	private String key;

	private String url;

	public Project() {
	}

	public Project(int id, String name, String key, String url) {
		this.id = id;
		this.name = name;
		this.key = key;
		this.url = url;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getKey() {
		return key;
	}

	public String getUrl() {
		return url;
	}

}

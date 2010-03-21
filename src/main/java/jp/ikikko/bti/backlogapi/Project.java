package jp.ikikko.bti.backlogapi;

public class Project {

	/** プロジェクトID */
	private int id;

	/** プロジェクト名 */
	private String name;

	/** プロジェクトキー */
	private String key;

	/** プロジェクトホームURL */
	private String url;

	Project() {
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

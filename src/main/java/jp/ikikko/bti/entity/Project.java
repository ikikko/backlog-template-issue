package jp.ikikko.bti.entity;

public class Project {

	/** プロジェクトID */
	private int id;

	/** プロジェクト名 */
	private String name;

	/** プロジェクトキー */
	private String key;

	/** プロジェクトホームURL */
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

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}

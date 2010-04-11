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

	public Project(final int id, final String name, final String key,
			final String url) {
		this.id = id;
		this.name = name;
		this.key = key;
		this.url = url;
	}

	public int getId() {
		return id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getKey() {
		return key;
	}

	public void setKey(final String key) {
		this.key = key;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(final String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "Project [id=" + id + ", key=" + key + ", name=" + name
				+ ", url=" + url + "]";
	}

}

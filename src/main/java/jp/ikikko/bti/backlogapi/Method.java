package jp.ikikko.bti.backlogapi;

public enum Method {

	GET_USER("backlog.getUser"), GET_PROJECT("backlog.getProject"), CREATE_ISSUE(
			"backlog.createIssue");

	private String name;

	private Method(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}

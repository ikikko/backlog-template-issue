package jp.ikikko.bti.backlog;

public enum Method {

	/** 指定された ログインID のユーザーを取得します。 */
	GET_USER("backlog.getUser"),

	/** プロジェクトキーを指定して、プロジェクトを取得します。 */
	GET_PROJECT("backlog.getProject"),

	/** 課題を追加します。追加に成功した場合は、追加された課題が返ります。 */
	CREATE_ISSUE("backlog.createIssue"),

	/** プロジェクトの参加メンバーを返します。 */
	GET_USERS("backlog.getUsers");

	private String name;

	private Method(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}

package jp.ikikko.bti.backlog;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.Map;

import jp.ikikko.bti.backlog.util.ConvertUtil;
import jp.ikikko.bti.entity.Issue;
import jp.ikikko.bti.entity.Project;
import jp.ikikko.bti.entity.User;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

public class BacklogApiClient {

	private final XmlRpcClient client;

	public BacklogApiClient() {
		client = new XmlRpcClient();
	}

	public void login(final String space, final String userName,
			final String password) {
		final String url = "https://" + space + ".backlog.jp/XML-RPC";

		try {
			final XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
			config.setServerURL(new URL(url));
			config.setBasicUserName(userName);
			config.setBasicPassword(password);

			client.setConfig(config);

			getUser(userName);
		} catch (final MalformedURLException e) {
			throw new RuntimeException(e);
		} catch (final XmlRpcException e) {
			throw new IllegalArgumentException("Login Failed.");
		}
	}

	/**
	 * 指定された ログインID のユーザーを取得します。
	 * 
	 * @throws XmlRpcException
	 * 
	 * @see http://www.backlog.jp/api/method###.html
	 * 
	 */
	public User getUser(final String userId) throws XmlRpcException {
		final Object[] params = new Object[] { userId };

		final Object result = client.execute(Method.GET_USER.getName(), params);
		final User user = ConvertUtil.responseToUser(result);

		return user;
	}

	/**
	 * プロジェクトキーを指定して、プロジェクトを取得します。
	 * 
	 * @throws XmlRpcException
	 * 
	 * @see http://www.backlog.jp/api/method1_2.html
	 * 
	 */
	public Project getProject(final String key) throws XmlRpcException {
		final Object[] params = new Object[] { key };

		final Object result = client.execute(Method.GET_PROJECT.getName(),
				params);
		final Project project = ConvertUtil.responseToProject(result);

		return project;
	}

	/**
	 * 課題を追加します。追加に成功した場合は、追加された課題が返ります。
	 * 
	 * @throws XmlRpcException
	 * 
	 * @see http://www.backlog.jp/api/method4_1.html
	 * 
	 */
	public Issue createIssue(final int projectId, final Issue newIssue)
			throws XmlRpcException {
		final Map<String, Object> request = ConvertUtil
				.issueToRequest(newIssue);
		request.put("projectId", projectId);
		final Object[] params = new Object[] { request };

		final Object result = client.execute(Method.CREATE_ISSUE.getName(),
				params);
		final Issue issue = ConvertUtil.responseToIssue(result);

		return issue;
	}

	/**
	 * プロジェクトの参加メンバーを返します。
	 * 
	 * @throws XmlRpcException
	 * 
	 * @see http://www.backlog.jp/api/method2_2.html
	 * 
	 */
	public Collection<User> getUsers(final int projectId)
			throws XmlRpcException {
		final Object[] params = new Object[] { projectId };

		final Object result = client
				.execute(Method.GET_USERS.getName(), params);
		final Collection<User> users = ConvertUtil.responseToUsers(result);

		return users;
	}

}

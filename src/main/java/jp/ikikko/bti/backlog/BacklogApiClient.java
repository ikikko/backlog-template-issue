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

	private XmlRpcClient client;

	public BacklogApiClient() {
		client = new XmlRpcClient();
	}

	public void login(String space, String userName, String password) {
		String url = "https://" + space + ".backlog.jp/XML-RPC";

		try {
			XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
			config.setServerURL(new URL(url));
			config.setBasicUserName(userName);
			config.setBasicPassword(password);

			client.setConfig(config);

			getUser(userName);
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		} catch (XmlRpcException e) {
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
	public User getUser(String userId) throws XmlRpcException {
		Object[] params = new Object[] { userId };

		Object result = client.execute(Method.GET_USER.getName(), params);
		User user = ConvertUtil.responseToUser(result);

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
	public Project getProject(String key) throws XmlRpcException {
		Object[] params = new Object[] { key };

		Object result = client.execute(Method.GET_PROJECT.getName(), params);
		Project project = ConvertUtil.responseToProject(result);

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
	public Issue createIssue(int projectId, Issue newIssue)
			throws XmlRpcException {
		Map<String, Object> request = ConvertUtil.issueToRequest(newIssue);
		request.put("projectId", projectId);
		Object[] params = new Object[] { request };

		Object result = client.execute(Method.CREATE_ISSUE.getName(), params);
		Issue issue = ConvertUtil.responseToIssue(result);

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
	public Collection<User> getUsers(int projectId) throws XmlRpcException {
		Object[] params = new Object[] { projectId };

		Object result = client.execute(Method.GET_USERS.getName(), params);
		Collection<User> users = ConvertUtil.responseToUsers(result);

		return users;
	}

}

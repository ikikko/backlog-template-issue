package jp.ikikko.bti.backlogapi;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import jp.ikikko.bti.backlogapi.util.ConvertUtil;
import jp.ikikko.bti.entity.Issue;
import jp.ikikko.bti.entity.Project;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

public class BacklogApiClient {

	private XmlRpcClient client;

	public BacklogApiClient(String space, String userName, String password) {
		String url = "https://" + space + ".backlog.jp/XML-RPC";

		try {
			XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
			config.setServerURL(new URL(url));
			config.setBasicUserName(userName);
			config.setBasicPassword(password);

			client = new XmlRpcClient();
			client.setConfig(config);
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
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
}

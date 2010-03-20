package jp.ikikko.bti.backlogapi;

import java.net.MalformedURLException;
import java.net.URL;

import jp.ikikko.bti.backlogapi.util.ConvertUtil;

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
		Project project = ConvertUtil.toProject(result);

		return project;
	}

	/**
	 * 課題を追加します。
	 * 
	 * @throws XmlRpcException
	 * 
	 * @see http://www.backlog.jp/api/method4_1.html
	 * 
	 */
	public boolean createIssue(int projectId, Issue issue)
			throws XmlRpcException {
		Object[] params = new Object[] { projectId, issue };

		// TODO 実装
		// client.execute(Method.CREATE_ISSUE.getName(), params);

		return false;
	}

}

package jp.ikikko.bti.backlog;

import java.util.Collection;

import jp.ikikko.bti.entity.Project;
import jp.ikikko.bti.entity.User;

import org.apache.xmlrpc.XmlRpcException;

public class BacklogDataRegistry {

	private Collection<User> registeredUsers;

	public BacklogDataRegistry(BacklogApiClient client, String key)
			throws XmlRpcException {
		Project project = client.getProject(key);

		registeredUsers = client.getUsers(project.getId());
	}

	/**
	 * Backlog に登録されているユーザのうち、userName と一致するユーザを取得する。
	 * 
	 * @return 一致する {@link User} が見つかった場合はその {@link User} 、 見つからなかった場合は null
	 */
	public User getRegisteredUser(String userName) {
		for (User user : registeredUsers) {
			if (user.getName().equals(userName)) {
				return user;
			}
		}

		return null;
	}

	Collection<User> getRegisteredUsers() {
		return registeredUsers;
	}

}
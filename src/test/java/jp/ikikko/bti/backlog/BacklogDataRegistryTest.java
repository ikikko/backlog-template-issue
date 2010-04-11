package jp.ikikko.bti.backlog;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.Collection;

import jp.ikikko.bti.BaseBtiTest;
import jp.ikikko.bti.entity.User;

import org.junit.Before;
import org.junit.Test;

public class BacklogDataRegistryTest extends BaseBtiTest {

	private static final String DUMMY_USER_NAME = "Dummy!";

	private BacklogDataRegistry registry;

	private BacklogApiClient client;

	@Before
	public void setUp() throws Exception {
		client = new BacklogApiClient();
		client.login(BACKLOG_SPACE, BACKLOG_USERNAME, BACKLOG_PASSWORD);

		registry = new BacklogDataRegistry(client, BACKLOG_PROJECT_KEY);
	}

	@Test
	public void コンストラクタ() throws Exception {
		Collection<User> users = registry.getRegisteredUsers();

		assertThat(users.size(), is(not(0)));
		for (User user : users) {
			assertThat(user, is(notNullValue()));
		}
	}

	@Test
	public void getRegisteredUserで成功() throws Exception {
		User actual = registry.getRegisteredUser(BACKLOG_USER_NAME);

		assertThat(actual, is(notNullValue()));
	}

	@Test
	public void getRegisteredUserで失敗() throws Exception {
		User actual = registry.getRegisteredUser(DUMMY_USER_NAME);

		assertThat(actual, is(nullValue()));
	}

}

package jp.ikikko.bti.backlog;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.Collection;

import jp.ikikko.bti.BaseBtiTest;
import jp.ikikko.bti.backlog.BacklogApiClient;
import jp.ikikko.bti.entity.Issue;
import jp.ikikko.bti.entity.Project;
import jp.ikikko.bti.entity.User;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class BacklogApiClientTest extends BaseBtiTest {

	private BacklogApiClient client;

	@Before
	public void setUp() throws Exception {
		client = new BacklogApiClient();
		client.login(BACKLOG_SPACE, BACKLOG_USERNAME, BACKLOG_PASSWORD);
	}

	@Test(expected = IllegalArgumentException.class)
	public void login失敗() throws Exception {
		client.login("fail", "fail", "fail");
	}

	@Test
	public void getProject() throws Exception {
		Project project = client.getProject(BACKLOG_PROJECT_KEY);

		assertThat(project, is(notNullValue()));
	}

	@Test
	public void getUser() throws Exception {
		User user = client.getUser(BACKLOG_USERNAME);

		assertThat(user, is(notNullValue()));
	}

	@Ignore("更新系APIは、普段のユニットテストでは実行しない")
	@Test
	public void createIssue() throws Exception {
		Issue newIssue = new Issue();
		newIssue.setSummary("Backlog API テスト登録");

		Issue issue = client.createIssue(BACKLOG_PROJECT_ID, newIssue);

		assertThat(issue, is(notNullValue()));
	}

	@Test
	public void getUsers() throws Exception {
		Collection<User> users = client.getUsers(BACKLOG_PROJECT_ID);

		assertThat(users, is(notNullValue()));
		for (User user : users) {
			assertThat(user, is(notNullValue()));
		}
	}

}

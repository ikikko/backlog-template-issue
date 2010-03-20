package jp.ikikko.bti.backlogapi;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class BacklogApiClientTest {

	private static final String SPACE = "demo";
	private static final String USERNAME = "demo";
	private static final String PASSWORD = "demo";
	private static final String PROJECT_KEY = "STWK";

	private BacklogApiClient client;

	@Before
	public void setUp() {
		client = new BacklogApiClient(SPACE, USERNAME, PASSWORD);
	}

	@Test
	public void getProject() throws Exception {
		Project project = client.getProject(PROJECT_KEY);

		assertThat(project, is(notNullValue()));
	}

	@Ignore
	@Test
	public void createIssue() throws Exception {
		fail();
	}

}

package jp.ikikko.bti.backlogapi;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.InputStream;
import java.util.Properties;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class BacklogApiClientTest {

	private static String SPACE;
	private static String USERNAME;
	private static String PASSWORD;
	private static int PROJECT_ID;
	private static String PROJECT_KEY;

	private BacklogApiClient client;

	@BeforeClass
	public static void setUpClass() throws Exception {
		Properties properties = new Properties();

		// プロパティファイル（テンプレート）の読込
		InputStream propertyFileTemplate = BacklogApiClientTest.class
				.getResourceAsStream("template.backlogApiClient.properties");
		properties.load(propertyFileTemplate);
		// 存在するならば、プロパティファイル（ユーザ固有）の読込
		InputStream propertyFile = BacklogApiClientTest.class
				.getResourceAsStream("backlogApiClient.properties");
		if (propertyFile != null) {
			properties.load(propertyFile);
		}

		SPACE = properties.getProperty("SPACE");
		USERNAME = properties.getProperty("USERNAME");
		PASSWORD = properties.getProperty("PASSWORD");
		PROJECT_ID = Integer.valueOf(properties.getProperty("PROJECT_ID"));
		PROJECT_KEY = properties.getProperty("PROJECT_KEY");
	}

	@Before
	public void setUp() throws Exception {
		client = new BacklogApiClient(SPACE, USERNAME, PASSWORD);
	}

	@Test
	public void getProject() throws Exception {
		Project project = client.getProject(PROJECT_KEY);

		assertThat(project, is(notNullValue()));
	}

	@Ignore("更新系APIは、普段のユニットテストでは実行しない")
	@Test
	public void createIssue() throws Exception {
		Issue newIssue = new Issue();
		newIssue.setSummary("Backlog API テスト登録");

		Issue issue = client.createIssue(PROJECT_ID, newIssue);

		assertThat(issue, is(notNullValue()));
	}

}

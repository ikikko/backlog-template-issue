package jp.ikikko.bti.gdata;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.InputStream;
import java.net.URL;
import java.util.Collection;
import java.util.Properties;

import jp.ikikko.bti.entity.Issue;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.gdata.util.AuthenticationException;

public class GdataServiceTest {

	private static String USERNAME;
	private static String PASSWORD;
	private static URL SPREADSHEET_URL;

	private GdataService service;

	@BeforeClass
	public static void setUpClass() throws Exception {
		Properties properties = new Properties();

		InputStream propertyFile = GdataServiceTest.class
				.getResourceAsStream("gdataService.properties");
		properties.load(propertyFile);

		USERNAME = properties.getProperty("USERNAME");
		PASSWORD = properties.getProperty("PASSWORD");
		SPREADSHEET_URL = new URL(properties.getProperty("SPREADSHEET_URL"));
	}

	@Before
	public void setUp() throws Exception {
		service = new GdataService();
		service.login(USERNAME, PASSWORD);
	}

	@Test(expected = AuthenticationException.class)
	public void login失敗() throws Exception {
		service.login("fail", "fail");
	}

	@Test
	public void getTemplateIssues() throws Exception {
		service.login(USERNAME, PASSWORD);
		Collection<Issue> issues = service.getTemplateIssues(SPREADSHEET_URL);

		for (Issue issue : issues) {
			assertThat(issue.getSummary(), is(notNullValue()));
			assertThat(issue.getDescription(), is(notNullValue()));
			assertThat(issue.getStartDate(), is(notNullValue()));
			assertThat(issue.getDueDate(), is(notNullValue()));
			assertThat(issue.getEstimatedHours(), is(notNullValue()));
			assertThat(issue.getActualHours(), is(notNullValue()));
			assertThat(issue.getIssueType(), is(notNullValue()));
			assertThat(issue.getComponents()[0], is(notNullValue()));
			assertThat(issue.getAffectsVersions()[0], is(notNullValue()));
			assertThat(issue.getMilestoneVersions()[0], is(notNullValue()));
			assertThat(issue.getPriority(), is(notNullValue()));
			assertThat(issue.getAssignerUser(), is(notNullValue()));

			System.out.println(issue);
		}
	}

	@Test
	public void getSpreadsheetKey() throws Exception {
		String key = "0Ajq41fTDA49TdDVIUlRTeldUV2dVNFdBbTNONmtYZ3c";
		URL url = new URL("http://spreadsheets.google.com/ccc?key=" + key
				+ "&hl=ja");

		String actual = service.getSpreadsheetKey(url);
		assertThat(actual, is(key));
	}

}

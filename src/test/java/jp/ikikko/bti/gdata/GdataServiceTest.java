package jp.ikikko.bti.gdata;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.net.URL;
import java.util.Collection;

import jp.ikikko.bti.BaseBtiTest;
import jp.ikikko.bti.backlog.BacklogApiClient;
import jp.ikikko.bti.backlog.BacklogDataRegistry;
import jp.ikikko.bti.entity.Issue;

import org.junit.Before;
import org.junit.Test;

import com.google.gdata.util.AuthenticationException;

public class GdataServiceTest extends BaseBtiTest {

	private GdataService service;

	@Before
	public void setUp() throws Exception {
		service = new GdataService();
		service.login(GDATA_USERNAME, GDATA_PASSWORD);
	}

	@Test(expected = AuthenticationException.class)
	public void login失敗() throws Exception {
		service.login("fail", "fail");
	}

	@Test
	public void getTemplateIssues() throws Exception {
		service.login(GDATA_USERNAME, GDATA_PASSWORD);

		BacklogApiClient backlogApiClient = new BacklogApiClient();
		backlogApiClient.login(BACKLOG_SPACE, BACKLOG_USERNAME,
				BACKLOG_PASSWORD);
		BacklogDataRegistry backlogDataRegistry = new BacklogDataRegistry(
				backlogApiClient, BACKLOG_PROJECT_KEY);
		Collection<Issue> issues = service.getTemplateIssues(
				GDATA_SPREADSHEET_URL, backlogDataRegistry);

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

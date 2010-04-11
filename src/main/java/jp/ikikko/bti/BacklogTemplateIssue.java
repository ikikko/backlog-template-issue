package jp.ikikko.bti;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;

import jp.ikikko.bti.backlog.BacklogApiClient;
import jp.ikikko.bti.backlog.BacklogDataRegistry;
import jp.ikikko.bti.entity.Issue;
import jp.ikikko.bti.entity.Project;
import jp.ikikko.bti.gdata.GdataService;

import org.apache.xmlrpc.XmlRpcException;

import com.google.gdata.util.ServiceException;

public class BacklogTemplateIssue {

	private final GdataService gdataService = new GdataService();

	private final BacklogApiClient backlogApiClient = new BacklogApiClient();

	public Collection<Issue> createIssue(final String googleId,
			final String googlePassword, final String googleUrl,
			final String backlogSpace, final String backlogId,
			final String backlogPassword, final String backlogProject)
			throws MalformedURLException, ServiceException, IOException,
			XmlRpcException {

		// Googleドキュメント, Backlogにlogin
		gdataService.login(googleId, new String(googlePassword));
		backlogApiClient.login(backlogSpace, backlogId, new String(
				backlogPassword));

		// Googleドキュメントから課題情報取得
		final Project project = backlogApiClient.getProject(backlogProject);
		final BacklogDataRegistry backlogDataRegistry = new BacklogDataRegistry(
				backlogApiClient, backlogProject);
		final Collection<Issue> issues = gdataService.getTemplateIssues(
				new URL(googleUrl), backlogDataRegistry);

		// Backlogに課題登録
		final Collection<Issue> newIssues = new ArrayList<Issue>();
		for (final Issue issue : issues) {
			newIssues.add(backlogApiClient.createIssue(project.getId(), issue));
		}

		return newIssues;
	}

}

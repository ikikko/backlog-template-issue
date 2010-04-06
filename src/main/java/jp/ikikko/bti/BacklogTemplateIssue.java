package jp.ikikko.bti;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;

import jp.ikikko.bti.backlogapi.BacklogApiClient;
import jp.ikikko.bti.entity.Issue;
import jp.ikikko.bti.entity.Project;
import jp.ikikko.bti.gdata.GdataService;

import org.apache.xmlrpc.XmlRpcException;

import com.google.gdata.util.ServiceException;

public class BacklogTemplateIssue {

	private GdataService gdataService = new GdataService();

	private BacklogApiClient backlogApiClient = new BacklogApiClient();

	public Collection<Issue> createIssue(String googleId,
			String googlePassword, String googleUrl, String backlogSpace,
			String backlogId, String backlogPassword, String backlogProject)
			throws MalformedURLException, ServiceException, IOException,
			XmlRpcException {

		gdataService.login(googleId, new String(googlePassword));
		Collection<Issue> issues = gdataService.getTemplateIssues(new URL(
				googleUrl));

		backlogApiClient.login(backlogSpace, backlogId, new String(
				backlogPassword));

		Collection<Issue> newIssues = new ArrayList<Issue>();
		Project project = backlogApiClient.getProject(backlogProject);
		for (Issue issue : issues) {
			newIssues.add(backlogApiClient.createIssue(project.getId(), issue));
		}

		return newIssues;
	}

}

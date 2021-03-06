package jp.ikikko.bti.gdata;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.ikikko.bti.backlog.BacklogDataRegistry;
import jp.ikikko.bti.entity.Issue;
import jp.ikikko.bti.entity.User;

import org.apache.commons.lang.StringUtils;

import com.google.gdata.client.spreadsheet.SpreadsheetService;
import com.google.gdata.data.spreadsheet.CustomElementCollection;
import com.google.gdata.data.spreadsheet.ListEntry;
import com.google.gdata.data.spreadsheet.ListFeed;
import com.google.gdata.data.spreadsheet.SpreadsheetEntry;
import com.google.gdata.data.spreadsheet.WorksheetEntry;
import com.google.gdata.util.AuthenticationException;
import com.google.gdata.util.ServiceException;

public class GdataService {

	private static final String SERVICE_COMPANY = "ikikko";
	private static final String SERVICE_APPLICATION = "backlogTemplateIssue";
	private static final String SERVICE_VERSION = "1.0";

	private static DateFormat format = new SimpleDateFormat("yyyy/MM/dd");

	private final SpreadsheetService service;

	public GdataService() {
		final String serviceName = StringUtils.join(new Object[] {
				SERVICE_COMPANY, SERVICE_APPLICATION, SERVICE_VERSION }, "-");

		service = new SpreadsheetService(serviceName);
	}

	/**
	 * ログインします。
	 */
	public void login(final String username, final String password)
			throws AuthenticationException {
		service.setUserCredentials(username, password);
	}

	/**
	 * テンプレートの課題情報を取得します。
	 */
	public Collection<Issue> getTemplateIssues(final URL url,
			final BacklogDataRegistry backlogDataRegistry)
			throws ServiceException, IOException {

		final String key = getSpreadsheetKey(url);
		final URL entryUrl = new URL(
				"http://spreadsheets.google.com/feeds/spreadsheets/" + key);

		final SpreadsheetEntry spreadsheet = service.getEntry(entryUrl,
				SpreadsheetEntry.class);
		final WorksheetEntry worksheet = spreadsheet.getDefaultWorksheet();
		final ListFeed listFeed = service.getFeed(worksheet.getListFeedUrl(),
				ListFeed.class);

		final List<Issue> issues = new ArrayList<Issue>();
		for (final ListEntry list : listFeed.getEntries()) {
			issues.add(createIssueFromSingleList(list, backlogDataRegistry));
		}

		return issues;
	}

	/**
	 * スプレッドシートの Key を取得します。
	 */
	String getSpreadsheetKey(final URL url) {
		final Map<String, String> map = new HashMap<String, String>();

		for (final String param : url.getQuery().split("&")) {
			final String key = param.split("=")[0];
			final String value = param.split("=")[1];
			map.put(key, value);
		}

		return map.get("key");
	}

	/**
	 * スプレッドシートの1行から、{@link Issue} を作成します。
	 */
	Issue createIssueFromSingleList(final ListEntry list,
			final BacklogDataRegistry backlogDataRegistry) {
		final Issue issue = new Issue();
		final CustomElementCollection elements = list.getCustomElements();

		issue.setSummary(elements.getValue("件名"));
		issue.setDescription(elements.getValue("詳細"));

		if (elements.getValue("開始日") != null) {
			try {
				issue.setStartDate(format.parse(elements.getValue("開始日")));
			} catch (final ParseException e) {
			}
		}
		if (elements.getValue("期限日") != null) {
			try {
				issue.setDueDate(format.parse(elements.getValue("期限日")));
			} catch (final ParseException e) {
			}
		}

		if (elements.getValue("予定時間") != null) {
			issue.setEstimatedHours(Double.valueOf(elements.getValue("予定時間")));
		}
		if (elements.getValue("実績時間") != null) {
			issue.setActualHours(Double.valueOf(elements.getValue("実績時間")));
		}

		issue.setIssueType(elements.getValue("種別名"));
		issue.setComponents(new String[] { elements.getValue("カテゴリ名") });
		issue
				.setAffectsVersions(new String[] { elements
						.getValue("発生バージョン名") });
		issue
				.setMilestoneVersions(new String[] { elements
						.getValue("マイルストーン名") });

		if (elements.getValue("優先度ID") != null) {
			issue.setPriority(Integer.valueOf(elements.getValue("優先度ID")));
		}
		if (elements.getValue("担当者ユーザ名") != null) {
			final User registeredUser = backlogDataRegistry
					.getRegisteredUser(elements.getValue("担当者ユーザ名"));
			if (registeredUser != null) {
				issue.setAssignerUser(registeredUser);
			}
		}

		return issue;
	}
}

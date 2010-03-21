package jp.ikikko.bti.gdata;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import jp.ikikko.bti.entity.Issue;

import org.apache.commons.lang.StringUtils;

import com.google.gdata.client.spreadsheet.SpreadsheetService;
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

	private SpreadsheetService service;

	public GdataService() {
		String serviceName = StringUtils.join(new Object[] { SERVICE_COMPANY,
				SERVICE_APPLICATION, SERVICE_VERSION }, "-");

		service = new SpreadsheetService(serviceName);
	}

	/**
	 * ログインします。
	 */
	public void login(String username, String password)
			throws AuthenticationException {
		service.setUserCredentials(username, password);
	}

	/**
	 * テンプレートの課題情報を取得します。
	 */
	public Issue getTemplateIssue(URL url) throws ServiceException, IOException {
		String key = getSpreadsheetKey(url);
		URL entryUrl = new URL(
				"http://spreadsheets.google.com/feeds/spreadsheets/" + key);

		SpreadsheetEntry spreadsheet = service.getEntry(entryUrl,
				SpreadsheetEntry.class);
		WorksheetEntry worksheet = spreadsheet.getDefaultWorksheet();
		ListFeed listFeed = service.getFeed(worksheet.getListFeedUrl(),
				ListFeed.class);

		Issue issue = new Issue();
		for (ListEntry list : listFeed.getEntries()) {
			// TODO 実装
			System.out.println(list.getCustomElements().getValue("件名"));
			System.out.println(list.getCustomElements().getValue("詳細"));
			System.out.println();
		}

		return issue;
	}

	/**
	 * スプレッドシートの Key を取得します。
	 */
	String getSpreadsheetKey(URL url) {
		Map<String, String> map = new HashMap<String, String>();

		for (String param : url.getQuery().split("&")) {
			String key = param.split("=")[0];
			String value = param.split("=")[1];
			map.put(key, value);
		}

		return map.get("key");
	}
}

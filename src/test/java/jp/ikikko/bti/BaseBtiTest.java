package jp.ikikko.bti;

import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import jp.ikikko.bti.backlog.BacklogApiClientTest;
import jp.ikikko.bti.gdata.GdataServiceTest;

import org.junit.BeforeClass;

public abstract class BaseBtiTest {

	protected static String BACKLOG_SPACE;
	protected static String BACKLOG_USERNAME;
	protected static String BACKLOG_PASSWORD;
	protected static int BACKLOG_PROJECT_ID;
	protected static String BACKLOG_PROJECT_KEY;
	protected static String BACKLOG_USER_NAME;

	protected static String GDATA_USERNAME;
	protected static String GDATA_PASSWORD;
	protected static URL GDATA_SPREADSHEET_URL;

	@BeforeClass
	public static void setUpClass() throws Exception {
		loadBacklogProperties();
		loadGdataProperties();
	}

	protected static void loadBacklogProperties() throws Exception {
		final Properties properties = new Properties();

		// プロパティファイル（テンプレート）の読込
		final InputStream propertyFileTemplate = BacklogApiClientTest.class
				.getResourceAsStream("template.backlogApiClient.properties");
		properties.load(propertyFileTemplate);
		// 存在するならば、プロパティファイル（ユーザ固有）の読込
		final InputStream propertyFile = BacklogApiClientTest.class
				.getResourceAsStream("backlogApiClient.properties");
		if (propertyFile != null) {
			properties.load(propertyFile);
		}

		BACKLOG_SPACE = properties.getProperty("SPACE");
		BACKLOG_USERNAME = properties.getProperty("USERNAME");
		BACKLOG_PASSWORD = properties.getProperty("PASSWORD");
		BACKLOG_PROJECT_ID = Integer.valueOf(properties
				.getProperty("PROJECT_ID"));
		BACKLOG_PROJECT_KEY = properties.getProperty("PROJECT_KEY");
		BACKLOG_USER_NAME = properties.getProperty("USER_NAME");
	}

	protected static void loadGdataProperties() throws Exception {
		final Properties properties = new Properties();

		final InputStream propertyFile = GdataServiceTest.class
				.getResourceAsStream("gdataService.properties");
		properties.load(propertyFile);

		GDATA_USERNAME = properties.getProperty("USERNAME");
		GDATA_PASSWORD = properties.getProperty("PASSWORD");
		GDATA_SPREADSHEET_URL = new URL(properties
				.getProperty("SPREADSHEET_URL"));
	}

}

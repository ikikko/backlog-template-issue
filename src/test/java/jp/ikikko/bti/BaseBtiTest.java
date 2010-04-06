package jp.ikikko.bti;

import java.io.InputStream;
import java.util.Properties;

import jp.ikikko.bti.backlogapi.BacklogApiClientTest;

import org.junit.BeforeClass;

public abstract class BaseBtiTest {

	protected static String SPACE;
	protected static String USERNAME;
	protected static String PASSWORD;
	protected static int PROJECT_ID;
	protected static String PROJECT_KEY;
	protected static int USER_ID;

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
		USER_ID = Integer.valueOf(properties.getProperty("USER_ID"));
	}

}

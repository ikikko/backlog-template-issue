package jp.ikikko.bti.backlogapi.util;

import java.util.Map;

import jp.ikikko.bti.backlogapi.Project;

public class ConvertUtil {

	private ConvertUtil() {
	}

	public static Project toProject(Object object) {
		if (object == null) {
			return null;
		}

		@SuppressWarnings("unchecked")
		Map<String, Object> map = (Map<String, Object>) object;
		if (map.isEmpty()) {
			return null;
		}

		int id = ((Integer) map.get("id")).intValue();
		String name = (String) map.get("name");
		String key = (String) map.get("key");
		String url = (String) map.get("url");

		return new Project(id, name, key, url);
	}
}

package jp.ikikko.bti.backlogapi.util;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import jp.ikikko.bti.entity.Issue;
import jp.ikikko.bti.entity.Project;
import jp.ikikko.bti.entity.User;

/**
 * @author ikikko
 * 
 */
public class ConvertUtil {

	private ConvertUtil() {
	}

	/**
	 * XML-RPC のレスポンスを {@link Project} に変換します。
	 */
	@SuppressWarnings("unchecked")
	public static Project responseToProject(Object response) {
		if (response == null) {
			return null;
		}

		Map<String, Object> map = (Map<String, Object>) response;
		if (map.isEmpty()) {
			return null;
		}

		int id = ((Integer) map.get("id")).intValue();
		String name = (String) map.get("name");
		String key = (String) map.get("key");
		String url = (String) map.get("url");

		return new Project(id, name, key, url);
	}

	/**
	 * XML-RPC のレスポンスを {@link Issue} に変換します。
	 */
	@SuppressWarnings("unchecked")
	public static Issue responseToIssue(Object response) {
		if (response == null) {
			return null;
		}

		Map<String, Object> map = (Map<String, Object>) response;
		if (map.isEmpty()) {
			return null;
		}

		String key = (String) map.get("key");
		String summary = (String) map.get("summary");
		String description = (String) map.get("description");
		String url = (String) map.get("url");

		Date startDate = responseToDate(map.get("start_date"));
		Date dueDate = responseToDate(map.get("due_date"));
		Double estimatedHours = (Double) map.get("estimated_hours");
		Double actualHours = (Double) map.get("actual_hours");

		String issueType = null;
		Map<String, Object> issueTypeObj = (Map<String, Object>) map
				.get("issueType");
		if (issueTypeObj != null) {
			issueType = (String) issueTypeObj.get("name");
		}

		String[] components = null;
		Object[] componentObjs = (Object[]) map.get("components");
		if (componentObjs != null) {
			components = new String[componentObjs.length];
			for (int i = 0; i < componentObjs.length; i++) {
				Map<String, Object> componentObj = (Map<String, Object>) componentObjs[i];
				components[i] = (String) componentObj.get("name");
			}
		}
		String[] affectsVersions = null;
		Object[] affectsVersionObjs = (Object[]) map.get("versions");
		if (affectsVersionObjs != null) {
			affectsVersions = new String[affectsVersionObjs.length];
			for (int i = 0; i < affectsVersionObjs.length; i++) {
				Map<String, Object> affectsVersionObj = (Map<String, Object>) affectsVersionObjs[i];
				affectsVersions[i] = (String) affectsVersionObj.get("name");
			}
		}
		String[] milestoneVersions = null;
		Object[] milestoneObjs = (Object[]) map.get("milestones");
		if (milestoneObjs != null) {
			milestoneVersions = new String[milestoneObjs.length];
			for (int i = 0; i < milestoneObjs.length; i++) {
				Map<String, Object> milestoneObj = (Map<String, Object>) milestoneObjs[i];
				milestoneVersions[i] = (String) milestoneObj.get("name");
			}
		}

		int priority = 0;
		Map<String, Object> priorityObj = (Map<String, Object>) map
				.get("priority");
		if (priorityObj != null) {
			priority = (Integer) priorityObj.get("id");
		}
		String resolution = null;
		Map<String, Object> resolutionObj = (Map<String, Object>) map
				.get("resolution");
		if (resolutionObj != null) {
			resolution = (String) resolutionObj.get("name");
		}
		String status = null;
		Map<String, Object> statusObj = (Map<String, Object>) map.get("status");
		if (statusObj != null) {
			status = (String) statusObj.get("name");
		}

		User assignerUser = responseToUser(map.get("assigner"));
		User createdUser = responseToUser(map.get("created_user"));

		Date createdOn = responseToDatetime(map.get("created_on"));
		Date updatedOn = responseToDatetime(map.get("updated_on"));

		return new Issue(key, summary, description, url, startDate, dueDate,
				estimatedHours, actualHours, issueType, components,
				affectsVersions, milestoneVersions, priority, resolution,
				status, assignerUser, createdUser, createdOn, updatedOn);
	}

	/**
	 * XML-RPC のレスポンスを {@link Date} に変換します。
	 */
	public static Date responseToDate(Object value) {
		if (value == null || value.equals("")) {
			return null;
		}
		if (!(value instanceof String)) {
			throw new IllegalArgumentException("illegal date type " + value);
		}
		String dateString = (String) value;
		if (dateString.length() != 8) {
			throw new IllegalArgumentException("illegal date format " + value);
		}

		try {
			Date date = DateUtil.parseYyyyMMddHHmmssSSS(dateString);
			return date;
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	/**
	 * XML-RPC のレスポンスを {@link Date} に変換します。
	 */
	public static Date responseToDatetime(Object value) {
		if (value == null) {
			return null;
		}
		if (!(value instanceof String)) {
			throw new IllegalArgumentException("illegal date type " + value);
		}
		String dateString = (String) value;
		if (dateString.length() != 14) {
			throw new IllegalArgumentException("illegal date format " + value);
		}

		try {
			Date date = DateUtil.parseYyyyMMddHHmmssSSS(StringUtil.fill(
					dateString, 16, '0'));
			return date;
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	/**
	 * XML-RPC のレスポンスを {@link User} に変換します。
	 */
	@SuppressWarnings("unchecked")
	public static User responseToUser(Object value) {
		if (value == null) {
			return null;
		}

		Map<String, Object> map = (Map<String, Object>) value;
		if (map.isEmpty()) {
			return null;
		}

		String name = (String) map.get("name");
		int id = ((Integer) map.get("id")).intValue();
		Date updatedOn = responseToDatetime(map.get("updated_on"));
		User user = new User(name, id, updatedOn);

		return user;
	}

	/**
	 * {@link Issue} を XML-RPC のリクエストに変換します。
	 */
	public static Map<String, Object> issueToRequest(Issue issue) {
		Map<String, Object> request = new HashMap<String, Object>();

		if (issue.getSummary() != null) {
			request.put("summary", issue.getSummary());
		}
		if (issue.getDescription() != null) {
			request.put("description", issue.getDescription());
		}

		if (issue.getStartDate() != null) {
			request.put("start_date", DateUtil.formatYyyyMMdd(issue
					.getStartDate()));
		}
		if (issue.getDueDate() != null) {
			request
					.put("due_date", DateUtil
							.formatYyyyMMdd(issue.getDueDate()));
		}
		if (issue.getEstimatedHours() != null) {
			request.put("estimated_hours", issue.getEstimatedHours());
		}
		if (issue.getActualHours() != null) {
			request.put("actual_hours", issue.getActualHours());
		}

		if (issue.getIssueType() != null) {
			request.put("issueType", issue.getIssueType());
		}

		if (issue.getComponents() != null) {
			request.put("component", Arrays.asList(issue.getComponents()));
		}
		if (issue.getAffectsVersions() != null) {
			request.put("version", Arrays.asList(issue.getAffectsVersions()));
		}
		if (issue.getMilestoneVersions() != null) {
			request.put("milestone", Arrays
					.asList(issue.getMilestoneVersions()));
		}

		request.put("priority", issue.getPriority());
		request.put("assignerId", issue.getAssignerUser().getId());

		return request;
	}

}

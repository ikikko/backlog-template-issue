package jp.ikikko.bti.entity;

import java.util.Arrays;
import java.util.Date;

public class Issue {

	public Issue() {
	}

	public Issue(final String key, final String summary,
			final String description, final String url, final Date startDate,
			final Date dueDate, final Double estimatedHours,
			final Double actualHours, final String issueType,
			final String[] components, final String[] affectsVersions,
			final String[] milestoneVersions, final int priority,
			final String resolution, final String status,
			final User assignerUser, final User createdUser,
			final Date createdOn, final Date updatedOn) {
		this.key = key;
		this.summary = summary;
		this.description = description;
		this.url = url;
		this.startDate = startDate;
		this.dueDate = dueDate;
		this.estimatedHours = estimatedHours;
		this.actualHours = actualHours;
		this.issueType = issueType;
		this.components = components;
		this.affectsVersions = affectsVersions;
		this.milestoneVersions = milestoneVersions;
		this.priority = priority;
		this.resolution = resolution;
		this.status = status;
		this.assignerUser = assignerUser;
		this.createdUser = createdUser;
		this.createdOn = createdOn;
		this.updatedOn = updatedOn;
	}

	/** 課題キー */
	private String key;

	/** 課題の件名 */
	private String summary;

	/** 課題の詳細 */
	private String description;

	/** 課題のURL */
	private String url;

	/** 開始日 */
	private Date startDate;

	/** 期限日 */
	private Date dueDate;

	/** 予定時間(hour) */
	private Double estimatedHours;

	/** 実績時間(hour) */
	private Double actualHours;

	/** 種別 */
	private String issueType;

	/** カテゴリ */
	private String[] components;

	/** 発生バージョン */
	private String[] affectsVersions;

	/** マイルストーン */
	private String[] milestoneVersions;

	/** 優先度 */
	private int priority;

	/** 完了理由 */
	private String resolution;

	/** 状態 */
	private String status;

	/** 担当者 */
	private User assignerUser;

	/** 登録者 */
	private User createdUser;

	/** 登録日時 */
	private Date createdOn;

	/** 最終更新日時 */
	private Date updatedOn;

	public String getKey() {
		return key;
	}

	public void setKey(final String key) {
		this.key = key;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(final String summary) {
		this.summary = summary;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setUrl(final String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public void setStartDate(final Date startDate) {
		this.startDate = startDate;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(final Date dueDate) {
		this.dueDate = dueDate;
	}

	public Double getEstimatedHours() {
		return estimatedHours;
	}

	public void setEstimatedHours(final Double estimatedHours) {
		this.estimatedHours = estimatedHours;
	}

	public Double getActualHours() {
		return actualHours;
	}

	public void setActualHours(final Double actualHours) {
		this.actualHours = actualHours;
	}

	public String getIssueType() {
		return issueType;
	}

	public void setIssueType(final String issueType) {
		this.issueType = issueType;
	}

	public String[] getComponents() {
		return components;
	}

	public void setComponents(final String[] components) {
		this.components = components;
	}

	public String[] getAffectsVersions() {
		return affectsVersions;
	}

	public void setAffectsVersions(final String[] affectsVersions) {
		this.affectsVersions = affectsVersions;
	}

	public String[] getMilestoneVersions() {
		return milestoneVersions;
	}

	public void setMilestoneVersions(final String[] milestoneVersions) {
		this.milestoneVersions = milestoneVersions;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(final int priority) {
		this.priority = priority;
	}

	public String getResolution() {
		return resolution;
	}

	public void setResolution(final String resolution) {
		this.resolution = resolution;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(final String status) {
		this.status = status;
	}

	public User getAssignerUser() {
		return assignerUser;
	}

	public void setAssignerUser(final User assignerUser) {
		this.assignerUser = assignerUser;
	}

	public User getCreatedUser() {
		return createdUser;
	}

	public void setCreatedUser(final User createdUser) {
		this.createdUser = createdUser;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(final Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(final Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	@Override
	public String toString() {
		return "Issue [actualHours=" + actualHours + ", affectsVersions="
				+ Arrays.toString(affectsVersions) + ", assignerUser="
				+ assignerUser + ", components=" + Arrays.toString(components)
				+ ", createdOn=" + createdOn + ", createdUser=" + createdUser
				+ ", description=" + description + ", dueDate=" + dueDate
				+ ", estimatedHours=" + estimatedHours + ", issueType="
				+ issueType + ", key=" + key + ", milestoneVersions="
				+ Arrays.toString(milestoneVersions) + ", priority=" + priority
				+ ", resolution=" + resolution + ", startDate=" + startDate
				+ ", status=" + status + ", summary=" + summary
				+ ", updatedOn=" + updatedOn + ", url=" + url + "]";
	}

}

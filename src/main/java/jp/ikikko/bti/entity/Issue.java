package jp.ikikko.bti.entity;

import java.util.Date;

public class Issue {

	public Issue() {
	}

	public Issue(String key, String summary, String description, String url,
			Date startDate, Date dueDate, Double estimatedHours,
			Double actualHours, String issueType, String[] components,
			String[] affectsVersions, String[] milestoneVersions,
			String priority, String resolution, String status,
			int assignerUser, int createdUser, Date createdOn, Date updatedOn) {
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
	private String priority;

	/** 完了理由 */
	private String resolution;

	/** 状態 */
	private String status;

	/** 担当者 */
	private int assignerUser;
	// TODO User系は、Stringを受け取ってgetUsers()でintにしてやる方が親切

	/** 登録者 */
	private int createdUser;

	/** 登録日時 */
	private Date createdOn;

	/** 最終更新日時 */
	private Date updatedOn;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Double getEstimatedHours() {
		return estimatedHours;
	}

	public void setEstimatedHours(Double estimatedHours) {
		this.estimatedHours = estimatedHours;
	}

	public Double getActualHours() {
		return actualHours;
	}

	public void setActualHours(Double actualHours) {
		this.actualHours = actualHours;
	}

	public String getIssueType() {
		return issueType;
	}

	public void setIssueType(String issueType) {
		this.issueType = issueType;
	}

	public String[] getComponents() {
		return components;
	}

	public void setComponents(String[] components) {
		this.components = components;
	}

	public String[] getAffectsVersions() {
		return affectsVersions;
	}

	public void setAffectsVersions(String[] affectsVersions) {
		this.affectsVersions = affectsVersions;
	}

	public String[] getMilestoneVersions() {
		return milestoneVersions;
	}

	public void setMilestoneVersions(String[] milestoneVersions) {
		this.milestoneVersions = milestoneVersions;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getResolution() {
		return resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getAssignerUser() {
		return assignerUser;
	}

	public void setAssignerUser(int assignerUser) {
		this.assignerUser = assignerUser;
	}

	public int getCreatedUser() {
		return createdUser;
	}

	public void setCreatedUser(int createdUser) {
		this.createdUser = createdUser;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

}

package jp.ikikko.bti.backlogapi;

import java.util.Date;

public class Issue {

	Issue() {
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

	void setKey(String key) {
		this.key = key;
	}

	public String getSummary() {
		return summary;
	}

	void setSummary(String summary) {
		this.summary = summary;
	}

	public String getDescription() {
		return description;
	}

	void setDescription(String description) {
		this.description = description;
	}

	public Date getStartDate() {
		return startDate;
	}

	void setUrl(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getDueDate() {
		return dueDate;
	}

	void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Double getEstimatedHours() {
		return estimatedHours;
	}

	void setEstimatedHours(Double estimatedHours) {
		this.estimatedHours = estimatedHours;
	}

	public Double getActualHours() {
		return actualHours;
	}

	void setActualHours(Double actualHours) {
		this.actualHours = actualHours;
	}

	public String getIssueType() {
		return issueType;
	}

	void setIssueType(String issueType) {
		this.issueType = issueType;
	}

	public String[] getComponents() {
		return components;
	}

	void setComponents(String[] components) {
		this.components = components;
	}

	public String[] getAffectsVersions() {
		return affectsVersions;
	}

	void setAffectsVersions(String[] affectsVersions) {
		this.affectsVersions = affectsVersions;
	}

	public String[] getMilestoneVersions() {
		return milestoneVersions;
	}

	void setMilestoneVersions(String[] milestoneVersions) {
		this.milestoneVersions = milestoneVersions;
	}

	public String getPriority() {
		return priority;
	}

	void setPriority(String priority) {
		this.priority = priority;
	}

	public String getResolution() {
		return resolution;
	}

	void setResolution(String resolution) {
		this.resolution = resolution;
	}

	public String getStatus() {
		return status;
	}

	void setStatus(String status) {
		this.status = status;
	}

	public int getAssignerUser() {
		return assignerUser;
	}

	void setAssignerUser(int assignerUser) {
		this.assignerUser = assignerUser;
	}

	public int getCreatedUser() {
		return createdUser;
	}

	void setCreatedUser(int createdUser) {
		this.createdUser = createdUser;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

}

package jp.ikikko.bti.entity;

import java.util.Date;

public class User {

	/** ユーザ名 */
	private String name;

	/** ユーザID */
	private int id;

	/** 更新日時 */
	private Date updatedOn;

	public User() {
	}

	public User(String name, int id, Date updatedOn) {
		this.name = name;
		this.id = id;
		this.updatedOn = updatedOn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", updatedOn=" + updatedOn
				+ "]";
	}

}

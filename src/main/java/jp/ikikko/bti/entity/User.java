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

	public User(final String name, final int id, final Date updatedOn) {
		this.name = name;
		this.id = id;
		this.updatedOn = updatedOn;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(final Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", updatedOn=" + updatedOn
				+ "]";
	}

}

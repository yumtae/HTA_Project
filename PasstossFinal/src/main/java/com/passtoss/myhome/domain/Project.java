package com.passtoss.myhome.domain;

public class Project {
	private int project_id;
	private String project_name;
	private String description;
	private int admin_auth;
	private int write_auth;
	private int comment_auth;
	private int file_auth;
	private int company_id;
	private int cnt;

	public int getProject_id() {
		return project_id;
	}

	public void setProject_id(int project_id) {
		this.project_id = project_id;
	}

	public String getProject_name() {
		return project_name;
	}

	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getAdmin_auth() {
		return admin_auth;
	}

	public void setAdmin_auth(int admin_auth) {
		this.admin_auth = admin_auth;
	}

	public int getWrite_auth() {
		return write_auth;
	}

	public void setWrite_auth(int write_auth) {
		this.write_auth = write_auth;
	}

	public int getComment_auth() {
		return comment_auth;
	}

	public void setComment_auth(int comment_auth) {
		this.comment_auth = comment_auth;
	}

	public int getFile_auth() {
		return file_auth;
	}

	public void setFile_auth(int file_auth) {
		this.file_auth = file_auth;
	}

	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

}

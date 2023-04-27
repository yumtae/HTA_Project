package com.passtoss.myhome.domain;

public class Member {
	private String updateType;
	private String memberType;
	private String id;
	private String password;
	private String username;
	private String join_date;
	private int join_type;
	private int belong = 0;
	private int company_id;
	private String dept;
	private String position;
	private String phone;
	private String profile_img = "profile-default.png";
	private String auth = "ROLE_MEMBER";
	private int auth_status;
	private int access_option;
	private String status;
	
	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUpdateType() {
		return updateType;
	}

	public void setUpdateType(String updateType) {
		this.updateType = updateType;
	}

	public String getMemberType() {
		return memberType;
	}

	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getJoin_date() {
		return join_date;
	}

	public void setJoin_date(String join_date) {
		this.join_date = join_date;
	}

	public int getJoin_type() {
		return join_type;
	}

	public void setJoin_type(int join_type) {
		this.join_type = join_type;
	}

	public int getBelong() {
		return belong;
	}

	public void setBelong(int belong) {
		this.belong = belong;
	}

	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getProfile_img() {
		return profile_img;
	}

	public void setProfile_img(String profile_img) {
		this.profile_img = profile_img;
	}

	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	public int getAuth_status() {
		return auth_status;
	}

	public void setAuth_status(int auth_status) {
		this.auth_status = auth_status;
	}

	public int getAccess_option() {
		return access_option;
	}

	public void setAccess_option(int access_option) {
		this.access_option = access_option;
	}

}

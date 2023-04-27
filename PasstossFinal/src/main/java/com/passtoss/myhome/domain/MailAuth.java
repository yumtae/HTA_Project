package com.passtoss.myhome.domain;

import java.util.Date;

public class MailAuth {
	private String id;
	private String authNum;
	private Date sendTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAuthNum() {
		return authNum;
	}

	public void setAuthNum(String authNum) {
		this.authNum = authNum;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

}

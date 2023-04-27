package com.passtoss.myhome.domain;

public class Chat {
	
	private int roomNumber;
	private String chat_date;
	private String chat_sender;
	private String chat_id;
	private String chat_content;
	private String chat_file;
	private String chat_original;
	private String chat_profile;
	
	
	@Override
	public String toString() {
		return "Chat [roomNumber=" + roomNumber + ", chat_date=" + chat_date + ", chat_sender=" + chat_sender
				+ ", chat_id=" + chat_id + ", chat_content=" + chat_content + ", chat_file=" + chat_file
				+ ", chat_original=" + chat_original + ", chat_profile=" + chat_profile + "]";
	}
	public String getChat_profile() {
		return chat_profile;
	}
	public void setChat_profile(String chat_profile) {
		this.chat_profile = chat_profile;
	}
	public String getChat_id() {
		return chat_id;
	}
	public void setChat_id(String chat_id) {
		this.chat_id = chat_id;
	}
	public int getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}
	public String getChat_date() {
		return chat_date;
	}
	public void setChat_date(String chat_date) {
		this.chat_date = chat_date;
	}
	public String getChat_sender() {
		return chat_sender;
	}
	public void setChat_sender(String chat_send) {
		this.chat_sender = chat_send;
	}
	public String getChat_content() {
		return chat_content;
	}
	public void setChat_content(String chat_content) {
		this.chat_content = chat_content;
	}
	public String getChat_file() {
		return chat_file;
	}
	public void setChat_file(String chat_file) {
		this.chat_file = chat_file;
	}
	public String getChat_original() {
		return chat_original;
	}
	public void setChat_original(String chat_original) {
		this.chat_original = chat_original;
	}

	
}

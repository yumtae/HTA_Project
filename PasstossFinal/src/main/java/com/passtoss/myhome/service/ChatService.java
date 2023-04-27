package com.passtoss.myhome.service;

import java.util.List;
import java.util.Map;

import com.passtoss.myhome.domain.Chat;

public interface ChatService {
	
	public List<?> getContactList(String chat_id);
	
	public List<Map<String, Object>> getSearchList(String chat_id, String searchword);

	public List<Map<String, Object>> getParticipantsList(String chat_id);
	
	public int getRoom(String id, String userid);
	
	public int createRoom(String id, String userid);
	
	public int chatSave(Chat chat);
	
	public List<Chat> getChatLog(int roomNumber);
	
	public Chat getChat(int roomNumber);
	
	public List<Map<String, Object>> getChatList(String id);
	
	public List<Map<String, Object>> getChatSearch(String searchword, String id);
	
	public int saveStatus(String status, String id);
	
	public Map<String, Object> getStatus(String id);
}

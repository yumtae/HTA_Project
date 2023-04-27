package com.passtoss.myhome.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.passtoss.myhome.domain.Chat;
import com.passtoss.myhome.mybatis.mapper.ChatMapper;

@Service
public class ChatServiceImpl implements ChatService{
	
	@Autowired
	private ChatMapper dao;
	
	public ChatServiceImpl(ChatMapper dao) {
		this.dao = dao;
	}

	@Override
	public List<?> getContactList(String chat_id) {

		return dao.getContactList(chat_id);
	}

	@Override
	public List<Map<String, Object>> getSearchList(String chat_id, String searchword) {
		
		return dao.getSearchList(chat_id, searchword);
	}

	@Override
	public List<Map<String, Object>> getParticipantsList(String chat_id) {
		return dao.getParticipantsList(chat_id);
	}
	
	@Override
	public int getRoom(String id, String userid) {

		Map<String, Object> map = new HashMap<>();
		map.put("user1", id);
		map.put("user2", userid);
		
		Map<String, Object> result = dao.getRoom(map);
		
		if(result == null) {
			return 0;
		}else {
			return Integer.parseInt(String.valueOf(result.get("ROOMNUMBER")));
		}
				
	}

	@Override
	public int createRoom(String id, String userid) {
		
		Map<String, Object> map = new HashMap<>();
		map.put("user1", id);
		map.put("user2", userid);
		
		int result = dao.createRoom(map);
		
		return Integer.parseInt(String.valueOf(result));
	}

	@Override
	public int chatSave(Chat chat) {
		
		return dao.chatSave(chat);
	}

	@Override
	public List<Chat> getChatLog(int roomNumber) {
		
		return dao.getChatLog(roomNumber);
	}

	@Override
	public Chat getChat(int roomNumber) {
		
		return dao.getChat(roomNumber);
	}

	@Override
	public List<Map<String, Object>> getChatList(String id) {
		
		return dao.getChatList(id);
	}

	@Override
	public List<Map<String, Object>> getChatSearch(String searchword, String id) {
		
		return dao.getChatSearch(searchword, id);
	}

	@Override
	public int saveStatus(String status, String id) {
		
		return dao.saveStatus(status, id);
	}

	@Override
	public Map<String, Object> getStatus(String id) {
		
		return dao.getStatus(id);
	}


	

	
}

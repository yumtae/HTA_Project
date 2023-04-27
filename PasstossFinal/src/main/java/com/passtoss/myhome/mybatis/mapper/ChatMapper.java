package com.passtoss.myhome.mybatis.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.passtoss.myhome.domain.Chat;


@Mapper
public interface ChatMapper {

	public List<?> getContactList(String chat_id);

	public List<Map<String, Object>> getSearchList(String chat_id, String searchword);

	public List<Map<String, Object>> getParticipantsList(String chat_id);
	
	public Map<String, Object> getRoom(Map<String, Object> map);

	public int createRoom(Map<String, Object> map);

	public int chatSave(Chat chat);

	public List<Chat> getChatLog(int roomNumber);

	public Chat getChat(int roomNumber);

	public List<Map<String, Object>> getChatList(String id);

	public List<Map<String, Object>> getChatSearch(String searchword, String id);

	public int saveStatus(String status, String id);

	public Map<String, Object> getStatus(String id);
	
}

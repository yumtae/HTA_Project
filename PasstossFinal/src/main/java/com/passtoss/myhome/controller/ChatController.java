package com.passtoss.myhome.controller;

import java.io.File;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.passtoss.myhome.domain.Chat;
import com.passtoss.myhome.domain.MySaveFolder;
import com.passtoss.myhome.domain.Room;
import com.passtoss.myhome.service.ChatServiceImpl;

@Controller
@RequestMapping(value = "myChat")
public class ChatController {
	
	private static final Logger logger = LoggerFactory.getLogger(ChatController.class);
	
	@Autowired
	private ChatServiceImpl chatService;
	
	@Autowired
	private MySaveFolder mysavefolder;
	
	public ChatController(ChatServiceImpl chatService, MySaveFolder mysavefolder) {
		this.chatService = chatService;
		this.mysavefolder = mysavefolder;
	}
	
	//연락처 목록 가져오기 
	@ResponseBody
	@GetMapping("/contactList")
	public List<?> contactlist(Principal principal) {
		//String chat_id = "e1234@hta.com"; // 시큐리티하면 세션아이디 가져오는걸로 수정 
		String chat_id = principal.getName(); 
		return chatService.getContactList(chat_id);
	}
	
	//연락처 검색 
	@ResponseBody
	@GetMapping("/contactSearch")
	public List<Map<String, Object>> search(String searchword, Principal principal){
		//String chat_id = "e1234@hta.com"; // 시큐리티하면 세션아이디 가져오는걸로 수정 
		String chat_id = principal.getName(); 
		return chatService.getSearchList(chat_id, searchword);
	}
	
	//홈 화면 프로젝트 리스트 
	@ResponseBody
	@GetMapping("/participantsList")
	public List<Map<String, Object>> getParticipantsList(Principal principal){
		//String chat_id = "e1234@hta.com"; // 시큐리티하면 세션아이디 가져오는걸로 수정 
		String chat_id = principal.getName(); 
		return chatService.getParticipantsList(chat_id);
	}
	
	//채팅창 view 페이지로 이동 
	@GetMapping("/chatview")
	public String chatview(Model mv) {
		return "chat/chatting";
	}
	
	//채팅방 있는지 확인하기 
	@ResponseBody
	@GetMapping("/getRoom")
	public int getRoom(ModelAndView mv, String userid,Principal principal) {
		//String id = "e1234@hta.com"; // 시큐리티하면 세션아이디 가져오는걸로 수정
		String id = principal.getName(); 	
		logger.info(id);
		return chatService.getRoom(id, userid);
	}
	
	//채팅방 만들기
	@ResponseBody
	@PostMapping("/createRoom")
	public int createRoom(String userid,Principal principal) {
		//String id = "e1234@hta.com"; // 시큐리티하면 세션아이디 가져오는걸로 수정
		String id = principal.getName(); 
		return chatService.createRoom(id, userid);
	}
	
	//채팅방에 DB저장하기 
	@ResponseBody
	@PostMapping("/chatSave")
	public int chatSave(Chat chat, Principal principal) {
		logger.info("chatSave =" + chat);
		String chat_id = principal.getName(); 
		chat.setChat_id(chat_id);
		return chatService.chatSave(chat);
	}
	
	//채팅로그 가져오기 
	@ResponseBody
	@GetMapping("/chatLog")
	public List<Chat> getChatLog(int roomNumber){
		
		return chatService.getChatLog(roomNumber);
	}

	//채팅 마지막 로그 가져오기 - send
	@ResponseBody
	@GetMapping("/getChat")
	public Chat getChat(int roomNumber){
		
		return chatService.getChat(roomNumber);
	}
	
	//채팅방 리스트 가져오기
	@ResponseBody
	@GetMapping("/getChatList")
	public List<Map<String, Object>> getChatList(Principal principal){
		String id = principal.getName();
		
		return chatService.getChatList(id);
	}
	
	//채팅방 검색하기 
	@ResponseBody
	@GetMapping("/chatSearch")
	public List<Map<String, Object>> getChatSearch(String searchword,Principal principal){
		String id = principal.getName();
		
		return chatService.getChatSearch(searchword, id);
	}
	
	//직원들 접속상태 변경하기 
	@ResponseBody
	@GetMapping("/saveStatus")
	public int saveStatus(String status,Principal principal){
		String id = principal.getName();
		
		return chatService.saveStatus(status, id);
	}	
	
	//본인 접속상태 가져오기
	@ResponseBody
	@GetMapping("/getStatus")
	public Map<String, Object> getStatus(Principal principal) {
		String id = principal.getName();
		
		return chatService.getStatus(id);
	}
	
	@ResponseBody
	@GetMapping("/down")
	public byte[] FileDown(String filename, HttpServletRequest request, String original,
	         					HttpServletResponse response) throws Exception {

	  String savefolder=mysavefolder.getSavefolder();
	  String sFilePath = savefolder + filename;

	  File file = new File(sFilePath);

	  byte[] bytes = FileCopyUtils.copyToByteArray(file);

	  String sEncoding = new String(original.getBytes("utf-8"), "ISO-8859-1");

	  // Content-Disposition: attachment: 브라우저는 해당 Content를 처리하지 않고, 다운로드하게 됩니다.
	  response.setHeader("Content-Disposition", "attachment;filename=" + sEncoding);

	  response.setContentLength(bytes.length);
	  return bytes;
	 }
	
}

package com.passtoss.myhome.handler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.passtoss.myhome.domain.MySaveFolder;

@Component
public class SocketHandler extends TextWebSocketHandler {
									//메시지 타입에 따라 handleBinaryMessage또는 handleTextMessage가 실행됨
	private static final Logger logger = LoggerFactory.getLogger(SocketHandler.class);
	
	//HashMap<String, WebSocketSession> sessionMap = new HashMap<>(); //웹소켓 세션을 담아둘 맵
	List<HashMap<String, Object>> clients = new ArrayList<>(); //웹소켓 세션을 담아둘 리스트 ---roomListSessions
								//세션 정보를 관리함 
	
	@Autowired
	private MySaveFolder mysavefolder;
	
	private String roomNumber = "";
	static String fileUploadSession = "";
	private String fileName = ""; //시스템 파일명 
	private String originalName = ""; //원본 파일명 
	
	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) {
		//메시지를 수신하면 실행
		String msg = message.getPayload();
		JSONObject obj = jsonToObjectParser(msg);
		logger.info("메시지 전송");
		logger.info("msg =" + msg);
		
		originalName = (String) obj.get("msg");
		logger.info("text originalName =" + originalName);
		fileName = fileDBName(originalName, mysavefolder.getSavefolder());
		logger.info("fileName =" + fileName );
		
		String rN =  (String) obj.get("roomNumber"); //현재의 방번호를 가져옴
		roomNumber = rN;
		logger.info("rN=" + rN);
		
		String msgType = (String) obj.get("type"); //메시지의 타입을 확인한다.
		
		String fileType = (String) obj.get("filetype");
		HashMap<String, Object> temp = new HashMap<String, Object>();
		
		logger.info("size =" + clients.size());
		if(clients.size() > 0) { //clients에서 데이터를 조회
			//temp.clear(); //초기화 
			logger.info("초기화 temp="+ temp );
			for(int i=0; i<clients.size(); i++) {
				String roomNumber = (String) clients.get(i).get("roomNumber"); //세션리스트의 저장된 방번호를 가져와서
				logger.info("clients에 roomNumber= " + roomNumber);
				if(roomNumber.equals(rN)) { //같은값의 방이 존재한다면
					logger.info("같은방 있음");
					temp = clients.get(i); //해당 방번호의 세션리스트의 존재하는 모든 object값을 가져온다.
					logger.info("temp=" + temp.toString());
					break;
				}
			}
			
			
		  
			
			//if(!msgType.equals("fileUpload")) { //메시지의 타입이 파일업로드가 아닐때만 전송한다.
				//해당 방의 세션들만 찾아서 메시지를 발송해준다.
				for(String k : temp.keySet()) { 
					System.out.println("temp for문 =" + k);
					if(k.equals("roomNumber")) { //방 번호가 다르면 발송안함 
						continue;
					}
					
					WebSocketSession wss = (WebSocketSession) temp.get(k);
					if(wss != null) {
						try {
						  if(fileType != null) {
							if(msgType.equals("fileUpload")) {
								obj.put("filesystemname", fileName);
								logger.info("filesned()");
							}
						  }
							wss.sendMessage(new TextMessage(obj.toJSONString()));
							logger.info("5번");
						} catch (IOException e) {
							e.printStackTrace();
							logger.info("e=" + e);
						}
					}
				//}
			}
		}//if(clients.size() > 0)
	}
	
	@Override
	public void handleBinaryMessage(WebSocketSession session, BinaryMessage message) {
		logger.info("파일전송시작");
		//바이너리 메시지 발송
		ByteBuffer byteBuffer = message.getPayload();
		
		File dir = new File(mysavefolder.getSavefolder());
		if(!dir.exists()) {
			dir.mkdirs();
		}
		
		File file = new File(mysavefolder.getSavefolder(), fileName);
		FileOutputStream out = null;
		FileChannel outChannel = null;
		try {
			byteBuffer.flip(); //byteBuffer를 읽기 위해 세팅
			out = new FileOutputStream(file, true); //생성을 위해 OutputStream을 연다.
			outChannel = out.getChannel(); //채널을 열고
			byteBuffer.compact(); //파일을 복사한다.
			outChannel.write(byteBuffer); //파일을 쓴다.
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(out != null) {
					out.close();
				}
				if(outChannel != null) {
					outChannel.close();
				}
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		byteBuffer.position(0); //파일을 저장하면서 position값이 변경되었으므로 0으로 초기화한다.
		HashMap<String, Object> temp = null; 
		
		for(int i=0; i<clients.size(); i++) {
			String Map_roomNumber = (String) clients.get(i).get("roomNumber"); //세션리스트의 저장된 방번호를 가져와서
			logger.info("clients에 Map_roomNumber= " + Map_roomNumber);
			if(Map_roomNumber.equals(roomNumber)) { //같은값의 방이 존재한다면
				logger.info("파일 전송");
				temp = clients.get(i); //해당 방번호의 세션리스트의 존재하는 모든 object값을 가져온다.
				logger.info("temp 파일=" + temp.toString());
				break;
			}
		}
		
		if(temp != null) {
			//파일쓰기가 끝나면 이미지를 발송한다.
			for(String k : temp.keySet()) {
				if(k.equals("roomNumber")) {
					continue;
				}
				WebSocketSession wss = (WebSocketSession) temp.get(k);
				try {
					wss.sendMessage(new BinaryMessage(byteBuffer)); //초기화된 버퍼를 발송한다. -이미지인 경우 
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		logger.info("파일전송끝");
	}
	
	@SuppressWarnings("unchecked") //컴파일시 발생하는 경고 무시
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		//웹소켓 연결되면 동작 
		super.afterConnectionEstablished(session); // 웹소켓 기본적인 기능 수행 
		logger.info("connection= 웹소켓 연결");
		
		boolean flag = false;
		String url = session.getUri().toString();
		logger.info(url); //ws://localhost:9600/passtoss/chating?roomnumber=8
		String roomNumber = url.split("=")[1];
		int idx = clients.size(); //세션리스트에 저장한 사이즈를 조사한다.
		if(clients.size() > 0) {  //세션리스트에 저장된 사이즈가 0보다 크면
			for(int i=0; i<clients.size(); i++) { 
				String rN = (String) clients.get(i).get("roomNumber"); //세션리스트에서 0번부터 저장된 roomNumber가져와서
				if(rN.equals(roomNumber)) { // 가져온 roomNumber랑 같은 roomNumber 있는지 확인 
					flag = true;			// 있으면 flag true로 변경
					idx = i;				// 저장된 세션리스트에서 방번호가 같았던 세션번호를 저장 
					break;
				}
			}
		}
		
		if(flag) { //존재하는 방이라면 세션만 추가한다.
			logger.info("idx =" + idx);
			HashMap<String, Object> map = clients.get(idx); //세션리스트에서 방번호가 같았던 세션 번호의 map을 가져와서
			map.put(session.getId(), session);			//새로운 세션 추가 
			logger.info("세션 추가 완료");
		}else { //최초 생성하는 방이라면 방번호와 세션을 추가한다.
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("roomNumber", roomNumber);
			map.put(session.getId(), session);
			clients.add(map);
			logger.info("새로운 채팅방 세션 추가완료");
		}
		
		logger.info(session.getId());
		
		//세션등록이 끝나면 발급받은 세션ID값의 메시지를 발송한다.
		JSONObject obj = new JSONObject();
		obj.put("type", "getId");
		obj.put("sessionId", session.getId());
		session.sendMessage(new TextMessage(obj.toJSONString()));
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		//웹소켓이 종료되면 동작 
		logger.info("웹소켓 Closed");
		if(clients.size() > 0) { //소켓이 종료되면 해당 세션값들을 찾아서 지운다.
			for(int i=0; i<clients.size(); i++) {
				logger.info(session.getId());
				clients.get(i).remove(session.getId());
			}
		}
		super.afterConnectionClosed(session, status);
	}
	
	
	
	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		exception.printStackTrace();
		super.handleTransportError(session, exception);
	}

	//json형태의 문자열을 파라미터로 받아서 SimpleJson의 파서를 활용하여 JSONObject로 파싱처리를 해주는 함수
	
	private static JSONObject jsonToObjectParser(String jsonStr) {
		JSONParser parser = new JSONParser();
		JSONObject obj = null;
		try {
			obj = (JSONObject) parser.parse(jsonStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return obj;
	}
	
	//시스템 파일명 구하기 
	private String fileDBName(String fileName, String savefolder) {
		// 새로운 폴더 이름 : 오늘 년+월+일
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);// 오늘 년도 구합니다.
		int month = c.get(Calendar.MONTH) + 1;// 오늘 월 구합니다.
		int date = c.get(Calendar.DATE);// 오늘 일 구합니다.

		String homedir = savefolder + "/" + year + "-" + month + "-" + date;
		logger.info(homedir);
		File path1 = new File(homedir);
		if (!(path1.exists())) {
			path1.mkdir();// 새로운 폴더 생성
		}

		// 난수를 구합니다.
		Random r = new Random();
		int random = r.nextInt(100000000);

		/**** 확장자 구하기 시작 ****/
		int index = fileName.lastIndexOf(".");
		// 문자열에서 특정 문자열의 위치 값(index)로 반환합니다.
		// indexOf가 처음 발견되는 문자열에 대한 index를 반환하는 반면,
		// lastIndexOf는 마지막으로 발견되는 문자열의 index를 반환합니다.
		// (파일명에 점이 여러개 있을 경우 맨 마지막에 발견되는 문자열의 위치를 리턴합니다.)
		//logger.info("index = " + index);

		String fileExtension = fileName.substring(index + 1);
		//logger.info("fileExtension = " + fileExtension);
		/**** 확장자 구하기 끝 ****/

		// 새로운 파일명
		String refileName = "bbs" + year + month + date + random + "." + fileExtension;
		//logger.info("refileNAme = " + refileName);

		// 오라클DB에 저장될 파일 명
		// String fileDBName = "/" + year + "-" + month + "-" + date + "/" + refileName;
		String fileDBname = File.separator + year + "-" + month + "-" + date + File.separator + refileName;
		//logger.info("fileDBname = " + fileDBname);
		return fileDBname;
	}
}

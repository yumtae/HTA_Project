package com.passtoss.myhome.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.passtoss.myhome.domain.Alarm;
import com.passtoss.myhome.domain.Board;
import com.passtoss.myhome.domain.MySaveFolder;
import com.passtoss.myhome.domain.ProjectManagers;
import com.passtoss.myhome.service.AlarmService;
import com.passtoss.myhome.service.BoardService;
import com.passtoss.myhome.service.MemberService;
import com.passtoss.myhome.service.ProjectManagersService;
import com.passtoss.security.CustomUserDetails;




@Controller
@RequestMapping(value = "/board")
public class BoardController {
	@Autowired
	private MySaveFolder mysavefolder;
	@Autowired
	private BoardService boardService;
	@Autowired
	private AlarmService alarmService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private ProjectManagersService projectManagersService;
	/*
	 * @Autowired public BoardController(BoardService boardService, CommentService
	 * commentService, MySaveFolder mysavefolder) { this.boardService =
	 * boardService; this.commentService = commentService;
	 * this.mysavefolder=mysavefolder;
	 * 
	 * }
	 */

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	@GetMapping(value = "myPro")
	public String myProject() {
		return "board/Project";
	}

	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public ModelAndView boardList(ModelAndView mv,@AuthenticationPrincipal CustomUserDetails user) {
		
		List<Board> boardlist = boardService.getBoardList(user.getCompanyId());
		
		System.out.println(user.getCompanyId());
		
//		  for (Board board : boardlist) { 
//			  	System.out.println("Board: " +  board.getBOARD_NUM()); 
//			  	List<Comment> commentList = board.getGetCommentList(); 
//			  	if (commentList != null && !commentList.isEmpty()) {
//					  System.out.println("comment List:"); 
//					  for (Comment manager : commentList) {
//					  System.out.println(manager.getCONTENT()); } 
//				 } else {
//				  System.out.println("No commentList"); } 
//			  	}
			 
		List<Map<String, Integer>> groupByStatus = boardService.groupByStatus();
		mv.addObject("groupByStatus", groupByStatus);
		//System.out.println(groupByStatus);
	
		int listcountAll = boardService.getListCountAll(user.getCompanyId());
		int listcount = boardService.getListCount(user.getCompanyId());
		mv.addObject("listcount", listcount);
		mv.addObject("listcountAll", listcountAll);
		mv.addObject("boardlist", boardlist);
	

		
		mv.setViewName("board/boardindex");
		
		
		return mv;

	}

	
	@RequestMapping(value = "subwork", method = RequestMethod.GET)
	public ModelAndView subWorkList( ModelAndView mv,@AuthenticationPrincipal CustomUserDetails user) {
		
		List<Board> boardlist = boardService.getBoardList(user.getCompanyId());
		int listcount = boardService.getListCount(user.getCompanyId());
		mv.addObject("listcount", listcount);
		mv.addObject("boardlist", boardlist);
		mv.setViewName("board/subwork");
		
		
		return mv;
		 
	}

	
	@ResponseBody
	@PostMapping(value = "getalarmlog")
	public Map<String, Object> getalarmlog(String ID) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<Map<String, Object>> list = alarmService.getAlarmLog(ID);		
		map.put("list", list);
		return map;

	}
	
	@ResponseBody
	@PostMapping(value = "readalarmlog")
	public void readalarmlog(String ID) {
		alarmService.readAlarmLog(ID);
	}
	
	
	
	
	@ResponseBody
	@PostMapping(value = "progressbarUpdate")
	public int progressbarChange(int STATUS) {

		return 0;

	}

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

	@PostMapping(value = "/boardAdd")
	@ResponseBody
	public Map<String,Object> boardAdd(Board board, @RequestParam(required = false, value = "persons") String persons,
			 @RequestParam(required = false, value = "SUBWORKLIST") String SUBWORKLIST,
			HttpServletRequest request) throws Exception {

		String message = "";
		Map<String, Object> map = new HashMap<String, Object>();
		
		  MultipartFile uploadfile = board.getUPLOADFILE();
		 
		 // System.out.println("file = "+uploadfile);
		  
		  if (uploadfile != null && !uploadfile.isEmpty()) { 
			  String fileName =
			  uploadfile.getOriginalFilename();
			  board.setORIGINAL_FILE_NAME(fileName);
			  
			  String savefolder=mysavefolder.getSavefolder(); String fileDBName =
			  fileDBName(fileName, savefolder);
			  
			  uploadfile.transferTo(new File(savefolder + fileDBName));
			  board.setFILE_NAME(fileDBName);
			}
		 
	
	
	
		//1. 게시글 내용 저장
		int result = boardService.insertBoard(board);
		
			///////////////2.하위업무 글 추가 ///////////////////
		   insertSubworkMethod(SUBWORKLIST,board);
		      
		if (result == 1) {
			
			Map<String, Object> alarmInsertMap = new HashMap<>();
			List<Object> idList = memberService.getCompanyAllID(board.getCOMPANY_ID());
			alarmInsertMap.put("idList", idList);
			alarmInsertMap.put("BOARD_NUM", board.getBOARD_NUM());
			alarmInsertMap.put("WRITER", board.getWRITER());
			alarmInsertMap.put("SUBJECT", board.getSUBJECT());
			alarmService.insertAlarmLog(alarmInsertMap);
			
			
			///////////////3. 담당자  추가 ///////////////////
			insertProjectManagers( board,  persons);
			
			//insert 후 삽입된 정보 재출력
			List<Board> list = boardService.getDetailOne(board.getBOARD_NUM());
			
			
		
			map.put("list", list);
			
			
			
			message = "입력성공";
		} else {
			message = "입력실패";
		}
		
		logger.info(message);

		
		
		
		
		
		
		return map;

	}
	
	
	
	@ResponseBody
	 @PostMapping(value = "/boardUpdate")
	   public Map<String,Object> BoardModifyAction(Board boarddata, @RequestParam(required = false, value = "persons") String persons,
				 @RequestParam(required = false, value = "SUBWORKLIST") String SUBWORKLIST,  @RequestParam(required = false, value = "filecheck") int filecheck,
					HttpServletRequest request) throws Exception {
		 
		 String message;
		 Map<String, Object> map = new HashMap<String, Object>();
		 
	      MultipartFile uploadfile = boarddata.getUPLOADFILE();
	      String savefolder=mysavefolder.getSavefolder();
	      
	      if (filecheck == 1  ) {// 기존파일 그대로 사용하는 경우
	         logger.info("기존파일 그대로 사용합니다.");
	         boarddata.setORIGINAL_FILE_NAME(savefolder);
	      } else {
	         if (uploadfile != null && !uploadfile.isEmpty()) {
	            logger.info("파일 추가/변경 되었습니다.");

	            String fileName = uploadfile.getOriginalFilename();// 원래 파일명
	            boarddata.setORIGINAL_FILE_NAME(fileName);

	            String fileDBName = fileDBName(fileName, savefolder);
	            logger.info("fileDBName = " + fileDBName);
	            // transferTo(File path) : 업로드한 파일을 매개변수의 경로에 저장합니다.
	            uploadfile.transferTo(new File(savefolder + fileDBName));
	            logger.info("transferTo path = " + savefolder + fileDBName);
	            // 바뀐 파일명으로 저장
	            boarddata.setFILE_NAME(fileDBName);
	         } else {// 기존 파일이 없는데 파일 선택하지 않은경우 또는 기존 파일이 있었는데 삭제한 경우
	            logger.info("선택파일이 없습니다.");
	            // <input type="hidden" name="BOARD_FILE" value="${boarddata.BOARD_FILE}">
	            // 위 태그에 값이 있다면 ""로 값을 변경합니다.
	            boarddata.setFILE_NAME("");// ""로 초기화합니다.
	            boarddata.setORIGINAL_FILE_NAME("");// ""로 초기화합니다.
	         } // else
	      } // else

	      
	      
	      // 게시글 업데이트
	      int result = boardService.boardUpdate(boarddata);
	      
	      //하위업무 추가 메서드
	      insertSubworkMethod(SUBWORKLIST,boarddata);
	      
	      
	      	///////////////담당자  추가 ///////////////////
	      insertProjectManagers( boarddata,  persons);
	      ///////////////담당자  추가 ///////////////////
		
		
	      
	      // 수정 실패한 경우
	      if (result == 0) {
	    	  message = "수정 실패";
	      } else {// 수정 성공한 경우
	            // 수정한 글 내용을 보여주기 위해 글 내용 보기 페이지로 이동하기 위해 경로를 설정합니다.
	    	  message = "수정 성공";
	    	  List<Board> list = boardService.getDetailOne(boarddata.getBOARD_NUM());
	    	  map.put("list", list);
	      } 
	      return map;
	   }
	 
	 
	
	
	
	
	
	
	
	
	@ResponseBody
	@PostMapping(value="/boardDelete")
	public int BoardDelete(int num) {
		int result =   boardService.boardDelete(num);
		return result;
		
	}
	
	@ResponseBody
	@PostMapping(value="/boardDeleteOne")
	public int boardDeleteOne(int BOARD_NUM) {
		int result =   boardService.boardDeleteOne(BOARD_NUM);
		return result;
		
	}
	
	
	
	@ResponseBody
	   @PostMapping("/boardDetailOne")
	public Map<String,Object> BoardDetailOne(int BOARD_NUM){
		List<Board> list = boardService.getDetailOne(BOARD_NUM);
		
		
//		  for (Board board : list) { 
//		  	System.out.println("Board: " +  BOARD_NUM); 
//		  	List<Comment> commentList = board.getGetCommentList(); 
//		  	if (commentList != null && !commentList.isEmpty()) {
//				  System.out.println("comment List:"); 
//				  for (Comment manager : commentList) {
//				  System.out.println(manager.getCONTENT()); } 
//			 } else {
//			  System.out.println("No commentList"); } 
//		  	}
//			
			
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("list", list);
		
		
		return map;
	
	}
	
	
	
		@ResponseBody
	   @PostMapping("/down")
	   public byte[] BoardFileDown(String filename, HttpServletRequest request, String original,
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
	
	
		

	public void insertProjectManagers(Board board, String persons) {
		System.out.println(persons);
		ObjectMapper objectMapper = new ObjectMapper();
	    try {
	    	projectManagersService.deleteProjectManager(board.getBOARD_NUM());
	        List<ProjectManagers> personList = objectMapper.readValue(persons, new TypeReference<List<ProjectManagers>>(){});
	        for (ProjectManagers person : personList) {
	            String id = person.getID();
	            
	            projectManagersService.insertProjectManager(board.getBOARD_NUM(), id);
	            System.out.println(id);
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
		
		
	}
		
		
		
	public void insertSubworkMethod(String SUBWORKLIST,Board board) throws JSONException {
	///////////////하위업무 글 추가 ///////////////////
		JSONArray jsonArray = new JSONArray(SUBWORKLIST);
	
		// 변환된 JSONArray 객체를 담을 List 생성
		List<JSONObject> jsonObjectList = new ArrayList<>();
	
		// JSONArray 배열 순회하며 JSONObject로 변환하여 List에 추가
		for (int i = 0; i < jsonArray.length(); i++) {
		    JSONObject jsonObject = jsonArray.getJSONObject(i);
		    jsonObjectList.add(jsonObject);
		}
		//하위업무 삭제후 재등록
		boardService.deleteSubWork(board.getBOARD_NUM());
		// List 출력
		for (int i = jsonObjectList.size() - 1; i >= 0; i--) {
		    JSONObject jsonObject = jsonObjectList.get(i);
			Board subBoard = new Board();
			String data = jsonObject.getString("data");
		    String status = jsonObject.getString("status");
		    subBoard.setSUBJECT(data);
		    subBoard.setSTATUS(status);
		    subBoard.setBOARD_RE_REF(board.getBOARD_NUM());
		    subBoard.setCOMPANY_ID(board.getCOMPANY_ID());
		    
		    
		    
		    boardService.insertSubWork(subBoard);
		}
		///////////////하위업무 글 추가 ///////////////////
			
	}/*/insertSubworkMethod*/
	
	
	
	
	
	

}

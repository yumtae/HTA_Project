package com.passtoss.myhome.controller;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.passtoss.myhome.domain.Board;
import com.passtoss.myhome.domain.MySaveFolder;
import com.passtoss.myhome.service.BoardService;
import com.passtoss.myhome.service.CalendarServiceImpl;
import com.passtoss.myhome.service.CommentService;
import com.passtoss.myhome.service.MemberService;
import com.passtoss.myhome.service.ProjectManagersService;
import com.passtoss.security.CustomUserDetails;

@Controller
@RequestMapping(value = "/cal")
public class CalendarController {
	@Autowired
	private MySaveFolder mysavefolder;
	@Autowired
	private BoardService boardService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private ProjectManagersService projectManagersService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private CalendarServiceImpl calendarService;
	

	private static final Logger logger = LoggerFactory.getLogger(CalendarController.class);

	

	
	  @GetMapping("/main")
	  public String Main() {
		return "calendar/calView";
		  
		  
	  }

	
	  @GetMapping("/view")
	    @ResponseBody
	    public List<Map<String, Object>> monthPlan(@AuthenticationPrincipal CustomUserDetails user) {
		
		 List<Board> boardlist = boardService.getBoardList(user.getCompanyId());
	        List<Map<String, Object>> list= new ArrayList<Map<String, Object>>(); 
	      
	 
	        for (int i = 0; i < boardlist.size(); i++) {
	        	  HashMap<String, Object> hash = new HashMap<>();
	        	  hash.put("num", boardlist.get(i).getBOARD_NUM());
	        	  hash.put("company", boardlist.get(i).getCOMPANY_ID());
	        	  hash.put("user", boardlist.get(i).getWRITER());
	            hash.put("title", boardlist.get(i).getSUBJECT());
	            hash.put("start", boardlist.get(i).getSTART_DATE());
	            hash.put("end", boardlist.get(i).getEND_DATE());
	            hash.put("content", boardlist.get(i).getCONTENT());
	            
	            list.add(hash);
	           
	        }
	        logger.info("jsonArrCheck: {}", list);
	        return list;
	    }

	
	
	@PostMapping(value = "/calAdd")
	@ResponseBody
	public String calAdd(Board board, @RequestParam(required = false, value = "persons") String persons,
			HttpServletRequest request, HttpSession session) throws Exception {

		System.out.println(board.toString());
		String message = "";

	
		int result = boardService.insertBoard(board);
		
		
		if (result == 1) {
			
			///////////////담당자  추가 ///////////////////
			if (persons != null && !persons.isEmpty()) {
				
				String[] personsArr = persons.split(",");
				
			
				for (String ID : personsArr) {
					
					projectManagersService.insertProjectManager(board.getBOARD_NUM(), ID);

				}
			}
			///////////////담당자  추가 ///////////////////
			
			
			message = "입력성공";
		} else {
			message = "입력실패";
		}
		
		logger.info(message);

		return message;

	}
	
	@PostMapping(value="/calDel")
	@ResponseBody
	public String DeleteAction(	@ModelAttribute Board board,
									Model mv, RedirectAttributes rattr,
									HttpServletRequest request) {
		System.out.println("----------------------------------------");
		System.out.println("" + board.getBOARD_NUM());
//		//글 삭제 명력ㅇ을 요청한 사용자가 글을 작성한 사용자인지 판단하기 위해
//		// 입력한 비밀번호와 저장된 비밀번호를 비교하여 일치하면 삭제합니다.
//		boolean usercheck = boardService.isBoardWriter(num, BOARD_PASS);
//		
//		//비밀번호 일치하지 않는경우
//		if(usercheck == false) {
//			rattr.addFlashAttribute("result", "passFail");
//			rattr.addAttribute("num", num);
//			return "redirect:detail";
//		}
//		
		//비밀번호 일치하는 경우 삭제 처리합니다.
		int result = calendarService.calDelete(board.getBOARD_NUM());
		/* int result = 1; */
		
		String message = "삭제성공";
		//삭제 처리 실패한 경우 
		/*
		 * if(result == 0) { logger.info("게시판 삭제 실패"); message = "삭제실패"; }
		 */
		
		//삭제 처리 성공한 경우 - 글 목록 보기 요청을 전송하는 부분입니다.
		logger.info("게시판 삭제 성공");

		return message;
	}
	        

	
	@PostMapping(value="/calUpdate")
	@ResponseBody
	public String upAction(Board board, @RequestParam(required = false, value = "persons") String persons,
			HttpServletRequest request, HttpSession session) throws Exception {

		System.out.println(board.toString());
		String message = "";

	
		int result = calendarService.calUpdate(board);
		
		
		if (result == 1) {
			
			///////////////담당자  추가 ///////////////////
			if (persons != null && !persons.isEmpty()) {
				
				String[] personsArr = persons.split(",");
				
			
				for (String ID : personsArr) {
					
					projectManagersService.insertProjectManager(board.getBOARD_NUM(), ID);

				}
			}
			///////////////담당자  추가 ///////////////////
			
			
			message = "수정성공";
		} else {
			message = "수정실패";
		}
		
		logger.info(message);

		return message;

	}
		
	}
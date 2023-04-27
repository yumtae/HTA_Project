package com.passtoss.myhome.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.passtoss.myhome.domain.Company;
import com.passtoss.myhome.domain.Member;
import com.passtoss.myhome.domain.MySaveFolder;
import com.passtoss.myhome.service.AdminService;
import com.passtoss.myhome.service.MemberService;




@Controller
@RequestMapping(value = "/admin")
public class AdminController {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	private MySaveFolder mysavefolder;
	private MemberService memberService;
	private AdminService adminService;
	
	@Autowired
	AdminController(MemberService memberService, MySaveFolder mysavefolder, AdminService adminService) {
		this.adminService = adminService;
		this.memberService = memberService;
		this.mysavefolder = mysavefolder;
				
	}
	

	
	
	@RequestMapping(value = "/main")
	public ModelAndView companyList(
			@RequestParam(value="page", defaultValue ="1", required=false) int page,
			@RequestParam(value="limit", defaultValue ="3", required=false) int limit,
			ModelAndView mv,
			@RequestParam(value="search_field", defaultValue ="-1", required=false) int index,
			@RequestParam(value="search_word", defaultValue ="", required=false)String search_word
			) {
			
			int listcount = adminService.getSearchListCount(index, search_word); //총 리스트 수를 받아옴
			List<Company> list = adminService.getSearchList(index, search_word, page, limit);
			
			// 총 페이지수
			int maxpage = (listcount + limit -1) / limit;
					
			//현재 페이지에 보여줄 시작 페이지 수 (1, 11, 21 등)
			int startpage =((page - 1) /10) * 10 + 1;
					
			//현재 페이지에 보여줄 마지막 페이지 수 (10, 20 30 등)
			int endpage = startpage + 10 - 1;
					
			if(endpage > maxpage)
				endpage = maxpage;
			
			mv.setViewName("CompanyAdmin/adminView");
			mv.addObject("page", page);
			mv.addObject("maxpage", maxpage);
			mv.addObject("startpage", startpage);
			mv.addObject("endpage", endpage);
			mv.addObject("listcount", listcount);
			mv.addObject("companylist", list);
			mv.addObject("search_field", index);
			mv.addObject("search_word", search_word);
			
			return mv;
		}
		
	
	@RequestMapping(value = "/comMember")
	public ModelAndView memberList(
			@RequestParam(value="page", defaultValue ="1", required=false) int page,
			@RequestParam(value="limit", defaultValue ="3", required=false) int limit,
			ModelAndView mv,
			@RequestParam(value="search_field", defaultValue ="-1", required=false) int index,
			@RequestParam(value="search_word", defaultValue ="", required=false)String search_word
			) {
			
			int listcount = adminService.getSearchListCount2(index, search_word); //총 리스트 수를 받아옴
			List<Member> list = adminService.getSearchList2(index, search_word, page, limit);
			
			// 총 페이지수
			int maxpage = (listcount + limit -1) / limit;
					
			//현재 페이지에 보여줄 시작 페이지 수 (1, 11, 21 등)
			int startpage =((page - 1) /10) * 10 + 1;
					
			//현재 페이지에 보여줄 마지막 페이지 수 (10, 20 30 등)
			int endpage = startpage + 10 - 1;
					
			if(endpage > maxpage)
				endpage = maxpage;
			
			mv.setViewName("CompanyAdmin/memberView");
			mv.addObject("page", page);
			mv.addObject("maxpage", maxpage);
			mv.addObject("startpage", startpage);
			mv.addObject("endpage", endpage);
			mv.addObject("listcount", listcount);
			mv.addObject("memberlist", list);
			mv.addObject("search_field", index);
			mv.addObject("search_word", search_word);
			
			return mv;
		}

	//회원 삭제
			@RequestMapping(value="/delete", method = RequestMethod.GET)
			public String delete(String id) {
				adminService.delete(id);
				return "redirect:comMember";
			}
	

	
	
			@RequestMapping(value="/update", method = RequestMethod.GET)
			public String update(String id) {
				adminService.update(id);
				return "redirect:comMember";
			}
	
}

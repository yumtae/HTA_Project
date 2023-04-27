package com.passtoss.myhome.controller;

import java.io.File;
import java.io.PrintWriter;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.passtoss.myhome.domain.Company;
import com.passtoss.myhome.domain.MailVO;
import com.passtoss.myhome.domain.Member;
import com.passtoss.myhome.domain.MySaveFolder;
import com.passtoss.myhome.service.MemberService;
import com.passtoss.myhome.task.SendMail;
import com.passtoss.security.CustomUserDetails;

@Controller
@RequestMapping(value = "/member")
public class MemberController {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	private MemberService memberService;
	private PasswordEncoder passwordEncoder;
	private SendMail sendMail;
	private MySaveFolder mySaveFolder;

	@Autowired
	public MemberController(MemberService memberService, PasswordEncoder passwordEncoder, SendMail sendMail, MySaveFolder mySaveFolder) {
		this.memberService = memberService;
		this.passwordEncoder = passwordEncoder;
		this.sendMail = sendMail;
		this.mySaveFolder=mySaveFolder;
	}

	@GetMapping("/login")
	public ModelAndView login(ModelAndView mv, @CookieValue(value = "remember-me", required = false) Cookie readCookie,
			HttpSession session, Principal userPrincipal) {
		if (readCookie != null) {
			logger.info("저장된 아이디 : " + userPrincipal.getName());
			mv.setViewName("redirect:/board/main");
		} else {
			mv.setViewName("member/login");
			mv.addObject("errorMsg", session.getAttribute("errorMsg"));
			session.removeAttribute("errorMsg");
		}
		return mv;
	}

	@GetMapping("/join")
	public String Join() {
		return "member/joinPro";
	}
	
	@GetMapping("/welcome")
	public ModelAndView welcome(ModelAndView mv, HttpSession session) {
		mv.addObject("belong", session.getAttribute("belong"));
		mv.setViewName("member/welcome");
		session.removeAttribute("belong");
		return mv;
	}
	
	@GetMapping("/forgotPw")
	public String forgotPw() {
		return "member/forgotPw";
	}
	
	// id 중복검사
	@GetMapping("/idcheck")
	public void idcheck(@RequestParam("id") String id, HttpServletResponse response) throws Exception {
		int result = memberService.isId(id);
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(result);
	}
	
	// 인증번호 발송
	@ResponseBody
	@PostMapping("/emailSend")
	public int emailSend(@RequestParam("id") String id) {
		MailVO mail = new MailVO();
		String authNum = Integer.toString((int) (Math.random() * (999999 - 100000 + 1)) + 100000);
		logger.info("생성된 인증번호 : " + authNum);

		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("authNum", authNum);

		int result = memberService.mailAuth(map);

		if (result == 1) {
			mail.setTo(id);
			mail.setContent("<div style=\"text-align:center; margin:3rem auto; width:400px;\">"
					+ "<hr><p>본 메일은 '패스토스'에서 본인 확인을 위해 <br>자동으로 발송되는 메일입니다.</p>"
					+ "<p><b>인증번호 입력창에 아래 인증번호를 입력하세요.</b></p>"
					+ "<div style=\"font-size:30px; background:#4e73df2e;\">" + authNum + "</div><hr>");

			sendMail.sendMail(mail);
		}
		
		return result;
	}
	
	// 인증번호 확인
	@ResponseBody
	@PostMapping("/emailCertify")
	public int emailCertify(@RequestParam("id") String id,
							@RequestParam("authNum") String authNum) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("authNum", authNum);

		int result = memberService.mailCertify(map);
		return result;
	}
	
	// 회사 url 중복검사 - 새 회사 만들기
	@ResponseBody
	@PostMapping("checkURL")
	public int checkURL(@RequestParam("url") String url) {
		return memberService.checkURL(url);
	}
	
	// 회사 url 정보확인 - 기존회사 참여
	@ResponseBody
	@PostMapping("isCompany")
	public Company isCompany(@RequestParam("url") String url) {
		Company c = memberService.isCompany(url);
		try {
			logger.info("회사이름 : " + c.getCompany_name());
			logger.info("회사 아이디 : " + c.getCompany_id());
		}catch(NullPointerException n) {
			logger.info("회사 정보 없음");
		}
		return c;
	}
	
	// 회원가입
	@PostMapping("/joinPro")
	public String joinProcess(Model model, Company c, Member m) {
		int result = 0;
		int belong = -1;
		logger.info(m.getMemberType()); // 가입 카테고리 확인
		String encPassword = passwordEncoder.encode(m.getPassword());
		m.setPassword(encPassword);

		if (m.getMemberType().equals("newCompany")) {
			logger.info("새 회사 만들기");
			m.setAuth("ROLE_MANAGER");
			result = memberService.createCompany(c, m);
			belong = 1;
		} else if (m.getMemberType().equals("joinCompany")) {
			logger.info("기존 회사 참여");
			Map<String, Integer> map = memberService.joinCompany(m);
			result = map.get("result");
			belong = map.get("belong");
		}

		if (result == 1) {
			model.addAttribute("belong", belong);
			logger.info("회원가입 완료");
		}
		
		return "member/welcome";
	}
	
	@ResponseBody
	@PostMapping("/resetPassword")
	public int reserPassword(@RequestParam("id") String id, @RequestParam("password") String password) {
		String encPassword = passwordEncoder.encode(password);

		int result = memberService.resetPassword(id, encPassword);

		return result;
	}
	
	@ResponseBody
	@PostMapping("/modifyProfileImg")
	public String modifyProfileImg(@RequestParam("profile_img") MultipartFile profileImg, Principal principal,
			@AuthenticationPrincipal CustomUserDetails user, HttpServletRequest request) throws Exception {

		String id = principal.getName();
		String original = profileImg.getOriginalFilename();// 원래 파일명
		String newName = fileReName(original, id);// 파일명 id이름으로 변경
		String uploadFolder = mySaveFolder.getProfileFolder();// 프로필이미지 폴더 경로
		logger.info("프로필 이미지 폴더 경로 = " + uploadFolder);

		File files = new File(uploadFolder);
		File file[] = files.listFiles();
		int index = newName.lastIndexOf(".");
		String noExNewName = newName.substring(0, index);
		logger.info("확장자 제거 업로드 프로필이미지 이름 = " + noExNewName);

		for (int i = 0; i < file.length; i++) {
			index = file[i].getName().lastIndexOf(".");
			String name = file[i].getName().substring(0, index);// 확장자 제거한 파일이름
			logger.info("파일명 = " + name);

			if (name.equals(noExNewName)) {
				logger.info("원래 프로필 이미지 제거");
				file[i].delete();
				break;
			}
		}

		profileImg.transferTo(new File(uploadFolder, newName));

		Member m = new Member();
		m.setId(id);
		m.setProfile_img(newName);
		m.setUpdateType("profileImg");
		
		memberService.updateProfile(m);
		user.setProfile_img(newName);
		return newName;
	}

	private String fileReName(String original, String id) {

		int index = original.lastIndexOf(".");
		String fileExtension = original.substring(index + 1);

		logger.info("index = " + index);
		logger.info("fileExtension = " + fileExtension);

		String newName = id.replace(".", "_") + "." + fileExtension;
		logger.info("프로필 이미지 명 = " + newName);

		return newName;
	}
	
	@ResponseBody
	@PostMapping("/getSearchMemberList")
	public List<Map<String, Object>> getSearchMemberList(String searchword, @RequestParam(required = false, value = "persons") String personsJson) {
		
	    List<String> personIds = new ArrayList<>();
	    
	    if (personsJson != null) {
	        try {
	        	 if (personsJson.startsWith("[")) { // JSON 배열인 경우
	                 JSONArray personsArray = new JSONArray(personsJson);
	                 for (int i = 0; i < personsArray.length(); i++) {
	                     JSONObject person = personsArray.getJSONObject(i);
	                     String id = person.getString("id");
	                     personIds.add(id);
	                 }
	             } else { // JSON 객체인 경우
	                 JSONObject person = new JSONObject(personsJson);
	                 String id = person.getString("id");
	                 personIds.add(id);
	             }
	        } catch (JSONException e) {
	            e.printStackTrace();
	        }
	    }

	    List<Map<String, Object>> memberlist = memberService.getSearchMemberList(searchword, personIds);
	    return memberlist;
	}
	
	@ResponseBody
	@PostMapping("/updateProfile")
	public int updateProfile(Member m, @AuthenticationPrincipal CustomUserDetails user) {
		logger.info("아이디 = "+m.getId());
		int result = memberService.updateProfile(m);
		if (result == 1) {
			logger.info("회원정보 수정완료");
			user.setPhone(m.getPhone());
		}
		return result;
	}
	
}
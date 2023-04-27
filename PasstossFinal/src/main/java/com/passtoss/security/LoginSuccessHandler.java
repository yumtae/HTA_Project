package com.passtoss.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.passtoss.myhome.domain.Member;
import com.passtoss.myhome.mybatis.mapper.MemberMapper;

//AuthenticationSuccessHandler : 사용자 인증이 성공 후 처리할 작업을 직접 작성할 때 사용하는 인터페이스 입니다.
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
	private static final Logger logger = LoggerFactory.getLogger(LoginSuccessHandler.class);

	@Autowired
	private MemberMapper dao;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		logger.info("로그인 성공 : LoginSuccessHandler");
		
		String id = request.getParameter("id");
		Member m = dao.isId(id);

		int belong = m.getBelong();
		int companyId = m.getCompany_id();

		logger.info(id + "의 소속 유무 : " + belong);
		logger.info(id + "의 회사 번호 : " + companyId);
		
		HttpSession session = request.getSession();
		String url = request.getContextPath() + "/board/main";
		session.setAttribute("companyId", companyId);

		if (belong == 0) {
			session.setAttribute("belong", belong);
			url = request.getContextPath() + "/member/welcome";
		}

		if( "soundychoi@gmail.com".equals(id) ) {
			url = request.getContextPath() + "/admin/main"; 
		}
		// 관리자페이지 바로 이동
		
		response.sendRedirect(url);
	}

}
package com.passtoss.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

//AuthenticationFailureHandler : 로그인 실패 후 처리할 작업을 해야할 때 사용하는 인터페이스입니다.
public class LoginFailHandler implements AuthenticationFailureHandler {
	private static final Logger logger = LoggerFactory.getLogger(LoginFailHandler.class);

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		logger.info("로그인 실패");
		logger.info("exception = " + exception);		
		
		String errorMessage = "";

		if (exception instanceof BadCredentialsException) {
			errorMessage = "아이디 또는 비밀번호가 맞지 않습니다.";
		}	
		
		HttpSession session = request.getSession();
		session.setAttribute("errorMsg", errorMessage);
		String url = request.getContextPath() + "/member/login";
		response.sendRedirect(url);

	}

}
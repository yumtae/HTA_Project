package com.passtoss.security;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {
	private static final Logger logger = LoggerFactory.getLogger(CustomAccessDeniedHandler.class);

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {

		logger.info("Access Denied Handler");
		String url = "/WEB-INF/views/error/403.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		request.setAttribute("key", "접근 권한이 없는 사용자입니다.");
		dispatcher.forward(request, response);
	}

}


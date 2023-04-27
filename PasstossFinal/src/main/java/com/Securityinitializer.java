package com;

import javax.servlet.ServletContext;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.multipart.support.MultipartFilter;

//별도의 구현 없이 클래스를 추가하는 것으로 내부적으로 DelegatingFilterProxy를 스프링에 등록합니다.
public class Securityinitializer extends AbstractSecurityWebApplicationInitializer {

	// 시큐리티 필터 앞에 놓을 필터들을 차례대로 추가합니다.
	@Override
	protected void beforeSpringSecurityFilterChain(ServletContext servletContext) {
		
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		characterEncodingFilter.setForceEncoding(true);
		insertFilters(servletContext, characterEncodingFilter);

		MultipartFilter multipartFilter = new MultipartFilter();
		multipartFilter.setMultipartResolverBeanName("multipartResolver");
		insertFilters(servletContext, multipartFilter);

	}

}


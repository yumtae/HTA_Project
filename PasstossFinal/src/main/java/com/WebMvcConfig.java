package com;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{
	
	@Value("${my.savepath}")
	private String saveFolder;
	
	@Value("${my.savechatpath}")
	private String saveChatFolder;
	
	@Value("${my.companyLogo}")
	private String companyLogo;

	private static final String[] CLASSPATH_RESOURCE_LOCATIONS 
	= { 
			"classpath:/static/", 
			"classpath:/resources/",
			"classpath:/META-INF/resources" 
	};
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		// url mapping "/"로 접속하면 "/member/login"으로 이동합니다.
		registry.addViewController("/").setViewName("forward:/member/login");
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**")
				.addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);		
		
		registry.addResourceHandler("/upload/**")
		  		.addResourceLocations(saveFolder); 
		
		registry.addResourceHandler("/chat/upload/**")
  				.addResourceLocations(saveChatFolder); 
		
		registry.addResourceHandler("/logo/**")
				.addResourceLocations(companyLogo);		
		
	}
	
}


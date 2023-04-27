package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.passtoss.myhome.handler.SocketHandler;


@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer{

	@Autowired
	SocketHandler socketHandler;
	
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(socketHandler, "/chating"); 
										//어디로 갈지 주소를 알려줌 
	}
}
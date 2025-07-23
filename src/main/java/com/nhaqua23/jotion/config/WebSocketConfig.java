package com.nhaqua23.jotion.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

	@Override
	public void configureMessageBroker(@NonNull MessageBrokerRegistry config) {
		config.enableSimpleBroker("/topic", "/queue"); // Enable simple broker for topics and queues
		config.setApplicationDestinationPrefixes("/app"); // Application prefix for messages
	}

	@Override
	public void registerStompEndpoints(@NonNull StompEndpointRegistry registry) {
		registry.addEndpoint("/ws") // WebSocket endpoint
				.setAllowedOrigins("*")
				.addInterceptors(new HttpSessionHandshakeInterceptor()) // Interceptor for session management
				.withSockJS(); // Enable SockJS fallback for WebSocket
	}
}

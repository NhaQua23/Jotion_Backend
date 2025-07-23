package com.nhaqua23.jotion.handler;

import org.springframework.lang.NonNull;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class PageWebSocketHandler extends TextWebSocketHandler {

	private List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

	@Override
	public void afterConnectionEstablished(@NonNull WebSocketSession session) throws Exception {
		sessions.add(session);
	}

	@Override
	public void handleTextMessage(@NonNull WebSocketSession session, @NonNull TextMessage message) throws IOException {
		String payload = message.getPayload();

		for (WebSocketSession webSocketSession : sessions) {
			if (webSocketSession.isOpen() && !session.getId().equals(webSocketSession.getId())) {
				webSocketSession.sendMessage(new TextMessage(payload));
			}
		}
	}

	@Override
	public void afterConnectionClosed(@NonNull WebSocketSession session, @NonNull CloseStatus status) throws Exception {
		sessions.remove(session);
	}

	public void broadcastUpdate(@NonNull String updateMessage) {
		for (WebSocketSession session : sessions) {
			try {
				session.sendMessage(new TextMessage(updateMessage));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}

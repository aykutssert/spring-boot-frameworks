package com.aykutsert.config;

import com.aykutsert.api.WsUser;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import com.aykutsert.api.ChatController;

@Component
public class WebSocketEventListener {

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
        MessageHeaderAccessor headerAccessor =
                MessageHeaderAccessor.getAccessor(event.getMessage().getHeaders(),
                        MessageHeaderAccessor.class);
        StompHeaderAccessor stompHeaderAccessor = MessageHeaderAccessor.getAccessor(
                (Message<?>) headerAccessor.getHeader("simpConnectMessage"),
                StompHeaderAccessor.class);
        String senderId = stompHeaderAccessor.getNativeHeader("senderId").get(0);
        String senderName = stompHeaderAccessor.getNativeHeader("senderName").get(0);

        if (senderId != null && senderName != null) {
            System.out.println("User connected: " + senderName + " (" + senderId + ")");
            ChatController.getOnlineUsers().add(new WsUser(senderId, senderName));

        }
        else{
            System.out.println("User Connection failed");
        }
    }

}
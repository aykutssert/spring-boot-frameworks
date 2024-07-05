package com.aykutsert.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.HashSet;
import java.util.Set;

@Controller
@CrossOrigin
public class ChatController {

    private final SimpMessagingTemplate messagingTemplate;
    private static final Set<WsUser> onlineUsers = new HashSet<>();

    @Autowired
    public ChatController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/chat")
    public void chatEndpoint(@Payload WsMessage wsMessage) {
        String receiverName = wsMessage.getReceiverName();
        messagingTemplate.convertAndSend("/topic/"+receiverName+"/messages", wsMessage);
    }

    @MessageMapping("/getUsers")
    public void getUsers() {
        messagingTemplate.convertAndSend(
                "/topic/onlineUsers",
                onlineUsers
        );
    }

    @MessageMapping("/disconnect/{senderId}")
    public void handleDisconnect(@DestinationVariable String senderId) {


        if (senderId != null) {

            onlineUsers.removeIf(user ->  user.getId().equals(senderId));
            messagingTemplate.convertAndSend(
                    "/topic/onlineUsers",
                    onlineUsers
            );
            System.out.println("User disconnected: " + senderId);
        } else {
            System.out.println("User disconnected failed");
        }
    }

    public static Set<WsUser> getOnlineUsers() {
        return onlineUsers;
    }
}


package project.personal.personalstoremanagementproject.controllers.v1.chatwebsocket;

import org.apache.logging.log4j.message.SimpleMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatWebSocketController {

    /**
     * Send message
     * @param chatMessage
     * @return
     */
    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessageModel sendMessage(@Payload ChatMessageModel chatMessage) {
        return chatMessage;
    }

    /**
     * Add user
     * @param chatMessage
     * @param headerAccessor
     * @return
     */
    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessageModel addUser(@Payload ChatMessageModel chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }
}

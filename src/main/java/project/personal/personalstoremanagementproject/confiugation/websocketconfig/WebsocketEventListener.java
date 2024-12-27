package project.personal.personalstoremanagementproject.confiugation.websocketconfig;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import project.personal.personalstoremanagementproject.controllers.v1.chatwebsocket.ChatMessageModel;
import project.personal.personalstoremanagementproject.utils.constants.ConstantEnum;

/**
 * WebSocket event listener
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class WebsocketEventListener {

    private final SimpMessageSendingOperations messagingTemplate;

    /**
     * Handle WebSocket connect listener
     * @param event
     */
    @EventListener
    public void handleWebSocketConnectListener(SessionDisconnectEvent event) {
        // Get the StompHeaderAccessor object
        var stompHeaderAccessor = StompHeaderAccessor.wrap(event.getMessage());
        // Get the username from the session attributes
        String username = (String) stompHeaderAccessor.getSessionAttributes().get("username");
        // Log the username
        if (username != null) {
            log.info("User Disconnected : " + username);
            var chatMessage = ChatMessageModel.builder()
                    .content("User Disconnected")
                    .sender(username)
                    .type(ConstantEnum.MessageType.LEAVE)
                    .build();
            messagingTemplate.convertAndSend("/topic/public", chatMessage);
        }


    }
}

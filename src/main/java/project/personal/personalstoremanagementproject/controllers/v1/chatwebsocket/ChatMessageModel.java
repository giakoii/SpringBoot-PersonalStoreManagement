package project.personal.personalstoremanagementproject.controllers.v1.chatwebsocket;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.experimental.FieldDefaults;
import project.personal.personalstoremanagementproject.utils.constants.ConstantEnum;

@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
@Value
@AllArgsConstructor
@Builder
public class ChatMessageModel {
    String content;
    String sender;
    ConstantEnum.MessageType type;
}

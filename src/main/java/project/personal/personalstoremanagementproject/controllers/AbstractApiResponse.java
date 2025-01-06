package project.personal.personalstoremanagementproject.controllers;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import project.personal.personalstoremanagementproject.exceptions.DetailError;
import project.personal.personalstoremanagementproject.utils.Messages;

import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class AbstractApiResponse<T> {

    /**
     * Success
     */
     boolean success;

    /**
     * MessageId
     */
     String messageId;

    /**
     * Message
     */
     String message;

     /**
      * DetailErrorList
      */
     List<DetailError> detailErrorList ;

     /**
      * Response
      */
     T Response ;

    /**
      * Set Message
      * @param messageId
      */
     public void setMessage(String messageId, String... params) {
         this.messageId = messageId;
         this.message = Messages.getInstance().getMessages(messageId, params);
     }

}

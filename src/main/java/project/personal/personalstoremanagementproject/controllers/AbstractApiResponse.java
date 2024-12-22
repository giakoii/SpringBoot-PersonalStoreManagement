package project.personal.personalstoremanagementproject.controllers;

import lombok.Getter;
import lombok.Setter;
import project.personal.personalstoremanagementproject.exceptions.DetailError;
import project.personal.personalstoremanagementproject.exceptions.ErrorCode;

import java.util.List;

@Getter
@Setter
public abstract class AbstractApiResponse<T> {
    /**
     * Success
     */
     boolean success;

    /**
     * MessageId
     */
     ErrorCode messageId;

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
      * @param message
      * @param detailErrors
      */
    public void setMessage(ErrorCode messageId, String message, List<DetailError> detailErrors) {
        this.messageId = messageId;
        this.message = message;
        this.detailErrorList = detailErrors;
    }

}

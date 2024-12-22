package project.personal.personalstoremanagementproject.controllers;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
public abstract class AbstractApiRequest {
 /**
  * API Caller ID
  */
  String apiCallerId;
   /**
    * API Caller Name
    */
  boolean isOnlyValidation;
}

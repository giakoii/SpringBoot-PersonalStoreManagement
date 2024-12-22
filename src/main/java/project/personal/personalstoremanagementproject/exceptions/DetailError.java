package project.personal.personalstoremanagementproject.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author giakhoi
 */
@AllArgsConstructor
@Getter
@Setter
public class DetailError {
     String fieldName;
     String errorCode;
     String errorMessage;
}

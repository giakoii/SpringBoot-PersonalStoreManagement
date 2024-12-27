package project.personal.personalstoremanagementproject.controllers.v1.registerscreen;

import lombok.AccessLevel;
import lombok.Value;
import lombok.experimental.FieldDefaults;
import project.personal.personalstoremanagementproject.controllers.AbstractApiResponse;

@Value
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VerifyInformationResponse extends AbstractApiResponse<String> {

}

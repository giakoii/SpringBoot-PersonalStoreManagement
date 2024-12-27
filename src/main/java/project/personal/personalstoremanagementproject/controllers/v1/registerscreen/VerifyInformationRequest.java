package project.personal.personalstoremanagementproject.controllers.v1.registerscreen;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;
import lombok.experimental.FieldDefaults;
import project.personal.personalstoremanagementproject.controllers.AbstractApiRequest;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VerifyInformationRequest extends AbstractApiRequest {

    @NotBlank(message = "Key is required")
    String key;
}

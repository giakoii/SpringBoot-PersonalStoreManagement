package project.personal.personalstoremanagementproject.controllers.v1.registerscreen;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Value;
import project.personal.personalstoremanagementproject.controllers.AbstractApiRequest;
import project.personal.personalstoremanagementproject.entities.UserAccount;

import java.io.Serializable;

/**
 * DTO for {@link UserAccount}
 * @author giakhoi
 */
@Value
public class RegisterScreenRequest extends AbstractApiRequest implements Serializable {

    @NotBlank(message = "UserName is required")
    @Pattern(regexp = "^\\D[a-zA-Z0-9@]*$", message = "UserName must not start with a digit and can only contain letters, digits, and @")
    String userName;

    @Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters")
    @NotBlank(message = "Password is required")
    String password;

    @NotBlank(message = "Email is required")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$", message = "Email is invalid")
    String email;
}
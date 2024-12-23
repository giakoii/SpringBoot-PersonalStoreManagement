package project.personal.personalstoremanagementproject.controllers.v1.user.profilescreen.request;

import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Value;
import lombok.experimental.FieldDefaults;
import project.personal.personalstoremanagementproject.controllers.AbstractApiRequest;
import project.personal.personalstoremanagementproject.entities.UserAccount;

import java.io.Serializable;

/**
 * DTO for {@link UserAccount}
 */
@Value
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateUserProfileRequest extends AbstractApiRequest implements Serializable {

    /**
     * Full name
     */
    @Pattern(regexp = "^[a-zA-Z\\s]{1,255}$", message = "Full name must contain only letters and spaces")
    String fullName;

    /**
     * Nick name
     */
    @Pattern(regexp = "^[a-zA-Z\\s]{1,255}$", message = "Nick name must contain only letters and spaces")
    String nickName;

    /**
     * Phone number
     */
    @Pattern(regexp = "^(0)\\d{9,10}$", message = "Phone number must be in the format 0xxxxxxxxx")
    String phoneNumber;

    /**
     * Address
     */
    @Pattern(regexp = "^[a-zA-Z0-9\\s,]{1,255}$", message = "Address must contain only letters, numbers, spaces, and commas")
    String address;

    String avatarUrl;

}
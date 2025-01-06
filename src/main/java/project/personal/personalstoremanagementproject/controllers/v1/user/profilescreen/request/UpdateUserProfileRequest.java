package project.personal.personalstoremanagementproject.controllers.v1.user.profilescreen.request;

import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Value;
import lombok.experimental.FieldDefaults;
import project.personal.personalstoremanagementproject.controllers.AbstractApiRequest;
import project.personal.personalstoremanagementproject.utils.constants.ConstantEnum;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link UserAccount}
 */
@Value
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateUserProfileRequest extends AbstractApiRequest implements Serializable {

    @Pattern(regexp = "^[a-zA-Z\\s]{1,255}$", message = "Full name must contain only letters and spaces")
    String fullName;

    @Pattern(regexp = "^[a-zA-Z\\s]{1,255}$", message = "Nick name must contain only letters and spaces")
    String nickName;

    @Pattern(regexp = "^(0)\\d{9,10}$", message = "Phone number must be in the format 0xxxxxxxxx")
    String phoneNumber;

    @Pattern(regexp = "^[a-zA-Z0-9\\s,]{1,255}$", message = "Address must contain only letters, numbers, spaces, and commas")
    String address;

    LocalDate dateOfBirth;

    ConstantEnum.Gender gender;

    String avatarUrl;

    @Pattern(regexp = "^[a-zA-Z0-9\\s,]{1,255}$", message = "Address line must contain only letters, numbers, spaces, and commas")
    String addressLine;

    @Pattern(regexp = "^[a-zA-Z\\s]{1,255}$", message = "City must contain only letters and spaces")
    String city;

    @Pattern(regexp = "^[a-zA-Z\\s]{1,255}$", message = "State must contain only letters and spaces")
    String state;

    @Pattern(regexp = "^[a-zA-Z\\s]{1,255}$", message = "Country must contain only letters and spaces")
    String country;

    @Pattern(regexp = "^\\d{5,10}$", message = "Zip code must contain only numbers and be between 5 and 10 digits")
    String zipCode;
}
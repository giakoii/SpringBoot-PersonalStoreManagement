package project.personal.personalstoremanagementproject.controllers.v1.user.profilescreen.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Value
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class SelectUserProfileModel{
    /**
     * Full name
     */
    String fullName;

    /**
     * Nick name
     */
    String nickName;

    /**
     * Phone number
     */
    String phoneNumber;

    /**
     * Email
     */
    String email;

    /**
     * Address
     */
    String address;

    /**
     * Image
     */
    String avatarUrl;
}

package project.personal.personalstoremanagementproject.controllers.v1.user.profilescreen.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Value
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class SelectUserProfileModel{
    String email;
    String fullName;
    LocalDate dateOfBirth;
    String phoneNumber;
    String avatarUrl;
    String nickName;
    String gender;
    String addressLine;
    String city;
    String state;
    String country;
    String zipCode;
}

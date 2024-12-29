package project.personal.personalstoremanagementproject.controllers.v1.admin.manageuser;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class SelectAllUserModel {
    String username;

    String fullName;

    LocalDate dateOfBirth;

    String gender;

    String email;

    String city;

    String country;
}

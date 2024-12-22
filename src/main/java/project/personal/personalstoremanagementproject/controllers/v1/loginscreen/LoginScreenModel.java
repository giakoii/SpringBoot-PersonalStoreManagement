package project.personal.personalstoremanagementproject.controllers.v1.loginscreen;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@Getter
public class LoginScreenModel {
    String token;
    String refreshToken;
    String expirationTime;
}

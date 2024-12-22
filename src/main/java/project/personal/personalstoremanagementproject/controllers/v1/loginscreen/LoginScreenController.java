package project.personal.personalstoremanagementproject.controllers.v1.loginscreen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.personal.personalstoremanagementproject.controllers.AbstractApiController;
import project.personal.personalstoremanagementproject.exceptions.ErrorCode;
import project.personal.personalstoremanagementproject.repositories.UserRepository;
import project.personal.personalstoremanagementproject.services.JwtService;
import project.personal.personalstoremanagementproject.exceptions.DetailError;

import java.util.List;

@RequestMapping("/api/v1/login")
@RestController
public class LoginScreenController extends AbstractApiController<LoginScreenRequest, LoginScreenResponse, LoginScreenModel> {

    private final JwtService jwtService;

    private final PasswordEncoder passwordEncoder;

    public LoginScreenController(AuthenticationManager authenticationManager, UserRepository userRepository, JwtService jwtService, PasswordEncoder passwordEncoder) {
        super(authenticationManager, userRepository, jwtService);
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Main processing
     *
     * @param request the request to process
     * @return
     */
    @Override
    protected LoginScreenResponse exec(LoginScreenRequest request) {
        // Find user by username in database
        var user = userRepository.findByUsername(request.getUserName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Generate token and refresh token
        var token = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);

        // Create LoginScreenEntity
        var loginEntity = LoginScreenModel.builder()
                .token(token)
                .refreshToken(refreshToken)
                .expirationTime("24Hrs")
                .build();

        // True
        LoginScreenResponse loginResponse = new LoginScreenResponse();
        loginResponse.setSuccess(true);
        loginResponse.setMessage(ErrorCode.SUCCESS, "Login successful", detailErrorList);
        loginResponse.setResponse(loginEntity);
        return loginResponse;
    }

    /**
     * Error check
     * @param request         the request to check
     * @param detailErrorList list of detected errors
     * @return
     */
    @Override
    protected LoginScreenResponse validate(LoginScreenRequest request, List<DetailError> detailErrorList) {
        if (!detailErrorList.isEmpty()) {
            LoginScreenResponse response = new LoginScreenResponse();
            response.setSuccess(false);
            response.setMessage(ErrorCode.VALIDATION_ERROR, "Validation errors occurred", detailErrorList);
            return response;
        }
        return null;
    }
}

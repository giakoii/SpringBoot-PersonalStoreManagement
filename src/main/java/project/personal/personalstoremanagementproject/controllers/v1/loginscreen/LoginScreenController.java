package project.personal.personalstoremanagementproject.controllers.v1.loginscreen;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.personal.personalstoremanagementproject.controllers.AbstractApiController;
import project.personal.personalstoremanagementproject.exceptions.DetailError;
import project.personal.personalstoremanagementproject.repositories.UserRepository;
import project.personal.personalstoremanagementproject.services.JwtService;
import project.personal.personalstoremanagementproject.utils.MessageId;
import project.personal.personalstoremanagementproject.utils.StringUtil;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Controller for login screen
 */
@RequestMapping("/api/v1/LoginScreen")
@RestController
public class LoginScreenController extends AbstractApiController<LoginScreenRequest, LoginScreenResponse, LoginScreenModel> {

    private final JwtService jwtService;

    /**
     * Constructor
     *
     * @param userRepository
     * @param jwtService
     */
    public LoginScreenController(UserRepository userRepository, JwtService jwtService) {
        super(userRepository, jwtService);
        this.jwtService = jwtService;
    }

    /**
     * Main processing
     *
     * @param request the request to process
     * @return
     */
    @Override
    protected LoginScreenResponse exec(LoginScreenRequest request) {
        var loginResponse = new LoginScreenResponse();
        // Find user by username in database
        var user = userRepository.findByUsernameAndIsActiveTrue(request.getUserName());
        // Check if user is not found
        if (user == null) {
            loginResponse.setSuccess(false);
            loginResponse.setMessage(MessageId.E0005);
            return loginResponse;
        }
        // Check if password is correct
        var passwordEncoder = new BCryptPasswordEncoder(10);
        if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
            loginResponse.setSuccess(false);
            loginResponse.setMessage(MessageId.E0005);
            return loginResponse;
        }
        // Generate token and refresh token
        var token = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        // Create LoginScreenEntity
        var loginEntity = LoginScreenModel.builder()
                .token(token)
                .refreshToken(refreshToken)
                .expirationTime("24Hrs")
                .build();
        user.setLastLogin(Instant.now());
        userRepository.save(user);
        // True
        loginResponse.setSuccess(true);
        loginResponse.setMessage(MessageId.I0001);
        loginResponse.setResponse(loginEntity);
        return loginResponse;
    }

    /**
     * Error check
     * @param request
     * @param detailErrorList
     * @return
     */
    @Override
    protected LoginScreenResponse validate(LoginScreenRequest request, List<DetailError> detailErrorList) {
        var response = new LoginScreenResponse();
        if (!detailErrorList.isEmpty()) {
            response.setSuccess(false);
            response.setMessage(MessageId.E0000);
            return response;
        }
        // True
        response.setSuccess(true);
        return response;
    }
}

package project.personal.personalstoremanagementproject.controllers.v1.loginscreen;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.personal.personalstoremanagementproject.controllers.AbstractApiController;
import project.personal.personalstoremanagementproject.exceptions.DetailError;
import project.personal.personalstoremanagementproject.repositories.UserRepository;
import project.personal.personalstoremanagementproject.services.JwtService;
import project.personal.personalstoremanagementproject.utils.MessageId;

import java.util.List;

@RequestMapping("/api/v1/login")
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
        var user = userRepository.findByUsername(request.getUserName());
        // Check if user is not found
        if (user.isEmpty()) {
            loginResponse.setSuccess(false);
            loginResponse.setMessage(MessageId.E0005);
            return loginResponse;
        }

        // Check if password is correct
        var passwordEncoder = new BCryptPasswordEncoder(10);
        if (!passwordEncoder.matches(request.getPassword(), user.get().getPasswordHash())) {
            loginResponse.setSuccess(false);
            loginResponse.setMessage(MessageId.E0005);
            return loginResponse;
        }

        // Generate token and refresh token
        var token = jwtService.generateToken(user.get());
        var refreshToken = jwtService.generateRefreshToken(user.get());

        // Create LoginScreenEntity
        var loginEntity = LoginScreenModel.builder()
                .token(token)
                .refreshToken(refreshToken)
                .expirationTime("24Hrs")
                .build();

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
        if (!detailErrorList.isEmpty()) {
            LoginScreenResponse response = new LoginScreenResponse();
            response.setSuccess(false);
            response.setMessage(MessageId.E0000);
            return response;
        }
        return null;
    }
}

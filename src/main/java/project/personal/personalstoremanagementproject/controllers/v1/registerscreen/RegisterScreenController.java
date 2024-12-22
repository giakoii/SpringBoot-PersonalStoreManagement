package project.personal.personalstoremanagementproject.controllers.v1.registerscreen;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.personal.personalstoremanagementproject.controllers.AbstractApiController;
import project.personal.personalstoremanagementproject.entities.UserAccount;
import project.personal.personalstoremanagementproject.services.JwtService;
import project.personal.personalstoremanagementproject.utils.constants.ConstantEnum;
import project.personal.personalstoremanagementproject.exceptions.ErrorCode;
import project.personal.personalstoremanagementproject.repositories.UserRepository;
import project.personal.personalstoremanagementproject.exceptions.DetailError;

import java.util.List;

/**
 * @author giakhoi
 */
@RequestMapping("api/v1/register")
@RestController
public class RegisterScreenController extends AbstractApiController<RegisterScreenRequest, RegisterScreenResponse, String> {

    private final RegisterScreenSendMailResult registerScreenSendMailResult;

    /**
     * Constructor
     * @param authenticationManager
     * @param userRepository
     * @param jwtService
     * @param registerScreenSendMailResult
     */
    public RegisterScreenController(AuthenticationManager authenticationManager, UserRepository userRepository, JwtService jwtService, RegisterScreenSendMailResult registerScreenSendMailResult) {
        super(authenticationManager, userRepository, jwtService);
        this.registerScreenSendMailResult = registerScreenSendMailResult;
    }

    /**
     * Main processing
     * @param request the request to process
     * @return
     */
    @Override
    protected RegisterScreenResponse exec(RegisterScreenRequest request) throws Exception {
        var response = new RegisterScreenResponse();
        // Find user by username and email
        var user = userRepository.existsByEmailAndUsername(request.getEmail(), request.getUserName());
        if (user) {
            response.setSuccess(false);
            response.setMessage(ErrorCode.VALIDATION_ERROR, "User already exists", detailErrorList);
            return response;
        }
        // Generate password encoder
        var passwordEncoder = new BCryptPasswordEncoder(10);
        // Create new user
        var newUser = UserAccount.builder()
                .username(request.getUserName())
                .passwordHash(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .role(ConstantEnum.Role.CUSTOMER)
                .lastLogin(null)
                .build();
        newUser.setIsActive(false);
        // Save to database
        userRepository.save(newUser);
        // Send mail to user
        registerScreenSendMailResult.sendMail("RegisterScreen", newUser);
        // True
        response.setSuccess(true);
        response.setMessage(ErrorCode.SUCCESS,"Create user successful", detailErrorList);
        return response;
    }

    /**
     * Error check
     * @param request the request to check
     * @param detailErrorList list of detected errors
     * @return
     */
    @Override
    protected RegisterScreenResponse validate(RegisterScreenRequest request, List<DetailError> detailErrorList) {
        if (!detailErrorList.isEmpty()) {
            RegisterScreenResponse response = new RegisterScreenResponse();
            response.setSuccess(false);
            response.setMessage(ErrorCode.VALIDATION_ERROR, "Validation errors occurred", detailErrorList);
            return response;
        }
        return null;
    }
}

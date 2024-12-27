package project.personal.personalstoremanagementproject.controllers.v1.registerscreen;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.personal.personalstoremanagementproject.controllers.AbstractApiController;
import project.personal.personalstoremanagementproject.exceptions.DetailError;
import project.personal.personalstoremanagementproject.repositories.UserRepository;
import project.personal.personalstoremanagementproject.services.JwtService;
import project.personal.personalstoremanagementproject.utils.MessageId;
import project.personal.personalstoremanagementproject.utils.StringUtil;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Verifying information user
 */
@RequestMapping("api/v1/verify")
@RestController
public class VerifyInformationController extends AbstractApiController<VerifyInformationRequest, VerifyInformationResponse, String> {

    private final StringUtil stringUtil;
    /**
     * Constructor
     * @param userRepository
     * @param jwtService
     */
    public VerifyInformationController(UserRepository userRepository, JwtService jwtService, StringUtil stringUtil) {
        super(userRepository, jwtService);
        this.stringUtil = stringUtil;
    }

    /**
     * Main processing
     * @param request the request to process
     * @return
     */
    @Override
    protected VerifyInformationResponse exec(VerifyInformationRequest request) throws Exception {
        var response = new VerifyInformationResponse();

        // Check if the key is valid
        String key = stringUtil.decrypt(request.getKey());
        // Find user by userId
        String userName = key.replaceFirst("\\d+", "");
        // Set active for user
        var user = userRepository.findByUsernameAndIsActiveFalse(userName);
        // If user is empty
        if (user.isEmpty()){
            response.setSuccess(false);
            response.setMessage(MessageId.E0000, "Key is invalid");
            return response;
        }
        // Check created time of user
        if (user.get().getCreatedAt().isAfter(LocalDateTime.now().minusDays(1))) {
            response.setSuccess(false);
            response.setMessage(MessageId.E0000, "Key is invalid");
            return response;
        }

        // Set active for user
        user.get().setIsActive(true);

        // True
        response.setSuccess(true);
        response.setMessage(MessageId.I0001);
        return response;
    }

    /**
     * Validate request
     * @param request
     * @param detailErrorList
     * @return
     */
    @Override
    protected VerifyInformationResponse validate(VerifyInformationRequest request, List<DetailError> detailErrorList) {
        if (!detailErrorList.isEmpty()) {
            var response = new VerifyInformationResponse();
            response.setSuccess(false);
            response.setMessage(MessageId.E0000, "Validation errors occurred");
            return response;
        }
        return null;
    }
}

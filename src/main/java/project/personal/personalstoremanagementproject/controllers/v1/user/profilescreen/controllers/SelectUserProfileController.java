package project.personal.personalstoremanagementproject.controllers.v1.user.profilescreen.controllers;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.personal.personalstoremanagementproject.controllers.AbstractApiController;
import project.personal.personalstoremanagementproject.controllers.v1.user.profilescreen.request.SelectUserProfileRequest;
import project.personal.personalstoremanagementproject.controllers.v1.user.profilescreen.responses.SelectUserProfileResponse;
import project.personal.personalstoremanagementproject.exceptions.DetailError;
import project.personal.personalstoremanagementproject.controllers.v1.user.profilescreen.models.SelectUserProfileModel;
import project.personal.personalstoremanagementproject.repositories.UserRepository;
import project.personal.personalstoremanagementproject.services.JwtService;
import project.personal.personalstoremanagementproject.utils.MessageId;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user/select-profile")
public class SelectUserProfileController extends AbstractApiController<SelectUserProfileRequest, SelectUserProfileResponse, SelectUserProfileModel> {

    public SelectUserProfileController(AuthenticationManager authenticationManager, UserRepository userRepository, JwtService jwtService) {
        super(userRepository, jwtService);
    }

    /**
     * Main process of the controller
     * @param request the request to process
     * @return
     */
    @Override
    protected SelectUserProfileResponse exec(SelectUserProfileRequest request) throws Exception {
        var response = new SelectUserProfileResponse();

        var user = getCurrentUser();
        // Check if user is not authenticated
        if (user == null) {
            response.setSuccess(false);
            response.setMessage(MessageId.E0000, "User not authenticated", null);
            return response;
        }
        // Create model
        var model = SelectUserProfileModel.builder()
                .fullName(user.getFullName())
                .phoneNumber(user.getPhoneNumber())
                .address(user.getAddress())
                .avatarUrl(user.getAvatarUrl())
                .email(user.getEmail())
                .nickName(user.getNickName())
                .build();

        // True
        response.setSuccess(true);
        response.setMessage(MessageId.I0001, "Select user information successful");
        response.setResponse(model);
        return response;
    }

    /**
     * Error check
     * @param request
     * @param detailErrorList
     * @return
     */
    @Override
    protected SelectUserProfileResponse validate(SelectUserProfileRequest request, List<DetailError> detailErrorList) {
        if (!detailErrorList.isEmpty()) {
            SelectUserProfileResponse response = new SelectUserProfileResponse();
            response.setSuccess(false);
            response.setMessage(MessageId.E0000, "Validation errors occurred");
            return response;
        }
        return null;
    }
}

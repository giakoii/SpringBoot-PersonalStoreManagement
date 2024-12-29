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
import project.personal.personalstoremanagementproject.repositories.ViewUserInformationRepository;
import project.personal.personalstoremanagementproject.services.JwtService;
import project.personal.personalstoremanagementproject.utils.MessageId;

import java.time.LocalDate;
import java.util.List;

/**
 * Controller for selecting user profile
 */
@RestController
@RequestMapping("/api/v1/SelectUserProfile")
public class SelectUserProfileController extends AbstractApiController<SelectUserProfileRequest, SelectUserProfileResponse, SelectUserProfileModel> {

    private final ViewUserInformationRepository viewUserInformationRepository;

    /**
     * Constructor
     * @param userRepository
     * @param jwtService
     * @param viewUserInformationRepository
     */
    public SelectUserProfileController(UserRepository userRepository, JwtService jwtService, ViewUserInformationRepository viewUserInformationRepository) {
        super(userRepository, jwtService);
        this.viewUserInformationRepository = viewUserInformationRepository;
    }

    /**
     * Main process of the controller
     * @param request the request to process
     * @return
     */
    @Override
    protected SelectUserProfileResponse exec(SelectUserProfileRequest request) throws Exception {
        var response = new SelectUserProfileResponse();

        var userInfor = viewUserInformationRepository.findById(getCurrentUser().getUserId());

        // Create model
        var model = SelectUserProfileModel.builder()
                .email(userInfor.get().getEmail())
                .fullName(userInfor.get().getFullName())
                .dateOfBirth(userInfor.get().getDateOfBirth())
                .phoneNumber(userInfor.get().getPhoneNumber())
                .avatarUrl(userInfor.get().getAvatarUrl())
                .nickName(userInfor.get().getNickName())
                .gender(userInfor.get().getGender())
                .addressLine(userInfor.get().getAddressLine())
                .city(userInfor.get().getCity())
                .state(userInfor.get().getState())
                .country(userInfor.get().getCountry())
                .zipCode(userInfor.get().getZipCode())
                .build();

        // True
        response.setSuccess(true);
        response.setMessage(MessageId.I0001);
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

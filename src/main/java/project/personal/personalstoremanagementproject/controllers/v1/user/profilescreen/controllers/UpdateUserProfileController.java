package project.personal.personalstoremanagementproject.controllers.v1.user.profilescreen.controllers;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.personal.personalstoremanagementproject.controllers.AbstractApiController;
import project.personal.personalstoremanagementproject.controllers.v1.user.profilescreen.request.UpdateUserProfileRequest;
import project.personal.personalstoremanagementproject.controllers.v1.user.profilescreen.responses.UpdateUserProfileResponse;
import project.personal.personalstoremanagementproject.exceptions.DetailError;
import project.personal.personalstoremanagementproject.repositories.UserRepository;
import project.personal.personalstoremanagementproject.services.JwtService;
import project.personal.personalstoremanagementproject.utils.MessageId;

import java.util.List;

/**
 * Controller for updating user profile
 */
@RestController
@RequestMapping("/api/v1/user/update-profile")
public class UpdateUserProfileController extends AbstractApiController<UpdateUserProfileRequest, UpdateUserProfileResponse, String> {

    /**
     * Constructor
     * @param authenticationManager
     * @param userRepository
     * @param jwtService
     */
    public UpdateUserProfileController(AuthenticationManager authenticationManager, UserRepository userRepository, JwtService jwtService) {
        super(userRepository, jwtService);
    }

    /**
     * Main process of the controller
     * @param request the request to process
     * @return
     */
    @Override
    protected UpdateUserProfileResponse exec(UpdateUserProfileRequest request) {
        var response = new UpdateUserProfileResponse();

        var user = getCurrentUser();
        if (user == null) {
            response.setSuccess(false);
            response.setMessage(MessageId.E0000, "User not authenticated");
            return response;
        }
        // Update user information
        if (request.getFullName() != null){
            user.setFullName(request.getFullName());
        }
        if (request.getNickName() != null){
            user.setNickName(request.getNickName());
        }
        if (request.getPhoneNumber() != null){
            user.setPhoneNumber(request.getPhoneNumber());
        }
        if (request.getAddress() != null){
            user.setAddress(request.getAddress());
        }
        if (request.getAvatarUrl() != null){
            user.setAvatarUrl(request.getAvatarUrl());
        }
        saveChange(user, request, false);
        userRepository.save(user);

        // True
        response.setSuccess(true);
        response.setMessage(MessageId.I0001 ,"Update user information successful");
        return response;
    }

    /**
     * Check for errors
     * @param request the request to check
     * @param detailErrorList the list of errors
     * @return
     */
    @Override
    protected UpdateUserProfileResponse validate(UpdateUserProfileRequest request, List<DetailError> detailErrorList) {
        if (!detailErrorList.isEmpty()) {
            UpdateUserProfileResponse response = new UpdateUserProfileResponse();
            response.setSuccess(false);
            response.setMessage(MessageId.E0000, "Validation errors occurred");
            return response;
        }
        return null;
    }
}

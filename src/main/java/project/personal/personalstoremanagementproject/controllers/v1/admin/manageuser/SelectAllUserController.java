package project.personal.personalstoremanagementproject.controllers.v1.admin.manageuser;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.personal.personalstoremanagementproject.controllers.AbstractApiController;
import project.personal.personalstoremanagementproject.exceptions.DetailError;
import project.personal.personalstoremanagementproject.repositories.UserRepository;
import project.personal.personalstoremanagementproject.repositories.ViewUserRepository;
import project.personal.personalstoremanagementproject.services.JwtService;
import project.personal.personalstoremanagementproject.utils.MessageId;

import java.util.List;

/**
 * Controller for the SelectAllUser API.
 */
@RequestMapping("api/v1/SelectAllUser")
@RestController
public class SelectAllUserController extends AbstractApiController<SelectAllUserRequest, SelectAllUserResponse, List<SelectAllUserModel>> {

    private final ViewUserRepository viewUserRepository;

    /**
     * Constructor
     * @param userRepository
     * @param jwtService
     * @param viewUserRepository
     */
    public SelectAllUserController(UserRepository userRepository, JwtService jwtService, ViewUserRepository viewUserRepository) {
        super(userRepository, jwtService);
        this.viewUserRepository = viewUserRepository;
    }

    /**
     * Main process of the controller
     * @param request the request to process
     * @return
     */
    @Override
    protected SelectAllUserResponse exec(SelectAllUserRequest request) throws Exception {
        var response = new SelectAllUserResponse();
        // Get all users
        var users = viewUserRepository.findAll();

        var userResponse = users.stream().map(user -> {
            return SelectAllUserModel.builder()
                            .username(user.getUsername())
                            .fullName(user.getFullName())
                            .dateOfBirth(user.getDateOfBirth())
                            .gender(user.getGender())
                            .email(user.getEmail())
                            .city(user.getCity())
                            .country(user.getCountry())
                            .build();
        }).toList();

        // True
        response.setSuccess(true);
        response.setMessage(MessageId.I0001);
        response.setResponse(userResponse);
        return response;
    }

    /**
     * Error check
     * @param request
     * @param detailErrorList
     * @return
     */
    @Override
    protected SelectAllUserResponse validate(SelectAllUserRequest request, List<DetailError> detailErrorList) {
        var response = new SelectAllUserResponse();
        if (!detailErrorList.isEmpty()) {
            response.setSuccess(false);
            response.setMessage(MessageId.E0000, "Validation errors occurred");
            return response;
        }
        return null;
    }
}

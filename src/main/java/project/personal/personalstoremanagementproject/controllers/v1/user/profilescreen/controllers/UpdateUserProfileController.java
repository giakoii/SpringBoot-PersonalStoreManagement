package project.personal.personalstoremanagementproject.controllers.v1.user.profilescreen.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.personal.personalstoremanagementproject.controllers.AbstractApiController;
import project.personal.personalstoremanagementproject.controllers.v1.user.profilescreen.request.UpdateUserProfileRequest;
import project.personal.personalstoremanagementproject.controllers.v1.user.profilescreen.responses.UpdateUserProfileResponse;
import project.personal.personalstoremanagementproject.entities.Address;
import project.personal.personalstoremanagementproject.entities.UserAccount;
import project.personal.personalstoremanagementproject.exceptions.DetailError;
import project.personal.personalstoremanagementproject.repositories.AddressRepository;
import project.personal.personalstoremanagementproject.repositories.UserRepository;
import project.personal.personalstoremanagementproject.services.JwtService;
import project.personal.personalstoremanagementproject.utils.MessageId;

import java.util.List;

/**
 * Controller for updating user profile
 */
@RestController
@RequestMapping("/api/v1/UpdateUserProfile")
public class UpdateUserProfileController extends AbstractApiController<UpdateUserProfileRequest, UpdateUserProfileResponse, String> {

    private final AddressRepository addressRepository;
    
    /**
     * Constructor
     * @param userRepository
     * @param jwtService
     * @param addressRepository
     */
    public UpdateUserProfileController(UserRepository userRepository, JwtService jwtService, AddressRepository addressRepository) {
        super(userRepository, jwtService);
        this.addressRepository = addressRepository;
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
        // Update user information
        updateUserInformation(request, user);
        saveChange(user, request, false);
        userRepository.save(user);

        // Update address information
        var addressUser = addressRepository.findByUserId(user.getUserId());
        if (addressUser.isEmpty()) {
            // Create new address
            Address address = new Address();
            updateAddressUser(request, address, user.getUserId());
            saveChange(address, request, true);
            addressRepository.save(address);
        } else {
            // Update address
            updateAddressUser(request, addressUser.get(), user.getUserId());
            saveChange(addressUser.get(), request, true);
            addressRepository.save(addressUser.get());
        }

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

    /**
     * Update user information
     * @param request the request to update
     * @param user the user to update
     */
    private void updateUserInformation(UpdateUserProfileRequest request, UserAccount user) {
            if (request.getFullName() != null) {
                user.setFullName(request.getFullName());
            }
            if (request.getNickName() != null) {
                user.setNickName(request.getNickName());
            }
            if (request.getPhoneNumber() != null) {
                user.setPhoneNumber(request.getPhoneNumber());
            }
            if (request.getAvatarUrl() != null) {
                user.setAvatarUrl(request.getAvatarUrl());
            }
            if (request.getGender() != null) {
                user.setGender(request.getGender());
            }
            if (request.getDateOfBirth() != null) {
                user.setDateOfBirth(request.getDateOfBirth());
            }
    }

    /**
     * Update address information
     * @param request the request to update
     * @param address the address to update
     * @param userId the user id
     */
    private void updateAddressUser(UpdateUserProfileRequest request, Address address, Long userId) {
        if (request.getAddressLine() != null) {
            address.setAddressLine(request.getAddressLine());
        }
        if (request.getCity() != null) {
            address.setCity(request.getCity());
        }
        if (request.getState() != null) {
            address.setState(request.getState());
        }
        if (request.getCountry() != null) {
            address.setCountry(request.getCountry());
        }
        if (request.getZipCode() != null) {
            address.setZipCode(request.getZipCode());
        }
        address.setUserId(userId);
    }
}

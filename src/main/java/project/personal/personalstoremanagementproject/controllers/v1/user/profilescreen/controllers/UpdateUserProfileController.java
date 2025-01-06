package project.personal.personalstoremanagementproject.controllers.v1.user.profilescreen.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.personal.personalstoremanagementproject.controllers.AbstractApiController;
import project.personal.personalstoremanagementproject.controllers.v1.user.profilescreen.request.UpdateUserProfileRequest;
import project.personal.personalstoremanagementproject.controllers.v1.user.profilescreen.responses.UpdateUserProfileResponse;
import project.personal.personalstoremanagementproject.entities.Address;
import project.personal.personalstoremanagementproject.entities.Customer;
import project.personal.personalstoremanagementproject.entities.UserAccount;
import project.personal.personalstoremanagementproject.exceptions.DetailError;
import project.personal.personalstoremanagementproject.repositories.AddressRepository;
import project.personal.personalstoremanagementproject.repositories.CustomerRepository;
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

    private final CustomerRepository customerRepository;
    
    /**
     * Constructor
     * @param userRepository
     * @param jwtService
     * @param addressRepository
     */
    public UpdateUserProfileController(UserRepository userRepository, JwtService jwtService, AddressRepository addressRepository, CustomerRepository customerRepository) {
        super(userRepository, jwtService);
        this.addressRepository = addressRepository;
        this.customerRepository = customerRepository;
    }

    /**
     * Main process of the controller
     * @param request the request to process
     * @return
     */
    @Override
    protected UpdateUserProfileResponse exec(UpdateUserProfileRequest request) {
        var response = new UpdateUserProfileResponse();
        // Get current user
        var user = getCurrentUser();
        // Get customer information
        var customerInformation = customerRepository.findById(user.getUserId()).orElse(null);
        // Get address information
        var addressUser = addressRepository.findById(user.getUserId()).orElse(null);
        // Update user information
        updateCustomerInformation(request, user, customerInformation);
        saveChange(user, request, false);
        // Update address information
        updateAddressUser(request, user, addressUser);

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
        var response = new UpdateUserProfileResponse();
        if (!detailErrorList.isEmpty()) {
            response.setSuccess(false);
            response.setMessage(MessageId.E0000, "Validation errors occurred");
            response.setDetailErrorList(detailErrorList);
            return response;
        }
        return null;
    }

    /**
     * Update user information
     * @param request the request to update
     * @param user the user to update
     */
    private void updateCustomerInformation(UpdateUserProfileRequest request, UserAccount user, Customer customerInformation) {
        if (request.getPhoneNumber() != null) {
            user.setPhoneNumber(request.getPhoneNumber());
        }
        if (request.getAvatarUrl() != null) {
            user.setAvatarUrl(request.getAvatarUrl());
        }
        if (request.getGender() != null) {
            user.setGender(request.getGender());
        }
        if (customerInformation != null){
            if (request.getDateOfBirth() != null) {
                customerInformation.setDateOfBirth(request.getDateOfBirth());
            }
            if (request.getFullName() != null) {
                customerInformation.setFullName(request.getFullName());
            }
            if (request.getNickName() != null) {
                customerInformation.setNickName(request.getNickName());
            }
        } else {
            customerInformation =  Customer.builder()
                    .userId(user.getUserId())
                    .fullName(request.getFullName())
                    .nickName(request.getNickName())
                    .dateOfBirth(request.getDateOfBirth())
                    .build();
        }
        userRepository.save(user);
        customerRepository.save(customerInformation);
    }

    /**
     * Update address information
     * @param request the request to update
     * @param address the address to update
     * @param userId the user id
     */
    private void updateAddressUser(UpdateUserProfileRequest request, UserAccount user, Address address) {
        if (address != null){
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
        } else {
            address = Address.builder()
                    .addressLine(request.getAddressLine())
                    .city(request.getCity())
                    .state(request.getState())
                    .country(request.getCountry())
                    .zipCode(request.getZipCode())
                    .userId(user.getUserId())
                    .build();
        }
        addressRepository.save(address);
    }
}

package project.personal.personalstoremanagementproject.controllers;

import jakarta.validation.Valid;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import project.personal.personalstoremanagementproject.entities.BaseEntity;
import project.personal.personalstoremanagementproject.entities.UserAccount;
import project.personal.personalstoremanagementproject.exceptions.DetailError;
import project.personal.personalstoremanagementproject.repositories.UserRepository;
import project.personal.personalstoremanagementproject.services.JwtService;
import project.personal.personalstoremanagementproject.utils.MessageId;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author giakhoi
 */
@RestController
public abstract class AbstractApiController<T extends AbstractApiRequest, U extends AbstractApiResponse<V>, V> {
    protected final UserRepository userRepository;

    protected AbstractApiController(UserRepository userRepository, JwtService jwtService) {
        this.userRepository = userRepository;
    }

    /**
     * API entry point
     * @param request the request body
     * @param token the JWT token
     * @return ResponseEntity containing the response
     */
    @Transactional(rollbackFor = Exception.class)
    @PostMapping
    public U post(@Valid @RequestBody T request, U response) throws Exception {
        // Perform validation
        U validationResponse = null;
        List<DetailError> detailErrorList = new ArrayList<>();
        try {
            validationResponse = validate(request, detailErrorList);
            if (validationResponse.isSuccess() && !request.isOnlyValidation()){
                return exec(request);
            }
        } catch (Exception e) {
            e.printStackTrace();
            validationResponse.setSuccess(false);
            validationResponse.setMessage(MessageId.E0000, "An error occurred");
            validationResponse.setDetailErrorList(detailErrorList);
            return validationResponse;
        }
        return validationResponse;
    }

    /**
     * Main processing
     * @param request the request to process
     * @return ResponseEntity containing the response
     */
    protected abstract U exec(T request) throws Exception;

    /**
     * Error check
     * @param request the request to check
     * @param detailErrorList list of detected errors
     * @return ResponseEntity with error response or null if no errors found
     */
    protected abstract U validate(T request, List<DetailError> detailErrorList);

    protected void saveChange(BaseEntity entity, T request, boolean isCreate) {
        entity.setApiCallerId(request.apiCallerId);
        entity.setUpdatedAt(LocalDateTime.now());
        entity.setUpdatedBy(getCurrentUser().getUsername());
        if (isCreate) {
            entity.setCreatedAt(LocalDateTime.now());
            entity.setCreatedBy(getCurrentUser().getUsername());
            entity.setIsActive(true);
        }
    }

    /**
     * Get the current authenticated user
     *
     * @return the current authenticated user
     */
    protected UserAccount getCurrentUser() {
        var response = new ConcreteApiResponse<>();
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        try{
            if (authentication != null && authentication.isAuthenticated()) {
                if (authentication.getPrincipal() instanceof User) {
                    // Get information from the principal
                    User springUser = (User) authentication.getPrincipal();
                    // Find the user in the database
                    return userRepository.findByUsernameAndIsActiveTrue(springUser.getUsername());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setSuccess(false);
            response.setMessage(MessageId.E0000, "Authentication failed");
            return null;
        }
        return null;
    }
}
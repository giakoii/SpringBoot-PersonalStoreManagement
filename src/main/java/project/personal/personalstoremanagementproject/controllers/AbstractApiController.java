package project.personal.personalstoremanagementproject.controllers;

import jakarta.validation.Valid;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import project.personal.personalstoremanagementproject.entities.UserAccount;
import project.personal.personalstoremanagementproject.exceptions.DetailError;
import project.personal.personalstoremanagementproject.repositories.UserRepository;
import project.personal.personalstoremanagementproject.services.JwtService;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author giakhoi
 */
@RestController
public abstract class AbstractApiController<T extends AbstractApiRequest, U extends AbstractApiResponse<V>, V> {

    protected final UserRepository userRepository;
    protected List<DetailError> detailErrorList;

    public AbstractApiController(UserRepository userRepository, JwtService jwtService) {
        this.userRepository = userRepository;
    }

    /**
     * API entry point
     * @param request the request body
     * @param token the JWT token
     * @return ResponseEntity containing the response
     */
    @Transactional
    @PostMapping
    public U post(@Valid @RequestBody T request, @RequestHeader(value = "Authorization", required = false) String token) throws Exception {
        // Perform validation
        List<DetailError> detailErrorList = validate(request);

        // Check if the request is for validation only
        System.out.println("isOnlyValidation: " + request.isOnlyValidation());
        if (request.isOnlyValidation) {
            U validationResponse = validate(request, detailErrorList);
            return validationResponse;
            // No errors found, return null as requested
        }

        // Error check
        U errorResponse = validate(request, detailErrorList);
        if (errorResponse != null) {
            return errorResponse;
        }

        // Set the API caller ID
        if (request.getApiCallerId() == null) {
            request.setApiCallerId("system");
        }

        // Main processing
        try {
            return exec(request);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
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

    /**
     * Validation method for request
     * @param request the request to validate
     * @return List of detected errors
     */
    private List<DetailError> validate(T request) {
        // Implement validation logic here and return a list of errors
        return List.of();
    }

    protected void saveChange(UserAccount user){
        user.setUpdatedBy(user.getUsername());
        user.setUpdatedAt(LocalDateTime.now());
    }

    /**
     * Get the current authenticated user
     * @return the current authenticated user
     */
    protected UserAccount getCurrentUser() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            if (authentication.getPrincipal() instanceof org.springframework.security.core.userdetails.User) {
                // Get information from the principal
                org.springframework.security.core.userdetails.User springUser =
                        (org.springframework.security.core.userdetails.User) authentication.getPrincipal();

                // Find the user in the database
                return userRepository.findByUsernameAndIsActiveTrue(springUser.getUsername())
                        .orElseThrow(() -> new RuntimeException("User not found"));
            }
        }
        return null;
    }
}
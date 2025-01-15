package project.personal.personalstoremanagementproject.controllers.v1.orderscreen;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.personal.personalstoremanagementproject.controllers.AbstractApiController;
import project.personal.personalstoremanagementproject.exceptions.DetailError;
import project.personal.personalstoremanagementproject.repositories.OrderRepository;
import project.personal.personalstoremanagementproject.repositories.UserRepository;
import project.personal.personalstoremanagementproject.services.JwtService;
import project.personal.personalstoremanagementproject.utils.MessageId;
import project.personal.personalstoremanagementproject.utils.constants.ConstantEnum;

import java.util.List;

/**
 * Insert order
 */
@RequestMapping("api/v1/InsertOrder")
@RestController
public class InsertOrderController extends AbstractApiController<InsertOrderRequest, InsertOrderResponse, String> {

    private final OrderRepository orderRepository;

    /**
     * Constructor
     * @param userRepository
     * @param jwtService
     * @param orderRepository
     */
    protected InsertOrderController(UserRepository userRepository, JwtService jwtService, OrderRepository orderRepository, EntityManager entityManager) {
        super(userRepository, jwtService);
        this.orderRepository = orderRepository;
    }

    /**
     * Main processing
     * @param request the request to process
     * @return
     */
    @Override
    protected InsertOrderResponse exec(InsertOrderRequest request) throws Exception {
        var response = new InsertOrderResponse();
        var objectMapper = new ObjectMapper();
        var orderDetailsJson = objectMapper.writeValueAsString(request.getOrderDetail());

        Long userId = request.getUserId();
        var createById = request.getCreateById();
        // Get user
        if (request.getUserId() == null){
            userId = getCurrentUser().getUserId();
            createById = userId;
        }
        // Insert Order
        orderRepository.createOrder(userId, createById, orderDetailsJson);

        // True
        response.setSuccess(true);
        response.setMessage(MessageId.I0001);
        return response;
    }

    /**
     * Validate request
     * @param request the request to validate
     * @param detailErrorList the list of detail error
     * @return
     */
    @Override
    protected InsertOrderResponse validate(InsertOrderRequest request, List<DetailError> detailErrorList) {
        var response = new InsertOrderResponse();
        // Check request
        Long userId = null;
        // Get user
        if (request.getUserId() != null){
            userId = request.getUserId();
        }else {
            userId = getCurrentUser().getUserId();
        }
        // Check when user login
        if (request.getUserId() == null && request.getCreateById() != null){
            response.setSuccess(false);
            response.setMessage(MessageId.I0000, "Account is not allowed to create order");
            return response;
        }
        var role = userRepository.findById(userId).get().getRole();
        // Check role of user
        if (role.equals(ConstantEnum.Role.STAFF) || role.equals(ConstantEnum.Role.ADMIN)){
            response.setSuccess(false);
            response.setMessage(MessageId.I0000, "Account is not allowed to create order");
            return response;
        }
        //Check Order Detail
        if (request.getOrderDetail().isEmpty()){
            response.setSuccess(false);
            response.setMessage(MessageId.I0000, "Order detail is empty");
            return response;
        }
        if (request.getUserId() == null && request.getCreateById() != null){
            response.setSuccess(false);
            response.setMessage(MessageId.I0000, "Account is not allowed to create order");
            return response;
        }

        if (!detailErrorList.isEmpty()){
            response.setSuccess(false);
            response.setDetailErrorList(detailErrorList);
            response.setMessage("E0000", "Invalid request");
            return response;
        }
        // True
        response.setSuccess(true);
        return response;
    }
}

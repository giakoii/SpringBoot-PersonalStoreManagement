package project.personal.personalstoremanagementproject.controllers.v1.productlistscreen;

import jakarta.persistence.Column;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.personal.personalstoremanagementproject.controllers.AbstractApiController;
import project.personal.personalstoremanagementproject.exceptions.DetailError;
import project.personal.personalstoremanagementproject.repositories.UserRepository;
import project.personal.personalstoremanagementproject.repositories.ViewProductRepository;
import project.personal.personalstoremanagementproject.services.JwtService;
import project.personal.personalstoremanagementproject.utils.MessageId;

import java.math.BigDecimal;
import java.util.List;

/**
 * Controller for the GetAllProduct API.
 */
@RequestMapping("api/v1/GetAllProduct")
@RestController
public class GetAllProductController extends AbstractApiController<GetAllProductRequest, GetAllProductResponse, List<GetAllProductModel>> {

    private final ViewProductRepository viewProductRepository;

    /**
     * Constructor
     * @param userRepository
     * @param jwtService
     * @param viewProductRepository
     */
    public GetAllProductController(UserRepository userRepository, JwtService jwtService, ViewProductRepository viewProductRepository) {
        super(userRepository, jwtService);
        this.viewProductRepository = viewProductRepository;
    }

    /**
     * Main process of the controller
     * @param request the request to process
     * @return
     */
    @Override
    protected GetAllProductResponse exec(GetAllProductRequest request) throws Exception {
        var response = new GetAllProductResponse();

        // Get all products
        var products = viewProductRepository.findAll();

        var productResponse = products.stream().map(product -> {
            return GetAllProductModel.builder()
                            .productId(product.getProductId())
                            .productName(product.getProductName())
                            .brandName(product.getBrandName())
                            .categoryName(product.getCategoryName())
                            .price(product.getPrice())
                            .imageUrl(product.getImageUrl())
                            .build();
        }).toList();

        // True
        response.setSuccess(true);
        response.setMessage(MessageId.I0001);
        response.setResponse(productResponse);
        return response;
    }

    /**
     * Validate the request
     * @param request the request to validate
     * @param detailErrorList the list of detail errors
     * @return
     */
    @Override
    protected GetAllProductResponse validate(GetAllProductRequest request, List<DetailError> detailErrorList) {
        var response = new GetAllProductResponse();
        if (!detailErrorList.isEmpty()) {
            response.setSuccess(false);
            response.setMessage(MessageId.E0000, "Validation errors occurred");
            return response;
        }
        return null;
    }
}

package project.personal.personalstoremanagementproject.controllers.v1.productscreen;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.personal.personalstoremanagementproject.controllers.AbstractApiController;
import project.personal.personalstoremanagementproject.exceptions.DetailError;
import project.personal.personalstoremanagementproject.repositories.UserRepository;
import project.personal.personalstoremanagementproject.repositories.ViewProductInfRepository;
import project.personal.personalstoremanagementproject.services.JwtService;
import project.personal.personalstoremanagementproject.utils.MessageId;

import java.util.List;

/**
 * Controller for the FilterProduct API.
 */
@RequestMapping("api/v1/FilterProduct")
@RestController
public class FilterProductController extends AbstractApiController<FilterProductRequest, FilterProductResponse, List<GetAllProductModel>> {

    private final ViewProductInfRepository viewProductRepository;

    /**
     * Constructor
     * @param userRepository
     * @param jwtService
     * @param viewProductRepository
     */
    public FilterProductController(UserRepository userRepository, JwtService jwtService, ViewProductInfRepository viewProductRepository) {
        super(userRepository, jwtService);
        this.viewProductRepository = viewProductRepository;
    }

    /**
     * Main process of the controller
     * @param request the request to process
     * @return
     */
    @Override
    protected FilterProductResponse exec(FilterProductRequest request) throws Exception {
        var response = new FilterProductResponse();

        // Get the product
        var filterProduct = viewProductRepository.findAll().stream()
                .filter(p -> p.getCategoryName().equals(request.getProductCategory()))
                .filter(p -> p.getBrandName().equals(request.getBrandName()))
                .filter(p -> p.getPrice().compareTo(request.getMinPrice()) >= 0)
                .filter(p -> p.getPrice().compareTo(request.getMaxPrice()) <= 0)
                .map(p -> GetAllProductModel.builder()
                        .productId(p.getProductId())
                        .productName(p.getProductName())
                        .brandName(p.getBrandName())
                        .categoryName(p.getCategoryName())
                        .price(p.getPrice())
                        .imageUrl(p.getProductImageUrl())
                        .build())
                .toList();

        // True
        response.setSuccess(true);
        response.setMessage(MessageId.I0001);
        response.setResponse(filterProduct);
        return response;
    }

    /**
     * Validate the request
     * @param request the request to validate
     * @param detailErrorList the list of detail errors
     * @return
     */
    @Override
    protected FilterProductResponse validate(FilterProductRequest request, List<DetailError> detailErrorList) {
        var response = new FilterProductResponse();
        if (!detailErrorList.isEmpty()) {
            response.setSuccess(false);
            response.setMessage(MessageId.E0000, "Validation errors occurred");
            return response;
        }
        return null;
    }
}

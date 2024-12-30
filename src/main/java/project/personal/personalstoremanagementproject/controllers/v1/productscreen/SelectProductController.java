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
import java.util.stream.Collectors;

/**
 * Controller for the SelectProduct API.
 */
@RequestMapping("api/v1/SelectProduct")
@RestController
public class SelectProductController extends AbstractApiController<SelectProductRequest, SelectProductResponse, SelectProductModel> {

    private final ViewProductInfRepository viewProductRepository;

    /**
     * Constructor
     * @param userRepository
     * @param jwtService
     * @param viewProductRepository
     */
    public SelectProductController(UserRepository userRepository, JwtService jwtService, ViewProductInfRepository viewProductRepository) {
        super(userRepository, jwtService);
        this.viewProductRepository = viewProductRepository;
    }

    /**
     * Main process of the controller
     * @param request the request to process
     * @return
     */
    @Override
    protected SelectProductResponse exec(SelectProductRequest request) throws Exception {
        var response = new SelectProductResponse();

        // Get the product
        var product = viewProductRepository.findById(request.productId);

        var productSpecifications = product.stream()
                .map(p -> SelectProdcutListSpecification.builder()
                        .specificationKey(p.getSpecificationKey())
                        .specificationValue(p.getSpecificationValue())
                        .build())
                .collect(Collectors.toList());

        var productResponse = SelectProductModel.builder()
                .productId(product.get().getProductId())
                .productName(product.get().getProductName())
                .productDescription(product.get().getProductDescription())
                .price(product.get().getPrice())
                .originalPrice(product.get().getOriginalPrice())
                .stockQuantity(product.get().getStockQuantity())
                .skuCode(product.get().getSkuCode())
                .productImageUrl(product.get().getProductImageUrl())
                .productCreatedAt(product.get().getProductCreatedAt())
                .productUpdatedAt(product.get().getProductUpdatedAt())
                .productStatus(product.get().getProductStatus())
                .brandName(product.get().getBrandName())
                .brandLogo(product.get().getBrandLogo())
                .categoryName(product.get().getCategoryName())
                .categoryImageUrl(product.get().getCategoryImageUrl())
                .productSpecification(productSpecifications)
                .build();

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
    protected SelectProductResponse validate(SelectProductRequest request, List<DetailError> detailErrorList) {
        var response = new SelectProductResponse();

        if (!detailErrorList.isEmpty()) {
            response.setSuccess(false);
            response.setMessage(MessageId.E0000, "Validation errors occurred");
            return response;
        }
        return null;
    }
}

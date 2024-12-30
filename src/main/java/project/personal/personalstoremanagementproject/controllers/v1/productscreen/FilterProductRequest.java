package project.personal.personalstoremanagementproject.controllers.v1.productscreen;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import project.personal.personalstoremanagementproject.controllers.AbstractApiRequest;

import java.math.BigDecimal;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public class FilterProductRequest extends AbstractApiRequest {
    String productName;

    String productCategory;

    String brandName;

    BigDecimal minPrice;

    BigDecimal maxPrice;

    String productSpecificationKey;

    String productSpecificationValue;
}

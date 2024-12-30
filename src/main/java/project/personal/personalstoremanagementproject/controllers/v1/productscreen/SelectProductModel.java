package project.personal.personalstoremanagementproject.controllers.v1.productscreen;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class SelectProductModel {
    Long productId;

    String productName;

    String productDescription;

    BigDecimal price;

    BigDecimal originalPrice;

    Integer stockQuantity;

    String skuCode;

    String productImageUrl;

    LocalDateTime productCreatedAt;

    LocalDateTime productUpdatedAt;

    Boolean productStatus;

    String brandName;

    String brandLogo;

    String categoryName;

    String categoryImageUrl;

    List<SelectProdcutListSpecification> productSpecification;
}

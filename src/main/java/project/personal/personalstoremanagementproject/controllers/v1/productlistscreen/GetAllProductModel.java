package project.personal.personalstoremanagementproject.controllers.v1.productlistscreen;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Builder
@Getter
public class GetAllProductModel {
    Long productId;

    String productName;

    String brandName;

    String categoryName;

    BigDecimal price;

    String imageUrl;
}

package project.personal.personalstoremanagementproject.controllers.v1.productscreen;

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

    String price;

    String imageUrl;
}

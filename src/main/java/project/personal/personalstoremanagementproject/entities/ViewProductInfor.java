package project.personal.personalstoremanagementproject.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Immutable;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Immutable
@Table(name = "product_view")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ViewProductInfor {

    @Id
    @Column(name = "product_id", nullable = false)
    Long productId;

    @Column(name = "product_name", nullable = false)
    String productName;

    @Column(name = "product_description")
    String productDescription;

    @Column(name = "price", nullable = false, precision = 18, scale = 2)
    BigDecimal price;

    @Column(name = "original_price", precision = 18, scale = 2)
    BigDecimal originalPrice;

    @Column(name = "stock_quantity", nullable = false)
    Integer stockQuantity;

    @Column(name = "skucode", nullable = false)
    String skuCode;

    @Column(name = "product_image_url")
    String productImageUrl;

    @Column(name = "product_created_at")
    LocalDateTime productCreatedAt;

    @Column(name = "product_updated_at")
    LocalDateTime productUpdatedAt;

    @Column(name = "product_status")
    Boolean productStatus;

    @Column(name = "brand_name")
    String brandName;

    @Column(name = "brand_logo")
    String brandLogo;

    @Column(name = "category_name")
    String categoryName;

    @Column(name = "category_image_url")
    String categoryImageUrl;

    @Column(name = "specification_key")
    String specificationKey;

    @Column(name = "specification_value")
    String specificationValue;
}
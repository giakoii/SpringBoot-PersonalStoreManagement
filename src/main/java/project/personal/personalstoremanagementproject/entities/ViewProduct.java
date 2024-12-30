package project.personal.personalstoremanagementproject.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Nationalized;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Immutable
@Table(name = "product_view")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ViewProduct {
    
    @Id
    @Column(name = "product_id", nullable = false)
    Long productId;

    @Column(name = "product_name", nullable = false)
    String productName;

    @Column(name = "brand_name")
    String brandName;

    @Column(name = "category_name")
    String categoryName;

    @Column(name = "price", nullable = false, precision = 18, scale = 2)
    BigDecimal price;

    @Column(name = "image_url")
    String imageUrl;
}
package project.personal.personalstoremanagementproject.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Nationalized;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "Product")
public class Product extends BaseEntity{
    @Id
    @Column(name = "ProductId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long productId;

    @Nationalized
    @Column(name = "Name", nullable = false, length = 255)
    String name;

    @Nationalized
    @Column(name = "SKUCode", nullable = false, length = 50)
    String sKUCode;

    @Nationalized
    @Lob
    @Column(name = "Description")
    String description;

    @Column(name = "Price", nullable = false, precision = 18, scale = 2)
    BigDecimal price;

    @Column(name = "OriginalPrice", precision = 18, scale = 2)
    BigDecimal originalPrice;

    @NotNull
    @Column(name = "StockQuantity", nullable = false)
    Integer stockQuantity;

    @Column(name = "CategoryId")
    Long categoryId;

    @Column(name = "BrandId")
    Long brandId;

    @Nationalized
    @Lob
    @Column(name = "ImageUrl")
    String imageUrl;
}
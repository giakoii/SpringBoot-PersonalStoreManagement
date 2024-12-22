package project.personal.personalstoremanagementproject.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Entity
@Table(name = "Wishlist")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Wishlist extends BaseEntity{
    @Id
    @Column(name = "WishlistId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wishlistId;

    @NotNull
    @Column(name = "UserId", nullable = false)
    private Long userId;

    @NotNull
    @Column(name = "ProductId", nullable = false)
    private Long productId;
}
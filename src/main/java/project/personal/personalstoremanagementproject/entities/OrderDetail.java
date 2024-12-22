package project.personal.personalstoremanagementproject.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "OrderDetail")
public class OrderDetail extends BaseEntity{
    @Id
    @Column(name = "OrderDetailId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderDetailId;

    @NotNull
    @Column(name = "OrderId", nullable = false)
    private Long orderId;

    @NotNull
    @Column(name = "ProductId", nullable = false)
    private Long productId;

    @NotNull
    @Column(name = "Quantity", nullable = false)
    private Integer quantity;

    @NotNull
    @Column(name = "UnitPrice", nullable = false, precision = 18, scale = 2)
    private BigDecimal unitPrice;

}
package project.personal.personalstoremanagementproject.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Nationalized;
import project.personal.personalstoremanagementproject.utils.constants.ConstantEnum;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "\"Order\"")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Order extends BaseEntity{
    @Id
    @Column(name = "OrderId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long orderId;

    @Column(name = "UserId", nullable = false)
    Long userId;

    @Column(name = "TotalPrice", nullable = false, precision = 18, scale = 2)
    BigDecimal totalPrice;

    @Nationalized
    @Column(name = "OrderStatus", nullable = false, length = 50)
    String orderStatus;

    @ColumnDefault("1")
    @Column(name = "Status", nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    ConstantEnum.OrderStatus status;
}
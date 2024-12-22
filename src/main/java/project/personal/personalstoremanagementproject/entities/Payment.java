package project.personal.personalstoremanagementproject.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Nationalized;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "Payment")
public class Payment extends BaseEntity{
    @Id
    @Column(name = "PaymentId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long paymentId;

    @NotNull
    @Column(name = "OrderId", nullable = false)
    Long orderId;

    @Size(max = 50)
    @NotNull
    @Nationalized
    @Column(name = "PaymentMethod", nullable = false, length = 50)
    String paymentMethod;

    @Size(max = 50)
    @NotNull
    @Nationalized
    @Column(name = "PaymentStatus", nullable = false, length = 50)
    String paymentStatus;

    @NotNull
    @Column(name = "AmountPaid", nullable = false, precision = 18, scale = 2)
    BigDecimal amountPaid;
}
package project.personal.personalstoremanagementproject.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "salary")
public class Salary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "salary_id", nullable = false)
    Long id;

    @Column(name = "base_salary", nullable = false, precision = 18, scale = 2)
    BigDecimal baseSalary;

    @ColumnDefault("0")
    @Column(name = "bonus", precision = 18, scale = 2)
    BigDecimal bonus;

    @Column(name = "total_salary", nullable = false, precision = 18, scale = 2)
    BigDecimal totalSalary;

    @Column(name = "payment_date", nullable = false)
    LocalDate paymentDate;
}
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
import java.time.LocalDate;

@Getter
@Setter
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "staff")
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "staff_id", nullable = false)
    Long id;

    @JoinColumn(name = "user_id", nullable = false)
    Long userId;

    @Nationalized
    @Column(name = "full_name", nullable = false)
    String fullName;

    @Nationalized
    @Column(name = "department", length = 255)
    String department;

    @Column(name = "hire_date")
    LocalDate hireDate;

    @Column(name = "base_salary", nullable = false, precision = 18, scale = 2)
    BigDecimal baseSalary;
}
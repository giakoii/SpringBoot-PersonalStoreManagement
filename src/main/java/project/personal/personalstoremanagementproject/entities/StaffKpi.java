package project.personal.personalstoremanagementproject.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "staff_kpi")
public class StaffKpi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "staff_kpi_id", nullable = false)
    Long staffKpiId;

    @Column(name = "staff_id", nullable = false)
    Long staffId;
    
    @Column(name = "kpi_id", nullable = false)
    Long kpiId;

    @Column(name = "target_value", precision = 18, scale = 2)
    BigDecimal targetValue;

    @Column(name = "achieved_value", precision = 18, scale = 2)
    BigDecimal achievedValue;

    @Column(name = "evaluation_date", nullable = false)
    LocalDate evaluationDate;
}
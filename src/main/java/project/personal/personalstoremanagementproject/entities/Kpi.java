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
@Table(name = "kpi")
public class Kpi extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kpi_id", nullable = false)
    private Long id;

    @Size(max = 255)
    @Nationalized
    @Column(name = "kpi_name", nullable = false)
    private String kpiName;

    @Nationalized
    @Lob
    @Column(name = "description")
    private String description;

    @Column(name = "weight", nullable = false, precision = 5, scale = 2)
    private BigDecimal weight;
}
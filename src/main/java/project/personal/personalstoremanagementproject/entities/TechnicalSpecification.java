package project.personal.personalstoremanagementproject.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Nationalized;

@Getter
@Setter
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "TechnicalSpecification")
public class TechnicalSpecification extends BaseEntity{
    @Id
    @Column(name = "SpecId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long specId;

    @Column(name = "ProductId", nullable = false)
    Long productId;

    @Nationalized
    @Column(name = "SpecificationKey", nullable = false, length = 255)
    String specificationKey;

    @Nationalized
    @Lob
    @Column(name = "SpecificationValue", nullable = false)
    String specificationValue;
}
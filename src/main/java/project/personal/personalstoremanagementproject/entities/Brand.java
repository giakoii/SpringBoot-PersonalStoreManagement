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

import java.time.Instant;

@Getter
@Setter
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "Brand")
public class Brand extends BaseEntity{
    @Id
    @Column(name = "BrandId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long brandId;

    @Nationalized
    @Column(name = "Name", nullable = false, length = 255)
    String name;

    @Nationalized
    @Lob
    @Column(name = "Description")
    String description;

    @Nationalized
    @Lob
    @Column(name = "LogoUrl")
    String logoUrl;

    @ColumnDefault("1")
    @Column(name = "Status", columnDefinition = "tinyint")
    Short status;

}
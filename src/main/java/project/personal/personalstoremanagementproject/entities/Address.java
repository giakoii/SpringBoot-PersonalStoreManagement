package project.personal.personalstoremanagementproject.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Nationalized;

import java.time.Instant;

@Getter
@Setter
@Entity
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Table(name = "Address")
@AllArgsConstructor
public class Address extends BaseEntity{
    @Id
    @Column(name = "AddressId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long addressId;

    @Column(name = "UserId", nullable = false)
    Long userId;

    @Nationalized
    @Lob
    @Column(name = "AddressLine", nullable = false)
    String addressLine;

    @Nationalized
    @Column(name = "City", nullable = false, length = 100)
    String city;

    @Nationalized
    @Column(name = "State", length = 100)
     String state;

    @Nationalized
    @Column(name = "ZipCode", length = 20)
    String zipCode;

    @Nationalized
    @Column(name = "Country", nullable = false, length = 100)
    String country;

}
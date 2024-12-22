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
@Table(name = "Address")
public class Address extends BaseEntity{
    @Id
    @Column(name = "AddressId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long addressId;

    @NotNull
    @Column(name = "UserId", nullable = false)
    Long userId;

    @NotNull
    @Nationalized
    @Lob
    @Column(name = "AddressLine", nullable = false)
    String addressLine;

    @Size(max = 100)
    @NotNull
    @Nationalized
    @Column(name = "City", nullable = false, length = 100)
    String city;

    @Size(max = 100)
    @Nationalized
    @Column(name = "State", length = 100)
     String state;

    @Size(max = 20)
    @Nationalized
    @Column(name = "ZipCode", length = 20)
    String zipCode;

    @Size(max = 100)
    @NotNull
    @Nationalized
    @Column(name = "Country", nullable = false, length = 100)
    String country;

}
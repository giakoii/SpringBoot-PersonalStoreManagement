package project.personal.personalstoremanagementproject.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Nationalized;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id", nullable = false)
    Long customerId;

    @Column(name = "user_id", nullable = false)
    Long userId;

    @Nationalized
    @Column(name = "full_name", length = 255)
    String fullName;

    @Nationalized
    @Column(name = "nick_name", length = 255)
    String nickName;

    @Column(name = "date_of_birth")
    LocalDate dateOfBirth;

    @Nationalized
    @Column(name = "phone_number", length = 20)
    String phoneNumber;

    @ColumnDefault("0")
    @Column(name = "loyalty_points")
    Integer loyaltyPoints;

    @Nationalized
    @Column(name = "membership_level", length = 50)
    String membershipLevel;
}
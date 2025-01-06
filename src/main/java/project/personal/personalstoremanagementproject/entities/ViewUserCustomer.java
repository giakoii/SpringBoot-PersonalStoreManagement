package project.personal.personalstoremanagementproject.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Immutable;

import java.time.LocalDate;

@Entity
@Immutable
@Table(name = "user_customer_view")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public class ViewUserCustomer {

    @Id
    @Column(name = "user_id")
    Long userId;

    @Column(name = "username")
    String username;

    @Column(name = "email")
    String email;

    @Column(name = "full_name")
    String fullName;

    @Column(name = "date_of_birth")
    LocalDate dateOfBirth;

    @Column(name = "phone_number")
    String phoneNumber;

    @Column(name = "avatar_url")
    String avatarUrl;

    @Column(name = "nick_name")
    String nickName;

    @Column(name = "gender")
    String gender;

    @Column(name = "address_id")
    Long addressId;

    @Column(name = "address_line")
    String addressLine;

    @Column(name = "city")
    String city;

    @Column(name = "state")
    String state;

    @Column(name = "country")
    String country;

    @Column(name = "zip_code")
    String zipCode;
}
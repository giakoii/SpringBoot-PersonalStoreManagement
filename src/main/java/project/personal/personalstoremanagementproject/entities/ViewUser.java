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
@Table(name = "user_view")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public class ViewUser {

    @Id
    @Column(name = "user_id")
    Long userId;

    @Column(name = "username")
    String username;

    @Column(name = "full_name")
    String fullName;

    @Column(name = "date_of_birth")
    LocalDate dateOfBirth;

    @Column(name = "gender")
    String gender;

    @Column(name = "email")
    String email;

    @Column(name = "city")
    String city;

    @Column(name = "country")
    String country;
}

package project.personal.personalstoremanagementproject.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Nationalized;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import project.personal.personalstoremanagementproject.utils.constants.ConstantEnum;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_account")
public class UserAccount extends BaseEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Nationalized
    @Lob
    @Column(name = "avatar_url")
    private String avatarUrl;

    @Nationalized
    @Column(name = "email", nullable = false, length = 255)
    private String email;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private ConstantEnum.Gender gender;

    @Column(name = "last_login")
    private Instant lastLogin;

    @Nationalized
    @Column(name = "username", nullable = false, length = 50)
    private String username;

    @Nationalized
    @Lob
    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Size(max = 15)
    @Nationalized
    @Column(name = "phone_number", length = 15)
    private String phoneNumber;

    @Column(name = "Role", length = 50)
    @Enumerated(EnumType.STRING)
    private ConstantEnum.Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return passwordHash;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
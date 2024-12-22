package project.personal.personalstoremanagementproject.entities;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.Nationalized;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import project.personal.personalstoremanagementproject.utils.constants.ConstantEnum;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@Table(name = "UserAccount")
@NoArgsConstructor
@AllArgsConstructor
public class UserAccount extends BaseEntity implements UserDetails {
    @Id
    @Column(name = "UserId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Size(max = 50)
    @Nationalized
    @Column(name = "Username", nullable = false, length = 50)
    private String username;

    @Size(max = 255)
    @Nationalized
    @Column(name = "Email", nullable = false)
    private String email;

    @Nationalized
    @Lob
    @Column(name = "PasswordHash", nullable = false)
    private String passwordHash;

    @Size(max = 255)
    @Nationalized
    @Column(name = "FullName", nullable = true)
    private String fullName;

    @Size(max = 255)
    @Nationalized
    @Column(name = "NickName", nullable = true)
    private String nickName;

    @Nationalized
    @Column(name = "PhoneNumber", nullable = true, length = 15)
    private String phoneNumber;

    @Nationalized
    @Lob
    @Column(name = "Address")
    private String address;

    @Nationalized
    @Lob
    @Column(name = "AvatarUrl")
    private String avatarUrl;

    @Column(name = "DateOfBirth")
    private LocalDate dateOfBirth;

    @Column(name = "Gender", columnDefinition = "tinyint")
    private Short gender;

    @Column(name = "Role", length = 50)
    @Enumerated(EnumType.STRING)
    private ConstantEnum.Role role;

    @Column(name = "LastLogin")
    LocalDateTime lastLogin;

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
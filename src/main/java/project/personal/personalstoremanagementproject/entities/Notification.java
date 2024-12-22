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
@Table(name = "Notification")
public class Notification extends BaseEntity{
    @Id
    @Column(name = "NotificationId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long notificationId;

    @NotNull
    @Column(name = "UserId", nullable = false)
    Long userId;

    @Size(max = 255)
    @NotNull
    @Nationalized
    @Column(name = "Title", nullable = false)
    String title;

    @NotNull
    @Nationalized
    @Lob
    @Column(name = "Message", nullable = false)
    String message;

    @ColumnDefault("0")
    @Column(name = "IsRead")
    Boolean isRead;
}
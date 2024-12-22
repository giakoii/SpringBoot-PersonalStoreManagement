package project.personal.personalstoremanagementproject.entities;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class BaseEntity {

    @Column(name = "CREATE_AT", updatable = false)
    LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "UPDATE_AT")
    LocalDateTime updatedAt = LocalDateTime.now();

    @Column(name = "IS_ACTIVE",nullable = false)
    Boolean isActive = true;

    @Column(name = "CREATE_BY")
    String createdBy;

    @Column(name = "UPDATE_BY")
    String updatedBy;
}

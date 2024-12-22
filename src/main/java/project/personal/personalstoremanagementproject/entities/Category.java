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
@Table(name = "Category")
public class Category extends BaseEntity{
    @Id
    @Column(name = "CategoryId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long categoryId;

    @Size(max = 255)
    @NotNull
    @Nationalized
    @Column(name = "Name", nullable = false)
    String name;

    @Column(name = "ParentCategoryId")
    Long parentCategoryId;

    @Nationalized
    @Lob
    @Column(name = "Description")
    String description;

    @Nationalized
    @Lob
    @Column(name = "ImageUrl")
    String imageUrl;
}
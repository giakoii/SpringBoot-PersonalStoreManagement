package project.personal.personalstoremanagementproject.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Nationalized;

@Getter
@Setter
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "Review")
public class Review extends BaseEntity{
    @Id
    @Column(name = "ReviewId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long reviewId;

    @Column(name = "ProductId", nullable = false)
    Long productId;

    @Nationalized
    @Column(name = "ReviewerName", nullable = false, length = 255)
    String reviewerName;

    @Column(name = "Rating", columnDefinition = "tinyint not null")
    Short rating;

    @Nationalized
    @Lob
    @Column(name = "Content")
    String content;
}
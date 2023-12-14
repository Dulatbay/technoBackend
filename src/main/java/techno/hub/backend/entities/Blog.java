package techno.hub.backend.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "blog", schema = "schema_techno")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "author_id", nullable = false)
    private String authorId;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "blog_tag", schema = "schema_techno",
            joinColumns = @JoinColumn(name = "blog_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<Tag> tags;

    @Column(name = "image_url")
    private String imageUrl;

    @PrePersist
    public void setPostDate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void setLastUpdated() {
        this.updatedAt = LocalDateTime.now();
    }
}

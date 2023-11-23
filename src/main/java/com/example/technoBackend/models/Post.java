package com.example.technoBackend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="Post")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "title")
    private String title;
    @Column(name = "content")
    private String content;
    @Column(name = "author")
    private String author;
    @Column(name = "postDate")
    private LocalDateTime postDate;
    @Column(name = "last_update")
    private LocalDateTime lastUpdate;
    @ManyToMany
    private List<Tag> tags;

    @PrePersist
    public void setPostDate() {
        this.postDate = LocalDateTime.now();
    }
    @PreUpdate
    public void setLastUpdated() {
        this.lastUpdate = LocalDateTime.now();
    }


}

package com.example.technoBackend.entities;

import com.example.technoBackend.dtos.BlogDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "tag", schema = "schema_techno")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "author_id", nullable = false)
    private String authorId;

    @ManyToMany(mappedBy = "tags")
    private List<Blog> blogs;

}

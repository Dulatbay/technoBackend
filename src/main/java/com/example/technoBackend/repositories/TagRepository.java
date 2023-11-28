package com.example.technoBackend.repositories;

import com.example.technoBackend.entities.Blog;
import com.example.technoBackend.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<Tag,Long>, CrudRepository<Tag,Long> {
    boolean existsByName(String name);
    Optional<Tag> findById(long id);

}

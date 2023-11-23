package com.example.technoBackend.repositories;

import com.example.technoBackend.entities.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {
//    @Query("SELECT b FROM Tag")
//    List<Blog> getBlogByTag(String tagName);
}
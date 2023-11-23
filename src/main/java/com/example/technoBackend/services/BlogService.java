package com.example.technoBackend.services;

import com.example.technoBackend.entities.Blog;

import java.util.List;
import java.util.Optional;

public interface BlogService {
    List<Blog> findAll();

    Blog getBlogById(long id);

    Blog saveBlog(Blog blog);

    void deleteBlog(long id);

}

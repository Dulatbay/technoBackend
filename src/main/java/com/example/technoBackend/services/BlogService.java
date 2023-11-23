package com.example.technoBackend.services;

import com.example.technoBackend.dtos.BlogCreateRequestDto;
import com.example.technoBackend.dtos.BlogDto;
import com.example.technoBackend.entities.Blog;

import java.util.List;

public interface BlogService {
    List<BlogDto> findAll();

    BlogDto getBlogById(long id);

    BlogDto saveBlog(BlogCreateRequestDto blog, String authorId);

    void deleteBlog(long id);

}

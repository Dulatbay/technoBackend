package com.example.technoBackend.services;

import com.example.technoBackend.dtos.BlogCreateRequestDto;
import com.example.technoBackend.dtos.BlogDto;

import java.util.List;

public interface BlogService {
    List<BlogDto> findAll();

    BlogDto getBlogById(long id);

    BlogDto saveBlog(BlogCreateRequestDto blog, String authorId);

    void deleteBlog(long id);

    void addTagsToBlog(Long id, List<Long> tagIds);

//    List<BlogDto> getBlogsByTagName(String tagName);
}

package com.example.technoBackend.services.impl;

import com.example.technoBackend.entities.Blog;
import com.example.technoBackend.exceptions.DbObjectNotFoundException;
import com.example.technoBackend.repositories.BlogRepository;
import com.example.technoBackend.services.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogServiceImpl implements BlogService {
    private final BlogRepository postRepository;

    @Override
    public List<Blog> findAll() {
        return postRepository.findAll();
    }

    @Override
    public Blog getBlogById(long id) {
        return postRepository.findById(id).orElseThrow(() -> new DbObjectNotFoundException(HttpStatus.NOT_FOUND.toString(), "Blog doesn't exist"));
    }

    @Override
    public Blog saveBlog(Blog post) {
        return postRepository.save(post);
    }

    @Override
    public void deleteBlog(long id) {
        postRepository.deleteById(id);
    }
}
package com.example.technoBackend.services.impl;

import com.example.technoBackend.dtos.BlogCreateRequestDto;
import com.example.technoBackend.dtos.BlogDto;
import com.example.technoBackend.exceptions.DbObjectNotFoundException;
import com.example.technoBackend.mappers.BlogMapper;
import com.example.technoBackend.mappers.custom.BlogCustomMapper;
import com.example.technoBackend.repositories.BlogRepository;
import com.example.technoBackend.repositories.TagRepository;
import com.example.technoBackend.services.BlogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BlogServiceImpl implements BlogService {
    private final BlogRepository postRepository;
    private final TagRepository tagRepository;
    private final BlogCustomMapper blogCustomMapper;
    private final BlogMapper blogMapper;

    @Override
    public List<BlogDto> findAll() {
        var blogList = postRepository.findAll();
        return blogMapper.toDTO(blogList);
    }

    @Override
    public BlogDto getBlogById(long id) {
        var blog = postRepository.findById(id).orElseThrow(() -> new DbObjectNotFoundException(HttpStatus.NOT_FOUND.toString(), "Blog doesn't exist"));
        return blogMapper.toDto(blog);
    }

    @Override
    public BlogDto saveBlog(BlogCreateRequestDto blogCreateRequestDto, String authorId) {
        blogCreateRequestDto.setAuthorId(authorId);
        var entityToSave = blogCustomMapper.toEntity(blogCreateRequestDto);
        var savedEntity = postRepository.save(entityToSave);
        return blogMapper.toDto(savedEntity);
    }

    @Override
    public void deleteBlog(long id) {
        postRepository.deleteById(id);
    }
}
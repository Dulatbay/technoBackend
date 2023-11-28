package com.example.technoBackend.services.impl;

import com.example.technoBackend.dtos.BlogCreateRequestDto;
import com.example.technoBackend.dtos.BlogDto;
import com.example.technoBackend.dtos.TagDto;
import com.example.technoBackend.entities.Blog;
import com.example.technoBackend.entities.Tag;
import com.example.technoBackend.exceptions.DbObjectNotFoundException;
import com.example.technoBackend.mappers.BlogMapper;
import com.example.technoBackend.mappers.custom.BlogCustomMapper;
import com.example.technoBackend.repositories.BlogRepository;
import com.example.technoBackend.repositories.TagRepository;
import com.example.technoBackend.services.BlogService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BlogServiceImpl implements BlogService {
    private final BlogRepository blogRepository;
    private final TagRepository tagRepository;
    private final BlogCustomMapper blogCustomMapper;
    private final BlogMapper blogMapper;

    @Override
    @Transactional
    public List<BlogDto> findAll() {
        List<Blog> blogs = blogRepository.findAll();
//        return blogs.stream().map(i -> blogMapper.toDTO(/)).collect(Collectors.toList());
        return blogMapper.toDTO(blogs);
    }
    @Override
    public BlogDto getBlogById(long id) {
        var blog = blogRepository.findById(id)
                .orElseThrow(() -> new DbObjectNotFoundException(HttpStatus.NOT_FOUND.toString(), "Blog doesn't exist"));

        return blogMapper.toDto(blog);
    }
//    private BlogDto mapToDto(Blog blog) {
//        BlogDto blogDto = new BlogDto();
//        blogDto.setId(blog.getId());
//        blogDto.setTitle(blog.getTitle());
//        blogDto.setContent(blog.getContent());
//        blogDto.setAuthorId(blog.getAuthorId());
//        blogDto.setCreatedAt(blog.getCreatedAt());
//        blogDto.setUpdatedAt(blog.getUpdatedAt());
//        // Map tags if needed
//        blogDto.setTags(TagDto.fromEntities(blog.getTags()));
//        return blogDto;
//    }

    @Transactional
    public void deleteTag(long id) {
        // Find the tag by ID
        Tag tagToDelete = tagRepository.findById(id)
                .orElseThrow(() -> new EmptyResultDataAccessException("Tag not found with ID: " + id,1));

        // Disassociate the tag from all blogs
        for (Blog blog : tagToDelete.getBlogs()) {
            blog.getTags().remove(tagToDelete);
        }

        // Delete the tag
        tagRepository.delete(tagToDelete);
    }
    @Override
    public BlogDto saveBlog(BlogCreateRequestDto blogCreateRequestDto, String authorId) {
        blogCreateRequestDto.setAuthorId(authorId);
        var entityToSave = blogCustomMapper.toEntity(blogCreateRequestDto);
        log.info("entityToSave: {}", entityToSave);
        var savedEntity = blogRepository.save(entityToSave);
        return blogMapper.toDto(savedEntity);
    }

    @Override
    public void deleteBlog(long id) {
        blogRepository.deleteById(id);
    }

    @Override
    public void addTagsToBlog(Long id, List<Long> tagIds) {
        var blogEntity = blogRepository.findById(id)
                .orElseThrow(
                        () -> new DbObjectNotFoundException(HttpStatus.NOT_FOUND.toString(), "Blog doesn't exist"));
        var tags = tagRepository.findAllById(tagIds);

        if (tags.size() != tagIds.size())
            throw new IllegalArgumentException("Could not find all specified tags");

        if(blogEntity.getTags().stream().anyMatch(tag -> tagIds.contains(tag.getId()))) {
            throw new IllegalArgumentException("Tag already added");
        }

        blogEntity.setTags(tags);
        blogRepository.save(blogEntity);
    }

    @Override
    public List<BlogDto> getBlogsByTagName(String tagName) {
        if (!tagRepository.existsByName(tagName))
            throw new DbObjectNotFoundException(HttpStatus.NOT_FOUND.toString(), "Tag doesn't exits");
        var blogs = blogRepository.getBlogByTags_Name(tagName);
        return blogMapper.toDTO(blogs);
    }

    @Override
    public void deleteTagFromBlog(long blogId, long tagId) {
        Blog blog = blogRepository.findById(blogId)
                .orElseThrow(() -> new DbObjectNotFoundException(HttpStatus.NOT_FOUND.toString(), "Blog not found"));

        blog.getTags().removeIf(tag -> tag.getId()==(tagId));

        blogRepository.save(blog);
    }

}
package com.example.technoBackend.mappers.custom;

import com.example.technoBackend.dtos.BlogCreateRequestDto;
import com.example.technoBackend.entities.Blog;
import com.example.technoBackend.repositories.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BlogCustomMapper {
    private final TagRepository tagRepository;

    public Blog toEntity(BlogCreateRequestDto blogCreateRequestDto) {
        Blog blog = new Blog();
        blog.setTitle(blogCreateRequestDto.getTitle());
        blog.setContent(blogCreateRequestDto.getContent());
        blog.setAuthorId(blogCreateRequestDto.getAuthorId());
        blog.setTags(tagRepository.findAllById(blogCreateRequestDto.getTagsIds()));
        return blog;
    }
}

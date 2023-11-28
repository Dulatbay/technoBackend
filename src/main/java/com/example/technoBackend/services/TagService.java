package com.example.technoBackend.services;

import com.example.technoBackend.dtos.CreateTagDto;
import com.example.technoBackend.dtos.TagDto;

import java.util.List;

public interface TagService {
    List<TagDto> findAll();
    TagDto getTagById(long id);
    TagDto createTag(CreateTagDto createTagDto);
    void deleteTag(long id);
    List<TagDto> getAllTagsWithBlogs();

}

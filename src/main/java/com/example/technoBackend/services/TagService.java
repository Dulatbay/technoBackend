package com.example.technoBackend.services;

import com.example.technoBackend.dtos.CreateTagDto;
import com.example.technoBackend.dtos.TagDto;
import com.example.technoBackend.entities.Tag;

import java.util.List;
import java.util.Optional;

public interface TagService {
    List<TagDto> findAll();
    TagDto getTagById(long id);
    TagDto saveTag(CreateTagDto createTagDto);
    void deleteTag(long id);
}

package com.example.technoBackend.services.impl;

import com.example.technoBackend.dtos.CreateTagDto;
import com.example.technoBackend.dtos.TagDto;
import com.example.technoBackend.exceptions.DbObjectNotFoundException;
import com.example.technoBackend.mappers.TagMapper;
import com.example.technoBackend.repositories.TagRepository;
import com.example.technoBackend.services.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;
    private final TagMapper tagMapper;

    public List<TagDto> findAll() {
        return tagMapper.toDTO(tagRepository.findAll());
    }

    public TagDto getTagById(long id) {
        var tag = tagRepository.findById(id).orElseThrow(() -> new DbObjectNotFoundException(HttpStatus.NOT_FOUND.toString(), "Tag doesn't exist"));
        return tagMapper.toDto(tag);
    }

    public TagDto createTag(CreateTagDto tag) {
        if(tagRepository.existsByName(tag.getName())) throw new IllegalArgumentException("Field name must be unique");
        var savedEntity = tagRepository.save(tagMapper.toEntity(tag));
        return tagMapper.toDto(savedEntity);
    }

    public void deleteTag(long id) {
        tagRepository.deleteById(id);
    }
}


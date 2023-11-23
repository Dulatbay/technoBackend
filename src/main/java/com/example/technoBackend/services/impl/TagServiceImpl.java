package com.example.technoBackend.services.impl;

import com.example.technoBackend.entities.Tag;
import com.example.technoBackend.exceptions.DbObjectNotFoundException;
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

    public List<Tag> findAll() {
        return tagRepository.findAll();
    }

    public Tag getTagById(long id) {
        return tagRepository.findById(id).orElseThrow(() -> new DbObjectNotFoundException(HttpStatus.NOT_FOUND.toString(), "Tag doesn't exist"));
    }

    public Tag saveTag(Tag tag) {
        return tagRepository.save(tag);
    }

    public void deleteTag(long id) {
        tagRepository.deleteById(id);
    }
}


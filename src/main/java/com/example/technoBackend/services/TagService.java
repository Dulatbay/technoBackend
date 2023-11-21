package com.example.technoBackend.services;

import com.example.technoBackend.models.PostTag;
import com.example.technoBackend.models.Tag;
import com.example.technoBackend.repositories.PostTagRepository;
import com.example.technoBackend.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TagService {
    private final TagRepository tagRepository;

    @Autowired
    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public List<Tag> findAll() {
        return tagRepository.findAll();
    }
}


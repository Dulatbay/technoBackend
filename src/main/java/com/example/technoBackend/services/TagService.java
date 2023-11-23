package com.example.technoBackend.services;

import com.example.technoBackend.models.Post;
import com.example.technoBackend.models.Tag;
import com.example.technoBackend.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Optional<Tag> getTagById(long id){
        return tagRepository.findById(id);
    }
    public Tag saveTag(Tag tag) {
        return tagRepository.save(tag);
    }

    public void deleteTag(long id) {
        tagRepository.deleteById(id);
    }
}


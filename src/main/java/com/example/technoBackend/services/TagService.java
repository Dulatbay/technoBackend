package com.example.technoBackend.services;

import com.example.technoBackend.entities.Tag;

import java.util.List;
import java.util.Optional;

public interface TagService {
    List<Tag> findAll();
    Tag getTagById(long id);
    Tag saveTag(Tag tag);
    void deleteTag(long id);
}

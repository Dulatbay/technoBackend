package com.example.technoBackend.controllers;

import com.example.technoBackend.models.Post;
import com.example.technoBackend.models.Tag;
import com.example.technoBackend.services.PostService;
import com.example.technoBackend.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/tags")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping
    public List<Tag> getAllTags() {
        return tagService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Tag> getTagById(@PathVariable long id) {
        return tagService.getTagById(id);
    }

    @PostMapping
    public Tag saveTag(@RequestBody Tag tag) {
        return tagService.saveTag(tag);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTag(@PathVariable long id) {
        tagService.deleteTag(id);
    }
}


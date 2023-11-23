package com.example.technoBackend.controllers;

import com.example.technoBackend.dtos.TagDto;
import com.example.technoBackend.entities.Tag;
import com.example.technoBackend.mappers.TagMapper;
import com.example.technoBackend.services.impl.TagServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tags")
@AllArgsConstructor
public class TagController {

    private TagServiceImpl tagService;
    private TagMapper tagMapper;

    @GetMapping("/all")
    public ResponseEntity<List<TagDto>> getAllTags() {
        var tags = tagService.findAll();
        return ResponseEntity.ok(tagMapper.toDTO(tags));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TagDto> getTagById(@PathVariable long id) {
        var tag = tagService.getTagById(id);
        return ResponseEntity.ok(tagMapper.toDto(tag));
    }

    @PostMapping("/create")
    public ResponseEntity<TagDto> saveTag(@RequestBody Tag tag) {
        var savedTag = tagService.saveTag(tag);
        return ResponseEntity.status(HttpStatus.CREATED).body(tagMapper.toDto(savedTag));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTag(@PathVariable long id) {
        tagService.deleteTag(id);
    }
}


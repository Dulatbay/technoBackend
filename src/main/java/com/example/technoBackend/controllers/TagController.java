package com.example.technoBackend.controllers;

import com.example.technoBackend.dtos.CreateTagDto;
import com.example.technoBackend.dtos.TagDto;
import com.example.technoBackend.entities.Tag;
import com.example.technoBackend.mappers.TagMapper;
import com.example.technoBackend.services.TagService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tags")
@AllArgsConstructor
public class TagController {

    private TagService tagService;

    @GetMapping("/all")
    public ResponseEntity<List<TagDto>> getAllTags() {
        var tags = tagService.findAll();
        return ResponseEntity.ok(tags);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TagDto> getTagById(@PathVariable long id) {
        var tag = tagService.getTagById(id);
        return ResponseEntity.ok(tag);
    }

    @PostMapping("/create")
    public ResponseEntity<TagDto> createTag(@RequestBody CreateTagDto tag) {
        var token = (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        tag.setAuthorId(token.getName());
        var savedTag = tagService.saveTag(tag);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTag);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTag(@PathVariable long id) {
        tagService.deleteTag(id);
    }
}


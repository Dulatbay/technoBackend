package com.example.technoBackend.controllers;

import com.example.technoBackend.dtos.BlogDto;
import com.example.technoBackend.dtos.CreateTagDto;
import com.example.technoBackend.dtos.TagDto;
import com.example.technoBackend.services.TagService;
import com.example.technoBackend.services.impl.TagServiceImpl;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger logger = LoggerFactory.getLogger(BlogController.class);

    @GetMapping("/all")
    public ResponseEntity<List<TagDto>> getAllTagsWithBlogs() {
        List<TagDto> tags = tagService.getAllTagsWithBlogs();
        return ResponseEntity.ok(tags);
    }

//    @GetMapping("/all")
//    public ResponseEntity<List<TagDto>> getAllTags() {
//        var tags = tagService.findAll();
//        return ResponseEntity.ok(tags);
//    }

//    @GetMapping("/{id}")
//    public ResponseEntity<TagDto> getTagById(@PathVariable long id) {
//        var tag = tagServiceImpl.getTagById(id);
//        return ResponseEntity.ok(tag);
//    }

    @GetMapping("/{id}")
    public ResponseEntity<TagDto> getTagById(@PathVariable long id) {
        logger.info("Fetching tag with ID: {}", id);
        var result = tagService.getTagById(id);
        logger.info("Result: {}", result);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/create")
    public ResponseEntity<TagDto> createTag(@RequestBody CreateTagDto tag) {
        var token = (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        tag.setAuthorId(token.getName());
        var createdTag = tagService.createTag(tag);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTag);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTag(@PathVariable long id) {
        tagService.deleteTag(id);
    }
    @DeleteMapping("/{id}/delete-tag/{tag-id}")
    public ResponseEntity<String> deleteTagFromBlog(
            @PathVariable("blog-id") long blogId,
            @PathVariable("tag-id") long tagId) {


        tagService.deleteTag(tagId);

        return ResponseEntity.ok("Tag deleted successfully");
    }
//    @GetMapping("/{tag-id}/blogs")
//    public ResponseEntity<String> getBlogsOfTag(){
//
//    }
}


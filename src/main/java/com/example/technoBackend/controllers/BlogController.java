package com.example.technoBackend.controllers;

import com.example.technoBackend.dtos.BlogCreateRequestDto;
import com.example.technoBackend.dtos.BlogDto;
import com.example.technoBackend.services.BlogService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/blogs")
@AllArgsConstructor
public class BlogController {

    private BlogService blogService;

    @GetMapping("/all")
    public ResponseEntity<List<BlogDto>> getAllPosts() {
        var result = blogService.findAll();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BlogDto> getPostById(@PathVariable int id) {
        var result = blogService.getBlogById(id);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/create")
    public ResponseEntity<BlogDto> createBlog(@RequestBody BlogCreateRequestDto blogCreateRequestDto) {
        var token = (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();


        var result = blogService.saveBlog(blogCreateRequestDto, token.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PostMapping("/{id}/add-tag")
    public ResponseEntity<Void> addTagToBlog(@PathVariable Long id, @RequestParam List<Long> tagIds) {
        if(tagIds.isEmpty()) throw new IllegalArgumentException("List of tag's id is empty");
        blogService.addTagsToBlog(id, tagIds);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public void deletePost(@PathVariable long id) {
        System.out.println("good?");
        blogService.deleteBlog(id);
    }

    @GetMapping("/get-by-tag/{tag-name}")
    public ResponseEntity<List<BlogDto>> getBlogsByTagName(@PathVariable("tag-name") String tagName){
        List<BlogDto> blogs = blogService.getBlogsByTagName(tagName);
        return ResponseEntity.ok(blogs);
    }
}

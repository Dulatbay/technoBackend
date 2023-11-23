package com.example.technoBackend.controllers;

import com.example.technoBackend.dtos.BlogDto;
import com.example.technoBackend.entities.Blog;
import com.example.technoBackend.mappers.BlogMapper;
import com.example.technoBackend.services.BlogService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/blogs")
@AllArgsConstructor
public class BlogController {

    private BlogService blogService;
    private BlogMapper blogMapper;

    @GetMapping("/all")
    public ResponseEntity<List<BlogDto>> getAllPosts() {
        var result = blogService.findAll();
        return ResponseEntity.ok(blogMapper.toDTO(result));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BlogDto> getPostById(@PathVariable int id) {
        var result = blogService.getBlogById(id);
        return ResponseEntity.ok(blogMapper.toDto(result));
    }

    @PostMapping("/create")
    public ResponseEntity<BlogDto> savePost(@RequestBody Blog blog) {
        var result = blogService.saveBlog(blog);
        return ResponseEntity.status(HttpStatus.CREATED).body(blogMapper.toDto(result));
    }

    @DeleteMapping("/delete/{id}")
    public void deletePost(@PathVariable long id) {
        System.out.println("good?");
        blogService.deleteBlog(id);
    }

}

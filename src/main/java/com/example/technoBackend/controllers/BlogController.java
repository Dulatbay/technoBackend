package com.example.technoBackend.controllers;

import com.example.technoBackend.dtos.BlogCreateRequestDto;
import com.example.technoBackend.dtos.BlogDto;
import com.example.technoBackend.mappers.BlogMapper;
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
    private BlogMapper blogMapper;

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
    public ResponseEntity<BlogDto> savePost(@RequestBody BlogCreateRequestDto blogCreateRequestDto) {
        var token = (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        var result = blogService.saveBlog(blogCreateRequestDto, token.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @DeleteMapping("/delete/{id}")
    public void deletePost(@PathVariable long id) {
        System.out.println("good?");
        blogService.deleteBlog(id);
    }
}

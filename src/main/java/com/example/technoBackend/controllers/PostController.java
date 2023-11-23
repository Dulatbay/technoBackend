package com.example.technoBackend.controllers;

import com.example.technoBackend.models.Post;
import com.example.technoBackend.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping
    public List<Post> getAllPosts() {
        return postService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Post> getPostById(@PathVariable int id) {
        return postService.getPostById(id);
    }

    @PostMapping("/post")
    public Post savePost(@RequestBody Post post) {
        return postService.savePost(post);
    }

    @DeleteMapping("/delete/{id}")
    public void deletePost(@PathVariable long id) {
        System.out.println("good");
        postService.deletePost(id);
    }

}

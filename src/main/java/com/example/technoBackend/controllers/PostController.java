package com.example.technoBackend.controllers;

import com.example.technoBackend.models.Post;
import com.example.technoBackend.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PostController {
    private final PostService postService;
    @Autowired
    public PostController(PostService postService){
        this.postService=postService;
    }

    @GetMapping(value = "/posts")
    public List<Post> getPost(){
        List<Post> postList = postService.findAll();

        for (Post post : postList)
            System.out.println(post.getTitle());

        return postService.findAll();
    }
}

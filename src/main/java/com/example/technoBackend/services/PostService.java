package com.example.technoBackend.services;

import com.example.technoBackend.models.Post;
import com.example.technoBackend.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
     private final PostRepository postRepository;
    @Autowired
    public PostService(PostRepository peopleRepository){
        this.postRepository=peopleRepository;
    }
    public List<Post> findAll(){
        return postRepository.findAll();
    }
    public Optional<Post> getPostById(long id){
        return postRepository.findById(id);
    }
    public Post savePost(Post post) {
        return postRepository.save(post);
    }

    public void deletePost(long id) {
        postRepository.deleteById(id);
    }


}
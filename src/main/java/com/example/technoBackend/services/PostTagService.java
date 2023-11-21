package com.example.technoBackend.services;

import com.example.technoBackend.models.Post;
import com.example.technoBackend.models.PostTag;
import com.example.technoBackend.repositories.PostRepository;
import com.example.technoBackend.repositories.PostTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PostTagService {
    private final PostTagRepository postTagRepository;
    @Autowired
    public PostTagService(PostTagRepository postTagRepository){
        this.postTagRepository=postTagRepository;
    }
    public List<PostTag> findAll(){
        return postTagRepository.findAll();
    }
}

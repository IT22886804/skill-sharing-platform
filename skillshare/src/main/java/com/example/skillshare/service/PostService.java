package com.example.skillshare.service;

import java.util.Collections;
import java.util.Date;
import java.util.List; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.skillshare.model.Post;
import com.example.skillshare.repository.PostRepository;


@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public Post createPost(Post post) {
        if (post.getUserId() == null || post.getUserId().isEmpty()) {
            throw new IllegalArgumentException("User ID is required");
        }
        if (post.getUserName() == null || post.getUserName().isEmpty()) {
            post.setUserName("Unknown User");
        }
        post.setCreatedAt(new Date());
        post.setUpdatedAt(new Date());

        return postRepository.save(post);
    }

    public List<Post> getAllPosts() {
        return postRepository.findAllByOrderByCreatedAtDesc();
    }

    public Post getPostById(String id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));
    }

    public List<Post> getPostsByUserId(String userId) {

        if(userId == null){
            return Collections.emptyList();
        }
        return postRepository.findByUserIdOrderByCreatedAtDesc(userId);
    }

    public Post updatePost(String id, Post postDetails) {
        Post post = getPostById(id);
        post.setDescription(postDetails.getDescription());
        post.setMediaUrls(postDetails.getMediaUrls());
        post.setUpdatedAt(new Date());
        return postRepository.save(post);
    }

    public void deletePost(String id) {
        Post post = getPostById(id);
        postRepository.delete(post);
    }

}

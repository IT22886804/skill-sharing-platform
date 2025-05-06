package com.example.skillshare.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.skillshare.model.Post;
import com.example.skillshare.repository.PostRepository;
import com.example.skillshare.model.Comment;

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

    public Post addComment(String postId, Comment comment) {
        Post post = getPostById(postId);
        if (post.getComments() == null) {
            post.setComments(new ArrayList<>());
        }
        if (comment.getUserName() == null || comment.getUserName().isEmpty()) {
            comment.setUserName("Unknown User");
        }
        comment.setId(UUID.randomUUID().toString());
        comment.setCreatedAt(new Date());
        comment.setUpdatedAt(new Date());
        post.getComments().add(comment);
        post = postRepository.save(post);
        //trigger notification if commenter is not the post owner
       
        return post;
    }

    public Post updateComment(String postId, String commentId, Comment commentDetails) {
        Post post = getPostById(postId);
        post.getComments().stream()
                .filter(c -> c.getId().equals(commentId))
                .findFirst()
                .ifPresent(c -> {
                    c.setContent(commentDetails.getContent());
                    c.setUpdatedAt(new Date());
                });
        return postRepository.save(post);
    }

    public Post deleteComment(String postId, String commentId, String userId) {
        Post post = getPostById(postId);
        boolean isPostOwner = post.getUserId().equals(userId);
        post.setComments(post.getComments().stream()
                .filter(c -> !(c.getId().equals(commentId) && (c.getUserId().equals(userId) || isPostOwner)))
                .collect(Collectors.toList()));
        return postRepository.save(post);
    }
}

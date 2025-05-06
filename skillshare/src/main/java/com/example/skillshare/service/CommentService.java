package com.example.skillshare.service;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillshare.skillshare.model.Post;
import com.skillshare.skillshare.model.Comment;
import com.skillshare.skillshare.repository.CommentRepository;
import com.skillshare.skillshare.repository.PostRepository;

@Service
public class CommentService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

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
        return postRepository.save(post);
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

    private Post getPostById(String postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new NoSuchElementException("Post not found with id: " + postId));
    }
}

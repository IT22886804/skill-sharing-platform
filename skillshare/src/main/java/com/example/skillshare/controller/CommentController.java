// package com.example.skillshare.controller;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.*;

// import com.example.skillshare.model.Post;
// import com.example.skillshare.model.Comment;
// import com.example.skillshare.service.CommentService;

// @RestController
// @RequestMapping("/api/comments")
// public class CommentController {

// @Autowired
// private CommentService commentService;

// // Add a comment
// @PostMapping("/{postId}")
// public Post addComment(@PathVariable String postId, @RequestBody Comment
// comment) {
// return commentService.addComment(postId, comment);
// }

// // Update a comment
// @PutMapping("/{postId}/{commentId}")
// public Post updateComment(@PathVariable String postId,
// @PathVariable String commentId,
// @RequestBody Comment commentDetails) {
// return commentService.updateComment(postId, commentId, commentDetails);
// }

// // Delete a comment
// @DeleteMapping("/{postId}/{commentId}")
// public Post deleteComment(@PathVariable String postId,
// @PathVariable String commentId,
// @RequestParam String userId) {
// return commentService.deleteComment(postId, commentId, userId);
// }
// }

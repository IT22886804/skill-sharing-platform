package com.example.skillshare.controller;

//import com.example.skillshare.model.Comment;
//import com.example.skillshare.model.Like;
import com.example.skillshare.model.LearningProgress;
import com.example.skillshare.service.LearningProgressService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/learning-progress")
public class LearningProgressController {

    @Autowired
    private LearningProgressService learningProgressService;

    //create a learning progress record for a specific user
    @PostMapping("/user/{userId}")
    public ResponseEntity<LearningProgress> createLearningProgressForUser(
            @PathVariable String userId,
            @RequestBody LearningProgress learningProgress) {
        learningProgress.setUserId(userId);
        LearningProgress created = learningProgressService.createLearningProgress(learningProgress);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    //get all learning progress entries
    @GetMapping
    public ResponseEntity<List<LearningProgress>> getAllLearningProgress() {
        List<LearningProgress> entries = learningProgressService.getAllLearningProgress();
        return new ResponseEntity<>(entries, HttpStatus.OK);
    }

    //get a learning progress entry by ID
    @GetMapping("/{id}")
    public ResponseEntity<LearningProgress> getLearningProgressById(@PathVariable String id) {
        LearningProgress entry = learningProgressService.getLearningProgressById(id);
        return new ResponseEntity<>(entry, HttpStatus.OK);
    }

    //get learning progress entries by user ID
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<LearningProgress>> getLearningProgressByUserId(@PathVariable String userId) {
        List<LearningProgress> entries = learningProgressService.getLearningProgressByUserId(userId);
        return new ResponseEntity<>(entries, HttpStatus.OK);
    }

    //uUapdate a learning progress entry
    @PutMapping("/{id}")
    public ResponseEntity<LearningProgress> updateLearningProgress(
            @PathVariable String id,
            @RequestBody LearningProgress learningProgress) {
        LearningProgress updated = learningProgressService.updateLearningProgress(id, learningProgress);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    //delete a learning progress entry
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLearningProgress(@PathVariable String id) {
        learningProgressService.deleteLearningProgress(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
   

   
}

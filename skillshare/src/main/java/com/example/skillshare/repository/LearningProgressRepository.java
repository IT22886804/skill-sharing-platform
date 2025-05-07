package com.example.skillshare.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.skillshare.model.LearningProgress;

@Repository
public interface LearningProgressRepository extends MongoRepository<LearningProgress, String> {

    // Get all by userId
    List<LearningProgress> findByUserId(String userId);

    // Get all ordered by creation date descending
    List<LearningProgress> findAllByOrderByCreatedAtDesc();

    List<LearningProgress> findByUserIdOrderByCreatedAtDesc(String userId);
}

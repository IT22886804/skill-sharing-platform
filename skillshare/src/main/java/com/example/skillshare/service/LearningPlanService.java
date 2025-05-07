package com.example.skillshare.service;

import com.example.skillshare.model.LearningPlan;
import com.example.skillshare.repository.LearningPlanRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
// import java.util.stream.Collectors;

@Service
public class LearningPlanService {

    @Autowired
    private LearningPlanRepository learningPlanRepository;

    // Create a new learning plan
    public LearningPlan createLearningPlan(LearningPlan plan) {
        if (plan.getUserId() == null || plan.getUserId().isEmpty()) {
            throw new IllegalArgumentException("User ID is required");
        }
        if (plan.getUserName() == null || plan.getUserName().isEmpty()) {
            plan.setUserName("Unknown User");
        }
        plan.setCreatedAt(new Date());
        plan.setUpdatedAt(new Date());
        return learningPlanRepository.save(plan);
    }

    // Get all learning plans
    public List<LearningPlan> getAllLearningPlans() {
        return learningPlanRepository.findAllByOrderByCreatedAtDesc();
    }

    // Get a learning plan by ID
    public LearningPlan getLearningPlanById(String id) {
        return learningPlanRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Learning plan not found"));
    }

    // Get plans by user ID
    public List<LearningPlan> getLearningPlansByUserId(String userId) {
        return learningPlanRepository.findByUserId(userId);
    }

    // Update learning plan
    public LearningPlan updateLearningPlan(String id, LearningPlan planDetails) {
        LearningPlan plan = getLearningPlanById(id);
        plan.setTitle(planDetails.getTitle());
        plan.setDescription(planDetails.getDescription());
        plan.setTopics(planDetails.getTopics());
        plan.setResources(planDetails.getResources());
        plan.setUpdatedAt(new Date());
        return learningPlanRepository.save(plan);
    }

    // Delete learning plan
    public void deleteLearningPlan(String id) {
        LearningPlan plan = getLearningPlanById(id);
        learningPlanRepository.delete(plan);
    }

    // Add comment
    public LearningPlan addComment(String planId) {
        LearningPlan plan = getLearningPlanById(planId);
        return learningPlanRepository.save(plan);
    }

    // Update comment
    public LearningPlan updateComment(String planId, String commentId) {
        LearningPlan plan = getLearningPlanById(planId);
        return learningPlanRepository.save(plan);
    }

    // Delete comment
    public LearningPlan deleteComment(String planId, String commentId, String userId) {
        LearningPlan plan = getLearningPlanById(planId);
        boolean isOwner = plan.getUserId().equals(userId);
        return learningPlanRepository.save(plan);
    }

    // Add like
    public LearningPlan addLike(String planId) {
        LearningPlan plan = getLearningPlanById(planId);
        return plan;
    }

    // Remove like
    public LearningPlan removeLike(String planId, String userId) {
        LearningPlan plan = getLearningPlanById(planId);
        return learningPlanRepository.save(plan);
    }
}

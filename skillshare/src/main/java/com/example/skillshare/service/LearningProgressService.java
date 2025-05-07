package com.example.skillshare.service;

//import com.example.skillshare.model.Comment;
import com.example.skillshare.model.LearningProgress;
//import com.example.skillshare.model.Like;
import com.example.skillshare.repository.LearningProgressRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class LearningProgressService {

    private final LearningProgressRepository learningProgressRepository;
    

     public LearningProgressService(LearningProgressRepository learningProgressRepository) {
      this.learningProgressRepository = learningProgressRepository;
  }

    // //create a new learning progress entry
    public LearningProgress createLearningProgress(LearningProgress progress) {
        if (progress.getUserId() == null || progress.getUserId().isEmpty()) {
            throw new IllegalArgumentException("User ID is required");
        }
        if (progress.getUserName() == null || progress.getUserName().isEmpty()) {
            progress.setUserName("Unknown User");
        }
        if (progress.getTemplateType() == null || progress.getTemplateType().isEmpty()) {
            throw new IllegalArgumentException("Template type is required");
        }

        //validate required fields based on template type
        switch (progress.getTemplateType()) {
            case "general":
                if (progress.getTitle() == null || progress.getTitle().isEmpty() ||
                        progress.getDescription() == null || progress.getDescription().isEmpty()) {
                    throw new IllegalArgumentException("Title and description are required for general template");
                }
                break;
            case "tutorial":
                if (progress.getTitle() == null || progress.getTitle().isEmpty() ||
                        progress.getTutorialName() == null || progress.getTutorialName().isEmpty()) {
                    throw new IllegalArgumentException("Title and tutorial name are required for tutorial template");
                }
                break;
            case "project":
                if (progress.getTitle() == null || progress.getTitle().isEmpty() ||
                        progress.getProjectName() == null || progress.getProjectName().isEmpty()) {
                    throw new IllegalArgumentException("Title and project name are required for project template");
                }
                break;
            default:
                throw new IllegalArgumentException("Invalid template type");
        }

        progress.setCreatedAt(new Date());
        progress.setUpdatedAt(new Date());
        return learningProgressRepository.save(progress);
    }

    //get all learning progress entries
    public List<LearningProgress> getAllLearningProgress() {
        return learningProgressRepository.findAllByOrderByCreatedAtDesc();
    }

    //get a learning progress entry by ID
    public LearningProgress getLearningProgressById(String id) {
        return learningProgressRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Learning progress not found"));
    }

    //get entries by user ID
    public List<LearningProgress> getLearningProgressByUserId(String userId) {
        return learningProgressRepository.findByUserId(userId);
    }

    //update learning progress entry
    public LearningProgress updateLearningProgress(String id, LearningProgress progressDetails) {
        LearningProgress progress = getLearningProgressById(id);
        progress.setTitle(progressDetails.getTitle());
        progress.setDescription(progressDetails.getDescription());
        progress.setTemplateType(progressDetails.getTemplateType());
        progress.setStatus(progressDetails.getStatus());
        progress.setTutorialName(progressDetails.getTutorialName());
        progress.setProjectName(progressDetails.getProjectName());
        progress.setSkillsLearned(progressDetails.getSkillsLearned());
        progress.setChallenges(progressDetails.getChallenges());
        progress.setNextSteps(progressDetails.getNextSteps());
        progress.setUpdatedAt(new Date());
        return learningProgressRepository.save(progress);
    }

    //delete learning progress entry
    public void deleteLearningProgress(String id) {
        LearningProgress progress = getLearningProgressById(id);
        learningProgressRepository.delete(progress);
    }

    
}

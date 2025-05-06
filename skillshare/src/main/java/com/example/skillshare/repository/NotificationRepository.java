package com.example.skillshare.repository;

import com.example.skillshare.model.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
 
public interface NotificationRepository extends MongoRepository<Notification, String> {
    List<Notification> findByUserIdOrderByCreatedAtDesc(String userId);

    List<Notification> findByUserIdAndReadFalse(String userId);
}

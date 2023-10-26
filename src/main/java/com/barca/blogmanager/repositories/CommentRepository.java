package com.barca.blogmanager.repositories;

import com.barca.blogmanager.models.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommentRepository extends MongoRepository<Comment, String> {
}
